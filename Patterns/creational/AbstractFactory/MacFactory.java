package Patterns.creational.AbstractFactory;

public class MacFactory implements GUIFactory{

    @Override
    public Button createBtton() {
        return new MacButton();
    }
    
}
