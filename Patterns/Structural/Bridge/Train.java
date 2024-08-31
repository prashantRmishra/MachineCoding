package Patterns.Structural.Bridge;

public class Train extends Toy {
    //dependency PaintAPI injection
    protected Train(PaintAPI paintApi) {
        super(paintApi);
    }

    @Override
    void createToy() {
        System.err.print("Train created and ");
        paintAPI.paintToy();
    }
    
}
