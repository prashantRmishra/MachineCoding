package Patterns.Behavioral.state;

public class Context {
    private State state;
    public void setState(State s){
        this.state = s;
    }

    public State getState(){
        return this.state;
    }
}
