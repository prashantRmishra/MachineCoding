import java.util.ArrayList;
import java.util.List;

public class Store {
    private int storeId;
    private VehicleInventoryManagement vehicleInventoryManagement;
    List<Reservation> reservations;
    private Location location;
    public Store(int sId,Location location,List<Vehicle> vehicles){
        storeId = sId;
        vehicleInventoryManagement = new VehicleInventoryManagement(vehicles);
        reservations = new ArrayList<>();
        this.location = location;
    }
    
    public Location getLocation() {
        return location;
    }


    public void setLocation(Location location) {
        this.location = location;
    }


    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }


    public int getStoreId() {
        return storeId;
    }


    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }


    public VehicleInventoryManagement getVehicleInventoryManagement() {
        return vehicleInventoryManagement;
    }


    public void setVehicleInventoryManagement(VehicleInventoryManagement vehicleInventoryManagement) {
        this.vehicleInventoryManagement = vehicleInventoryManagement;
    }
    

  


    public List<Reservation> getReservations(){
        return this.reservations;
    }

    public void createReservation(Reservation reservation){
        reservations.add(reservation);
    }
    public void removeReservation(Reservation reservation){
        reservations.remove(reservation);
    }
    public void addVehicle(Vehicle v){
        vehicleInventoryManagement.addVehicle(v);
    }
    public void removeVehicle(Vehicle v){
        vehicleInventoryManagement.removeVehicle(v);
    }

    public List<Vehicle> getVehicles(VehicleType type){
        return vehicleInventoryManagement.getVehicles(type);
    }
    

}
