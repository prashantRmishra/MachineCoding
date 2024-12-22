package companySpecific.flipkart.ride_sharing_app.ride_strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import companySpecific.flipkart.ride_sharing_app.model.Ride;

public class BalenoRideStrategy implements RideStrategy {
    @Override
    public List<Ride> findRidesByStrategy(Map<String, List<Ride>> rides) {
        List<Ride> rideResults = new ArrayList<>();

        for(Map.Entry<String,List<Ride>> entry : rides.entrySet()){
            for(Ride r : entry.getValue()){
                if(r.getVehicle().getVehicleModel().equals("baleno") && r.getSeatAVailability()>=1){
                    rideResults.add(r);
                }
            }
        }
        return rideResults;
    }
    
}
