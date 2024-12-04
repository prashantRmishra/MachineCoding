# MultiLevelCache System

## Overview

The **MultiLevelCache** system simulates a multi-level caching mechanism, offering optimized read and write operations across multiple levels of cache. Each level of cache is modeled with unique read/write times, capacity, and an LRU (Least Recently Used) eviction policy.

This implementation is designed to demonstrate a basic cache system with multi-levels of hierarchy, where higher-level caches are faster but smaller in capacity, and lower-level caches are slower but larger in capacity.

---

## Features

1. **Multi-Level Cache Hierarchy**  
   Supports multiple levels of cache with configurable capacities and read/write times.

2. **LRU Eviction Policy**  
   Automatically evicts the least recently used entry when a cache reaches its capacity.

3. **Synchronized Access**  
   Ensures thread-safe operations using locks for read and write access.

4. **Read and Write Propagation**  
   - Read: Updates the data in higher-priority (upper-level) caches for faster subsequent access.
   - Write: Writes to all levels of the cache to maintain data consistency.

5. **Usage and Performance Metrics**  
   - Reports cache usage as a percentage of capacity.
   - Tracks average read/write times for the last few operations.

---

## Classes and Interfaces

### 1. `Cache` (Interface)  
Defines the contract for all cache levels:  
- **Methods:**  
  - `String get(String key)`  
  - `void put(String key, String value)`  
  - `int getLevel()`  
  - `int getCapacity()`  
  - `int getUsage()`  
  - `int getReadTime()`  
  - `int getWriteTime()`

---

### 2. `LevelCache` (Implementation of `Cache`)  
Represents an individual cache level.  
- **Key Features:**  
  - Configurable capacity, read time, and write time.  
  - Synchronized read and write access with `ReentrantLock`.  
  - Implements LRU replacement policy using `LinkedHashMap`.

---

### 3. `CacheManager`  
Manages operations across all cache levels.  
- **Key Features:**  
  - Handles multi-level read and write operations.  
  - Maintains metrics for read/write times and calculates averages.  
  - Propagates updates to appropriate cache levels.

---

### 4. `CacheConfig`  
Provides configuration constants.  
- **Example:**  
  - `AVERAGE`: Number of operations to consider for average time calculations.

---

### 5. `Driver`  
The entry point of the application. Demonstrates the usage of the MultiLevelCache system with sample operations.

---

## Code Example

### Driver Code
```java
public class Driver {
    public static void main(String[] args) {
        // Define three levels of cache with different configurations
        Cache cache1 = new LevelCache(1, 2, 2, 2);
        Cache cache2 = new LevelCache(2, 3, 3, 3);
        Cache cache3 = new LevelCache(3, 4, 4, 4);

        // Initialize the CacheManager with all levels
        CacheManager manager = new CacheManager(List.of(cache1, cache2, cache3));

        // Perform write operations
        manager.write("key1", "value1");
        manager.write("key2", "value2");

        // Perform read operations
        manager.read("key1"); // Should retrieve the value from the cache
        manager.read("key3"); // Should report key not found

        // Add another entry
        manager.write("key3", "value3");

        // Get performance and usage statistics
        manager.getStats();
    }
}
```

---

## Outputs

### Example Output
```
total write time 7
total write time 7
found: value1, total read time 2
key not found, total read time 9
total write time 9
Usage:
C1: 1.0
C2: 1.0
C3: 0.75
Cache(s) Read/Write average:
Read: (average of last 5) 5.5
Write: (average of last 5) 7.0
```

---

## How It Works

1. **Write Operation:**  
   - Writes the key-value pair to all cache levels sequentially.  
   - Stops early if the same key-value pair is already present.

2. **Read Operation:**  
   - Searches for the key across all cache levels, starting from the highest level.  
   - Updates the data in higher-priority caches to optimize future access.

3. **Statistics:**  
   - Tracks the cache usage for each level as a fraction of its capacity.  
   - Calculates the average read/write time for the last few operations.

---

## Configuration

### Modify Cache Levels
You can modify cache configurations by adjusting the parameters in `Driver`:
```java
Cache cache1 = new LevelCache(1, 2, 2, 2);
Cache cache2 = new LevelCache(2, 3, 3, 3);
Cache cache3 = new LevelCache(3, 4, 4, 4);
```
- **Parameters:**  
  - Level Number  
  - Capacity  
  - Read Time  
  - Write Time

---

## Dependencies

- Java 8+  
- No external libraries are required.

---

## Improvements & Extensions

1. **Persistence:**  
   Add functionality to persist data to disk for lower cache levels.

2. **Eviction Policies:**  
   Extend support for other eviction policies, such as FIFO or LFU.

3. **Concurrency:**  
   Enhance multi-threading support for simultaneous read/write operations.

4. **Monitoring Tools:**  
   Integrate monitoring dashboards to visualize cache metrics in real-time.

---

## License
This implementation is open-source and available for educational purposes.

[reference](https://leetcode.com/discuss/interview-experience/6029482/phonepe-software-engineer-pune-accepted)