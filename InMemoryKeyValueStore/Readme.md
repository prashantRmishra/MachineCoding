# In value key value store with auto expiration

## Functional Requirements
put(key, value, ttlInSeconds): Store a key-value pair with an optional TTL (time to live).
get(key): Retrieve the value associated with the key if it's not expired.
remove(key): Manually delete a key.
Keys should expire automatically after TTL.
Thread-safe for concurrent access.

## Non-Functional Requirements
Low latency reads/writes.
Memory-efficient cleanup of expired keys.
Scalable to a large number of entries.


## Code has following : 
TTL and expiration logic

Thread-safe concurrent data access

Background cleanup using DelayQueue

Lightweight, scalable in-memory store


## What is a DelayQueue?
DelayQueue is a special queue in Java’s java.util.concurrent package. It holds elements that become available for processing only after a certain delay has expired.

Think of it like a priority queue where items are ordered by their delay and only become accessible when their delay has passed.

## Use Case of DelayQueue
It's perfect when you want to:

Automatically remove or process items after a fixed delay.

Schedule tasks, timeouts, or TTL-like expiration events.

Design caches with expiry, retry mechanisms, or rate limiters.

## How DelayQueue Works
It holds elements that implement the Delayed interface.

You cannot just poll() and get an element unless its delay has expired.

Internally, it uses a priority queue sorted by delay time (i.e., earliest deadline first).

```
DelayQueue<Delayed> queue = new DelayQueue<>();
queue.put(new MyDelayed("A", 5)); // 5 seconds
queue.put(new MyDelayed("B", 2)); // 2 seconds

queue.take(); // will block until "B" is ready (after 2 seconds)

```
## What is the Delayed Interface?
Delayed is an interface that a class must implement to be used in a DelayQueue.

It has two methods:
```
long getDelay(TimeUnit unit);
int compareTo(Delayed other);

```
getDelay() returns how much time is left before the item is ready.
compareTo() ensures items are ordered by delay time.

note: we are comparing the expire time of key and value when the thread is doing the clean up this is because we want to make sure we are not deleting the key,value from the cache whose expire time is updated later on

## Why Compare expiryTime?
Because of race conditions with updates. Let’s break it down:

Scenario:

You call put("foo", "bar", 5) → creates ExpiringKey with expiryTime = t1 + 5s.

That ExpiringKey is added to delayQueue.

Before expireKeys() removes it:

You call put("foo", "baz", 10) → updates the store with new expiryTime = t2 + 10s.

A new ExpiringKey is also added to the queue.

Now:

The first ExpiringKey expires and is pulled from the queue.

But the store now has a new value with a new expiryTime.

Without the check:
```
if (wrapper.expiryTime == expiringKey.expiryTime)
```
you would incorrectly delete the updated value.

