package model.fileProcessor;

import java.io.*;

public class TextEncryptor {
    private final String filePath;
    private final char keyChar;

    public TextEncryptor(String filePath, char keyChar){
        this.filePath = filePath;
        this.keyChar = keyChar;
    }

    public void writeFileWithEncryption(String text) throws IOException{
        try (EncryptingWriter ew = new EncryptingWriter(new FileWriter(filePath, true), keyChar)) {
            ew.write(text, 0, text.length());
        }
    }
    public String readFileWithEncryption() throws IOException{
        try (EncryptingReader ew = new EncryptingReader(new FileReader(filePath), keyChar)) {
            StringBuilder stringBuilder = new StringBuilder();
            boolean hasNext = true;
            int current;
            while(hasNext){
                current = ew.read();
                if(current == -1 - keyChar) hasNext = false;
                else{
                    stringBuilder.append((char)current);
                }
            }

            return stringBuilder.toString();
        }
    }
}

class EncryptingWriter extends FilterWriter{
    private final char keyChar;

    public EncryptingWriter(Writer out, char keyChar) {
        super(out);
        this.keyChar = keyChar;
    }

    @Override
    public void write(int c) throws IOException {
        super.write(c + keyChar);
    }
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) {
            write(cbuf[i]);
        }
    }
    @Override
    public void write(String str, int off, int len) throws IOException {
        write(str.toCharArray(), off, len);
    }
}

class EncryptingReader extends FilterReader{
    private final char keyChar;

    public EncryptingReader(Reader in, char keyChar) {
        super(in);
        this.keyChar = keyChar;
    }

    @Override
    public int read() throws IOException {
        return super.read() - keyChar;
    }
    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int read = super.read(cbuf, off, len);
        for(int i = off; i < off + len; i++){
            cbuf[i] -= keyChar;
        }
        return read;
    }    
}

// The realisation with FilterOutputStream and FilterInputStream is analogous. 
// The difference is that we read and write the bytes.
class EncryptingOutput extends FilterOutputStream{
    private final char keyChar;

    public EncryptingOutput(OutputStream out, char keyChar) {
        super(out);
        this.keyChar = keyChar;
    }

    @Override
    public void write(int c) throws IOException {
        super.write(c + keyChar);
    }
    @Override
    public void write(byte[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) {
            write(cbuf[i]);
        }
    }
}

class EncryptingInput extends FilterInputStream{
    private final char keyChar;

    public EncryptingInput(InputStream in, char keyChar) {
        super(in);
        this.keyChar = keyChar;
    }

    @Override
    public int read() throws IOException {
        return super.read() - keyChar;
    }
    @Override
    public int read(byte[] cbuf, int off, int len) throws IOException {
        int read = super.read(cbuf, off, len);
        for(int i = off; i < off + len; i++){
            cbuf[i] -= keyChar;
        }
        return read;
    }    
}