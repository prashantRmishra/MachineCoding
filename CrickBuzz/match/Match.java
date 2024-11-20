package CrickBuzz.match;

import java.time.LocalDate;

import java.util.Random;

import CrickBuzz.Innings.Inning;
import CrickBuzz.teams.Team;

public class Match {
    private String name;
    private MatchFormat format;
    private Team team1;
    private Team team2;
    private String location;
    private Inning[] innings;
    private LocalDate matchDate;
    private Team tossWinner;
    public Match(String name, MatchFormat format, Team team1, Team team2, String location) {
        this.name = name;
        this.format = format;
        this.team1 = team1;
        this.team2 = team2;
        this.location = location;
        innings = new Inning[2];
        matchDate = LocalDate.now();

    }

    public void startMatch(){
        Inning inning;
        Team battingTeam;
        Team bowlingTeam;
        for(int inn  = 1;inn <=2;inn++){
            tossWinner = toss()==1 ? team1: team2;
            if(inn ==1){
                //assuming winning team decides to bat first
                battingTeam = tossWinner;
                bowlingTeam = tossWinner.equals(team1) ? team2: team1;
                inning = new Inning(battingTeam, bowlingTeam, format);
                inning.startInning(-1);// since we are batting first, hence no chasing is involved
            }
            //now we are chasing
            else{
                battingTeam = tossWinner.equals(team1) ? team2: team1;
                bowlingTeam = tossWinner;
                inning = new Inning(battingTeam, bowlingTeam, format);
                inning.startInning(tossWinner.getTotalRun());

                // who won is already updated in when startOver() runs in the Over table
            
            }
            innings[inn-1] = inning;
            printInningDetails(inn,battingTeam,bowlingTeam);
        }
    }

    public void setWinningTeam(Team team){
        this.tossWinner = team;
    }
    public Team getTossWinner(){
        return tossWinner;
    }

    public int toss(){
        return new Random().nextInt(2);
    }
    public void printInningDetails(int inning, Team bat, Team bowl){
        System.out.println("..........Score updates for the Inning "+ inning+" ..........");
        System.out.println("............Batting score card...........");
        bat.printBattingScoreCard();
        System.out.println("...........Bowling score card ................");
        bowl.printBowlingScoreCard();
    }
}
