# Online Food Ordering System

## Overview
The Online Food Ordering System allows customers to place orders from multiple restaurants while managing restaurant capacities and order fulfillment efficiently. The system focuses on modularity, scalability, and testability, ensuring future extensibility with minimal changes.

---

## Features

1. **Restaurant Menu Management**
   - Restaurants can onboard with their menus and item processing capacities.
   - Menus include item names and their respective prices.

2. **Capacity Management**
   - Each restaurant has a maximum processing capacity for items at a given time.
   - Orders exceeding this capacity are not accepted until ongoing items are fulfilled.

3. **Order Fulfillment**
   - All items in an order must be fulfilled by one or more restaurants.
   - Restaurants notify the system when items are fulfilled, freeing up processing capacity.

4. **Order Placement and Strategies**
   - Customers can place orders with multiple items and quantities.
   - Orders are assigned based on a selection strategy (e.g., lowest price).

5. **Order Tracking**
   - Track items served by each restaurant.
   - Mark orders as delivered, updating restaurant capacities accordingly.

6. **In-Memory Storage**
   - No external databases or NoSQL stores are used; the system operates on an in-memory store.

7. **Driver Class for Demonstration**
   - A driver class executes commands for demo purposes and test cases.

---

## Requirements

1. **Core Functionalities**
   - Onboard new restaurants with their menu and processing capacities.
   - Update restaurant menus.
   - Place orders from customers.
   - Implement at least one restaurant selection strategy.
   - Track served items and restaurant details.

2. **Additional Features**
   - Mark orders as delivered.
   - Add new restaurants and change processing capacities.

3. **Constraints**
   - Handle concurrency and scalability wherever applicable.
   - Maintain separation of concerns and ensure code readability.

4. **Extensions (Optional)**
   - Add delivery cost and estimated delivery times.
   - Customize menu options (e.g., add toppings or special preparations).
   - Allow order cancellations.

---

## Test Cases

### Example Commands and Outputs:

1. **Adding Restaurants**
   ```python
   add_restaurant('resta1', {
       'king_burger': 10,
       'samosa_pizza': 20,
       'alu_pasta': 19
   }, 15)

   add_restaurant('resta2', {
       'bendi_macaroni': 12,
       'samosa_pizza': 25,
       'alu_pasta': 17
   }, 12)
   ```

2. **Updating Menus**
   ```python
   update_menu('resta1', {
       'bendi_macaroni': 8,
       'king_burger': 15
   })
   ```

3. **Printing Restaurant Details**
   ```json
   [
       {
           "name": "resta1",
           "menu": {
               "king_burger": 15,
               "samosa_pizza": 20,
               "alu_pasta": 19,
               "bendi_macaroni": 8
           },
           "total_capacity": 15,
           "capacity_in_use": 0
       },
       {
           "name": "resta2",
           "menu": {
               "bendi_macaroni": 12,
               "samosa_pizza": 25,
               "alu_pasta": 17
           },
           "total_capacity": 12,
           "capacity_in_use": 0
       }
   ]
   ```

4. **Placing Orders**
   ```python
   book('cust1', {
       'bendi_macaroni': 3,
       'samosa_pizza': 2
   })
   # Output: resta1, order1
   ```

5. **Tracking Orders**
   ```json
   [
       {
           "order_id": "order1",
           "user": "cust1",
           "order_details": [
               {
                   "restaurant": "resta1",
                   "items": {
                       "bendi_macaroni": 3,
                       "samosa_pizza": 2
                   },
                   "cost": 64
               }
           ]
       }
   ]
   ```

6. **Marking Orders as Delivered**
   ```python
   mark_as_delivered('order1')
   ```

---

## Expectations

- Functional correctness with executable code.
- Modular, readable, and testable code.
- Unit test coverage for all core functionalities.
- Concurrency handling where applicable.
- Support for easy accommodation of new requirements.

---

## Good-to-Have Features

1. Delivery cost estimation.
2. Support for menu customization.
3. Cancellation and refund mechanisms.
4. Scalability enhancements for high traffic.

---

## How to Run the System
1. Onboard five restaurants, each with three items and a maximum capacity of four items.
2. Use the driver class to execute demo commands and test cases.
3. Verify outputs and behavior against expected results.
4. Extend functionality as needed using modular code structure.

---



![class Diagram](image.png)


---

## Implementation

### Modules

#### 1. Models
- **Item**: Represents an individual menu item.
- **Menu**: Manages a collection of items for a restaurant.
- **Restaurant**: Stores restaurant details, menu, and processing capacity.
- **OnlineUser**: Represents customers placing orders.
- **Order**: Tracks order details, including status and delivery.
- **ItemOrder**: Represents individual items in an order.

#### 2. Strategies
- **Restaurant Selection Strategy**:
  - A strategy interface and implementation (e.g., lowest price strategy).
  - Identifies suitable restaurants for fulfilling an order.

#### 3. Core Management
- **Order Management**:
  - Handles order creation, restaurant selection, and capacity updates.
  - Tracks orders and their statuses.

### Code Structure
- **Models**:
  - `Item`: Represents menu items.
  - `Menu`: Manages the list of items.
  - `Restaurant`: Represents a restaurant with menu and capacity details.
  - `OnlineUser`: Represents a customer.
  - `Order`: Tracks order details and status.
- **Strategy**:
  - `Strategy`: Interface for restaurant selection strategies.
  - `RestaurantPriceStrategy`: Implementation of the lowest price strategy.
- **Order Management**:
  - Handles restaurant selection, order placement, and capacity updates.

---

## Execution

1. **Driver Class**:
   - Demonstrates adding restaurants, updating menus, placing orders, and marking orders as delivered.
   - Example commands include:
     ```java
     add_restaurant("resta1", {"king_burger": 10, "samosa_pizza": 20, "alu_pasta": 19}, 15);
     book("cust1", {"bendi_macaroni": 3, "samosa_pizza": 2});
     mark_as_delivered("order1");
     print_all_restaurants();
     print_all_orders();
     ```

2. **Concurrency**:
   - Capacity updates are thread-safe.
   - Restaurants notify the system asynchronously when orders are completed.

3. **Testing**:
   - Unit tests cover core functionalities, including:
     - Adding restaurants.
     - Updating menus.
     - Placing orders.
     - Verifying restaurant capacities.

---



---

## Design Principles

- **Modularity**: Each functionality is encapsulated in its own module.
- **Scalability**: Designed to handle multiple restaurants and concurrent orders.
- **Separation of Concerns**: Clear distinction between models, strategies, and core logic.
- **Extensibility**: Supports additional features like delivery costs and customizable menus with minimal changes.
- **Thread Safety**: Concurrency is handled for capacity updates.

---

## Future Enhancements

1. **Database Integration**: Replace the in-memory store with a relational or NoSQL database.
2. **UI Development**: Add a web or mobile interface for user interactions.
3. **Advanced Strategies**: Implement strategies based on delivery time or customer ratings.
4. **Analytics**: Generate insights on restaurant performance and order trends.

---

## Conclusion

The Online Food Ordering System is a robust and modular solution for managing restaurant orders. It efficiently handles restaurant selection, order placement, and capacity management, while remaining extensible for future requirements.



Output of the code written

```
Restaurant [restaurantId=23223, name=restaurant2, maxItemProcessingCapacity=12, menu=Menu [items=[Item [itemName=bhindi macroni, itemPrice=12.0], Item [itemName=samosa pizza, itemPrice=25.0], Item [itemName=aloo pasta, itemPrice=17.0]]]] 

Restaurant [restaurantId=11123, name=restaurant1, maxItemProcessingCapacity=15, menu=Menu [items=[Item [itemName=king burger, itemPrice=10.0], Item [itemName=samosa pizza, itemPrice=20.0], Item [itemName=aloo pasta, itemPrice=19.0]]]] 

[Order [orderId=2, user=prashant, restaurant=restaurant1, item=samosa pizza, quantity=2, price=40.0, isDelivered=false, status=in_progress], Order [orderId=3, user=prashant, restaurant=restaurant2, item=bhindi macroni, quantity=3, price=36.0, isDelivered=false, status=in_progress]]
Restaurant [restaurantId=23223, name=restaurant2, maxItemProcessingCapacity=9, menu=Menu [items=[Item [itemName=bhindi macroni, itemPrice=12.0], Item [itemName=samosa pizza, itemPrice=25.0], Item [itemName=aloo pasta, itemPrice=17.0]]]] 

Restaurant [restaurantId=11123, name=restaurant1, maxItemProcessingCapacity=13, menu=Menu [items=[Item [itemName=king burger, itemPrice=10.0], Item [itemName=samosa pizza, itemPrice=20.0], Item [itemName=aloo pasta, itemPrice=19.0]]]] 

Restaurant [restaurantId=23223, name=restaurant2, maxItemProcessingCapacity=12, menu=Menu [items=[Item [itemName=bhindi macroni, itemPrice=12.0], Item [itemName=samosa pizza, itemPrice=25.0], Item [itemName=aloo pasta, itemPrice=17.0]]]] 

Restaurant [restaurantId=11123, name=restaurant1, maxItemProcessingCapacity=15, menu=Menu [items=[Item [itemName=king burger, itemPrice=10.0], Item [itemName=samosa pizza, itemPrice=20.0], Item [itemName=aloo pasta, itemPrice=19.0]]]] 

```