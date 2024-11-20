package CrickBuzz.Innings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import CrickBuzz.enums.BallType;
import CrickBuzz.enums.RunType;
import CrickBuzz.enums.WicketType;
import CrickBuzz.observers.BattingUpdaterObserver;
import CrickBuzz.observers.BowlingUpdaterObserver;
import CrickBuzz.observers.UpdaterObserver;
import CrickBuzz.teams.Player;
import CrickBuzz.teams.Team;

public class Ball {
    public Over over;
    public int ballNo;
    public BallType ballType;
    public RunType runType;
    public Player playedBy;
    public Player bowledBy;
    public Wicket wicket;
    List<UpdaterObserver> observers;
    public Ball(int ballCount){
        this.ballNo = ballCount;
        observers = new ArrayList<>();

        //ideally these should be added in the observer list as and when these 
        //observers are created
        observers.add(new BattingUpdaterObserver());
        observers.add(new BowlingUpdaterObserver());
    }
    public void startBallDelivery(Team bat, Team bowl, Over currentOver){
        playedBy = bat.getStriker();
        bowledBy = bowl.getCurrentBowler();
        ballType = BallType.normal;// assuming normal ball is delivered
        if(isWicket()){
            //taking only bold type
            wicket =new Wicket(WicketType.bold, bowledBy.getName(), currentOver, this);
            bat.setStriker(null); // once this is set to null new striker will come to bat
        }
        //if not bold then run must have been made
        else{
            runType  = getRunType();
            if(runType ==RunType.one || runType == RunType.three){
                bat.exchangeStriker();
            }

        }

        // Score card is updated after delivery of each ball
        notifyObserver(this);// Ball has all the info needed
    }
    public RunType getRunType(){
        int run = new Random().nextInt(RunType.values().length);
       
        switch(run){
            case 0:return RunType.values()[0];
            case 1:return RunType.values()[1];
            case 2:return RunType.values()[2];
            case 3:return RunType.values()[3];
            case 4:return RunType.values()[4];
            case 5:return RunType.values()[5];
        }
        return RunType.one; //other wise;
    }

    public boolean isWicket(){
        return new Random().nextInt(2)==0;
    }


    public void notifyObserver(Ball ball){
        for(UpdaterObserver observer : observers){
            observer.update(ball);
        }
    }
}
