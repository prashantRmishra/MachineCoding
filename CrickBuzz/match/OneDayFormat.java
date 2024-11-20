package CrickBuzz.match;

public class OneDayFormat implements MatchFormat{
    
    private int maxOver;
    private int maxOverPerBowler;

    public OneDayFormat(int mo, int mopb){
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
