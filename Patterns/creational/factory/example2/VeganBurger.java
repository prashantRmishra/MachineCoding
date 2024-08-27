package Patterns.creational.factory.example2;

public class VeganBurger implements Burger{

    @Override
    public void getBurger() {
        System.out.println("VeganBurger for you!");
    }
    
}
