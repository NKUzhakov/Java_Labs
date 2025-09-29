package model.shapes;

public abstract class Shape implements Drawable{
    protected String shapeColor;

    public Shape(String shapeColor)throws IllegalArgumentException{
        if(shapeColor.isEmpty()) throw new IllegalArgumentException("The color name cannot be empty string");
        this.shapeColor = shapeColor;
    }
    public String getShapeColor(){ return shapeColor;}
    public abstract double calcArea();
    @Override
    public String toString(){
        return "Shape{\n" + 
                "   shapeColor = " + shapeColor + ";\n}";
    }
}
