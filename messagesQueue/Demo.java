package messagesQueue;

import java.util.concurrent.ConcurrentLinkedDeque;


public class Demo {
    public static void main(String[] args) throws InterruptedException {
        KafkaQueueBroker broker = new KafkaQueueBroker();
        String topic = "topic1";
        broker.queues.putIfAbsent("topic1", new ConcurrentLinkedDeque<>());

        Producer p1 = new Producer(broker,topic,3);
        Producer p2 = new Producer(broker,topic,2);
        Consumer c1 = new Consumer(broker,topic, "consumer-1");
        Consumer c2 = new Consumer(broker,topic, "consumer-2");
        p1.start();
        p2.start();
        c1.start();
        c2.start();

        
        //wait for producers to finish
        p1.join();
        p2.join();
        System.out.println("Producer finished ");
        broker.add(new Event<String>("end", topic));
        broker.add(new Event<String>("end", topic));

        
    }
}


