package CrickBuzz;

public class Player {
    private Person person;
    private PlayerType playerType;
    BattingScoreCard battingScoreCard;
    BowlingScoreCard bowlingScoreCard;
    public Player(String name, PlayerType playerType) {
        person = new Person(name);
        this.playerType = playerType;
        battingScoreCard = new BattingScoreCard();
        bowlingScoreCard = new BowlingScoreCard();
    }
    public PlayerType getPlayerType(){
        return this.playerType;
    }
    public String getName(){
        return person.getName();
    }
    public BattingScoreCard getBattingScoreCard(){
        return this.battingScoreCard;
    }
    public BowlingScoreCard getBowlingScoreCard(){
        return this.bowlingScoreCard;
    }
    public void printBowlingScoreCard() {
        System.out.println(bowlingScoreCard.toString());
    }
    public void printBattingScoreCard() {
        System.out.println(battingScoreCard.toString());
    }
}
