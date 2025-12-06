package controller;

import view.ConsoleView;
import model.fileProcessor.*;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppController {
    private static final Logger logger = LogManager.getLogger(AppController.class);
    private final ConsoleView consoleView;    

    public AppController(ConsoleView consoleView) throws IllegalArgumentException{        
        if(consoleView == null){
            logger.error("The console view was null");
            throw new IllegalArgumentException("The console view cannot be null");
        }
        this.consoleView = consoleView;
    }

    public void start(){
        char keyChar;
        String filePath, userText, language;
        TextEncryptor textEncryptor;

        mainloop:
        while(true){
            int operation = consoleView.askOperation();
            switch(operation){
                case 0: // Exit
                    logger.info("Operation exit");
                    break mainloop;
                case 1: // Write text into file using encoding
                    logger.info("Operation write text into file using encoding");
                    filePath = consoleView.askFilePath();
                    keyChar = consoleView.askKeyChar();
                    userText = consoleView.askText();
                    logger.debug("Encryption char: " + keyChar + "; user text: " + userText);

                    textEncryptor = new TextEncryptor(filePath, keyChar);
                    try{ textEncryptor.writeFileWithEncryption(userText);}
                    catch(IOException e){ 
                        consoleView.printFileNotFound(filePath);
                        logger.error(e.getMessage());
                    }
                    break; 
                case 2: // Decode written text
                    logger.info("Operation decode written text");
                    filePath = consoleView.askFilePath();
                    keyChar = consoleView.askKeyChar();
                    logger.debug("Encryption char: " + keyChar + "; file path: " + filePath);

                    textEncryptor = new TextEncryptor(filePath, keyChar);
                    try{ consoleView.print(textEncryptor.readFileWithEncryption());}
                    catch(IOException e){ 
                        consoleView.printFileNotFound(filePath);
                        logger.error(e.getMessage());
                    }
                    break;
                case 3: // Change language
                    logger.info("Operation change language");
                    language = consoleView.askLanguage();
                    logger.debug("Chosen language: " + language);
                    consoleView.changeLanguage(language);
                    break;
            }
        }
    }
}
