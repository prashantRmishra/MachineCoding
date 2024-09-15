package Patterns.Behavioral.memento;

public class Originator{
    private String state; // could be any data, string, Wrapper classes or custom objects as well
    public Originator(){}

    public void updateState(String s){
        this.state = s;
    }
    public String getCurrentState(){
        return this.state;
    }
    public Memento saveStateToMemento(){
        return new Memento(this.state);
    }
    public void getStateFromMemento(Memento memento){
        this.state = memento.getState();
    }
}