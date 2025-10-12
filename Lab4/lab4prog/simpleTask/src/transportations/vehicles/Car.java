package transportations.vehicles;

import transportations.passengers.Human;

public class Car<T extends Human> extends Vehicle<T>{
    public Car(String number, int seatLimit){
        super(number, seatLimit);
    }
}
