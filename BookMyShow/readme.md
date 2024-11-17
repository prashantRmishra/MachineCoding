### `README.md` 

# Ticket Booking System

## Overview

The Ticket Booking System is a Java-based application that facilitates booking tickets for events like movies or shows. It supports features such as searching events, reserving tickets, processing payments, and managing reservations. This project demonstrates clean coding practices, design patterns, and adherence to the SOLID principles.

## Class Diagram

![class-diagram](image.png)
---

## Features
- **Event Search**: Users can search for events by name or location.
- **Reservation Management**: Handles reservation and un-reservation of tickets.
- **Payment Processing**: Supports multiple payment methods like UPI and Credit Card.
- **Factory**: Simplifies the creation of mock data for testing.

---

## Key Components

### 1. **Classes and Responsibilities**
- **User**: Represents a system user.
- **Artist**: A specialized `User` representing an event performer.
- **Event**: Contains details of the event, such as the artist, venue, and available tickets.
- **Location**: Represents the geographical location of a venue.
- **Ticket**: Represents an individual ticket with attributes like status, price, and associated user.
- **Venue**: Contains details about the event location.
- **ReservationManager**: Handles ticket reservation and un-reservation.
- **PaymentProcessor**: Coordinates payment methods to process transactions.
- **Catalog**: Facilitates searching for events by name or location.
- **Factory**: Provides mock data for events, users, venues, and tickets.

### 2. **Main Flow**:
1. Initialize the system with predefined users and events using the `Factory`.
2. Allow users to search for events.
3. Reserve tickets for selected events.
4. Process payments for reservations.
5. Un-reserve tickets if payment fails.

---

## Design Patterns Used

### 1. **Factory Pattern**
- **Class**: `Factory`
- **Usage**: Simplifies the creation of predefined objects (users, events, tickets, etc.) for testing and initialization.
- **Benefit**: Decouples object creation from the main application logic.

### 2. **Strategy Pattern**
- **Class**: `PaymentProcessor`
- **Usage**: Implements different payment strategies (`UPIPayment`, `CreditPayment`) using composition.
- **Benefit**: Allows adding new payment methods without modifying existing code, adhering to the Open/Closed Principle.

### 3. **Template Method Pattern**
- **Class**: `Payment`
- **Usage**: Defines the common structure for payment processing, allowing subclasses to implement specific details.
- **Benefit**: Promotes code reuse and enforces a consistent payment process.

---

## SOLID Principles in Use

### 1. **Single Responsibility Principle (SRP)**
- Each class has a single responsibility. For instance:
  - `Catalog` handles event searches.
  - `ReservationManager` manages ticket reservations.
  - `PaymentProcessor` processes payments.

### 2. **Open/Closed Principle (OCP)**
- The `PaymentProcessor` class can accommodate new payment methods without changes, thanks to its use of the `Payment` abstraction.

### 3. **Liskov Substitution Principle (LSP)**
- `Artist` is a subclass of `User` and can replace `User` in all contexts without altering the functionality.

### 4. **Interface Segregation Principle (ISP)**
- Applied implicitly. Classes like `PaymentProcessor` and `ReservationManager` interact with specific components without being overloaded with unrelated methods.

### 5. **Dependency Inversion Principle (DIP)**
- High-level modules like `BookingSystem` depend on abstractions (`Payment`, `ReservationManager`) rather than concrete implementations.

---

## Additional Observations

1. **Thread Safety**:
   - `ReservationManager.reserveTicket()` is synchronized to ensure thread safety during ticket reservations.

2. **Enum Usage**:
   - The `TicketStatus` enum defines clear states (`available`, `reserved`, `booked`) for tickets.

3. **Immutability**:
   - Some classes could benefit from immutability (e.g., `Location`, `Ticket`) to prevent unintended state changes.

---

## Future Enhancements

- **Error Handling**: Add comprehensive exception handling for edge cases (e.g., ticket unavailability, invalid payment details).
- **Validation**: Incorporate validations for user inputs and event selections.
- **Scalability**: Introduce database integration for handling large-scale data efficiently.
- **User Interface**: Develop a front-end or CLI interface for better usability.

---

## How to Run
1. Clone the repository.
2. Compile the Java files using `javac`.
3. Run the `Driver` class to execute the application flow.

---

[High Level Design of BookMyShow/TicketMaster](https://github.com/prashantRmishra/System-design/blob/main/ticket-master/Readme.md)
