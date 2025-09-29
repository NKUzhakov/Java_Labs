package tourniquet.skiPass;

public abstract class SkiPass implements Cloneable{
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
}
