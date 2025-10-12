package tests;
import transportations.*;
import transportations.passengers.*;
import transportations.vehicles.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class TestPoliceCar{
    @Test 
    public void testBoardPassenger(){
        PoliceCar policeCar = new PoliceCar("AA 3456 BB");
        assertDoesNotThrow(() -> policeCar.boardPassenger(new Policeman("Alex", "Patrol")));                    
    }
    @Test 
    public void testBoardPassengerLimit(){
        PoliceCar policeCar = new PoliceCar("AA 3456 BB");
        for(int i = 0; i < policeCar.getMaxSeatsCount(); i++)
            policeCar.boardPassenger(new Policeman("Alex " + (i+1), "Patrol"));
        assertThrows(IllegalArgumentException.class, () -> policeCar.boardPassenger(new Policeman("Alex", "Patrol")));                
    }
    @Test 
    public void testDisembarkPassenger(){
        PoliceCar policeCar = new PoliceCar("AA 3456 BB");
        assertThrows(IllegalArgumentException.class, () -> policeCar.disembarkPassenger(new Policeman("Just Ivan who is not in the car", "Patrol")));
    }
}