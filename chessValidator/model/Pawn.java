public class Pawn extends Piece{
    public Pawn(String c, String t){
        super(c, t);
    }

    @Override
   public void move(String startingPosition,String endPosition,Piece[][] grid) throws Exception{
 
        char c[] = startingPosition.toCharArray();
        int col = (int)c[0]+1;
        int row = c[1]-'a' +1;
        char d[] = endPosition.toCharArray();
        int dcol = (int) d[0]+1;
        int drow = d[1]-'a'+1;
        if(dcol != col || (row+1)!=drow) throw new Exception("Invalid move");
        if(grid[drow][dcol]!=null){
            System.out.println("taking out "+ grid[drow][dcol]);
            grid[drow][dcol] = this; 
        }
        this.setRow(drow);
        this.setCol(dcol);

   }
   public boolean isValid(int i, int j,int n){
    if(i>n || j>n || i<)
    return false;
   }
   
}