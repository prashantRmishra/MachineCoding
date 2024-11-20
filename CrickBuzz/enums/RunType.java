package CrickBuzz.enums;

public enum RunType {
    one(1),two(2),three(3),four(4),six(6),wide_run(1);
    private int run;
    RunType(int run){
        this.run = run;
    }
    public int getRun() {
        return this.run;
    }
}
