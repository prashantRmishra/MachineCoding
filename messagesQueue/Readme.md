# Message Queueing System
focus: multithreading and Design patterns
Use-case:
Processing of event driven data 
For async communication
Guaranteed Persistence 
Ordered read of events/messages
Useful in microservices architecture for decoupling producer and consumers



# How it works
Produces produces the messages/events to the queue partitioned based on topic which unique key to identify the partition to which the event should be added

Each Message has a unique offset and once read the same messages is not read again and can be deleted based on the deletion policy configured
Once the consumer consumes the message successfully it commits the offset back to the kafka queue so that it can be marked as read and can not be read again.


```output
Thread-0 produced: Message-1 from Thread-0
Thread-1 produced: Message-1 from Thread-1
Thread-2 consumed: Message-1 from Thread-1
Thread-3 consumed: Message-1 from Thread-1
Thread-1 produced: Message-2 from Thread-1
Thread-0 produced: Message-2 from Thread-0
Thread-2 consumed: Message-1 from Thread-0
Thread-3 consumed: Message-1 from Thread-0
Thread-0 produced: Message-3 from Thread-0
Thread-2 consumed: Message-2 from Thread-1
Thread-3 consumed: Message-2 from Thread-1
Thread-2 consumed: Message-2 from Thread-0
Thread-3 consumed: Message-2 from Thread-0
Thread-3 consumed: Message-3 from Thread-0
Thread-2 consumed: Message-3 from Thread-0
```