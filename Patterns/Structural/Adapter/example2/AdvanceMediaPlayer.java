package Patterns.Structural.Adapter.example2;


/**
 * Interface capable of playing mp4 and vlc files both
*/
public interface AdvanceMediaPlayer {

    public void playMp4(String fileName);
    public void playVlc(String fileName);

}
