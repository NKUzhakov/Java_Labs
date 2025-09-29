package model;

import model.shapes.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ShapeProcessor {
    private final ArrayList<Shape> shapes = new ArrayList<>();
    private final Class<?>[] figureClasses = {Rectangle.class, Triangle.class, Circle.class};

    public ShapeProcessor(){
        preInitialize();       
    }
    public ArrayList<Shape> getSavedShapes(){ return new ArrayList<Shape>(shapes);}
    public double getTotalArea(){
        double totalArea = 0;
        for(Shape shape : shapes){
            totalArea += shape.calcArea();
        }
        return totalArea;
    }
    public double getTotalAreaOfType(int shapeClassIndex){
        Class<?> shapeClass = figureClasses[shapeClassIndex];
        double totalArea = 0;
        for(Shape shape : shapes){
            if(shape.getClass() == shapeClass) totalArea += shape.calcArea();
        }
        return totalArea;
    }
    public ArrayList<Shape> getSortedShapesByArea(){
        ArrayList<Shape> sortedShapes = new ArrayList<Shape>(shapes);
        Collections.sort(sortedShapes, new AreaComparator());
        return sortedShapes;
    }
    public ArrayList<Shape> getSortedShapesByColor(){
        ArrayList<Shape> sortedShapes = new ArrayList<Shape>(shapes);
        Collections.sort(sortedShapes, new ColorComparator());
        return sortedShapes;
    }

    private void preInitialize(){
        shapes.add(new Rectangle(1, 2, "Green"));
        shapes.add(new Rectangle(5, 4, "Blue"));
        shapes.add(new Rectangle(12, 7, "Yellow"));
        shapes.add(new Triangle(3, 4, 5, "Orange"));
        shapes.add(new Triangle(10, 10, 10, "White"));
        shapes.add(new Triangle(7, 5, 7, "Cyan"));
        shapes.add(new Circle(5, "Purpur"));
        shapes.add(new Circle(3, "Pink"));
        shapes.add(new Circle(15, "Brown"));
        shapes.add(new Circle(10, "Red"));
    }

    private static class AreaComparator implements Comparator{
        @Override
        public int compare(Object obj1, Object obj2){
            Shape shape1 = (Shape)obj1;
            Shape shape2 = (Shape)obj2;
            if(shape1.calcArea() > shape2.calcArea()) return 1;
            if(shape1.calcArea() < shape2.calcArea()) return -1;
            return 0;
        }
    }
    private static class ColorComparator implements Comparator{
        @Override
        public int compare(Object obj1, Object obj2){
            Shape shape1 = (Shape)obj1;
            Shape shape2 = (Shape)obj2;
            return shape1.getShapeColor().compareTo(shape2.getShapeColor());
        }
    }
}
