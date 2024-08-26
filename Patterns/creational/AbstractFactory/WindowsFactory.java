package Patterns.creational.AbstractFactory;

public class WindowsFactory implements GUIFactory {

    @Override
    public Button createBtton() {
        return new WindowsButton();
    }
    
}
