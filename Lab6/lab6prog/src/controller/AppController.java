package controller;

import view.ConsoleView;
import model.Translator;

public class AppController {
    private final ConsoleView consoleView;
    private final Translator translator;

    public AppController(ConsoleView consoleView, Translator translator){
        this.consoleView = consoleView;
        this.translator = translator;
    }

    public void start(){
        mainloop:
        while(true){
            int operation = consoleView.askOperation();        
            switch(operation){
                case 0: // Exit
                    break mainloop;
                case 1: // Add word pair to the dictionary
                    String engWord = consoleView.askWord(consoleView.ENGLISH_WORD_QUERY);
                    String ukrWord = consoleView.askWord(consoleView.UKRAINIAN_WORD_QUERY);
                    translator.addWordPair(engWord, ukrWord);
                    break;
                case 2: // Translate the phrase
                    String phrase = consoleView.askPhrase();                    
                    try{
                        consoleView.printTranslation(translator.translateEngToUkr(phrase));
                    }catch(IllegalArgumentException e){
                        consoleView.errorNotification(e.getMessage());
                    }
                    break;
            }
        }
    }
}
