Observer pattern is used when there is one to many relationship between objects such as if one object is modified, its dependent objects are to be notified automatically. Observer pattern falls under behavioral pattern category.
Example: a Youtube Channel and its subscribers
It is also called pub sub model

Whenever new contents in the youtube channel is added or existing content is modified, all the subscribers of the youtube channel will be notified

`Subject` or `YoutubeChannel`

```java
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
```

`Abstract Subscriber` or `Observer`

```java
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
```

`Concrete Subscribers`


```java
package Patterns.Behavioral.observer;

public class SubscriberOneObserver extends Observer {
    public SubscriberOneObserver(YouTubeChannelSubject subject,String name){
        super(subject,name);
        // add this subscriber as of the observers of the youtube channel defined by object 'subject'
        this.subject.addObserver(this);
    }

    @Override
    void update() {
        System.out.println(this.subscriberName +" got updated !");
        System.out.println(this.subject.getChannelName()+" has updated contents, the total content on the channel now is "+ this.subject.getContents().size());
        System.out.println("List of contents on the channel "+this.subject.getChannelName()+" are  as follows: ");
        for(String content : subject.getContents()){
            System.out.println(content);
        }
        System.out.println("------------------------------");
    }
    
}
```

```java
package Patterns.Behavioral.observer;

public class SubscriberTwoObserver extends Observer {
    public SubscriberTwoObserver(YouTubeChannelSubject subject,String name){
        super(subject,name);
        // add this subscriber as of the observers of the youtube channel defined by object 'subject'
        this.subject.addObserver(this);
    }

    @Override
    void update() {
        System.out.println(this.subscriberName +" got updated !");
        System.out.println(this.subject.getChannelName()+" has updated contents, the total content on the channel now is "+ this.subject.getContents().size());
        System.out.println("List of contents on the channel "+this.subject.getChannelName()+" are  as follows: ");
        for(String content : subject.getContents()){
            System.out.println(content);
        }
        System.out.println("------------------------------");
    }
    
}
```
`Main`

```java
package Patterns.Behavioral.observer;

public class Main {
    public static void main(String args[]){
        //creating youtube channel
        YouTubeChannelSubject subject = new YouTubeChannelSubject();

        //subscriber's of the above youtube channel
       new SubscriberOneObserver(subject,"prashant");
       new SubscriberTwoObserver(subject,"sandeep");

       //now updating the contents of the youtube channel
       subject.addNewContent("Men Vs Women survive the wilderness of $500,000");
       subject.addNewContent("7 Days stranded in a cave");
    }
}
```
`output`

```output
prashant got updated !
Mr.Beast has updated contents, the total content on the channel now is 1
List of contents on the channel Mr.Beast are  as follows: 
Men Vs Women survive the wilderness of $500,000
------------------------------
sandeep got updated !
Mr.Beast has updated contents, the total content on the channel now is 1
List of contents on the channel Mr.Beast are  as follows: 
Men Vs Women survive the wilderness of $500,000
------------------------------
prashant got updated !
Mr.Beast has updated contents, the total content on the channel now is 2
List of contents on the channel Mr.Beast are  as follows: 
Men Vs Women survive the wilderness of $500,000
7 Days stranded in a cave
------------------------------
sandeep got updated !
Mr.Beast has updated contents, the total content on the channel now is 2
List of contents on the channel Mr.Beast are  as follows: 
Men Vs Women survive the wilderness of $500,000
7 Days stranded in a cave
------------------------------
prashantmishra@Prashants-MacBook-Air machinecoding % 

```

