package ElevatorSystem;
import java.util.List;

public class ExternalDispatcher {
    List<ElevatorCar> lifts = ElevatorCarCreator.createElevator();
    List<ElevatorCarController> controllers = ElevatorControllerCreator.createElevator(lifts);


    public void submitExternalRequest(int floor, Direction direction){
        ///considering even odd logic
        for(ElevatorCarController controller : controllers){
            int id = controller.elevatorCar.getId();
            if(id%2 ==1 && floor%2 ==1){ // if the elevator stops at odd floors and the requested floor is also odd
                controller.submitExternalRequest(floor, direction);
            }
            else if(id%2 ==0 && floor %2 ==0){
                controller.submitExternalRequest(floor, direction);
            }
        }
    }
}
