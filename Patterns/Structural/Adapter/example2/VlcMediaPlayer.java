package Patterns.Structural.Adapter.example2;
public class VlcMediaPlayer implements AdvanceMediaPlayer {

    //note: this is clear violation of ISP (as this client is being forced to override a method it does use)
    @Override
    public void playMp4(String fileName){
        //do nothing
    }
     public void playVlc(String fileName){
        System.out.println("playing "+fileName+  " of type vlc");
    }
    
}
