package CrickBuzz.Innings;

public class BattingScoreCard {
    public int totalRun;
    public int Fours;
    public int sixes;
    public double strikeRate;
    public int totalBallPlayed;
    public Wicket wicketDetails;
    @Override
    public String toString() {
        return "BattingScoreCard [totalRun=" + totalRun + ", Fours=" + Fours + ", sixes=" + sixes + ", strikeRate="
                + strikeRate + ", totalBallPlayed=" + totalBallPlayed + ", wicketDetails=" + wicketDetails + "]";
    }
    

}
