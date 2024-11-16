import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleInventoryManagement {
    private List<Vehicle> vehicles;
    public VehicleInventoryManagement(List<Vehicle> vehicles){
        this.vehicles = vehicles;
        
    }

    //crud op on vehicles available with the store
    public void addVehicle(Vehicle v){
        this.vehicles.add(v);
    }
    public void removeVehicle(Vehicle v){
        this.vehicles.remove(v);
    }

    public List<Vehicle> getVehicles(VehicleType type){
        return this.vehicles.stream().filter(vehicle-> vehicle.getType().equals(type)).collect(Collectors.toList());
    }


}
