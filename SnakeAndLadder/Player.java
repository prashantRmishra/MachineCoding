package machinecodingexamples.SnakeAndLadder;
public class Player {
    String name;
    int currentPos;
    int finalPos;
    public Player(String n){
        this.name = n;
        currentPos = 0;
        finalPos  =0;
    } 
    public Player(String n, int c, int f){
        this.name = n;
        this.currentPos =c;
        this.finalPos = f;
    }
    public String getPlayerName(){
        return this.name;
    }
    public int getCurrentPosition() {
        return this.currentPos;
    }
    public int getFinalPostion(){
        return this.finalPos;
    }
    public void setFinalPosition(int val){
        this.finalPos = val;
    }

    public String toString(){
        return "[player: "+ this.getPlayerName()+"]";
    }

}
