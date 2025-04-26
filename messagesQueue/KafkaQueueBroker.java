package messagesQueue;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class KafkaQueueBroker {
    Map<String,Deque<Event<?>>> queues; 
    Map<String,Map<String,Integer>> consumerOffsets;// this is to insure that the consumers are not stealing each others data
  
    List<Producer> producers;
    List<Consumer> consumers;
    public KafkaQueueBroker(){
        queues = new ConcurrentHashMap<>(); 
        producers = new ArrayList<>();
        consumers = new ArrayList<>();
        consumerOffsets = new ConcurrentHashMap<>();
    }
    public boolean createTopic(String topicName){
        if(queues.containsKey(topicName)) return false;
        queues.put(topicName, new ConcurrentLinkedDeque<>());
        return true;
    }
    public boolean add(Event<?> event){
        if(!queues.containsKey(event.topic)){
            //topic does not exist
            return false;
        }
        queues.get(event.topic).addLast(event);
        return true;
    }
    public Event<?> get(String topic, String consumerId){
        if(!queues.containsKey(topic)){
            //topic does not exist
            return null;
        }
        consumerOffsets.putIfAbsent(topic, new ConcurrentHashMap<>());
        consumerOffsets.get(topic).putIfAbsent(consumerId, 0);//initialize the offset to be 0 
        int offset = consumerOffsets.get(topic).get(consumerId);
        Deque<Event<?>> queue = queues.get(topic);
        if(offset>=queue.size()) return null;// we don't value of the given offset
        Event<?>[] events = queue.toArray(new Event<?>[0]);
        Event<?> event = events[offset];
        consumerOffsets.get(topic).put(consumerId, offset+1);
        return event;
    }
    
}
class Producer extends Thread{
    KafkaQueueBroker broker;
    int messageCount =0;
    String topic;
    public Producer(KafkaQueueBroker broker,String topic, int messageCount){
        this.broker = broker;
        broker.producers.add(this);
        this.messageCount = messageCount;
        this.topic = topic;
    }
    public boolean produce(Event<?> event){
        return broker.add(event);
    }
    @Override
    public void run(){
        //this producer puts 10 events in the broker
        for(int i = 1;i<=messageCount;i++){
            try {
                String data = "Message-"+ i+" from "+ Thread.currentThread().getName();
                Event<String> event = new Event<String>(data, topic);
                System.out.println(Thread.currentThread().getName() + " produced: "+ event.getData());
                this.produce(event);
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Consumer extends Thread{
    KafkaQueueBroker broker;
    String consumerId;
    String topic;
    public Consumer(KafkaQueueBroker broker,String topic,String consumerId){
        this.broker = broker;
        broker.consumers.add(this);
        this.consumerId = consumerId;
        this.topic = topic;
    }
    public Event<?> consume(String topic){
        return broker.get(topic,consumerId);
    }

    @Override
    public void run(){
        while(true){
            try {
                Event<?> event = this.consume(this.topic);
                if(event!=null){
                    System.out.println(Thread.currentThread().getName() +" consumed: "+ event.getData());
                    if(event.getData().equals("end")) break;
                }

                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
class Event<V> {
    private V data;
    private long timeStamp;
    private String offset;
    String topic;
    public Event(V data,String topic){
        this.data = data;
        timeStamp = System.currentTimeMillis();
        this.topic = topic;

    }
    public V getData(){return this.data;}
    public long getTimeStamp(){return this.timeStamp;}
    public void setOffset(String val){
        this.offset= val;
    }
    public String getOffset(){
        return this.offset;
    }
}

class IdGenerator{
    static Set<String> set = new HashSet<>();
    public static String get(){
        String id = UUID.randomUUID().toString();
        while(!set.add(id)){
            id = UUID.randomUUID().toString();
        }
        return id;
    }
}

