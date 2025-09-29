package model.shapes;

public class Circle extends Shape{
    private double r;

    public Circle(double r, String shapeColor) throws IllegalArgumentException{
        super(shapeColor);
        if(r < 0) throw new IllegalArgumentException("The circle dimentions cannot be negative");
        this.r = r;
    }
    @Override
    public double calcArea(){
        return Math.PI * r * r;
    }
    @Override
    public String draw(){
        return "(" + shapeColor + " circle with area " + calcArea() + ")";
    }
    @Override
    public String toString(){
        return "Circle{\n" + 
                "   shapeColor = " + shapeColor + ";\n" + 
                "   r = " + r + ";\n}";
    }
}
