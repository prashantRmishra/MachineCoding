package Patterns.creational.AbstractFactory;


//concreate product for windows
public class WindowsButton implements Button{

    @Override
    public void print() {
        System.out.println("Render button for windows");
    }
    
}
