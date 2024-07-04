package SnakeAndLadder;
public class Ladder {
    int start;
    int end;
    public Ladder(int s, int en){
        start  = s;
        end = en;
    }
    public int getStart(){
        return this.start;
    }
    public int getEnd(){
        return this.end;
    }
    public String toString(){
        return "["+this.getEnd()+"]";
    }
}
