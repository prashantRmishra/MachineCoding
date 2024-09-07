package Patterns.Structural.decorator;

public class ToyManufacturingMain {
    public static void main(String args[]){
        ToyDecorator redToy = new RedToyDecorator(new Car());
        ToyDecorator greenToy = new GreenToyDecorator(new Train());
        redToy.createToy();
        greenToy.createToy();
    }
}