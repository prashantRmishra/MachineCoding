package Patterns.Structural.decorator;

public class Train implements Toy {
    @Override
    public void createToy(){
        System.out.print("Creating Train ");
    }
}
