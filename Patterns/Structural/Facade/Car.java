package Patterns.Structural.Facade;

public class Car implements Toy {
    @Override
    public void createToy(){
        System.out.println("Creating Car toy");
    }
}
