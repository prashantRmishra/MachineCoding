package Patterns.Structural.decorator;

public class Car implements Toy {
    
    @Override
    public void createToy(){
        System.out.print("Creating Car ");
    }
}
