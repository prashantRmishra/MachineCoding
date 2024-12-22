package companySpecific.flipkart.ride_sharing_app.ride_strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import companySpecific.flipkart.ride_sharing_app.model.Ride;

public class RideByMostVacantStrategy implements RideStrategy {

    //mostVacant
    @Override
    public List<Ride> findRidesByStrategy(Map<String, List<Ride>> rides) {
        List<Ride> rideResults = new ArrayList<>();
        for(Map.Entry<String,List<Ride>> entry : rides.entrySet()){
            for(Ride r :entry.getValue()){
                if(r.getSeatAVailability()>=1){
                    rideResults.add(r);
                }
            }
        }
        Collections.sort(rideResults,(a,b)->Integer.compare(b.getSeatAVailability(), a.getSeatAVailability()));
        return rideResults;
    }
    
}
