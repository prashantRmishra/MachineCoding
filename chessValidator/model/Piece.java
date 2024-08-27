public class Piece{
    String piece;
    int row;
    int col;
    String color;
    public Piece(String c, String type){
       piece = c+type;
       color = c;
    }
    // public void move(String startingPostion,String endposition,Piece[][] grid) throws Exception{
    //     //needs to be overridden
    // }
    public String getPiece(){
        return piece;
    }
    public void setRow( int row){
        this.row  =row;
    }
    public void setCol(int col){
        this.col  =col;
    }
    public int getCurrentRow(){
        return this.row;
    }
    public int getCurrentCol(){
        return this.col;
    }
    public String getColor(){
        return this.color;
    }
}