package tests;
import transportations.*;
import transportations.passengers.*;
import transportations.vehicles.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class TestFireTruck{
    @Test 
    public void testBoardPassenger(){
        FireTruck fireTruck = new FireTruck("AA 4567 BB");
        assertDoesNotThrow(() -> fireTruck.boardPassenger(new Fireman("Oleg", "some rank")));                   
    }
    @Test 
    public void testBoardPassengerLimit(){
        FireTruck fireTruck = new FireTruck("AA 4567 BB");
        for(int i = 0; i < fireTruck.getMaxSeatsCount(); i++)
            fireTruck.boardPassenger(new Fireman("Oleg " + (i+1), "some rank"));
        assertThrows(IllegalArgumentException.class, () -> fireTruck.boardPassenger(new Fireman("Oleg", "some rank")));                
    }
    @Test 
    public void testDisembarkPassenger(){
        FireTruck fireTruck = new FireTruck("AA 4567 BB");
        assertThrows(IllegalArgumentException.class, () -> fireTruck.disembarkPassenger(new Fireman("Just Ivan who is not in the car", "some rank")));
    }
}
