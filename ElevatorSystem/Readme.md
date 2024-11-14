

### Elevator System Overview

The elevator system consists of the following entities:
- **Building**
- **Floor**
- **Elevator Cars** (Each elevator has attributes like ID, current floor, direction, status, display, internal buttons, and door).
- **User** (an actor, not an object)

### Requirements
1. **Dispatch Algorithm**: Determines which elevator should respond when a button is pressed.
   - Options:
     - **Fixed Floor Range**: Each elevator is assigned to a range of floors (e.g., Lift 1 for floors 1-3, Lift 2 for floors 4-6).
     - **Odd-Even Split**: Lift 1 serves only odd floors, and Lift 2 serves even floors.
     - **Minimum Seek Time**: The elevator with the minimum seek time (fastest to reach) serves the request.

2. **Button Actions**:
   - **External Button** (on each floor): Determines which elevator to dispatch based on the algorithm.
   - **Internal Button** (inside each elevator car): Sends a floor request directly to the elevator controller.

---

### Entities

#### 1. **Building**
   - Attributes: List of **Floors**

#### 2. **Floor**
   - Attributes:
     - **floorId**: Unique ID for each floor
     - **externalButton**: External button (up/down) to request an elevator.

#### 3. **ElevatorCar**
   - Attributes:
     - **int id**: Unique ID for the elevator car
     - **int currentFloor**: Current floor of the elevator
     - **Display display**: Shows the current floor and direction
     - **Direction direction** (enum: `UP`, `DOWN`): Current direction of travel
     - **Status status** (enum: `MOVING`, `IDLE`): Current status of the elevator
     - **InternalButton internalButton**: Button panel inside the car to select floors
     - **Door door**: Represents the elevator door
   - **Methods**:
     - `move(int destinationFloor, Direction direction)`: Moves the elevator to the specified floor.

#### 4. **Display**
   - Attributes:
     - **int floor**: Current floor displayed
     - **Direction direction**: Direction displayed

#### 5. **Direction (Enum)**
   - Values: `UP`, `DOWN`

#### 6. **Status (Enum)**
   - Values: `MOVING`, `IDLE`

#### 7. **InternalButton**
   - Methods:
     - `pressButton(int floorNumber)`: Sends a request to the elevator controller via the dispatcher.

#### 8. **ElevatorController**
   - Controls an **ElevatorCar**.
   - Attributes: Reference to the **ElevatorCar**
   - Methods:
     - `acceptNewRequest(int floor, Direction direction)`: Accepts and processes new requests.
     - `controlElevatorCar()`: Handles the elevator's movement based on queued requests.

---

### Request Handling

There are two sources of requests:
- **External Button**: Located on each floor.
- **Internal Button**: Located inside each elevator car.

---

### Dispatcher Components

To maintain low coupling, dispatchers will handle the communication between buttons and controllers.

#### **InternalButtonDispatcher**
   - Purpose: Routes internal button requests from **InternalButton** to the appropriate **ElevatorController**.
   - Attributes:
     - `List<ElevatorController> controllers`: List of elevator controllers
   - Methods:
     - `submitRequest(ElevatorCarId)`: Routes the request to the appropriate elevator controller.

#### **ExternalButtonDispatcher**
   - Purpose: Routes external button requests from **ExternalButton** on each floor to the appropriate elevator based on the dispatch algorithm (Odd-Even, Fixed Floor Range, or Minimum Seek Time).
   - Attributes:
     - Access to the dispatch algorithm (e.g., Odd-Even, Fixed Floor Range, Minimum Seek Time)
   - Methods:
     - `submitRequest(int floor, Direction direction)`: Determines which **ElevatorController** to dispatch, then calls `acceptNewRequest(floor, direction)` on the selected controller.

---

### Example of a Request Flow

1. **External Request** (from a floor button):
   - User presses the up/down button on a floor.
   - **ExternalButtonDispatcher** determines which elevator should handle the request based on the dispatch algorithm.
   - It then calls the `acceptNewRequest(floor, direction)` method on the selected **ElevatorController**.

2. **Internal Request** (from inside the elevator):
   - User presses a floor button inside the elevator.
   - The **InternalButtonDispatcher** receives this request and routes it to the **ElevatorController** for the respective elevator.
   - The **ElevatorController** manages the queue of requests and controls the **ElevatorCar** movement.


![classdiagram](image.png)