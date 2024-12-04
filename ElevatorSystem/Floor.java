package ElevatorSystem;
public class Floor {
    private int floorNumber;
    private ExternalButton button;

    public Floor(int floorNumber){
        this.floorNumber = floorNumber;
        this.button = new ExternalButton();
    }
    public void pressButton(Direction direction){
        button.pressButton(floorNumber, direction);
    }
}
