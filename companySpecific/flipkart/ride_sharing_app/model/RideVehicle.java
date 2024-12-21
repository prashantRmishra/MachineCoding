package companySpecific.flipkart.ride_sharing_app.model;



public class RideVehicle {
    private String vehicleNumber;
    private RideUser owner;
    private RideVehicleType vehicleType;
    private String vehicleModel;
    
    @Override
    public String toString() {
        return "[vno.=" + vehicleNumber + ", owner=" + owner.getUserName() + ", vehicleType=" + vehicleType
                + ", vehicleModel=" + vehicleModel + "]";
    }
    public RideVehicle(String number,RideUser owner, RideVehicleType type,String model){
        this.vehicleModel = model;
        this.vehicleNumber = number;
        this.owner =owner;
        this.vehicleType = type;
    }
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    public RideUser getOwner() {
        return owner;
    }
    public RideVehicleType getVehicleType() {
        return vehicleType;
    }
    public String getVehicleModel() {
        return vehicleModel;
    }
    
}
