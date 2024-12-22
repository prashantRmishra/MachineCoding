package companySpecific.flipkart.ride_sharing_app;

import companySpecific.flipkart.ride_sharing_app.manager.RideSharingManager;
import companySpecific.flipkart.ride_sharing_app.model.RegisterRide;
import companySpecific.flipkart.ride_sharing_app.model.Ride;
import companySpecific.flipkart.ride_sharing_app.model.RideRequest;

public class Controller {
    private RideSharingManager manager;

    public Controller(RideSharingManager manager){
        this.manager  = manager;
    }

    //create ride
    public void createRide(RegisterRide r){
        manager.addRide(r);
    }
    //request
    public void requestRide(RideRequest request){
       
        manager.rideRequest(request);
    }
    //complete ride
    public void completeRide(Ride r){
        manager.endRide(r);
    }
    //get stats
    public void getStats(){
        manager.getRideStats();
    }
}
