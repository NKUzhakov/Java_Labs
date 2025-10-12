package transportations.passengers;

public class Policeman extends Human{
    private String speciality;
    public Policeman(String name, String speciality){
        super(name);
        this.speciality = speciality;
    }
    public String getSpeciality(){ return speciality;}
    @Override
    public boolean equals(Object other){
        if(other == null) return false;
        if(other.getClass() != this.getClass()) return false;
        Policeman otherPoliceman = (Policeman)other;
        return otherPoliceman.speciality.equals(this.speciality) && ((Human)otherPoliceman).equals((Human)this);
    }
    @Override
    public int hashCode(){
        return 31*this.speciality.hashCode() + super.hashCode();
    }
}
