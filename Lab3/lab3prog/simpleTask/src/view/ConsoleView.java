package view;

import model.shapes.Shape;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class ConsoleView {
    private Scanner scanner = new Scanner(System.in);
    public int askOperation(){
        System.out.println("Enter the number of operation:\n" + 
                            "1 - Show all data\n" + 
                            "2 - Get total area of all shapes\n" + 
                            "3 - Get total area of shape class\n" + 
                            "4 - Sort shapes by area ascending\n" + 
                            "5 - Sort shapes by colors\n" + 
                            "0 - Exit");
        int operationNumber = -1;        
        try{
            operationNumber = scanner.nextInt();
            if(operationNumber >= 0 && operationNumber <= 5) return operationNumber;
            else throw new InputMismatchException();
        }catch(InputMismatchException e){
            System.out.println("Entered value must be integer, from 0 to 5. Try again");
            scanner.nextLine();
            return askOperation();
        }
    }
    public int askFigureClass(){
        System.out.println("Enter the number of figure:\n" + 
                            "1 - Rectangle\n" +  
                            "2 - Triangle\n" + 
                            "3 - Circle");
        int figureNumber = 0;
        try{
            figureNumber = scanner.nextInt();
            if(figureNumber >= 1 && figureNumber <= 3) return figureNumber;
            else throw new InputMismatchException();
        }catch(InputMismatchException e){
            System.out.println("Entered value must be integer, from 1 to 3. Try again");
            scanner.nextLine();
            return askFigureClass();
        }
    }
    public void drawShapes(ArrayList<Shape> shapes){
        for(Shape shape : shapes){
            System.out.println(shape.draw());
        }
    }
    public void printAllShapes(ArrayList<Shape> shapes){
        System.out.println("All shapes:");
        drawShapes(shapes);
    }
    public void printSortedByAreaShapes(ArrayList<Shape> shapes){
        System.out.println("Sorted shapes by their areas:");
        drawShapes(shapes);
    }
    public void printSortedByColorShapes(ArrayList<Shape> shapes){
        System.out.println("Sorted shapes by their colors:");
        drawShapes(shapes);
    }

    public void printShapeList(ArrayList<Shape> shapes){
        for(Shape shape : shapes){
            System.out.println(shape.toString());
        }
    }
    public void printTotalShapeArea(double area){
        System.out.println("The total area of all shapes is " + area);
    }
    public void printTotalFigureClassArea(double area){
        System.out.println("The total area of this class is " + area);
    }
}
