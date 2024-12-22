package companySpecific.flipkart.ride_sharing_app.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import companySpecific.flipkart.ride_sharing_app.model.RegisterRide;
import companySpecific.flipkart.ride_sharing_app.model.Ride;
import companySpecific.flipkart.ride_sharing_app.model.RideRequest;
import companySpecific.flipkart.ride_sharing_app.model.RideStatus;
import companySpecific.flipkart.ride_sharing_app.ride_strategy.RideStrategy;

public class RideSharingManager {
    private Map<String,List<Ride>> allUserRides;
    private UserManager userManager;
    private RideStrategy rideStrategy;
   
    public RideSharingManager(UserManager userManager){
        allUserRides = new HashMap<>();
        this.userManager = userManager;
    }
    public synchronized void addRide(RegisterRide r){
        Ride ride = new Ride(r.getOwner(), new ArrayList<>(), r.getSeatAvailability(), RideStatus.REGISTRATION,r.getVehicle(), r.getSource(), r.getDestination());
        if(unique(ride)) {
            List<Ride> rs = allUserRides.getOrDefault(r.getOwner().getUserName(), new ArrayList<>());
            rs.add(ride);
            allUserRides.put(r.getOwner().getUserName(), rs);
            //one ride is offered by the owner of the ride 'r'
            userManager.updateOfferedStats(r.getOwner().getUserName(), 1);
            print("ride added/created ");
            return ;
        }
        print("Ride not created as ride already exists by the same owner "+ r.getOwner().getUserName() +" for the same/different source and destination");
    }

    private boolean unique(Ride r){
        List<Ride> rs = allUserRides.getOrDefault(r.getOwner().getUserName(), new ArrayList<>());
        for(Ride ride  :rs){
            if(ride.getVehicle().equals(r.getVehicle())) return false;
        }
        return true;
    }

    public void rideRequest(RideRequest request){
        List<Ride> rideList = new ArrayList<>();
        List<Ride> availableRidesBasedOnStrategy = findRides(request.getStrategy(),allUserRides);
                for(Ride ride : availableRidesBasedOnStrategy){
                    if(!request.getRequester().equals(ride.getOwner()) && !ride.getRideStatus().equals(RideStatus.COMPLETED) && ride.getSeatAVailability()>=1  && ride.getSource().equals(request.getSource()) && ride.getDestination().equals(request.getDestination())){
                        // the user of the request has requested for one ride, hence update the requested stats
                        userManager.updateTakenStats(request.getRequester().getUserName(), 1);
                        ride.decrementAvailableSeat();// decrement the seat availability by one as this requester is assigned this ride 
                        rideList.add(ride);
                        ride.getPassengers().add(request.getRequester());
                        request.markAllocated();
                        ride.setRideStatus(RideStatus.IN_PROGRESS);
                        break;// once the requested ride is found the break out
                    }
                }
                print(rideList.toString());
            }
            
    private List<Ride> findRides(RideStrategy strategy, Map<String,List<Ride>> allUserRides) {
        setRideStrategy(strategy); 
        return this.rideStrategy.findRidesByStrategy(allUserRides);

    }
    public void endRide(Ride ride){
        List<Ride> rs = allUserRides.get(ride.getOwner().getUserName());
        if(rs.contains(ride)) {
            ride.setRideStatus(RideStatus.COMPLETED);
            print("ride updated to completed");
            return ;
        }
        print("no such ride exists");
    }
    public void setRideStrategy(RideStrategy strategy){
        this.rideStrategy = strategy;
    }

    public void getRideStats(){
        userManager.printStats();
    }
    
    private void print(String msg){
        System.out.println(msg);
    }

}
