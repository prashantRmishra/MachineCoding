package CarRentalService;

public class Car extends Vehicle {
    public Car(String vehicleNumber,VehicleType type, Status status, int dailyCost, int hourlyCost){
        super(vehicleNumber, type, status, hourlyCost, dailyCost);
    }
}
