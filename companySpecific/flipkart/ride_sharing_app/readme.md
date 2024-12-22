Ride-Sharing Application- SDE II.  2hrs (90min Coding + 30min Review)

Description: Implement a ride-sharing application with the below-expected features.

Features:

The application allows users to share rides on a route.
Users can either offer a shared ride (Driver) or consume a shared ride (Passenger).
Users can search and select one from multiple available rides on a route with the same source and destination.
Requirements:

Application should allow user onboarding.
add_user(user_detail)
Add basic user details
add_vehicle(vehicle_detail)
Add the user’s vehicle(s) details
User should be able to offer a shared ride on a route with details.
offer_ride(ride_detail)
Ride will have details like vehicle, origin, destination, available seats. (A ride will have no intermediate stops.)
Users can select a ride from multiple offered rides using a selection strategy. (A user can only request  a ride (only for 1 or 2 people))
select_ride(source, destination, seats, selection_strategy)
Preferred Vehicle (Activa/Polo/XUV)
Most Vacant.
System should be able to end the ride. User can only offer a ride for a given vehicle, once there are no active offered rides for that vehicle.
end_ride(ride_details)
Find total rides offered/taken by all users.
print_ride_stats()
Bonus Question:

If the user’s origin/destinations are not available directly but it’s possible via multiple rides, then the application should output multiple rides. (Example: for input: Bangalore to Mumbai, the output can be Bangalore to Goa and Goa to Mumbai)
Other Notes:

Write a driver class for demo purposes. Which will execute all the commands in one place in the code and test cases.
Do not use any database or NoSQL store, use in-memory data-structure for now.  
Do not create any UI for the application.
Please prioritize code compilation, execution, and completion.  
Work on the expected output first and then add good-to-have features of your own.
Expectations:

Make sure that you have a working and demonstrable code.
Make sure that the code is functionally correct.
Use of proper abstraction, modeling, separation of concerns is required.
Code should be modular, readable and unit-testable.
Code should easily accommodate new requirements with minimal changes.
Proper exception handling is required.
Sample Test Cases:

Onboard 5 users
add_user(“Rohan, M, 36”); add_vehicle(“Rohan, Swift, KA-01-12345)
add_user(“Shashank, M, 29”); add_vehicle(“Shashank, Baleno, TS-05-62395)
add_user(“Nandini, F, 29)  
add_user(“Shipra, F, 27”) ; add_vehicle(“Shipra”, Polo, KA-05-41491); add_vehicle(“Shipra, Activa KA-12-12332”)
add_user(“Gaurav, M, 29)
add_user(“Rahul, M, 35); add_vehicle(“Rahul”, “XUV”, KA-05-1234);
Offer 4 rides by 3 users
offer_ride(“Rohan, Origin=Hyderabad, Available Seats=1, Vehicle=Swift, KA-01-12345, Destination= Bangalore”)
offer_ride(“Shipra, Origin=Bangalore, Available Seats=1, Vehicle=Activa KA-12-12332, Destination=Mysore”)
offer_ride(“Shipra, Origin=Bangalore, Available Seats=2, Vehicle=Polo, KA-05-41491, Destination=Mysore”)
offer_ride(“Shashank, Origin=Hyderabad, Available Seats=2, Vehicle=Baleno, TS-05-62395, Destination=Bangalore”)
offer_ride(“Rahul, Origin=Hyderabad, Available Seats=5, Vehicle=XUV,  KA-05-1234, Destination=Bangalore”)
offer_ride(“Rohan, Origin=Bangalore, Available Seats=1, Vehicle=Swift, KA-01-12345, Destination=Pune”)
This call should fail, since a ride has already been offered by this user for this vehicle.
Find rides for 4 users
select_ride(“Nandini, Origin=Bangalore, Destination=Mysore, Seats=1, Most Vacant”)
2(c) is the desired output.
select_ride(“Gaurav, Origin=Bangalore, Destination=Mysore, Seats=1, Preferred Vehicle=Activa”)
2(b) is the desired output
select_ride(“Shashank, Origin=Mumbai, Destination=Bangalore, Seats=1, Most Vacant”)
No rides found
select_ride(“Rohan, Origin=Hyderabad, Destination=Bangalore, Seats=1, Preferred Vehicle=Baleno”)
2(d) is the desired output
select_ride(“Shashank, Origin=Hyderabad, Destination=Bangalore, Seats=1,Preferred Vehicle=Polo”)
No rides found
End Rides
end_ride(2-a)
end_ride(2-b)
end_ride(2-c)
end_ride(2-d)
Find total rides by user: Rides Taken: Rides that have were taken and have been marked as “ended” Rides Offered: Rides that were offered and have been marked as “ended”. Note: Even if the offered ride was not taken by any user, it counts as an offered ride.
print_ride_stats()
Nandini: 1 Taken, 0 Offered  
Rohan: 1 Taken, 1 Offered 
Shashank: 0 Taken, 1 Offered 
Gaurav: 1 Taken, 0 Offered
Rahul: 0 Taken, 0 Offered 
Shipra: 0 Taken, 2 Offered


## Overview

This project is a **Ride Sharing Application** designed to simulate a system where users can offer and request rides. It includes features for registering rides, managing users, applying ride allocation strategies, and tracking ride statistics.

---

## Project Structure

### Class diagram
![class-diagram](image.png)

### **Packages**

- `manager`: Contains classes to manage rides and users.
- `model`: Includes data models for users, rides, vehicles, and ride requests.
- `ride_strategy`: Implements strategies for allocating rides.
- `ride_sharing_app`: Contains utility classes and the main controller.

---

## Core Components

### **1. Ride Management**

- ``
  - Manages the lifecycle of rides (registration, allocation, completion).
  - Tracks all rides offered by users.
  - Supports different ride allocation strategies.
  - Ensures ride uniqueness per user and vehicle.

### **2. User Management**

- ``
  - Manages user profiles and their stats.
  - Tracks rides offered and taken by users.

### **3. Ride Models**

- ``
  - Represents a ride being registered by a user.
- ``
  - Represents a ride with details like owner, passengers, vehicle, source, and destination.
- ``
  - Represents a request to join a ride.
- ``
  - Enum to represent the ride's state (e.g., `REGISTRATION`, `IN_PROGRESS`, `COMPLETED`).
- ``
  - Tracks statistics of rides offered and taken by users.

### **4. Ride Strategies**

- ``
  - Abstract class or interface that defines the `findRides` method.
  - Allows implementing custom logic for filtering rides based on specific criteria.
- **Examples of Implementations**
  - ``: Filters rides based on the type of vehicle (e.g., sedan, hatchback).
  - ``: Prioritizes rides with the most vacant seats.
  - ``: Chooses rides closest to the requested destination.

### **5. Controller**

- Manages interactions between the user and the system.
- Provides APIs for:
  - Registering rides.
  - Requesting rides.
  - Completing rides.
  - Viewing ride statistics.

---

## How It Works

1. **Register a Ride**:

   - A user offers a ride using the `RegisterRide` model.
   - `RideSharingManager` validates and stores the ride.

2. **Request a Ride**:

   - A user requests a ride using the `RideRequest` model.
   - `RideSharingManager` finds suitable rides based on the chosen strategy.

3. **Complete a Ride**:

   - Marks a ride as `COMPLETED` and updates the stats.

4. **View Statistics**:

   - Displays the number of rides offered and taken by each user.

---

## Usage Example

### **Registering a Ride**

```java
RideUser owner = new RideUser("John");
RideVehicle vehicle = new RideVehicle("KA01AB1234", owner, RideVehicleType.FOUR_WHEELER_SEDAN, "Baleno");
RegisterRide ride = new RegisterRide(owner, 4, "Bangalore", "Mysore", vehicle);
Controller controller = new Controller(new RideSharingManager(new UserManager()));
controller.createRide(ride);
```

### **Requesting a Ride**

```java
RideUser requester = new RideUser("Doe");
RideRequest request = new RideRequest(requester, "Bangalore", "Mysore", 1, new VacancyStrategy());
controller.requestRide(request);
```

### **Completing a Ride**

```java
Ride rideToComplete = ... // fetched from available rides
controller.completeRide(rideToComplete);
```

### **Viewing Stats**

```java
controller.getStats();
```

---

## Design Principles

1. **Modularity**:

   - Separation of concerns with `manager`, `model`, and `ride_strategy` packages.

2. **Strategy Pattern**:

   - Dynamic allocation of rides using different strategies.

3. **Synchronization**:

   - Ensures thread safety during ride creation and allocation.

4. **Scalability**:

   - Easily extendable to include new ride allocation strategies or user features.

---

## Future Enhancements

- Implement payment integration.
- Add location-based ride suggestions using GPS data.
- Build a UI for better user interaction.
- Include cancellation policies for rides.

---

## Prerequisites

- **Java 8 or higher**
- Maven for dependency management.

---

## Run Instructions

1. Clone the repository.
2. Build the project using `mvn clean install`.
3. Run the main controller class.

---

Feel free to explore and extend the application! 😊



Output that you get once you run the `driver.java`

```output
Ride sharing app!!
ride added/created 
ride added/created 
ride added/created 
ride added/created 
ride added/created 
Ride not created as ride already exists by the same owner rohan for the same/different source and destination
[Rides: [rideId=9, owner=shilpa, passengers=[[user=nandini]], seatAVailability=1, rideStatus=IN_PROGRESS, vehicle=[vno.=KA-01-5454, owner=shilpa, vehicleType=FOUR_WHEELER_SEDAN, vehicleModel=polo], source=bangalore, destination=mysore]]
[Rides: [rideId=8, owner=shilpa, passengers=[[user=gaurav]], seatAVailability=0, rideStatus=IN_PROGRESS, vehicle=[vno.=KA-12-1233, owner=shilpa, vehicleType=TWO_WHEELER, vehicleModel=activa], source=bangalore, destination=mysore]]
[]
[Rides: [rideId=10, owner=shashank, passengers=[[user=rohan]], seatAVailability=1, rideStatus=IN_PROGRESS, vehicle=[vno.=KA-01-76755, owner=shashank, vehicleType=FOUR_WHEELER_SEDAN, vehicleModel=baleno], source=hyderabad, destination=bangalore]]
[]
[user shilpa, ride taken=0, ride offered=2]
[user rohan, ride taken=1, ride offered=1]
[user rahul, ride taken=0, ride offered=1]
[user shashank, ride taken=0, ride offered=1]
[user nandini, ride taken=1, ride offered=0]
[user gaurav, ride taken=1, ride offered=0]
```