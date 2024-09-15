package Patterns.Behavioral.memento;

public class Main {
    public static void main(String args[]){
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();
        originator.updateState("state #1");
        originator.updateState("state #2");
        //save current state in memento and store memento in caretake
        careTaker.add(originator.saveStateToMemento());// one state is saved

        originator.updateState("state #3");
        //save current state in memento and store memento in caretake
        careTaker.add(originator.saveStateToMemento());

        originator.updateState("state #4");
         //current state of originator
         System.out.println("current state : "+originator.getCurrentState());
        //first snapshot of state
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("first snapshot/saved state: "+ originator.getCurrentState());
        //second snapshot of state
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("second snapshot/saved state: "+originator.getCurrentState());

       
    }
}

