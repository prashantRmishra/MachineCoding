package companySpecific.flipkart.ride_sharing_app.model;

import java.util.ArrayList;
import java.util.List;

import companySpecific.flipkart.ride_sharing_app.RideSharingUtility;

public class RideUser{
    private int userId;
    private String userName;
    @Override
    public String toString() {
        return "[user=" + userName + "]";
    }
    private List<RideVehicle> vehicles;
    public int getUserId() {
        return userId;
    }
    public String getUserName() {
        return userName;
    }
    public List<RideVehicle> getVehicles() {
        return vehicles;
    }
    public void addVehicle(RideVehicle vehicle){
        this.vehicles.add(vehicle);
    }
    public void removeVehicle(RideVehicle vehicle){
        this.vehicles.remove(vehicle);
    }
    public RideUser(String userName){
        this.userName = userName;
        this.userId  = RideSharingUtility.getUniqueNumber();
        vehicles = new ArrayList<>();
    }
}
