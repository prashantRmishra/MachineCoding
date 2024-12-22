package companySpecific.flipkart.ride_sharing_app.ride_strategy;

import java.util.List;
import java.util.Map;

import companySpecific.flipkart.ride_sharing_app.model.Ride;

public interface RideStrategy {
    public List<Ride> findRidesByStrategy(Map<String,List<Ride>> rides);
}
