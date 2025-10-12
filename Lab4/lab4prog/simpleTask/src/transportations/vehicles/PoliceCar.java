package transportations.vehicles;

import transportations.passengers.Policeman;

public class PoliceCar extends Car<Policeman> {
    public PoliceCar(String number){
        super(number, 4);
    }
} 
