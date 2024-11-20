package CrickBuzz;

import java.util.List;
import java.util.Queue;

public class Team {
    private String name;
    private PlayerBattingController battingController;
    private PlayerBowlingController bowlingController;
    private List<Player> benchPlayers;
    public boolean isWinner;
    public Team(Queue<Player> players, List<Player> benchPlayers, List<Player> bowlers){
        battingController = new PlayerBattingController(players);
        bowlingController = new PlayerBowlingController(bowlers);
        this.benchPlayers = benchPlayers;
        isWinner = false;
    }

    public String getName(){
        return this.name;
    }
    public void assignNextBatsman(){
        try {
            battingController.assignNextBatsman();;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void assignNextBowler(int maxOver){
        bowlingController.assignNextBowler(maxOver);
    }
    public Player getStriker(){
        return battingController.getStriker();
    }
    public void setStriker(Player p){
        this.battingController.setStriker(p);
    }
    public Player getNonStriker(){
        return battingController.getNonStriker();
    }
    public void setNonStriker(Player p){
        this.battingController.setNonStriker(p);
    }
    public Player getCurrentBowler(){
        return bowlingController.getCurrentBowler();
    }
    public void printBattingScoreCard(){
        battingController.printBattingScoreCard();
    }
    public  void printBowlingScoreCard(){
        bowlingController.printBowlingScoreCard();
    }

    public  int getTotalRun(){
        return battingController.getTotalRun();
    }

    public void exchangeStriker() {
        Player striker = getStriker();
        setStriker(getNonStriker());
        setNonStriker(striker);
    }
}
