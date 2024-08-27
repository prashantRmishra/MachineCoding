package Patterns.creational.AbstractFactory;


// concrete product
public class MacButton implements Button {
    
    @Override
    public void print() {
        System.out.println("Render button for mac");
    }
    
}
