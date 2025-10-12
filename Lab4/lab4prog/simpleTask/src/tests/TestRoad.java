package tests;
import transportations.*;
import transportations.passengers.*;
import transportations.vehicles.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class TestRoad {
    @Test 
    public void testAddCarToRoad(){
        Bus bus = new Bus("AA 1234 BB", 11);
        bus.boardPassenger(new Human("Ivan"));

        Road road = new Road();
        assertDoesNotThrow(() -> road.addCarToRoad(bus)); 
        assertThrows(IllegalArgumentException.class, () -> road.addCarToRoad(bus));
    }

    @Test 
    public void testCountOfHumans(){
        Bus bus = new Bus("AA 1234 BB", 11);
        bus.boardPassenger(new Human("Ivan"));
        bus.boardPassenger(new Fireman("Oleg", "some rank"));
        bus.boardPassenger(new Policeman("Alex", "Patrol")); 
        
        Taxi taxi = new Taxi("AA 2345 BB");
        taxi.boardPassenger(new Human("Ivan"));
        taxi.boardPassenger(new Fireman("Oleg", "some rank"));

        PoliceCar policeCar = new PoliceCar("AA 3456 BB");
        policeCar.boardPassenger(new Policeman("Alex", "Patrol"));  

        FireTruck fireTruck = new FireTruck("AA 4567 BB");
        for(int i = 0; i < fireTruck.getMaxSeatsCount(); i++)
            fireTruck.boardPassenger(new Fireman("Oleg " + (i+1), "some rank"));

        Road road = new Road();
        road.addCarToRoad(bus); 
        road.addCarToRoad(taxi); 
        road.addCarToRoad(policeCar); 
        road.addCarToRoad(fireTruck); 
        assertEquals(12, road.getCountOfHumans());
    }
}
