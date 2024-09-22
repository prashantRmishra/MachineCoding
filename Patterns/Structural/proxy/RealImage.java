package Patterns.Structural.proxy;

//Real object
public class RealImage implements Image {
    private String file;

    public RealImage(String fileName){
        this.file = fileName;
        loadImageFromDisk();
    }
    @Override
    public void display(){
        System.out.println("Rendering image : "+ file);
    }
    private void loadImageFromDisk(){
        System.out.println("Loading image "+file+" from disk");
    }
}
