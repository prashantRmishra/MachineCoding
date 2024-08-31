package Patterns.Structural.Bridge;

public class Car extends Toy {
    //dependency PaintAPI injection
    protected Car(PaintAPI paintApi) {
        super(paintApi);
    }

    @Override
    void createToy() {
        System.out.print("Car created and ");
        paintAPI.paintToy();
    }
}
