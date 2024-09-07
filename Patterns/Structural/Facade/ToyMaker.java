package Patterns.Structural.Facade;

public class ToyMaker {
    private Toy car;
    private Toy train;

    public ToyMaker(){
        car = new Car();
        train = new Train();
    }

    public  void createTrain(){
        train.createToy();
    }

    public void createCar(){
        car.createToy();
    }
}
