import javax.tools.JavaCompiler;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class Main {
    public static void main(String[] args){
        final String watchDirectoryName = "hardTask\\src";
        final String watchClassName = "TestModule.java";
        final String relativeLoaderClassName = "hardTask\\out\\TestModule.class";
        
        try{
            // Checking for source updates
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(watchDirectoryName);
            System.out.println("Hello");           
            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            
            WatchKey key;
            boolean modificationProceeded = false; // To avoid the double proceeding of update
            while (true) {
                key = watchService.take(); // Stops until the event occurs
                
                for (WatchEvent<?> event : key.pollEvents()) {
                    if(((event.context()).toString().equals(watchClassName))){
                        if(!modificationProceeded){
                            System.out.println("Update!");
                            try{                
                                // Deleting the old version
                                File classFile = new File(relativeLoaderClassName); 
                                boolean fileExists =  classFile.exists();
                                if(fileExists){
                                    boolean deleted = classFile.delete();
                                    if(!deleted)
                                        throw new Exception(".class file is unable to be updated");
                                }                                                     
                                // The main work with class
                                DynamicClassLoader dcl = new DynamicClassLoader(ClassLoader.getSystemClassLoader());            
                                Class<?> clazz = dcl.loadClass("TestModule");
                                Object t = clazz.getDeclaredConstructor().newInstance();
                                System.out.println(t);                                           
                            }catch(Exception e){
                                System.out.println(e.getMessage());
                            }
                        }

                        modificationProceeded = !modificationProceeded;
                    }
                }                
                
                key.reset();
            }
        }catch(IOException ioe){
            System.out.println("Some problems with watchers -> we can`t control the updates");
        }catch(InterruptedException ie){
            System.out.println("Can`t check the updates in the main cycle");
        }
    }
}
