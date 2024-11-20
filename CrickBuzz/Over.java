package CrickBuzz;

import java.util.List;

public class Over {
    public int no;
    public Player player;
    List<Ball> balls;
    int extraBallCount;
    public Over (int no, Player player){
        this.no = no;
        this.player = player;
        extraBallCount = 0;
    }
    public boolean startOver(Team battingTeam, Team bowlingTeam, int runsToWin) {
        int ballCount  =1;
        while(ballCount<=6){

            Ball ball = new Ball(ballCount);
            ball.startBallDelivery(battingTeam,bowlingTeam,this);
            if(ball.ballType ==BallType.normal){
                if(ball.wicket!=null){
                    battingTeam.assignNextBatsman();
                }
                if(runsToWin!=-1 && battingTeam.getTotalRun() >=runsToWin){
                    battingTeam.isWinner = true;
                    return true;
                }
                ballCount++;
            }
            else extraBallCount++;
        }
        return false;
    }
}
