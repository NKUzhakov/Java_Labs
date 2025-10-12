package transportations.passengers;

public class Fireman extends Human{
    private String rank;
    public Fireman(String name, String rank){
        super(name);
        this.rank = rank;
    }
    public String getRank(){ return rank;}

    @Override
    public boolean equals(Object other){
        if(other == null) return false;
        if(other.getClass() != this.getClass()) return false;
        Fireman otherFireman = (Fireman)other;
        return otherFireman.rank.equals(this.rank) && ((Human)otherFireman).equals((Human)this);
    }
    @Override
    public int hashCode(){
        return 31*this.rank.hashCode() + super.hashCode();
    }
}