package transportations.vehicles;

import transportations.passengers.Fireman;

public class FireTruck extends Car<Fireman> {
    public FireTruck(String number){
        super(number, 6);
    }
}
