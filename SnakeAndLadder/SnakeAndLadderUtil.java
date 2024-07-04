package SnakeAndLadder;
import java.util.Map;
import java.util.Random;

public class SnakeAndLadderUtil {
    public static final int BOARD_SIZE = 100;
    public int rollDice(){
        Random random = new Random();
        return random.nextInt(6)+1; // it will give random numbers between 1 to 6
    }
    public void play(Map<Integer,Snake> snakes, Map<Integer,Ladder> ladders, Map<Integer,Player> players){
        boolean over = false;
        int currentChance = 0;
        while(!over){
            // which player is playing ?
            Player p  = players.get(currentChance);
            //player p rolls the dice
            int diceVal  = rollDice();

            System.out.print( p.getPlayerName() + " rolled dice " + diceVal);

            // what was players old position ? 
            int previousPosition = p.getFinalPostion();
            //new position 
            int finalPosition = previousPosition + diceVal;
            //is new position less than BOARD_SIZE ? 
            if(finalPosition<=BOARD_SIZE){
                //is the game won by player p ?
                if(finalPosition ==BOARD_SIZE){
                    System.out.println(" " +p.getPlayerName() +" wins the game ");
                    over  = true;
                    break;
                }

                else{
                    //go the the final position
                    p.setFinalPosition(finalPosition);
                    System.out.println(" and moved from "+ previousPosition + " to "+p.getFinalPostion());
                    //check if there is snake or ladder at the final position and update the final postion accordingly
                    checkSnake(p,p.getFinalPostion(),snakes);
                    checkLadder(p, p.getFinalPostion(), ladders);

                }
            }
            // if the final position is greater than BOARD_SIZE the move is not possible
            else{
                System.out.println(" cant move though ");
            }
            //rotate the chance to next player
            currentChance = (currentChance +1) % players.size();
        }
        //is game over ?

        if(over){
            System.out.println(" game finish");
        }
       
    }
    public void checkSnake(Player p, int position,Map<Integer, Snake> snakes){
        if(snakes.containsKey(position)){
            Snake  snake = snakes.get(position);
            p.setFinalPosition(snake.getEnd());
            System.out.println("snake bit, "+p.getPlayerName() + " went from position "+position + "to "+ p.getFinalPostion());
        }
    }
    public void checkLadder(Player p, int position, Map<Integer,Ladder> ladders){
        if(ladders.containsKey(position)){
            Ladder ladder = ladders.get(position);
            p.setFinalPosition(ladder.getEnd());
            System.out.println(" got ladder, "+ p.getPlayerName() + " went from postion "+ position + " to "+ p.getFinalPostion());
        }
    }
}
