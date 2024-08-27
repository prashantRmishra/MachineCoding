package ticktacktoe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TickTackToeMethod {
    private static String grid[][];
    private static String chance;
    private Map<String,String> userMap  = new HashMap<>();
    private static List<String> chances;
    private static String OVER = "O";
    private static String WON = "W";
    private static String INVALID_MOVE = "I";
    private static String CONTINUE = "C";
    private static int GRID_SIZE;
    public TickTackToeMethod(String [] gridSize){
        grid = new String[Integer.parseInt(gridSize[0])][Integer.parseInt(gridSize[1])];
        for(String g[] : grid){
            Arrays.fill(g,"-");
        }
        GRID_SIZE = grid.length*2;
    }
    public String move(String p, String q){
        int i = Integer.parseInt(p)-1;
        int j = Integer.parseInt(q)-1;
        //isvalid()
        if(!validPosition(i,j)) return INVALID_MOVE;

        else{
            updateGrid(i,j);
            reducePositionLeftToPlay();

            if(GRID_SIZE ==0){
                if(checkGameStatus(i,j)) return WON;
                return OVER;
            }
            if(checkGameStatus(i,j)) return WON;
            rotateChance();
        }
        return CONTINUE;
    }
    private boolean checkGameStatus(int i, int j){
        //check row i
        boolean won  = true;
        for(int column =0;column<grid.length;column++){
            if(grid[i][column]!=chance) {
                won = false;
                break;
            }
        }
        if(won) return true;
        //check column j
        won = true;
        for(int row = 0;row <grid.length;row++){
            if(grid[row][j]!=chance) {
                won = false;
                break;
            }
        }
        if(won) return true;
        won  = true;
        //check diagonal
        int row = 0,col = 0;
        //left diagonal 
        while(row<grid.length){
            if(grid[row++][col++]!=chance) {
                won = false;
                break;
            }
        }
        if(won) return true;
        won = true;
        row = grid.length-1;col =grid.length-1;
        while(row>=0){
            if(grid[row--][col--]!=chance) {
                won = false;
                break;
            }
        }
        return won;
    }
    private void updateGrid(int i, int j){
        grid[i][j] = chance;
    }
    private void reducePositionLeftToPlay(){
        GRID_SIZE--;
    }
    private boolean validPosition(int i, int j){
        if(i>grid.length || j>grid.length || i<0 || j<0 || !grid[i][j].equals("-")) return false;
        return true;
    }
    private void rotateChance(){
        int index = chances.indexOf(chance);
        index = (index +1) % chances.size();
        chance = chances.get(index);
    }
    public void getGrid(){
        for(int i =0;i<grid.length;i++){
            for(int j =0;j<grid.length;j++){
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
    }
    public void updateUser(String k,String v){
        userMap.put(k, v);
    }
    public void initializeChance(){
        chances = userMap.keySet().stream().toList();
        chance = userMap.entrySet().stream().toList().get(0).getKey();
        System.out.println(chances);
    }
    public Map<String,String> getUserDetails(){
        return this.userMap;
    }
    public static String getChance(){
        return chance;
    }
}
