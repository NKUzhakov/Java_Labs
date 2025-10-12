package transportations.vehicles;
import transportations.passengers.*;
import java.util.ArrayList;

public abstract class Vehicle<T extends Human> {
    private String number;
    private final int seatLimit;
    private ArrayList<T> passengers;

    public Vehicle(String number, int seatLimit) throws IllegalArgumentException{   
        if(number.isEmpty()) throw new IllegalArgumentException("The number annot be empty");
        if(seatLimit < 0) throw new IllegalArgumentException("The number of seats cannot be negative");

        this.number = number;
        this.seatLimit = seatLimit;
        passengers = new ArrayList<>(seatLimit);
    }
    public String getNumber(){ return number;}
    public int getMaxSeatsCount(){ return seatLimit;}
    public int getOccupiedSeatsCount(){ return passengers.size();}

    public void boardPassenger(T passenger) throws IllegalArgumentException{
        if(getOccupiedSeatsCount() == seatLimit) 
            throw new IllegalArgumentException("Cannot board the passenger, because all the seats are occupied");
        passengers.add(passenger);        
    }
    public void disembarkPassenger(T passenger) throws IllegalArgumentException{
        if(!passengers.contains(passenger))
            throw new IllegalArgumentException("Cannot disembark the passenger, who is not in the vehicle");
        passengers.remove(passenger);
    }
}
