public class Bishop extends Piece {

    public Bishop(String c, String type) {
        super(c, type);
    }

    public String move(Pair<Integer> start, Pair<Integer> end, Piece[][] grid) throws Exception {

        if (!isValid(start, end, grid))
            return "not valid";
        Piece p = grid[end.getRow()][end.getCol()];
        System.out.println((p != null) ? "Removing " + p.getPiece() : "");
        grid[end.getRow()][end.getCol()] = this;
        return "moved successfully";
    }

    private boolean isValid(Pair<Integer> start, Pair<Integer> end, Piece[][] grid) {

        if (end.getRow() > grid.length || end.getCol() > grid.length || end.getRow() < 1 || end.getCol() < 1 ||
                start.getRow() > grid.length || start.getCol() > grid.length || start.getRow() < 1
                || start.getCol() < 1 || grid[start.getRow()][end.getCol()].getColor() != this.getColor())
            return false;
        // moving left upper diagonal ?
        int i = start.getRow();
        int j = end.getCol();
        if (end.getCol() < start.getCol() && end.getRow() > start.getRow()) {
            while (j > 0 && row < grid.length) {
                j--;
                i++;
                if (i == end.getRow() && j == end.getCol())
                    return true;
                if (grid[i][j] != null)
                    return false;
            }
        }
        i = start.getRow();
        j = end.getCol();
        // moving lower left diagonal
        if (end.getCol() < start.getCol() && end.getRow() < start.getRow()) {
            while (j > 0 && i > 0) {
                j--;
                i--;
                if (i == end.getRow() && j == end.getCol())
                    return true;
                if (grid[i][j] != null)
                    return false;

            }
        }
        //moving to right upper diagonal
        i = start.getRow();
        j = end.getCol();
        if(end.getCol() > start.getCol() && start.getRow() < end.getRow()){
            while(j<grid.length && i<grid.length){
                i++;j++;
                if(i == end.getRow() && j == end.getCol()) return true;
                if(grid[i][j]!=null) return false;
            }
        }
        //moving to lower right diagonal
        i = start.getRow();
        j = start.getCol();
        if(end.getCol() > start.getCol() && start.getRow() < end.getRow()){
            while(j<grid.length && i>0){
                j++;i--;
                if(i == end.getRow() && j == end.getCol()) return true;
                if(grid[i][j]!=null) return false;
            }
        }

        return false;
    }

}
