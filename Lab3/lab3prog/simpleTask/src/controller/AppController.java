package controller;

import java.util.ArrayList;

import model.ShapeProcessor;
import model.shapes.*;
import view.ConsoleView;

public class AppController {
    private final ShapeProcessor shapeProcessor;
    private final ConsoleView consoleView;

    public AppController(ShapeProcessor shapeProcessor, ConsoleView consoleView)throws IllegalArgumentException{
        if(shapeProcessor == null) throw new IllegalArgumentException("The shape processor cannot be null");
        if(consoleView == null) throw new IllegalArgumentException("The console view cannot be null");

        this.shapeProcessor = shapeProcessor;
        this.consoleView = consoleView;
    }

    public void start(){
        mainloop:
        while(true){
            int operation = consoleView.askOperation();
            switch(operation){
                case 0: // Exit
                    break mainloop;
                case 1: // Show all data
                    consoleView.printAllShapes(shapeProcessor.getSavedShapes());
                    break;
                case 2: // Get total area of all shapes
                    consoleView.printTotalShapeArea(shapeProcessor.getTotalArea());
                    break;
                case 3: // Get total area of shape class
                    int figureClassIndex = consoleView.askFigureClass() - 1;
                    double totalFigureClassArea = shapeProcessor.getTotalAreaOfType(figureClassIndex);
                    consoleView.printTotalFigureClassArea(totalFigureClassArea);
                    break;
                case 4: // Sort shapes by area ascending
                    ArrayList<Shape> sortedShapesByArea = shapeProcessor.getSortedShapesByArea();
                    consoleView.printSortedByAreaShapes(sortedShapesByArea);
                    break;
                case 5: // Sort shapes by colors
                    ArrayList<Shape> sortedShapesByColor = shapeProcessor.getSortedShapesByColor();
                    consoleView.printSortedByColorShapes(sortedShapesByColor);
                    break;                    
            }
        }
    }
}
