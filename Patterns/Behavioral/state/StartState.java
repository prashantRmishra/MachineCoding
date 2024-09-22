package Patterns.Behavioral.state;

public class StartState implements State {
    private Context context;
    public StartState(){}
    @Override
    public void doAction(Context context){
        this.context = context;
        this.context.setState(this);
        System.out.println("Player is in StartState");
    }

    public String toString(){
        return "Start State";
    }
}
