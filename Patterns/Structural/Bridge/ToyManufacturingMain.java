package Patterns.Structural.Bridge;

public class ToyManufacturingMain {
    public static void main(String args[]){
        Toy toy1 = new Train(new GreenPaint());
        Toy toy2 = new Car(new RedPaint());
        toy1.createToy();
        toy2.createToy();
    }
}
