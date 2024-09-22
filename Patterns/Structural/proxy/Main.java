package Patterns.Structural.proxy;

public class Main {
    public static void main(String args[]){
        Image image = new ProxyImage("wallpaper.png");
        //image is loaded and displayed for the first time
        image.display();
        //image will not be loaded again, only display will be called 
        image.display();
    }
}
