## ParkingLot

Design Parking lot
Requirements:
See the available parking slots per floor
    Various slots on each floor like Compact, Large, Handicapped, Motorcycle.etc
    Each type of spot area will have some reserved spots for electric vehicle/car
    EV spots should have charing points the the customer can use and pay
    Parking for different types of vehicle should be supported
Park/un-park vehicle
    Park if the slot is available
Pay parking fees
    Payment can be made via cash, credit card, etc
    Fee model for parking per hour like 4$ for 1st hr, 3$ for 2nd and 2.5$ for the rest of the hrs
Admin should be able to add more parking floors

Core Entities

```
Vehicle like Bike, compact vehicle:Car, large vehicle:Truck,Van(Van can also be handicapped vehicle)
Floor: Map<Vehicle,List<Spot>>
Spot:VehicleId
ParkingLot: List<Floor>
```

Class Diagram


Key takeaways

The code is **extensible** meaning it can accommodate changes like adding new floors, adding more vehicle types, changing the fees prices



