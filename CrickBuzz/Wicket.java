package CrickBuzz;

public class Wicket {
    private WicketType type;
    private  String takeBy;
    private Over over;
    private Ball ball;
    @Override
    public String toString() {
        return "Wicket [type=" + type + ", takeBy=" + takeBy + ", in over=" + over + ", on ball=" + ball + "]";
    }
    public Wicket(WicketType type, String takenBy, Over over, Ball ball){
        this.type = type;
        this.takeBy = takenBy;
        this.over = over;
        this.ball = ball;
    }
}
