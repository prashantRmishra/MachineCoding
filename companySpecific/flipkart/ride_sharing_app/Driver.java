package companySpecific.flipkart.ride_sharing_app;

import companySpecific.flipkart.ride_sharing_app.manager.RideSharingManager;
import companySpecific.flipkart.ride_sharing_app.manager.UserManager;
import companySpecific.flipkart.ride_sharing_app.model.RegisterRide;
import companySpecific.flipkart.ride_sharing_app.model.RideRequest;
import companySpecific.flipkart.ride_sharing_app.model.RideUser;
import companySpecific.flipkart.ride_sharing_app.model.RideVehicle;
import companySpecific.flipkart.ride_sharing_app.model.RideVehicleType;
import companySpecific.flipkart.ride_sharing_app.ride_strategy.ActivaRideStrategy;
import companySpecific.flipkart.ride_sharing_app.ride_strategy.BalenoRideStrategy;
import companySpecific.flipkart.ride_sharing_app.ride_strategy.PoloRideStrategy;
import companySpecific.flipkart.ride_sharing_app.ride_strategy.RideByMostVacantStrategy;

public class Driver {
    public static void main(String[] args) {
        System.out.println("Ride sharing app!!");

        RideUser rohan = new RideUser("rohan");
        RideUser shashank = new RideUser("shashank");
        RideUser nandini = new RideUser("nandini");
        RideUser shilpa = new RideUser("shilpa");
        RideUser gaurav = new RideUser("gaurav");
        RideUser rahul = new RideUser("rahul");

        RideVehicle swift = new RideVehicle("KA-01-12345", rohan,RideVehicleType.FOUR_WHEELER_SEDAN , "swift");
        RideVehicle baleno = new RideVehicle("KA-01-76755", shashank,RideVehicleType.FOUR_WHEELER_SEDAN , "baleno");
        RideVehicle polo = new RideVehicle("KA-01-5454", shilpa,RideVehicleType.FOUR_WHEELER_SEDAN , "polo");
        RideVehicle activa = new RideVehicle("KA-12-1233", shilpa,RideVehicleType.TWO_WHEELER , "activa");
        RideVehicle xuv = new RideVehicle("KA-12-1236766", rahul, RideVehicleType.FOUR_WHEELER_XUV  , "Ertiga");

        rohan.addVehicle(swift);
        shashank.addVehicle(baleno);
        shilpa.addVehicle(polo);
        shilpa.addVehicle(activa);

        UserManager userManager = new UserManager();
        userManager.addUser(rahul);
        userManager.addUser(rohan);
        userManager.addUser(shashank);
        userManager.addUser(nandini);
        userManager.addUser(shilpa);
        userManager.addUser(gaurav);
        RideSharingManager rideManager = new RideSharingManager(userManager);
        Controller controller = new Controller(rideManager);
        RegisterRide registerRideRohan = new RegisterRide(rohan, 1, "hyderabad", "bangalore", swift);
        RegisterRide registerRideShilpa = new RegisterRide(shilpa, 1, "bangalore", "mysore", activa);
        RegisterRide registerRideShilpa2 = new RegisterRide(shilpa, 2, "bangalore", "mysore", polo);
        RegisterRide registerRideShashank = new RegisterRide(shashank, 2, "hyderabad", "bangalore", baleno);
        RegisterRide registerRideRahul = new RegisterRide(rahul, 5, "hyderabad", "bangalore", xuv);
        RegisterRide regsiterRideRohan2 = new RegisterRide(rohan, 1, "mumbai", "pune", swift);

        //following ride should be created
        controller.createRide(registerRideRohan);
        controller.createRide(registerRideShilpa);
        controller.createRide(registerRideShilpa2);
        controller.createRide(registerRideShashank);
        controller.createRide(registerRideRahul);
        //this should not be created as the vehicle 'swift' is already register in different ride
        controller.createRide(regsiterRideRohan2);

        //requesting rides
        RideRequest rideRequestNandini =new RideRequest(nandini, "bangalore", "mysore", 1, new RideByMostVacantStrategy());
        RideRequest rideRequestGaurav = new RideRequest(gaurav, "bangalore", "mysore", 1, new ActivaRideStrategy());
        RideRequest rideRequestShashank = new RideRequest(shashank, "mumbai", "bangalore", 1, new RideByMostVacantStrategy());
        RideRequest rideRequestRohan    = new RideRequest(rohan, "hyderabad", "bangalore", 1, new BalenoRideStrategy());

        RideRequest rideRequestShashank2 = new RideRequest(shashank, "hyderabad", "bangalore", 1, new PoloRideStrategy());

        controller.requestRide(rideRequestNandini);
        controller.requestRide(rideRequestGaurav);
        controller.requestRide(rideRequestShashank);
        controller.requestRide(rideRequestRohan);
        controller.requestRide(rideRequestShashank2);


        controller.getStats();
        
    }
}
