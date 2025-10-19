package model.fileProcessor;

import java.io.*;

public class TextFileProcessor {
    private final String filePath;

    public TextFileProcessor(String filePath){
        this.filePath = filePath;
    }

    public String findMaxLineInFile() throws IOException{
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String currentLine, longestLine = "";
            int longestLineLength = 0;
            while((currentLine = reader.readLine()) != null){
                int currentWordsCount = (currentLine.split("([\\s*\\.,\\?!])\\s*")).length;
                if(currentWordsCount > longestLineLength){
                    longestLine = currentLine;
                    longestLineLength = currentWordsCount;
                }
            }

            return longestLine;
        }
    }
    public void writeInFile(String text) throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(text);
        }
    }

}
