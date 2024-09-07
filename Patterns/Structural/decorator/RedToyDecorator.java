package Patterns.Structural.decorator;

public class RedToyDecorator extends ToyDecorator {

    public RedToyDecorator(Toy t) {
        super(t);
    }

    @Override
    public void createToy() {
        toy.createToy();
        paint(toy.getClass().getSimpleName());
        
    }
    //additional feature
    public void paint(String toyName){
        System.out.print("and painted the toy "+toyName+" with red color\n");
    }
    
}
