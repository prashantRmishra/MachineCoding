package Patterns.Behavioral.observer;

public abstract class Observer {
    public String subscriberName;
    public YouTubeChannelSubject subject;
    public Observer(YouTubeChannelSubject youTubeChannelSubject,String name){
        this.subject = youTubeChannelSubject;
        this.subscriberName = name;
    }
    abstract void update();
}
