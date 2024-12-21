package companySpecific.flipkart.ride_sharing_app.model;

import companySpecific.flipkart.ride_sharing_app.RideStrategy;

public class RideRequest {
    private RideUser requester;
    private String source;
    private String destination;
    private int noOfSeats;
    private RideStrategy strategy;
    private boolean isAllocated;
    
    public RideRequest(RideUser requester, String source, String destination, int noOfSeats, RideStrategy strategy) {
        this.requester = requester;
        this.source = source;
        this.destination = destination;
        this.noOfSeats = noOfSeats;
        this.strategy = strategy;
        this.isAllocated = false;
    }
    @Override
    public String toString() {
        return "RideRequest [requester=" + requester.getUserName() + ", source=" + source + ", destination=" + destination
                + ", noOfSeats=" + noOfSeats + "]";
    }
    public RideUser getRequester() {
        return requester;
    }
    public String getSource() {
        return source;
    }
    public void markAllocated(){
        this.isAllocated = true;
    }
    public boolean isAllocated(){return this.isAllocated;}
    public String getDestination() {
        return destination;
    }
    public int getNoOfSeats() {
        return noOfSeats;
    }
    public RideStrategy getStrategy() {
        return strategy;
    }


    
}
