package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {
    public final String INIT_STR_VALUE_QUERY = "Enter the initial string";
    public final String NEW_STR_VALUE_QUERY = "Enter the new string";

    public final String INIT_WAY_QUERY = "Enter the way of initial string input";
    public final String NEW_VAL_WAY_QUERY = "Enter the way of new string input";

    private final Scanner scanner = new Scanner(System.in);
    
    public int askStrInitWay(String desc){
        int wayNumber;
        while(true){
            System.out.println(desc + ":\n" + 
                                    "1 - Initialise with default program value\n" + 
                                    "2 - Initialise from keyboard");
            try{
                wayNumber = scanner.nextInt();
                if(wayNumber >= 1 && wayNumber <= 2){                    
                    scanner.nextLine();
                    return wayNumber;
                }else throw new InputMismatchException();
            }catch(InputMismatchException e){
                System.out.println("Entered value must be integer, 1 or 2. Try again");
                scanner.nextLine();
            }           
        }
    }
    public String askString(String description){
        String askedStr = null;
        while(true){
            System.out.println(description);
            askedStr = scanner.nextLine();
            if(askedStr.isEmpty()) System.out.println("The string cannot be empty. Try again");
            else return askedStr;
        }
    }

    public void printModificationReport(String initial, String modified){
        System.out.println("Modification report:");
        System.out.println("\tInitial value : " + initial);
        System.out.println("\tModified value : " + modified);
    }
}
