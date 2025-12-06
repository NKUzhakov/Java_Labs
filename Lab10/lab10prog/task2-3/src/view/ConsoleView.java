package view;

import java.text.MessageFormat;
import java.util.*;

public class ConsoleView {
    public final List<String> SUPPORTED_LANGS = Arrays.asList("en", "ua");

    private final String resLocation = "resources.location.messages";
    private Locale locale = Locale.forLanguageTag("en");
    private ResourceBundle bundle = ResourceBundle.getBundle(resLocation, locale);

    private final Scanner scanner = new Scanner(System.in);
    
    public int askOperation(){
        int operationNumber;
        while(true){
            System.out.println(bundle.getString("opQuery") + ":\n" + 
                                    "1 - " + bundle.getString("writeWithEncOption") + "\n" +
                                    "2 - " + bundle.getString("decodeOption") + "\n" +
                                    "3 - " + bundle.getString("langChangeOption") + "\n" +
                                    "0 - " + bundle.getString("exitOption"));
            try{
                operationNumber = scanner.nextInt();
                if(operationNumber >= 0 && operationNumber <= 3){                    
                    scanner.nextLine();
                    return operationNumber;
                }
                else throw new InputMismatchException();
            }catch(InputMismatchException e){
                System.out.println(MessageFormat.format(bundle.getString("rangeMismatchMessage"), 0, 3));
                scanner.nextLine();
            }           
        }
    }

    public String askLanguage(){        
        String menu = bundle.getString("langQuery") + ": ";
        for(int i = 0; i < SUPPORTED_LANGS.size(); i++){
            menu += SUPPORTED_LANGS.get(i) + " ";
        }
        String chosenLang = null;

        
        while(true){
            System.out.println(menu);
            try{
                chosenLang = scanner.nextLine();
                if(SUPPORTED_LANGS.contains(chosenLang)){                    
                    return chosenLang;
                }
                else throw new InputMismatchException();
            }catch(InputMismatchException e){
                System.out.println(bundle.getString("notSupportedLangMessage"));
            }  
        }
    }
    public void changeLanguage(String language){
        if(!SUPPORTED_LANGS.contains(language)) 
            throw new IllegalArgumentException("This language is not supported");
        locale = Locale.forLanguageTag(language);
        bundle = ResourceBundle.getBundle(resLocation, locale);
    }

    public char askKeyChar(){
        String input;
        while(true){
            System.out.print(bundle.getString("keyCharQuery") + ": ");
            input = scanner.nextLine();
            if(input.length() == 1) return input.charAt(0);
            System.out.println(bundle.getString("wrongKeyCharInputMessage"));
        }
    }
    public String askFilePath(){
        String filePath;
        while(true){
            System.out.println(bundle.getString("filePathQuery"));
            filePath = scanner.nextLine();
            if(filePath.isEmpty()) System.out.println(bundle.getString("emptyFilePathMessage"));
            else return filePath;
        }
    }
    public String askText(){
        System.out.println(bundle.getString("textInputQuery"));
        StringBuilder inputStringBuilder = new StringBuilder();

        String currentLine;
        while(true){
            currentLine = scanner.nextLine();
            if(currentLine.equals("/E")) break;
            inputStringBuilder.append(currentLine + "\n");
        }
        return inputStringBuilder.toString();
    }
    
    public void print(String message){
        System.out.println(message);
    }
    public void printFileNotFound(String filePath){
        System.out.println(bundle.getString("fileNotFoundMessage") + ": " + filePath);
    }
}
