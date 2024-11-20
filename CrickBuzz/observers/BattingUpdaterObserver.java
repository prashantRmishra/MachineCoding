package CrickBuzz.observers;

import CrickBuzz.Innings.Ball;
import CrickBuzz.enums.RunType;

public class BattingUpdaterObserver  implements UpdaterObserver{

    @Override
    public void update(Ball ball) {

        // will update the score card
        ball.playedBy.battingScoreCard.totalBallPlayed++;
        ball.playedBy.battingScoreCard.wicketDetails = ball.wicket;
        if(ball.runType.equals(RunType.four)){
            ball.playedBy.battingScoreCard.Fours++;
        }
        else if(ball.runType.equals(RunType.six)){
            ball.playedBy.battingScoreCard.sixes++;
        }
        ball.playedBy.battingScoreCard.totalRun+=ball.runType.getRun();

    }

}
