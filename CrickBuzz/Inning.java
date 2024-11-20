package CrickBuzz;

import java.util.ArrayList;
import java.util.List;

public class Inning {
    private Team battingTeam;
    private Team bowlingTeam;
    private List<Over> overs;
    private MatchFormat format;
    public Inning(Team b, Team bo, MatchFormat format){
        this.battingTeam = b;
        this.bowlingTeam = bo;
        this.overs = new ArrayList<>();
        this.format = format;
    }

    public void startInning(int runsToWin){
        //inning will run for the maxOvers for the given format

        for(int over  = 1;over<=format.getMaxOvers();over++){
            //choose bowler
            bowlingTeam.assignNextBowler(format.getMaxOverPerBowler());
            Over o  = new Over(over,bowlingTeam.getCurrentBowler());
            overs.add(o);
            try {
                o.startOver(battingTeam,bowlingTeam,runsToWin);
            } catch (Exception e) {
                 
            }
        }

        // at the end of the over striker and nonStriker change places 
        battingTeam.exchangeStriker();

    }

}
