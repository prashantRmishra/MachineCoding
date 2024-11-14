import java.util.PriorityQueue;

public class ElevatorCarController {
    // this will be responsible for moving the elevator up and down efficiently
    //we can use LOOK algo for this 
    PriorityQueue<Integer> up;
    PriorityQueue<Integer> down;
    ElevatorCar elevatorCar;
    public ElevatorCarController(ElevatorCar car){
        this.elevatorCar = elevatorCar;
        up = new PriorityQueue<>();/// will have floor no. in ascending order
        down = new PriorityQueue<>((a,b)->b-a);// will have floor no. in descending order
    }

    //this controller will get request from Internal/External buttons


    //a lift has to reach to the given floor number in the given direction
    public void submitExternalRequest(int floor,Direction direction){
        if(direction == Direction.down){
            down.remove(); // remove top floor as we reach it, moving down
        }
        else{
            up.remove();// remove top floor as we reach it, moving up 
        }
    }

    
    //lift should take actor to the given floor so that actor can go out
    public void submitInternalRequest(int floor){
        controlElevator();
    }

    public void controlElevator(){
        while(true){
            if(elevatorCar.direction == Direction.up){
                // if the floor is reached actor will come out 
            }
            else{
                // if the floor is reached actor will come out
            }
        }
    }
    
}
