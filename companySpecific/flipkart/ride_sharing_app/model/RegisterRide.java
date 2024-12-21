package companySpecific.flipkart.ride_sharing_app.model;

public class RegisterRide {
    private RideUser owner;
    private int seatAvailability;
    private String source;
    private String destination;
    private RideVehicle vehicle;
    public RideUser getOwner() {
        return owner;
    }
    public int getSeatAvailability() {
        return seatAvailability;
    }
    public String getSource() {
        return source;
    }
    public String getDestination() {
        return destination;
    }
    public RideVehicle getVehicle() {
        return vehicle;
    }
    public RegisterRide(RideUser owner, int seatAvailability, String source, String destination, RideVehicle vehicle) {
        this.owner = owner;
        this.seatAvailability = seatAvailability;
        this.source = source;
        this.destination = destination;
        this.vehicle = vehicle;
    }


}
