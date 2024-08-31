


The Adapter Design Pattern is a **structural** design pattern that **allows** **incompatible interfaces to work together**. It acts as a bridge between two objects, allowing them to collaborate even if they have incompatible interfaces.
One of the examples is the memory card reader.
*We insert a memory card in the card reader and the card reader in the laptop
then we can read the contents of the memory card in a laptop, this is possible because of the card reader (acting as an adaptor)*

Let's take the example of `MediaPlayer`(legacy) that can only play `mp3`, now we want it to play `mp4` and `vlc` media as well.
So, `MediaPlayer` can take **help of third party** application( which is nothing but an adapter) to play `mp4` and `vlc` media as well.
`Adapter` works as a bridge between two interfaces that are not related to each other allowing them to work together ( as the example of `MediaPlayer` needs to play `AdvanceMediaPlayer` files like `mp4` , `vlc` etc);

![adapter](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/a4uladp21gmy58rff1lb.png)

**Steps**: 
Creating interface `MediaPlayer` > create concrete implementations
Creating interface `AdvanceMediaPlayer` > Create a concrete implementation
Creating `MediaAdapter` > implementing `MediaPlayer` and using `AdvanceMediaPlayer` (as a dependency) to act as a bridge between these two

In summary `MediaPlayer` uses `MediaAdapter` and `MediaAdapter` uses `AdvanceMediaPlayer` to help `MediaPlayer` get the features of `AdvanceMediaPlayer`

`MediaPlayer`

```java
/**
 * Interface is capable of only playing mp3 media files
*/
public interface MediaPlayer {
    public void play(String mediaType,String fileName);
}
```
`AdvanceMediaPlayer`

```java
/**
 * Interface capable of playing mp4 and vlc files both
*/
public interface AdvanceMediaPlayer {

    public void playMp4(String fileName);
    public void playVlc(String fileName);

}
```
`Mp3MediaPlayer`

```java
public class Mp3MediaPlayer implements MediaPlayer {
    //dependency
    MediaAdapter mediaAdapter;
    @Override
    public void play(String mediaType,String fileName){
        if(mediaType.equals("mp3")){
            System.out.println("playing "+fileName+  " of type "+ mediaType);
        }
        else if(mediaType.equals("mp4") || mediaType.equals("vlc")){
            mediaAdapter = new MediaAdapter(mediaType);
            mediaAdapter.play(mediaType, fileName);
        }
        else System.out.println("Given media type is not supported ");
        
    }
}
```
`MediaAdapter`

```java
public class MediaAdapter  implements MediaPlayer{

    //dependency in MediaAdapter
    AdvanceMediaPlayer advanceMediaPlayer;
    
    //dependency injection in the same class(not recommended)
    public MediaAdapter(String mediaType){
        this.advanceMediaPlayer = mediaType.equals("mp4") ? new Mp4MediaPlayer(): (mediaType.equals("vlc") ? new VlcMediaPlayer(): null);
    }

    @Override
    public void play(String mediaType, String fileName){
        if(mediaType.equals("vls")) this.advanceMediaPlayer.playVlc(fileName);
        else if (mediaType.equals("mp4")) this.advanceMediaPlayer.playMp4(fileName);
    }
    
}
```

`Mp4MediaPlayer`

```java

public class Mp4MediaPlayer implements AdvanceMediaPlayer {
    @Override
    public void playMp4(String fileName){
        System.out.println("playing "+fileName+  " of type mp4");
    }
    //note: this is a clear violation of ISP (as this client(Mp4MediaPlayer) is being forced to override a method it does use)
    public void playVlc(String fileName){
        // do nothing
    }
}
```

`VlcMediaPlayer`

```java

public class VlcMediaPlayer implements AdvanceMediaPlayer {

    //note: this is a clear violation of ISP (as this client is being forced to override a method it does use), for solution, we can segregate these two methods from the parent interface into separate interfaces and only implement the required one
    @Override
    public void playMp4(String fileName){
        //do nothing
    }
     public void playVlc(String fileName){
        System.out.println("playing "+fileName+  " of type vlc");
    }
    
}
```

`Main`

```java

public class MediaPlayerMain{
    public static void main(String args[]){
        // below class Mp3MediaPlayer can play mp3 player (inbuilt) but can also play mp4 and vlc because of 
        //MediaAdapter
         Mp3MediaPlayer mediaPlayer = new Mp3MediaPlayer();
         mediaPlayer.play("mp3", "classics.mp3");
         mediaPlayer.play("mp4", "Krish.mp4");
         mediaPlayer.play("vlc", "MIB.vlc");
         mediaPlayer.play("csv", "random.csv");

        
    }
}

```
**Output**: 

```console
playing classics.mp3 of type mp3
playing Krish.mp4 of type mp4
Given media type is not supported 
```



