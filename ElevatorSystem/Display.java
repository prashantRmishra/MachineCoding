package ElevatorSystem;
public class Display {
    private int currentFloor;
    private Direction direction;
    
    public void showDisplay() {
        toString();
    }
    public void setDisplay(int currentFloor, Direction direction) {
        this.currentFloor = currentFloor;
        this.direction = direction;
    }
    @Override
    public String toString() {
        return "Display [currentFloor=" + currentFloor + ", direction=" + direction + "]";
    }
}
