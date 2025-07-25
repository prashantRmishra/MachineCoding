# Low level design of High Frequency Trading application

### 📝 **Problem Statement: Low-Level Design of a High-Frequency Trading System**

Design a simplified low-level component of a **High-Frequency Trading (HFT) System** that allows multiple trading strategies to operate on real-time market data and execute trades with minimal latency.

#### 💼 Functional Requirements:

1. **Market Data Ingestion**:

   * The system should consume real-time market data (e.g., price updates, order book changes) from multiple exchanges.
   * Market data can arrive with extremely high throughput and low latency.

2. **Order Management**:

   * Support placing, modifying, and canceling buy/sell orders.
   * Each order must be tracked with a unique identifier and status (e.g., NEW, FILLED, CANCELLED, PARTIALLY\_FILLED).

3. **Trading Strategy Interface**:

   * Allow multiple strategy modules to subscribe to market data.
   * Strategies should be able to react to market events and place trade orders based on their logic.
   * Strategies should be hot-pluggable (i.e., able to be added or removed without system downtime).

4. **Risk Management**:

   * Implement a basic risk management module to:

     * Reject trades that violate limits (e.g., position size, order value).
     * Maintain P\&L and position tracking per strategy.

5. **Performance Constraints**:

   * The system must process market data and send trade orders with **extremely low latency** (microseconds to milliseconds).
   * Thread safety and concurrency are critical due to simultaneous updates and actions from multiple sources.

#### 🔒 Non-Functional Requirements:

* High throughput and low latency under heavy load.
* Thread-safe data structures and efficient concurrency management.
* Scalable to support thousands of trades per second.
* Modular design to allow easy addition of new strategies and risk checks.

#### 📦 Optional Extensions (for further exploration):

* Persistent order and trade logs.
* Simulated backtest mode using historical market data.
* Real-time metrics (e.g., latency tracking, trade volume).
