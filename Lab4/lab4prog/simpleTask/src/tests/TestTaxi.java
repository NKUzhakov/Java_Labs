package tests;
import transportations.*;
import transportations.passengers.*;
import transportations.vehicles.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class TestTaxi{
    @Test 
    public void testBoardPassenger(){
        Taxi taxi = new Taxi("AA 2345 BB");
        assertDoesNotThrow(() -> taxi.boardPassenger(new Human("Ivan")));
        assertDoesNotThrow(() -> taxi.boardPassenger(new Fireman("Oleg", "some rank")));
        assertDoesNotThrow(() -> taxi.boardPassenger(new Policeman("Alex", "Patrol")));                    
    }
    @Test 
    public void testBoardPassengerLimit(){
        Taxi taxi = new Taxi("AA 2345 BB");
        for(int i = 0; i < taxi.getMaxSeatsCount(); i++)
            taxi.boardPassenger(new Human("Ivan #" + (i+1)));
        assertThrows(IllegalArgumentException.class, () -> taxi.boardPassenger(new Human("Just Ivan who missed the taxi")));                
    }
    @Test 
    public void testDisembarkPassenger(){
        Taxi taxi = new Taxi("AA 2345 BB");
        assertThrows(IllegalArgumentException.class, () -> taxi.disembarkPassenger(new Human("Just Ivan who is not in the taxi")));
    }
}