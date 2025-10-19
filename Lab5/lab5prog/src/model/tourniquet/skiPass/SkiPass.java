package model.tourniquet.skiPass;

import java.io.Serializable;

public abstract class SkiPass implements Cloneable, Serializable{
    private static Long nextId = 1L;

    private final Long id;
    private final SkiPassType type;
    private boolean active = true;

    public SkiPass(SkiPassType type){
        id = nextId;
        nextId = nextId + 1L;
        this.type = type;
    }
    public Long getId(){ return id;}
    public void block(){ active = false;}
    public boolean isActive(){ return active;}
    public SkiPassType getType(){ return type;}
    public boolean isAppliable(){ return active;}
    public boolean apply(){ return isAppliable();}
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString(){
        return "SkiPass{\n" + 
                    "\tid = " + id + 
                    "\n\ttype = " + type + 
                    "\n\tactive = " + active + 
                "\n}";
    }

}