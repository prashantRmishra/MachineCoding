package Patterns.Behavioral.state;

public class Main {
    public static void main(String args[]){
        Context context = new Context();
        State state = new StartState();
        state.doAction(context);
        //current state
        System.out.println(context.getState().toString());

        State state2 = new EndState();
        state2.doAction(context);
        //new State
        System.out.println(context.getState().toString());
    }
}
