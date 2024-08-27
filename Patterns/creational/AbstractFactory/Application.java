package Patterns.creational.AbstractFactory;

public class Application {
    Button button;
    public Application(GUIFactory factory){
        this.button = factory.createBtton();
    }
    public static void main(String args[]){
        GUIFactory factory = new WindowsFactory(); // or new MacFactory();
        Application application = new Application(factory);
        application.print();
    }

    public void print(){
        button.print();
    }
}
