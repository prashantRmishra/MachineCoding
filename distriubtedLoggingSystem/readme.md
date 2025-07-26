# Distributed Logging System

A simple distributed logging system implemented in Java. This project demonstrates the use of design patterns (Chain of Responsibility, Strategy) to handle and persist log messages of different severity levels, with flexible search capabilities.

## Features

- **Multiple Log Levels:** Supports FATAL, ERROR, DEBUG, WARN, INFO.
- **Chain of Responsibility:** Loggers are chained to handle messages based on severity.
- **Centralized Persistence:** All logs are stored in a thread-safe central repository.
- **Flexible Search:** Search logs by level or by keyword using interchangeable strategies.
- **Concurrent Logging:** Log generation and searching run in separate threads.

## How It Works

- Log messages are generated randomly and passed through a chain of loggers.
- Each logger persists logs of its level to the central repository.
- The system can search logs by level or keyword, switching strategies at runtime.

## How to Run

1. Compile the Java file:
   ```sh
   javac DistributedLoggingSystem.java
   ```
2. Run the program:
   ```sh
   java DistributedLoggingSystem
   ```

## Example Output

```
[]
[]
FATAL: this is fatal log
WARN: this is warning log
DEBUG: this is debug
INFO: this is info log
[Log [msg=this is fatal log, level=0, timestamp=...]]
[Log [msg=this is debug, level=2, timestamp=...]]
...
```

## Design Patterns Used

- **Chain of Responsibility:** For passing log messages through different loggers.
- **Strategy:** For switching between search by level and search by keyword.

## File Structure

- `DistributedLoggingSystem.java` - Main source code.

## Author

-