public class Vehicle {
    private String vehicleNumber;
    private VehicleType type;
    private Status status;
    private double hourlyCost;
    private double dailyCost;
    private Store store;
    public Store getStore(){
        return this.store;
    }
    public void setStore(Store store){
        this.store  = store;
    }
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    public VehicleType getType() {
        return type;
    }
    public void setType(VehicleType type) {
        this.type = type;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public double getHourlyCost() {
        return hourlyCost;
    }
    public void setHourlyCost(double hourlyCost) {
        this.hourlyCost = hourlyCost;
    }
    public double getDailyCost() {
        return dailyCost;
    }
    public void setDailyCost(double dailyCost) {
        this.dailyCost = dailyCost;
    }
    public Vehicle(String vehicleNumber, VehicleType type, Status status, double hourlyCost, double dailyCost) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
        this.status = status;
        this.hourlyCost = hourlyCost;
        this.dailyCost = dailyCost;
    }
    

}
