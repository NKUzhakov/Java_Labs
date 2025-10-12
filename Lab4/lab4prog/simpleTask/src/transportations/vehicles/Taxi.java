package transportations.vehicles;

import transportations.passengers.Human;

public class Taxi extends Car<Human>{
    public Taxi(String number){
        super(number, 4);
    }
}
