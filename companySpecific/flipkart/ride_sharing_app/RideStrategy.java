package companySpecific.flipkart.ride_sharing_app;

import java.util.List;
import java.util.Map;

import companySpecific.flipkart.ride_sharing_app.model.Ride;
import companySpecific.flipkart.ride_sharing_app.model.RideRequest;

public interface RideStrategy {
    public List<Ride> findRidesByStrategy(Map<String,List<Ride>> rides, RideRequest request);
}
