Proxy is one of the structural design patterns, It is used to create a surrogate or placeholder object, which is used to control the access of original object.
It acts as an intermediary adding extra level of control, and can perform extra actions before and after delegating request to real object.

**Key Concepts**:
*Proxy Object*: Represents the real object and controls access to it.
*Real Object* (Subject): The actual object that does the work.
*Client*: The entity that interacts with the proxy instead of the real object directly.

Lets understand this taking an example of Image.

```java
//Object interface
public interface Image{
    public void display();
}


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

// client
public class Main {
    public static void main(String args[]){
        Image image = new ProxyImage("wallpaper.png");
        //image is loaded and displayed for the first time
        image.display();
        //image will not be loaded again, only display will be called 
        image.display();
    }
}
```


Output:

```output:
Loading image wallpaper.png from disk
Rendering image : wallpaper.png
```






**Use Cases**:
*Lazy initialization*: Delaying object creation until it's absolutely necessary.
*Access control*: Restricting access to specific methods based on user roles or permissions.
*Logging*: Adding logging or monitoring functionalities.


