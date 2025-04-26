
# LLD of a Distributed Cache System

[Same as InMemory Key value store](../InMemoryKeyValueStore/Readme.md)

You are tasked with designing a **distributed, highly available cache** similar to **Redis**, **Memcached**, or an internal caching layer at a large company (e.g., Amazon, Netflix).

The system should cache **key-value pairs** in memory, with the ability to **scale horizontally** across multiple machines (nodes).

---

# Requirements:

### 1. Basic Functionalities:
put(k,v),get(k),remove(k)

### 2. Expiry / TTL (Time to Live):
- Allow setting an optional **expiration time** when storing a key.
- Expired keys should not be returned.

### 3. Scalability:
- The cache must support **multiple servers (nodes)**.
- Keys should be **evenly distributed across nodes** (this is achieved via module operation to get the server no.)


### 6. Memory Management (Optional not done in this code):
- If memory is full, you can implement **eviction policies** like **LRU** (Least Recently Used) or **LFU** (Least Frequently Used).

---

# Scope and Assumptions:

- Assume **single data center** (no cross-region replication yet).
- Assume **in-memory only** (no persistence to disk for now).
- Simple network simulation between nodes (no real networking needed, just function calls across node objects).
- **Single-threaded** per node (no thread safety required unless mentioned).
- For distributed part, **simulate nodes** as independent instances inside a Java/Python program.
  
---


# Extensions (if you complete early):
- Add **replication** (write to two nodes for redundancy).
- Add **LRU eviction** per node if memory is limited.
- Real networking using TCP sockets or gRPC.
- Add support for **batch operations** (e.g., `mget`, `mset`).

---

# Bonus Tips (not done in this code):
- Use **consistent hashing** to map keys to nodes.
- Design clean interfaces: `Node`, `DistributedCache`, `Cache`, `EvictionPolicy`.
- Unit test basic flows: put → get → expire → delete.

---

---
# Summary of Deliverables:
- `put`, `get`, `remove`
- TTL (expiry)
- Multiple nodes simulation (via module operation)

# Sample output of the code

```output
Putting keys with TTL...
Immediately fetching keys:
apple -> red
banana -> yellow
grape -> purple

Sleeping for 3 seconds...

Server 2 expired key: apple
Fetching after 3 seconds:
apple -> null
banana -> yellow
grape -> purple

Sleeping for 3 more seconds...

Server 1 expired key: banana
Fetching after total 6 seconds:
banana -> null
grape -> purple

Sleeping for 5 more seconds...

Server 2 expired key: grape
Fetching after total 11 seconds:
grape -> null
```