package machinecodingexamples.SnakeAndLadder;
public class Snake{
    int start;
    int end;
    public Snake(int s, int e){
        start  = s;
        end = e;
    }
    public int getStart(){
        return this.start;
    }
    public int getEnd(){
        return this.end;
    }

    public String toString(){
        return "[snakeStartPosition :" + this.getStart()+"]";
    }
}