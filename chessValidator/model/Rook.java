public class Rook extends Piece {

    public Rook(String c, String type) {
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
                || start.getCol() < 1 || grid[start.getRow()][end.getCol()].getColor()!=this.getColor())
            return false;
        // if is not moving in the same row or column its invalid
        else if (start.getRow() != end.getRow() || start.getCol() != end.getCol()
                || grid[end.getRow()][end.getCol()] != null
                        && grid[end.getRow()][end.getCol()].getColor() == this.getColor())
            return false;

        else if (start.getRow() == end.getRow()) {
            for (int i = start.getCol(); i < end.getCol(); i++) {
                if (grid[start.getRow()][i] != null)
                    return false;
            }
        } else if (start.getCol() == end.getCol()) {
            for (int i = start.getRow(); i < end.getRow(); i++) {
                if (grid[i][start.getCol()] != null)
                    return false;
            }
        }

        return true;
    }

}
