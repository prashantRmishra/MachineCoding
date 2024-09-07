package Patterns.Structural.Facade;

public class ToyManufacturingMain {
    public static void main(String args[]){
        ToyMaker  toyMaker = new ToyMaker();
        

        //if client want to create train
        toyMaker.createTrain();
        //if client wants to create car
        toyMaker.createCar();
    }
}
