package Patterns.Behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class YouTubeChannelSubject{
    private String channelName;
    private List<String> contents;
    private List<Observer> subscriberObservers;
    public YouTubeChannelSubject(){
        this.channelName = "Mr.Beast";
        contents = new ArrayList<>();
        subscriberObservers = new ArrayList<>();
    }
    public void addNewContent(String name){
        contents.add(name);
        //notify all listeners
        notifySubscriberObserver();
    }
    public void removeContent(String name){
        if(contents.contains(name)){
            contents.remove(name);
            //notify all listeners
            notifySubscriberObserver();
        }
    }
    public List<String> getContents(){
        return this.contents;
    }
    public String getChannelName(){
        return this.channelName;
    }
    public void addObserver(Observer observer){
        this.subscriberObservers.add(observer);
    }

    public void removeObserver(Observer observer){
        this.subscriberObservers.remove(observer);
    }

    public void notifySubscriberObserver(){
        for(Observer subscriber : subscriberObservers){
            subscriber.update();
        }
    }

}