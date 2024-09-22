package Patterns.Structural.proxy;

//Proxy class
public class ProxyImage implements Image {
    private Image image;
    private String file;

    public ProxyImage(String fileName){
        this.file =fileName;
    }
    @Override
    public void display(){
        if(image ==null){// create object of RealImage only if the image reference is null, thus resulting in LazyIntialization 
            //( i.e. Initializing the object only when it is needed not beforehand)
            image = new RealImage(file);
        }
        image.display();
    }
    
}
