package Patterns.Structural.Bridge;

public abstract class Toy {
    PaintAPI paintAPI;
    protected Toy(PaintAPI paintApi){
        this.paintAPI = paintApi;
    }
    abstract void createToy();
}
