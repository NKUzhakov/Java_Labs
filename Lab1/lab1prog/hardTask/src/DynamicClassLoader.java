import javax.tools.JavaCompiler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DynamicClassLoader extends ClassLoader {
    public DynamicClassLoader(ClassLoader parent) {
        super(parent);      
    }

    public Class<?> findClass(String className) throws ClassNotFoundException {          
        final String LoaderClassName = "TestModule"; 

        final String CompilationDir = "hardTask\\out";
        final String SourceDir = "hardTask\\src";
        
        if(className != LoaderClassName)
            throw new ClassNotFoundException("className != TestModule");
  
        // Compiling the class        
        JavaCompiler compiler = javax.tools.ToolProvider.getSystemJavaCompiler(); 
        int compilationResult = compiler.run(null, null, null, "-d", CompilationDir, SourceDir + "\\" + LoaderClassName + ".java");
        if(compilationResult != 0)
            throw new ClassNotFoundException("Class wasn`t found because of related .java file couldn`t be compiled");

        try{
            byte[] bytes = readFile(CompilationDir + "\\" + LoaderClassName + ".class");
            return defineClass(className, bytes, 0, bytes.length);
        }catch(IOException e){
            throw new ClassNotFoundException("Class wasn`t found because of related .class file couldn`t be properly read");
        }
        
    }

    private byte[] readFile(String relativeFileName) throws IOException{
        File classFile = new File(relativeFileName);
        try(FileInputStream is = new FileInputStream(classFile);){ 
            int classFileLength = (int) classFile.length();            
            byte[] bytes = new byte[classFileLength];
            int totalBytesRead = 0;
            int currentBytesRead = 0;
            while (totalBytesRead < classFileLength && (currentBytesRead=is.read(bytes, totalBytesRead, bytes.length - totalBytesRead)) >= 0) {
                totalBytesRead += currentBytesRead;
            }
            if(totalBytesRead != classFileLength)
                throw new IOException("The file is not read completely"); 
            return bytes;
        }catch(IOException e){
            throw new IOException("File couldn`t be properly read");
        }
    }
}
