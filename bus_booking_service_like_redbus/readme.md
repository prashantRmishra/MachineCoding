# Bus Booking System

## Overview

This project is a simplified **Bus Booking System** inspired by platforms like RedBus. It is designed using object-oriented principles in Java and provides a modular and extensible structure, focusing on core functionalities such as searching for buses, booking tickets, and managing user accounts.

## Features

1. **Bus Search**:
   - Search for buses based on source, destination, and travel date.
   - Handles cases where no buses are found.

2. **Booking Management**:
   - Book tickets for available buses.
   - Ensure thread safety during the booking process using `ReentrantLock`.

3. **User Management**:
   - Manage user accounts and their bookings.
   - Store user details securely.

4. **Exception Handling**:
   - Custom exceptions for various error scenarios (e.g., `NoBusFoundException`).

5. **Logging**:
   - Use of `Logger` utility for logging important actions and exceptions.

## Class Diagram

![classDiagram](image.png)

## Classes

### Core Classes
- `BusBookingSystem`: Main class to run the bus booking operations.
- `SearchService`: Handles searching for buses.
- `BookingService`: Manages the booking of tickets.

### Model Classes
- `Bus`: Represents a bus with details like travel date, source, destination, etc.
- `User`: Represents a user with details like name, email, and bookings.

### Utility Classes
- `Logger`: Utility for logging messages.

### Enums
- `SourceDestination`: Enum for source and destination locations.

## How to Run

1. **Setup**:
   - Ensure you have **Java 8 or higher** installed.
   - Clone the repository or copy the source files into your IDE.

2. **Compile and Run**:
   - Compile all the `.java` files.
   - Run the `BusBookingSystem` class to simulate the bus booking operations.

3. **Simulated Workflow**:
   - Search for buses based on source, destination, and travel date.
   - Book tickets for available buses.
   - Observe logs and display messages for feedback.

## Sample Data

Three sample buses are pre-configured in the system:

| Bus ID | Source | Destination | Travel Date |
|--------|--------|-------------|-------------|
| 1      | CityA  | CityB       | 2023-12-01  |
| 2      | CityA  | CityC       | 2023-12-01  |
| 3      | CityB  | CityC       | 2023-12-02  |

## Future Enhancements

1. **Payment Integration**:
   - Add payment gateway integration for booking tickets.

2. **User Authentication**:
   - Implement user authentication and authorization.

3. **Database Integration**:
   - Replace in-memory storage with persistent databases for bus and user data.

4. **UI/UX Improvements**:
   - Add a graphical user interface for enhanced user interaction.

5. **Notification System**:
   - Implement email/SMS notifications for booking confirmations and reminders.

## Design Principles Followed

1. **Single Responsibility Principle (SRP)**:
   - Each class has a well-defined responsibility (e.g., `SearchService` only handles bus searches).

2. **Open-Closed Principle (OCP)**:
   - The system is easily extensible with new features (e.g., adding new search filters).

3. **Encapsulation**:
   - All sensitive data is hidden behind getters/setters.

4. **Thread Safety**:
   - Booking operations are synchronized for safe multi-threaded access.

---

This project is a demonstration of **low-level design (LLD)** skills and object-oriented programming (OOP) concepts.