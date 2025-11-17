package view;

import model.ParallelMonteCarloPi.CalculationResult;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner = new Scanner(System.in);
    public final String THREAD_COUNT_QUERY = "Enter the thread count (to exit enter 0)";
    public int askInteger(String query){
        while(true){
            System.out.print(query + ": ");
            try{
                return scanner.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Entered value must be integer. Try again");
                scanner.nextLine();
            }           
        }
    }
    public void printCalcResult(CalculationResult calculationResult){
        System.out.println("Results:\n" + 
            "\tPI is " + calculationResult.pi() + "\n" +
            "\tTHREADS " + calculationResult.threads() + "\n" +
            "\tITERATIONS " + calculationResult.iterations() + "\n" +
            "\tTIME " + calculationResult.timeInMilliSeconds() + "ms");
    }

    public void errorNotification(String errorDescription){
        System.out.println("Error: " + errorDescription);
    }
}
