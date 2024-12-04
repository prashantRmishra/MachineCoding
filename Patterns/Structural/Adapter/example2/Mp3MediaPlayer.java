package Patterns.Structural.Adapter.example2;
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
