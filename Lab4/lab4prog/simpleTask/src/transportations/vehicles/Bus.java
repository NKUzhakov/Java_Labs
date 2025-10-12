package transportations.vehicles;

import transportations.passengers.Human;

public class Bus extends Vehicle<Human>{
    private static int validateSeats(int seatCount, int minBorder){
        if(seatCount <= minBorder) throw new IllegalArgumentException("The seats count cannot be " + minBorder + " or less");
        return seatCount;
    }

    public Bus(String number, int seatLimit){
        super(number, validateSeats(seatLimit, 10));        
    }
    
}
