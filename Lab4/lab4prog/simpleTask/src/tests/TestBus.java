package tests;
import transportations.*;
import transportations.passengers.*;
import transportations.vehicles.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;


public class TestBus{
    @Test 
    public void testBoardPassenger(){
        Bus bus = new Bus("AA 1234 BB", 11);
        assertDoesNotThrow(() -> bus.boardPassenger(new Human("Ivan")));
        assertDoesNotThrow(() -> bus.boardPassenger(new Fireman("Oleg", "some rank")));
        assertDoesNotThrow(() -> bus.boardPassenger(new Policeman("Alex", "Patrol")));                    
    }
    @Test 
    public void testBoardPassengerLimit(){
        Bus bus = new Bus("AA 1234 BB", 11);
        for(int i = 0; i < bus.getMaxSeatsCount(); i++)
            bus.boardPassenger(new Human("Ivan #" + (i+1)));
        assertThrows(IllegalArgumentException.class, () -> bus.boardPassenger(new Human("Just Ivan who missed the bus")));                
    }
    @Test 
    public void testDisembarkPassenger(){
        Bus bus = new Bus("AA 1234 BB", 11);
        assertThrows(IllegalArgumentException.class, () -> bus.disembarkPassenger(new Human("Just Ivan who is not in the bus")));
    }
}

