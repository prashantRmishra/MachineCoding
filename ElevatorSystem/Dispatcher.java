package ElevatorSystem;
import java.util.List;

public class Dispatcher {
    List<ElevatorCarController> controllers;
    public Dispatcher(List<ElevatorCarController> list){
        this.controllers = controllers;
    }
    void submitInternalRequest(int destinationFloor, ElevatorCar elevatorCar){
        controllers.get(elevatorCar.getId()).submitInternalRequest(destinationFloor);
    }
    public void submitExternalRequest(int floor, Direction direction){
        
    }
}
