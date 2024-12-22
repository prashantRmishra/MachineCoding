package companySpecific.flipkart.ride_sharing_app.ride_strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import companySpecific.flipkart.ride_sharing_app.model.Ride;

public class RideSharingStrategy implements RideStrategy {
    private Predicate<Ride> predicate;
    private Comparator<Ride> comparator;
    public RideSharingStrategy(Predicate<Ride> predicate,Comparator<Ride> c){
        this.predicate = predicate;
        this.comparator = c;
    }
    @Override
    public List<Ride> findRidesByStrategy(Map<String, List<Ride>> rides) {
        List<Ride> rideResults = new ArrayList<>();

        for(Map.Entry<String,List<Ride>> entry : rides.entrySet()){
            for(Ride r : entry.getValue()){
                if(predicate.test(r) && r.getSeatAVailability()>=1){
                    rideResults.add(r);
                }
            }
        }
        if(comparator!=null){
            Collections.sort(rideResults,comparator);
        }
        return rideResults;
    }
    
}
