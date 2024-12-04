package ElevatorSystem;

import java.util.List;

public class Building {
    private List<Floor> floors;
    Building (List<Floor> floors){
        this.floors = floors;
    }
    public void addFloor(Floor f){
        this.floors.add(f);
    }
    public void removeFloor(Floor f){
        this.floors.remove(f);
    }
    public List<Floor> getAllFloors(){
        return this.floors;
    }
}
