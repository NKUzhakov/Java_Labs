import java.util.InputMismatchException;
import java.util.Scanner;
public class ZerosMinimizerConsole {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        while(true){            
            System.out.println("Enter the natural number (for exit enter 0)"); 
             
            try{            
                int testNum = scanner.nextInt();
                if(testNum == 0){
                    scanner.close();
                    break;
                }

                ZerosMinimizer zm = new ZerosMinimizer();
                int res = zm.findOptimalSimpleNumber(testNum);
                System.out.println("The result is " + res);

            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }catch(InputMismatchException ime){
                System.out.println("The entered value Was not integer");
                scanner = new Scanner(System.in);
            }
            
        }


        // Scanner scanner = new Scanner(System.in);
        // System.out.println("Enter ");
        // int testNum = scanner.nextInt();
        // System.out.println("Enter "); 
    }

}
