package transportations.passengers;

public class Human {
    private String name;

    public Human(String name){
        this.name = name;
    }
    public String getName(){ return name;}
    @Override
    public boolean equals(Object other){
        if(other == null) return false;
        if(other.getClass() != this.getClass()) return false;
        Human otherHuman = (Human)other;
        return otherHuman.name.equals(this.name);
    }
    @Override
    public int hashCode(){
        return 31*this.name.hashCode();
    }
}
