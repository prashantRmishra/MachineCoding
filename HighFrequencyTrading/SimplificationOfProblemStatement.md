
### ✅ What You’re Being Asked to Design

You are to **design a system that simulates a basic high-speed trading system**, where:

1. **Market data** (like stock prices) keeps coming in.
2. **Trading strategies** (think: small programs that make decisions) read this data and **make trade orders** (buy/sell).
3. The system **places orders** and **keeps track of them**.
4. It also **checks for risk** (like not letting a strategy buy more than a certain amount).

---

### 🎯 Your Goal in LLD

Design the **classes, interfaces, and flow** that will allow this system to:

* Accept **incoming market data**
* Notify **strategy modules** about it
* Let those modules **place/cancel/modify orders**
* Let the system **track the state of all orders**
* Use a **risk checker** to block unsafe trades

---

### 💡 Breakdown of What Needs to Be Done

#### 1. **MarketDataFeed**

* A class that keeps receiving price updates.
* It pushes these updates to all registered strategies.

#### 2. **TradingStrategy (Interface + Implementations)**

* Interface with a method like `onMarketData(MarketData data)`
* Each strategy reads market data and decides whether to buy/sell.

#### 3. **OrderManager**

* Accepts new orders, modifies or cancels them.
* Tracks current status of all orders: `NEW`, `CANCELLED`, `FILLED`, etc.

#### 4. **RiskManager**

* Checks if an order is allowed.
* Rejects orders violating limits (e.g., position too big, exposure too high).

#### 5. **TradeEngine**

* Ties everything together.
* Receives market data → notifies strategies → gets their orders → sends to OrderManager.

---

### 🧱 Example Classes You Might Define

```java
interface TradingStrategy {
    void onMarketData(MarketData data);
}

class MarketData {
    String symbol;
    double price;
}

class Order {
    String orderId;
    String symbol;
    int quantity;
    double price;
    OrderStatus status;
}

enum OrderStatus { NEW, FILLED, CANCELLED }

class OrderManager {
    void placeOrder(Order order) { ... }
    void cancelOrder(String orderId) { ... }
    void modifyOrder(Order updatedOrder) { ... }
}

class RiskManager {
    boolean isOrderAllowed(Order order) { ... }
}
```

---

### 🔁 Sample Flow

1. `MarketDataFeed` receives: Apple stock is now ₹150.
2. It calls all strategies' `onMarketData(data)` methods.
3. One strategy says “buy 10 shares of Apple at ₹150” → sends `Order` to `OrderManager`.
4. `OrderManager` asks `RiskManager`: Is this allowed?
5. If yes, `OrderManager` processes the order.

---


```
Classes:
MarketData(symbol, price)
Order(orderId, symbol, price, qty,orderStatus);
OrderStatus(new_order,patially_filled_order,filled_order,cancelled_order);
RiskManager: "to accept or reject an order"
OrderManger: "for placing, updating, cancelling an order" has RiskManager
TradingStrategy: "Strategies for execution the order like buyOnLow and sellOnHigh(like short selling), they are reigstered with MarketDataHandler(publisher)
MarketDataHandler "for handeling new market data coming in every few seconds and notifying all the listeners or subscribers (TradingStrategies)
HighFrequencyTradingApp: "for simulating live trade data processing like buy/sell by various strategies"
```

