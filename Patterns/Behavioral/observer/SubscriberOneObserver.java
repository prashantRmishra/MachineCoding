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
