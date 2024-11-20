package CrickBuzz.match;

public class T20Format implements MatchFormat {

    private int maxOver;
    private int maxOverPerBowler;

    public T20Format(int mo, int mopb){
        this.maxOver = mo;
        this.maxOverPerBowler = mopb;
    }
    @Override
    public int getMaxOvers() {
        return this.maxOver;
    }

    @Override
    public int getMaxOverPerBowler() {
        return this.maxOverPerBowler;
    }
    
}
