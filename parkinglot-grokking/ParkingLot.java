import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// Interface representing a Parkable entity
interface Parkable {
    String getVehicleNo();
    TypeOfVehicle getType();
    LocalDateTime getParkingTime();
    void setParkingTime(LocalDateTime parkingTime);
}

// Vehicle base class implementing Parkable
abstract class Vehicle implements Parkable {
    private String vehicleNo;
    private TypeOfVehicle type;
    private LocalDateTime parkingTime;

    public Vehicle(String vehicleNo, TypeOfVehicle type) {
        this.vehicleNo = vehicleNo;
        this.type = type;
    }

    public String getVehicleNo() { return this.vehicleNo; }
    public TypeOfVehicle getType() { return this.type; }
    public LocalDateTime getParkingTime() { return this.parkingTime; }
    public void setParkingTime(LocalDateTime parkingTime) { this.parkingTime = parkingTime; }
}

// Concrete vehicle classes
class Car extends Vehicle {
    public Car(String vehicleNo) { super(vehicleNo, TypeOfVehicle.Car); }
}
class Truck extends Vehicle {
    public Truck(String vehicleNo) { super(vehicleNo, TypeOfVehicle.Truck); }
}
class Bike extends Vehicle {
    public Bike(String vehicleNo) { super(vehicleNo, TypeOfVehicle.Bike); }
}

// Enum for vehicle types
enum TypeOfVehicle {
    Car, Truck, Bike, Electric;
}

// Spot base class
abstract class Spot {
    private boolean free;
    private int spotNumber;
    private Vehicle vehicle;
    private LocalDateTime parkingTime;

    public Spot(int spotNumber) {
        this.spotNumber = spotNumber;
        this.free = true;
    }

    public boolean isAvailable() { return free; }
    public void occupySpot(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.free = false;
        this.parkingTime = LocalDateTime.now();
    }
    public void freeSpot() {
        this.vehicle = null;
        this.free = true;
    }
}

// Concrete spot types
class CarSpot extends Spot { public CarSpot(int spotNumber) { super(spotNumber); } }
class TruckSpot extends Spot { public TruckSpot(int spotNumber) { super(spotNumber); } }
class BikeSpot extends Spot { public BikeSpot(int spotNumber) { super(spotNumber); } }

// Factory to create appropriate spots based on vehicle type
class SpotFactory {
    public static Spot getSpotForVehicle(TypeOfVehicle type, int spotNumber) {
        switch (type) {
            case Car: return new CarSpot(spotNumber);
            case Truck: return new TruckSpot(spotNumber);
            case Bike: return new BikeSpot(spotNumber);
            default: throw new IllegalArgumentException("No spot available for vehicle type.");
        }
    }
}

// Interface for Parking Strategy
interface ParkingStrategy {
    Spot findSpot(List<Spot> spots, Parkable vehicle);
}

// Strategy for Electric Vehicle Parking
class ElectricVehicleParkingStrategy implements ParkingStrategy {
    public Spot findSpot(List<Spot> spots, Parkable vehicle) {
        // Logic for finding reserved electric spots
        for (Spot spot : spots) {
            if (spot.isAvailable()) {
                return spot;
            }
        }
        return null;
    }
}

// Ticket class for managing tickets
class Ticket {
    private String vehicleNo;
    private LocalDateTime entryTime;
    private Spot spot;

    public Ticket(String vehicleNo, Spot spot) {
        this.vehicleNo = vehicleNo;
        this.entryTime = LocalDateTime.now();
        this.spot = spot;
    }

    public Spot getSpot() { return spot; }
    public LocalDateTime getEntryTime() { return entryTime; }
}

// Manager for creating and closing tickets
class TicketManager {
    private Map<String, Ticket> activeTickets = new HashMap<>();

    public Ticket createTicket(Parkable vehicle, Spot spot) {
        Ticket ticket = new Ticket(vehicle.getVehicleNo(), spot);
        activeTickets.put(vehicle.getVehicleNo(), ticket);
        return ticket;
    }

    public Ticket closeTicket(String vehicleNo) {
        return activeTickets.remove(vehicleNo);
    }
}

// Fee calculator
class StandardFeeCalculator {
    public double calculateFee(LocalDateTime entryTime, LocalDateTime exitTime) {
        long hours = Duration.between(entryTime, exitTime).toHours();
        double fee = 5.0;  // Base fee
        if (hours > 1) fee += 3.0 * (hours - 1);  // Example rate: $3 per additional hour
        return fee;
    }
}

// Floor class containing different spots and managing parking/unparking
class Floor {
    private Map<TypeOfVehicle, List<Spot>> spots;
    private TicketManager ticketManager;

    public Floor(Map<TypeOfVehicle, List<Spot>> spots, TicketManager ticketManager) {
        this.spots = spots;
        this.ticketManager = ticketManager;
    }

    public Ticket parkVehicle(Parkable vehicle) {
        List<Spot> availableSpots = spots.get(vehicle.getType());
        for (Spot spot : availableSpots) {
            if (spot.isAvailable()) {
                spot.occupySpot((Vehicle) vehicle);
                return ticketManager.createTicket(vehicle, spot);
            }
        }
        return null;  // No spot available
    }

    public double unparkVehicle(String vehicleNo) {
        Ticket ticket = ticketManager.closeTicket(vehicleNo);
        if (ticket != null) {
            Spot spot = ticket.getSpot();
            spot.freeSpot();
            return new StandardFeeCalculator().calculateFee(ticket.getEntryTime(), LocalDateTime.now());
        }
        return 0;
    }
}

// ParkingLot class to handle multiple floors
class ParkingLot {
    private Map<Integer, Floor> floors = new TreeMap<>();

    public void addFloor(int floorNumber, Floor floor) {
        floors.put(floorNumber, floor);
    }

    public boolean parkVehicle(int floorNumber, Parkable vehicle) {
        Floor floor = floors.get(floorNumber);
        if (floor != null) {
            Ticket ticket = floor.parkVehicle(vehicle);
            return ticket != null;
        }
        return false;
    }

    public double unparkVehicle(int floorNumber, String vehicleNo) {
        Floor floor = floors.get(floorNumber);
        if (floor != null) {
            return floor.unparkVehicle(vehicleNo);
        }
        return 0;
    }
}
