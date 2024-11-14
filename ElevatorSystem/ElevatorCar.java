public class ElevatorCar {
    private int id;
    private InternalButton button;
    Direction direction;
    private Status status;
    private int currentFloor;
    private Door door;
    private Display display;

    public ElevatorCar() {
        this.button = new InternalButton();
        this.direction = Direction.up;
        this.status = Status.idle;
        this.currentFloor = 0;
        this.door = new Door();
        this.display = new Display();
    }

    public void setId(int id){
        this.id = id;
    }
    public InternalButton getButton(){
        return this.button;
    }
    public void showDisplay(){
        display.showDisplay();
    }
    public void setDisplay(){
        this.display.setDisplay(currentFloor,direction);
    }

    public void pressButton(int destinationFloor){
        button.pressButton(destinationFloor,this);
    }
    public boolean move(int destinationFloor,Direction direction){
        //move down
        int startFloor= currentFloor;
        if(direction == Direction.up){
            for(int i = startFloor;i<=destinationFloor;i++){
                this.currentFloor = i;
                setDisplay();
                showDisplay();
            }
            return true;
        }
        if(direction == Direction.down){
            startFloor = this.currentFloor;
            for(int i  =startFloor;i>=destinationFloor;i--){
                this.currentFloor = i;
                setDisplay();
                showDisplay();
            }
            return true;
        }
        return false;

    }

    public int getId() {
        return this.id;
    }
}
