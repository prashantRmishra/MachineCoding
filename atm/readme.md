### **README: ATM Low Level System Design**

---

## **Overview**

This project is a simplified **ATM System** designed using object-oriented principles in Java. It provides a modular and extensible structure, focusing on the core functionalities of an ATM, such as user authentication, balance inquiries, cash withdrawals, and error handling.

---

## **Features**

1. **Authentication**:
   - Validates card details and PIN.
   - Checks for card expiration.
   - Throws appropriate errors for invalid cards or accounts.

2. **Account Management**:
   - Stores user account details, including balance, card information, and personal details.
   - Supports efficient account lookups using `ConcurrentHashMap`.

3. **Transactions**:
   - Balance inquiry.
   - Cash withdrawal with sufficient balance checks.
   - Updates balances in a thread-safe manner.

4. **Display System**:
   - Uses a centralized `ATMMessage` enum for maintaining and displaying user-friendly messages.
   - Logs key messages for debugging and traceability.

5. **Cash Dispenser**:
   - Simulates dispensing cash for successful transactions.

---

## Class Diagram

![classDiagram](image.png)

## **Classes**

### 1. **Core Classes**
- **`ATMSystem`**:
  - Manages the core operations of the ATM, including authentication and transaction processing.
- **`TransactionManager`**:
  - Handles account lookups, balance checks, and balance updates in a thread-safe way.
- **`AuthenticationManager`**:
  - Validates card expiration and authenticates users against their accounts.

---

### 2. **Model Classes**
- **`Account`**:
  - Represents a user account, including ID, name, address, balance, and associated card.
- **`Card`**:
  - Represents a user's ATM card, including card number, name, and expiry date.
- **`Address`**:
  - Placeholder for user address details.
  
---

### 3. **Utility Classes**
- **`Display`**:
  - Manages ATM screen output, displaying messages and options for user interaction.
- **`CashDispenser`**:
  - Simulates dispensing cash to the user.
- **`TransactionFactory`**:
  - Generates a `TransactionManager` with sample accounts for testing and demonstration.
- **`Encryption`**:
  - Placeholder for encrypting sensitive information like PINs.

---

### 4. **Enums**
- **`ATMMessage`**:
  - Contains pre-defined user-friendly messages for display. Ensures consistent messaging throughout the application.

---

## **How to Run**

1. **Setup**:
   - Ensure you have **Java 8 or higher** installed.
   - Clone the repository or copy the source files into your IDE.

2. **Compile and Run**:
   - Compile all the `.java` files.
   - Run the `ATMSystem` class to simulate the ATM operations.

3. **Simulated Workflow**:
   - Insert a card and authenticate.
   - Choose from available options (e.g., balance inquiry, cash withdrawal).
   - Observe logs and display messages for feedback.

---

## **Sample Data**

Three sample accounts are pre-configured in the system (via `TransactionFactory`):

| Account ID | Name      | Balance | Card No | Expiry Date |
|------------|-----------|---------|---------|-------------|
| 1          | Prashant  | 100.0   | 1       | 12/11/2029  |
| 2          | Sandeep   | 200.0   | 2       | 12/11/2029  |
| 3          | Ajay      | 300.0   | 3       | 12/11/2029  |

---

## **Future Enhancements**

1. **PIN Validation**:
   - Add encrypted PIN storage and matching logic.

2. **Transaction Logs**:
   - Maintain a log of transactions for audit purposes.

3. **Multi-Threaded Simulations**:
   - Test the system under concurrent user operations.

4. **Integration with Databases**:
   - Replace in-memory storage with persistent databases for account data.

5. **UI/UX Improvements**:
   - Add a graphical user interface for enhanced user interaction.

---

## **Design Principles Followed**

1. **Single Responsibility Principle (SRP)**:
   - Each class has a well-defined responsibility (e.g., `AuthenticationManager` only handles authentication).

2. **Open-Closed Principle (OCP)**:
   - The system is easily extensible with new features (e.g., adding new transaction types).

3. **Encapsulation**:
   - All sensitive data is hidden behind getters/setters.

4. **Thread Safety**:
   - Balance updates and account lookups are synchronized for safe multi-threaded access.

---

This project is a demonstration of **low-level design (LLD)** skills and object-oriented programming (OOP) concepts.