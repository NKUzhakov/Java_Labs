package model.shapes;

public class Triangle extends Shape{
    private double a, b, c;

    public Triangle(double a, double b, double c, String shapeColor) throws IllegalArgumentException{
        super(shapeColor);
        if(a < 0 || b < 0 || c < 0) throw new IllegalArgumentException("The triangle dimentions cannot be negative");
        this.a = a;
        this.b = b;
        this.c = c;
    }
    @Override
    public double calcArea(){
        double p = (a + b + c)/2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
    @Override
    public String draw(){
        return "<" + shapeColor + " triangle with area " + calcArea() + ">";
    }
    @Override
    public String toString(){
        return "Triangle{\n" + 
                "   shapeColor = " + shapeColor + ";\n" + 
                "   a = " + a + ";\n" +
                "   b = " + b + ";\n" +
                "   c = " + c + ";\n}";
    }
}
