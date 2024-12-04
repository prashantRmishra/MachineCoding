package ElevatorSystem;
import java.util.List;

public class InternalDispatcher {

    List<ElevatorCar> lifts = ElevatorCarCreator.createElevator();
    List<ElevatorCarController> controllers = ElevatorControllerCreator.createElevator(lifts);
    public void submitInternalRequest(int destinationFloor, ElevatorCar elevatorCar) {
        
    }

}
