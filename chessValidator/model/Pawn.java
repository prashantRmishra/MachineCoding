package chessValidator.model;

public class Pawn extends Piece{
    public Pawn(String c, String t){
        super(c, t);
    }

    
   public String  move(Pair<Integer> start, Pair<Integer> end,Piece[][] grid) throws Exception{

    if(!isValid(start,end,grid)) return "not valid";
    Piece p = grid[end.getRow()][end.getCol()];
    System.out.println((p!=null) ? "Removing "+p.getPiece():"");
    grid[end.getRow()][end.getCol()] = this;
    return "moved successfully";
   }
   public boolean isValid(Pair<Integer> start, Pair<Integer> end, Piece[][] grid){
    if(end.getRow()> grid.length || end.getCol() > grid.length || end.getRow() <1 || end.getCol() <1 ||
        start.getRow() >grid.length || start.getCol() > grid.length || start.getRow() <1 || start.getCol() <1 || grid[start.getRow()][end.getCol()].getColor()!=this.getColor()) return false;
    //is at the start location and (going to different column  or going to only 1 step ) or taking out self piece return false;
    // 
    else if(start.getRow()==2 && (end.getRow()!=start.getRow()+2)  ||  grid[end.getRow()][end.getCol()].getColor()==this.getColor()) return false;
    else if(start.getRow() > 2 &&  (end.getRow()!=start.getRow()+1 ) || grid[end.getRow()][end.getCol()].getColor()==this.getColor()) return false;
    return true;
   }
   
   
}