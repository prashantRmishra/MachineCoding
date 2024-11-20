package CrickBuzz.teams;

import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerBowlingController {
    Deque<Player> bowlers;
    Map<Player,Integer> bowlerToOverMapping;
    Player currBowler;
    List<Player> list;
    public PlayerBowlingController(List<Player> boList) {
        this.list = boList;
        bowlerToOverMapping = new HashMap<>();
        for(Player p : boList){
            this.bowlers.addLast(p);
            //initialize the bowler to over mapping with count 0 meaning non of the bowlers have bowled yet
            bowlerToOverMapping.put(p, 0);
        }
    }
    public void assignNextBowler(int maxOvers) {
        //check if the player take out from the bowlers queue is delivery its last over allowed
        //they will not be allowed to bowl again
        Player player = bowlers.removeFirst();
        if(bowlerToOverMapping.get(player)==maxOvers-1){
            currBowler = player;
        }
        //add then back again in the deque
        else{
            currBowler = player;
            bowlers.addLast(currBowler);
            bowlerToOverMapping.put(currBowler,bowlerToOverMapping.get(currBowler)+1);
        }
    }
    public Player getCurrentBowler() {
        return this.currBowler;
    }



    /////print score card
    public void printBowlingScoreCard() {
        for(Player p  : list){
            if(p.getBowlingScoreCard().totalOverBowled > 0){
                p.getBattingScoreCard().toString();
            }
        }
    }

}
