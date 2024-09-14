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

/**
 * output:
 * 
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
*/
