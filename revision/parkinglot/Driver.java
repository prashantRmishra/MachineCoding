package revision.parkinglot;

import revision.parkinglot.controller.ParkingLotController;
import revision.parkinglot.model.MotorCycle;
import revision.parkinglot.model.VehicleType;

public class Driver {
    public static void main(String[] args) {
        ParkingLotController controller =new ParkingLotController();
        ///add a floor in the parking building
        controller.addFloor();

        for(int i =0;i<39;i++){
            controller.parkVehicle(new MotorCycle(i, VehicleType.bike));
        }
        //1 other vehicle should be allowed now
        System.out.println(controller.parkVehicle(new MotorCycle(40, VehicleType.bike))==null?"no spot found":"parked");
        //1 more vehicle should not be allowed now
        System.out.println(controller.parkVehicle(new MotorCycle(41, VehicleType.bike))==null?"no spot found":"parked");;
   
        
    }
}
