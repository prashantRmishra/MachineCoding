
public class MediaPlayerMain{
    public static void main(String args[]){
        // below class Mp3MediaPlayer can play mp3 player (in built) but can also play mp4 and vlc because of 
        //MediaAdapter
         Mp3MediaPlayer mediaPlayer = new Mp3MediaPlayer();
         mediaPlayer.play("mp3", "classics.mp3");
         mediaPlayer.play("mp4", "Krish.mp4");
         mediaPlayer.play("vlc", "MIB.vlc");
         mediaPlayer.play("csv", "random.csv");

        
    }
}
