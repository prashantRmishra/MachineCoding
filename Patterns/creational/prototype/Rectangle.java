package Patterns.creational.prototype;

public class Rectangle extends Shape {

    public Rectangle(){
        shape = "Rectangle";
    }
    public void draw(){
       System.out.println("calling draw() of Rectangle shape");
    }
}
