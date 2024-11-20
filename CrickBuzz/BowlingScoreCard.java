package CrickBuzz;

public class BowlingScoreCard {
    public int totalRunGiven;
    public int maidenOver;
    public int totalOverBowled;
    public int wicketsTaken;
    public int noOfNoBalls;
    public int noOfWideBalls;
    public double economy;
    @Override
    public String toString() {
        return "BowlingScoreCard [totalRunGiven=" + totalRunGiven + ", maidenOver=" + maidenOver + ", totalOverBowled="
                + totalOverBowled + ", wicketsTaken=" + wicketsTaken + ", noOfNoBalls=" + noOfNoBalls
                + ", noOfWideBalls=" + noOfWideBalls + ", economy=" + economy + "]";
    }
}
