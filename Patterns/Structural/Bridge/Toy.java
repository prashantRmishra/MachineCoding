package Patterns.Structural.Bridge;

/**
 * Toy is an abstraction So, that we can inject dependency of PaintAPI implementation
 * That can be used to color the toy
*/
public abstract class Toy {
    PaintAPI paintAPI;
    protected Toy(PaintAPI paintApi){
        this.paintAPI = paintApi;
    }
    abstract void createToy();
}
