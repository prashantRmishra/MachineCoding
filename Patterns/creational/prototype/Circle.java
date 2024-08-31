package Patterns.creational.prototype;

public class Circle extends Shape {
    public Circle(){
        shape = "Circle";
    }
    public void draw(){
        System.out.println("Calling draw in Circle method");
    }
}
