package controller;

import view.ConsoleView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.fileProcessor.*;
import model.tourniquet.skiPass.*;

public class AppController {
    private final ConsoleView consoleView;

    public AppController(ConsoleView consoleView) throws IllegalArgumentException{
        if(consoleView == null) throw new IllegalArgumentException("The console view cannot be null");
        this.consoleView = consoleView;
    }

    public void start(){
        int sortingOrder;
        char keyChar;
        String filePath, userText, URL;
        TimeLimitedSkiPass createdTimeLimitedSkiPass;
        ClimbAmountLimitedSkiPass createdClimbAmountLimitedSkiPass;
        HashMap<String, Integer> tagStats;
        TextFileProcessor textFileProcessor;
        ObjectSerialisationProcessor objectSerialisationProcessor;
        TextEncryptor textEncryptor;
        HTMLTagsStatsProcessor htmlTagsStatsProcessor;
        mainloop:
        while(true){
            int operation = consoleView.askOperation();
            switch(operation){
                case 0: // Exit
                    break mainloop;
                case 1: // Write text into file
                    filePath = consoleView.askFilePath();
                    textFileProcessor = new TextFileProcessor(filePath);
                    userText = consoleView.askText();
                    try{ textFileProcessor.writeInFile(userText);}
                    catch(IOException e){ consoleView.print(e.getMessage());}                    
                    break;
                case 2: // Get the longest line from file
                    filePath = consoleView.askFilePath();
                    textFileProcessor = new TextFileProcessor(filePath);
                    try{ consoleView.print(textFileProcessor.findMaxLineInFile());}
                    catch(IOException e){ consoleView.print(e.getMessage());}     
                    break;
                case 3: // Create and save the object
                    filePath = consoleView.askFilePath();
                    objectSerialisationProcessor = new ObjectSerialisationProcessor(filePath);
                    int classNumber = consoleView.askObjectToCreate();
                    switch(classNumber){
                        case 0: // Exit
                            break;
                        case 1: // TimeLimitedSkiPass
                            createdTimeLimitedSkiPass = consoleView.createTimeLimitedSkiPass();
                            try{ objectSerialisationProcessor.serializeSkiPasses(new ArrayList<>(List.of(createdTimeLimitedSkiPass)));}
                            catch(ClassNotFoundException | IOException e){ consoleView.print(e.getMessage());}
                            break;
                        case 2: // ClimbAmountLimitedSkiPass
                            createdClimbAmountLimitedSkiPass = consoleView.createClimbAmountLimitedSkiPass();
                            try{ objectSerialisationProcessor.serializeSkiPasses(new ArrayList<>(List.of(createdClimbAmountLimitedSkiPass)));}
                            catch(ClassNotFoundException | IOException e){ consoleView.print(e.getMessage());}
                            break;
                    }
                    break;
                case 4: // Get all saved objects
                    filePath = consoleView.askFilePath();
                    objectSerialisationProcessor = new ObjectSerialisationProcessor(filePath);
                    try{ consoleView.print(objectSerialisationProcessor.deserializeSkiPasses().toString());}
                    catch(IOException | ClassNotFoundException e){ consoleView.print(e.getMessage());}
                    break;
                case 5: // Write text into file using encoding
                    filePath = consoleView.askFilePath();
                    keyChar = consoleView.askKeyChar();
                    userText = consoleView.askText();

                    textEncryptor = new TextEncryptor(filePath, keyChar);
                    try{ textEncryptor.writeFileWithEncryption(userText);}
                    catch(IOException e){ consoleView.print(e.getMessage());}
                    break; 
                case 6: // Decode written text
                    filePath = consoleView.askFilePath();
                    keyChar = consoleView.askKeyChar();

                    textEncryptor = new TextEncryptor(filePath, keyChar);
                    try{ consoleView.print(textEncryptor.readFileWithEncryption());}
                    catch(IOException e){ consoleView.print(e.getMessage());}
                    break;
                case 7: // Get tags stats for HTML page
                    URL = consoleView.askURL();
                    try{ 
                        htmlTagsStatsProcessor = new HTMLTagsStatsProcessor(URL);
                        tagStats = htmlTagsStatsProcessor.getTagsStats();

                        sortingOrder = consoleView.askStatsSortingOrder();
                        switch(sortingOrder){
                            case 0: // None
                                consoleView.printTagStats(tagStats);
                                break;
                            case 1: // Sort by alphabet
                                consoleView.printSortedTagStats(tagStats, HTMLTagsStatsProcessor.getSortedByAlphabetTags(tagStats));
                                break;
                            case 2: // Sort by frequency
                                consoleView.printSortedTagStats(tagStats, HTMLTagsStatsProcessor.getSortedByFrequencyTags(tagStats));
                                break;
                        }
                    }catch(IOException e){ consoleView.print(e.getMessage());}
                    break;                   
            }
        }
    }
}
