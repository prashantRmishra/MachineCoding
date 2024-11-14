import java.util.ArrayList;
import java.util.List;

public class ElevatorCarCreator {

    public static List<ElevatorCar> createElevator() {
        List<ElevatorCar> list = new ArrayList<>();
        list.add(new ElevatorCar());
        list.add(new ElevatorCar());
        list.add(new ElevatorCar());
        return list;
    }

}
