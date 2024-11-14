import java.util.ArrayList;
import java.util.List;

public class ElevatorControllerCreator {

    public static List<ElevatorCarController> createElevator(List<ElevatorCar> lifts) {
        List<ElevatorCarController> controllers = new ArrayList<>();
        for(ElevatorCar lift : lifts){
            ElevatorCarController controller = new ElevatorCarController(lift);
            controllers.add(controller);
        }
        return controllers;
    }

}
