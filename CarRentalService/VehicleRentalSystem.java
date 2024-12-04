package CarRentalService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleRentalSystem {
    private List<User> users;
    private List<Store> stores;

    public VehicleRentalSystem(){
        users = new ArrayList<>();
        stores =  new ArrayList<>();
    }
    //crud ops on store and vehicle

    public void addStore(Store s){
        stores.add(s);
    }
    public void addUser(User u) {
        this.users.add(u);
    }
    // get stores for the given city 
    public List<Store> getStore(String city){
        return stores.stream().filter(store-> store.getLocation().getCity().equals(city)).collect(Collectors.toList());
    }

    //similarly we can have remove ops as well
}
