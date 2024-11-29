package Patterns.Behavioral;


import java.util.ArrayList;
import java.util.List;

class Subject{
    private String state;
    public Subject(String s){
        this.state = s;
    }

    public String getState(){
        return this.state;
    }
    public void updateState(String s){
        this.state = s;
    }
    public Memento saveStateToMemento(){
        return new Memento(state);
    }
    public void updateStateFromMemento(Memento m){
        this.state = m.getState();
    }
}
class Memento{
    private final String state;
    public Memento(String s){
        this.state = s;
    }
    public String getState(){
        return this.state;
    }
}
class SnapshotManager{
    private List<Memento> mementos;
    public SnapshotManager(){
        this.mementos = new ArrayList<>();
    }

    public void save(Memento m){
        mementos.add(m);
    }

    public Memento getMemento(int index){
        try {
            return mementos.get(index);
        } catch (Exception e) {
           System.out.println("given snapshot index does not exist");
        }
        return null;
    }
}


public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject("state 1");
        SnapshotManager manager = new SnapshotManager();
        System.out.println("current state is "+ subject.getState());//state 1
        subject.updateState("state 2");
        manager.save(subject.saveStateToMemento());//state 2 of subject is saved

        subject.updateState("state 3");
        System.out.println("current state is "+subject.getState()); //state 3

        subject.updateStateFromMemento(manager.getMemento(0));// state updated to state 2
        System.out.println("current state is "+ subject.getState());



    }
}
