package model.shapes;

public class Rectangle extends Shape{
    private double a, b;

    public Rectangle(double a, double b, String shapeColor) throws IllegalArgumentException{
        super(shapeColor);
        if(a < 0 || b < 0) throw new IllegalArgumentException("The rectangle dimentions cannot be negative");
        this.a = a;
        this.b = b;
    }
    @Override
    public double calcArea(){ return a * b;}
    @Override
    public String draw(){
        return "[" + shapeColor + " rectangle with area " + calcArea() + "]";
    }
    @Override
    public String toString(){
        return "Rectangle{\n" + 
                "   shapeColor = " + shapeColor + ";\n" + 
                "   a = " + a + ";\n" +
                "   b = " + b + ";\n}";
    }

}
