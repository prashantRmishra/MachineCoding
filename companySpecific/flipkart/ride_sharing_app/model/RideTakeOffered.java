package companySpecific.flipkart.ride_sharing_app.model;

public class RideTakeOffered{
    private int rideTaken;
    private int rideOffered;
    private String user;
    @Override
    public String toString() {
        return "[user "+user+", ride taken=" + rideTaken + ", ride offered=" + rideOffered + "]";
    }
    public RideTakeOffered(String user){this.user = user;
    this.rideOffered = 0;this.rideTaken = 0;}
    public void incrementRideTake(int count){
        this.rideTaken+=count;
    }
    public String getUser(){
        return this.user;
    }
    public void incrementRideOffered(int count){
        this.rideOffered+=count;
    }
    public int getRideTaken(){return this.rideTaken;}
    public int getRideOffered(){return this.rideOffered;}
}