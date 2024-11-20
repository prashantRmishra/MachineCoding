package CrickBuzz.teams;

import java.util.Queue;

import CrickBuzz.Exception.AllOutException;
import CrickBuzz.enums.Message;

public class PlayerBattingController {
    Queue<Player> players;
    Player striker;
    Player nonStriker;
    public PlayerBattingController(Queue<Player> players) {
        this.players = players;
    }
    public void assignNextBatsman() throws Exception{
        if(players.isEmpty()) throw new AllOutException(Message.allout.getMessage());
        if(striker ==null){
            striker = players.remove();
        }
        if(nonStriker ==null){
            nonStriker = players.remove();
        }

    }
    public Player getStriker() {
        return this.striker;
    }
    public Player getNonStriker() {
        return this.nonStriker;
    }
    public void printBattingScoreCard() {
        for(Player player: players){
            player.printBattingScoreCard();
        }
    }

    public int getTotalRun() {
        int total = 0;
        for(Player p  : players){
            total+=p.getBattingScoreCard().totalRun;
        }
        return total;
    }

    /////print score card
    public void printBowlingScoreCard() {
        for(Player player: players){
            player.printBowlingScoreCard();
        }
    }
    public void setStriker(Player p) {
        this.striker = p;
    }
    public void setNonStriker(Player p) {
        this.nonStriker = p;
    }


}
