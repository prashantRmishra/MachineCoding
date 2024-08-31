package Patterns.Structural.Bridge;

public class RedPaint implements PaintAPI {

    @Override
    public void paintToy() {
        System.out.println("painted red");
    } 
}
