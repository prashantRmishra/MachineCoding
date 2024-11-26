package Patterns;

interface Image{
    public void display();
}
class RealImage implements Image{
    private String filename;

    public RealImage(String name){
        this.filename = name;
        loadImageFromDisk();
    }
    public void loadImageFromDisk(){
        System.out.println("loading image from disk named: " + filename);
    }

    @Override
    public void display(){
        System.out.println("rendering image " + filename);
    }
}

class ProxyImage implements Image{
    private Image image;
    private String filename;
    public ProxyImage(String filename){
        this.filename = filename;
    }
    @Override
    public void display() {
        if(image ==null){
            image = new RealImage(filename); //lazy loading 
        }
        image.display();
    }
    
}

public class Main {
    public static void main(String[] args) {
        ProxyImage image = new ProxyImage("wallpaper.png");
        //image is loaded and displayed for the first time
        image.display();
        //image will not be loaded again, only display will be called 
        image.display();
    }
}
