package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner = new Scanner(System.in, "ibm866");

    public final String ENGLISH_WORD_QUERY = "Enter the english word";
    public final String UKRAINIAN_WORD_QUERY = "Enter the ukrainian word";

    public int askOperation(){
        int operationNumber;
        while(true){
            System.out.println("Enter the identifier of operation:\n" + 
                                    "1 - Add word pair to the dictionary\n" + 
                                    "2 - Translate the phrase\n" +
                                    "0 - Exit");
            try{
                operationNumber = scanner.nextInt();
                if(operationNumber >= 0 && operationNumber <= 2){                    
                    scanner.nextLine();
                    return operationNumber;
                }
                else throw new InputMismatchException();
            }catch(InputMismatchException e){
                System.out.println("Entered value must be integer, from 0 to 2. Try again");
                scanner.nextLine();
            }           
        }
    }

    public String askWord(String askedWordQuery){
        String word = null;
        while(true){
            System.out.println(askedWordQuery);
            word = scanner.nextLine();
            if(word.isBlank()) System.out.println("The word cannot be blank. Try again");
            else return word;   
        }
    }
    public String askPhrase(){
        String phrase = null;
        while(true){
            System.out.println("Enter the phrase:");
            phrase = scanner.nextLine();
            if(phrase.isBlank()) System.out.println("The phrase cannot be blank. Try again");
            else return phrase;       
        }
    }


    public void printTranslation(String translation){
        System.out.println("Ukrainian translation: " + translation);
    }
    public void errorNotification(String errorDescription){
        System.out.println("Error: " + errorDescription);
    }
}
