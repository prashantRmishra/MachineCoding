package Patterns.Structural.Bridge;

public class GreenPaint implements PaintAPI {
    @Override
    public void paintToy() {
        System.out.println("painted green");
    }
    
}
