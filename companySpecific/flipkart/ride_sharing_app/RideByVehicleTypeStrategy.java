package companySpecific.flipkart.ride_sharing_app;

import java.util.List;
import java.util.Map;

import companySpecific.flipkart.ride_sharing_app.model.Ride;
import companySpecific.flipkart.ride_sharing_app.model.RideRequest;

public class RideByVehicleTypeStrategy implements RideStrategy {

    @Override
    public List<Ride> findRidesByStrategy(Map<String, List<Ride>> rides, RideRequest request) {
        return null;
    }
    
}
