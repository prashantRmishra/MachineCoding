package companySpecific.flipkart.ride_sharing_app.model;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import companySpecific.flipkart.ride_sharing_app.RideSharingUtility;

public class Ride {
    private int rideId;
    private RideUser owner;
    private List<RideUser> passengers;
    private AtomicInteger seatAVailability;
    private RideStatus rideStatus;
    private RideVehicle vehicle;
    private String source;
    private String destination;
    
    public Ride(RideUser owner, List<RideUser> passengers, int seatAVailability, RideStatus rideStatus,
            RideVehicle vehicle, String source, String destination) {
                this.seatAVailability = new AtomicInteger(0);
        this.owner = owner;
        this.passengers = passengers;
        this.seatAVailability.set(seatAVailability);
        this.rideStatus = rideStatus;
        this.vehicle = vehicle;
        this.source = source;
        this.destination = destination;
        this.rideId = RideSharingUtility.getUniqueNumber();
    }
    @Override
    public String toString() {
        return "Rides: [rideId=" + rideId + ", owner=" + owner.getUserName() + ", passengers=" + passengers + ", seatAVailability="
                + seatAVailability + ", rideStatus=" + rideStatus + ", vehicle=" + vehicle + ", source=" + source
                + ", destination=" + destination + "]";
    }
    public int getRideId() {
        return rideId;
    }
    public RideUser getOwner() {
        return owner;
    }
    public List<RideUser> getPassengers() {
        return passengers;
    }
    public int getSeatAVailability() {
        return seatAVailability.get();
    }
    public void decrementAvailableSeat(){
        this.seatAVailability.decrementAndGet();
    }
    public int incrementSeatAvailability(){
        return this.seatAVailability.incrementAndGet();
    }
    public RideStatus getRideStatus() {
        return rideStatus;
    }
    public void setRideStatus(RideStatus status){
        this.rideStatus = status;
    }
    public RideVehicle getVehicle() {
        return vehicle;
    }
    public String getSource() {
        return source;
    }
    public String getDestination() {
        return destination;
    }
}
