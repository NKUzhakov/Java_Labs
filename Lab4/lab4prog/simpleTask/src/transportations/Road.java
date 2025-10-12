package transportations;
import transportations.vehicles.*;

import java.util.ArrayList;

public class Road { 
    public ArrayList<Vehicle<?>> carsInRoad = new ArrayList<>(); 
    public int getCountOfHumans(){
        int count = 0;
        for(Vehicle<?> vehicle : carsInRoad){
            count += vehicle.getOccupiedSeatsCount();
        }
        return count;
    } 
    public void addCarToRoad(Vehicle<?> vehicle) throws IllegalArgumentException{
        if(carsInRoad.contains(vehicle))
            throw new IllegalArgumentException("Cannot add the vehicle that is already on the road");
        carsInRoad.add(vehicle);
    } 
} 
