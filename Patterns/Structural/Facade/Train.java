package Patterns.Structural.Facade;

public class Train implements Toy {
    @Override
    public void createToy(){
        System.out.println("Creating Train toy");
    }
}
