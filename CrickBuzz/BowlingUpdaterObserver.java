package CrickBuzz;

public class BowlingUpdaterObserver implements UpdaterObserver {

    @Override
    public void update(Ball ball) {
        //update the bowling score card
        ball.bowledBy.bowlingScoreCard.totalRunGiven+=ball.runType.getRun();
        if(ball.ballNo ==6 && ball.ballType==BallType.normal){
            ball.bowledBy.bowlingScoreCard.totalOverBowled++;
        }
        if(ball.wicket!=null){
            ball.bowledBy.bowlingScoreCard.wicketsTaken++;
        }
        if(ball.ballType == BallType.no_ball){
            ball.bowledBy.bowlingScoreCard.noOfNoBalls++;
        }
        if(ball.ballType == BallType.wide){
            ball.bowledBy.bowlingScoreCard.noOfWideBalls++;
        }
    }
    
}
