
public class Mp4MediaPlayer implements AdvanceMediaPlayer {
    @Override
    public void playMp4(String fileName){
        System.out.println("playing "+fileName+  " of type mp4");
    }
    //note: this is clear violation of ISP (as this client(Mp4MediaPlayer) is being forced to override a method it does use)
    public void playVlc(String fileName){
        // do nothing
    }
}
