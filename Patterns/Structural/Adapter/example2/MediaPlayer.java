package Patterns.Structural.Adapter.example2;
/**
 * Interface is capable of only playing mp3 media files
*/
public interface MediaPlayer {
    public void play(String mediaType,String fileName);
}
