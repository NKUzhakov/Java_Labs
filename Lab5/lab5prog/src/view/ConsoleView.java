package view;
import model.tourniquet.skiPass.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ConsoleView {
    private final Scanner scanner = new Scanner(System.in);
    
    public int askOperation(){
        int operationNumber;
        while(true){
            System.out.println("Enter the identifier of operation:\n" + 
                                    "1 - Write text into file\n" + 
                                    "2 - Get the longest line from file\n\n" + 
                                    "3 - Create and save the object\n" + 
                                    "4 - Get all saved objects\n\n" + 
                                    "5 - Write text into file using encoding\n" +
                                    "6 - Decode written text\n\n" +
                                    "7 - Get tags stats for HTML page\n" +
                                    "0 - Exit");
            try{
                operationNumber = scanner.nextInt();
                if(operationNumber >= 0 && operationNumber <= 7){                    
                    scanner.nextLine();
                    return operationNumber;
                }
                else throw new InputMismatchException();
            }catch(InputMismatchException e){
                System.out.println("Entered value must be integer, from 0 to 7. Try again");
                scanner.nextLine();
            }           
        }
    }
    public int askObjectToCreate(){
        int classNumber;
        while(true){
            System.out.println("Enter the identifier of Class:\n" + 
                                    "1 - TimeLimitedSkiPass\n" + 
                                    "2 - ClimbAmountLimitedSkiPass\n" + 
                                    "0 - Exit");
            try{
                classNumber = scanner.nextInt();
                if(classNumber >= 0 && classNumber <= 2){ 
                    scanner.nextLine();
                    return classNumber;
                }
                else throw new InputMismatchException();
            }catch(InputMismatchException e){
                System.out.println("Entered value must be integer, from 0 to 2. Try again");
                scanner.nextLine();
            }               
        }
    }
    public int askStatsSortingOrder(){
        int sortingOrder;
        while(true){
            System.out.println("Enter the sortinf order:\n" + 
                                    "1 - Sort by alphabet\n" + 
                                    "2 - Sort by frequency\n" + 
                                    "0 - None");
            try{
                sortingOrder = scanner.nextInt();
                if(sortingOrder >= 0 && sortingOrder <= 2){ 
                    scanner.nextLine();
                    return sortingOrder;
                }
                else throw new InputMismatchException();
            }catch(InputMismatchException e){
                System.out.println("Entered value must be integer, from 0 to 2. Try again");
                scanner.nextLine();
            }               
        }
    }
        
    public TimeLimitedSkiPass createTimeLimitedSkiPass(){
        while(true){
            SkiPassType type = askType("type");
            LocalDateTime issueDateTime = askDateTime("issueDateTime");
            LocalDateTime expirationDateTime = askDateTime("expirationDateTime");
            try{
                return new TimeLimitedSkiPass(type, issueDateTime, expirationDateTime);
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                System.out.println("Try again");
            }
        }
    }
    public ClimbAmountLimitedSkiPass createClimbAmountLimitedSkiPass(){
        while(true){
            SkiPassType type = askType("type");
            int totalClimbAmount = askNaturalNumber("totalClimbAmount");
            try{
                return new ClimbAmountLimitedSkiPass(type, totalClimbAmount);
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                System.out.println("Try again");
            }
        }
    }

    public SkiPassType askType(String typeDesc){
        System.out.println(typeDesc + " (SEASON, WORKDAY_CLIMB_AMOUNT_UNLIMITED, WORKDAY_CLIMB_AMOUNT_LIMITED, WEEKEND_CLIMB_AMOUNT_UNLIMITED, WEEKEND_CLIMB_AMOUNT_LIMITED): ");
        while(true){
            try{
                return SkiPassType.valueOf(scanner.nextLine());
            }catch(IllegalArgumentException e) {
                System.out.println("Not correct type, try again");
            }
        }        
    }
    public LocalDateTime askDateTime(String dateTimeDesc){
        System.out.println(dateTimeDesc + ":");
        while(true){
            try{
                return LocalDateTime.parse(scanner.nextLine());
            }catch(DateTimeParseException e){
                System.out.println("The value must be in format like: 2007-12-03T10:15:30. Try again");
            }
        }
    }
    public int askNaturalNumber(String numDesc){
        System.out.println(numDesc + ":");
        int number;
        while(true){
            try{
                number = scanner.nextInt();
                if(number <= 0) throw new InputMismatchException();
                return number;
            }catch(InputMismatchException e){
                System.out.println("The value must be natural number. Try again");
                scanner.nextLine();
            }
        }
    }
    public char askKeyChar(){
        String input;
        while(true){
            System.out.print("Enter the key char: ");
            input = scanner.nextLine();
            if(input.length() == 1) return input.charAt(0);
            System.out.print("Entered value contains more than one char... Try again");
        }
    }
    public String askURL(){
        String URL;
        while(true){
            System.out.println("Enter the URL");
            URL = scanner.nextLine();
            if(URL.isEmpty()) System.out.println("The URL cannot be empty. Try again");
            else return URL;
        }
    }
    public String askFilePath(){
        String filePath;
        while(true){
            System.out.println("Enter the path of the file");
            filePath = scanner.nextLine();
            if(filePath.isEmpty()) System.out.println("The path cannot be empty. Try again");
            else return filePath;
        }
    }
    public String askText(){
        System.out.println("Enter the text by lines. To finish, enter '/E' from the new line.");
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
    public void printSortedTagStats(HashMap<String, Integer> tagStats, ArrayList<String> sortedKeys){
        for(String tag : sortedKeys){
            System.out.println(tag + " -> " + tagStats.get(tag));
        }
    }
    public void printTagStats(HashMap<String, Integer> tagStats){
        printSortedTagStats(tagStats, new ArrayList<String>(tagStats.keySet()));
    }
}
