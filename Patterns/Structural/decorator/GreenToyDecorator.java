package Patterns.Structural.decorator;

public class GreenToyDecorator extends ToyDecorator {

    public GreenToyDecorator(Toy t){
        super(t);
    }

    @Override
    public void createToy() {
        toy.createToy();
        paint(toy.getClass().getSimpleName());
        
    }
    //additional feature
    public void paint(String toyName){
        System.out.print("and painted the toy "+toyName+" with green color\n");
    }
    
}
