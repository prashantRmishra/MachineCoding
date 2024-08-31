

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
