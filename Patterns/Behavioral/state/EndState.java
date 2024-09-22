package Patterns.Behavioral.state;

public class EndState implements State {
    private Context context;
    public EndState(){}
    @Override
    public void doAction(Context context){
        this.context = context;
        this.context.setState(this);
        System.out.println("Player is in EndState");
    }

    public String toString(){
        return "End State";
    } 
}
