import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
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
                scanner.nextLine();
            }            
        }
    }
}

// compile: javac -d ./out/ ./src/*.java
// start: java -cp ./out Main