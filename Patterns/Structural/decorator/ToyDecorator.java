package Patterns.Structural.decorator;

/**
 * 
 * Decorator is an abstract class not an interface because we want to initialize
 * the Object (in our case Train or Car) so that we can provide additional feature on 
 * top of the existing Object (in this case Car or Train) when we extend this abstract class
 * And initialization is possible through dependency injection via constructor
 * 
 * If we had interface in this place then we would not have been able to initialize 
 * Object in run time, rather we will have had to initialize the Object in the individual 
 * implementation of interface ToyDecorator which is not efficient
*/
public abstract class ToyDecorator implements Toy{
    protected Toy toy;
    public ToyDecorator(Toy t){
        this.toy = t;
    }
}
