package machinecodingexamples.SnakeAndLadder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String str[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       

        Map<Integer, Snake> snakes = new HashMap<>();
        Map<Integer,Ladder> ladders = new HashMap<>();
        Map<Integer,Player> players = new HashMap<>();
        SnakeAndLadderUtil snakeAndLadderUtil = new SnakeAndLadderUtil();
    
        // reading no. of snakes and their start and end position
        System.out.println("Enter no. snakes");
        int noOfSnakes = Integer.parseInt(br.readLine());
        while(noOfSnakes-->0){
            String s[] = br.readLine().split(" ");
            int start = Integer.parseInt(s[0]);
            int end = Integer.parseInt(s[1]);
            snakes.put(start, new Snake(start, end));
            
        }
        System.out.println(snakes.size() + " "+snakes);

        //reading no. of ladders and their start and end positions
        System.out.println("Enter no. ladders");
        int noOfLadders = Integer.parseInt(br.readLine());

        while(noOfLadders-->0){
            String s[] = br.readLine().split(" ");
            int start = Integer.parseInt(s[0]);
            int end = Integer.parseInt(s[1]);
            ladders.put(start, new Ladder(start, end));
        }
        System.out.println(ladders.size() + " "+ladders);

        //reading no. of players and their names
        System.out.println("Enter no. players");
        int noOfPlayers = Integer.parseInt(br.readLine());
        int index = 0;
        while(noOfPlayers-->0){
            players.put(index++,new Player(br.readLine()));
        }
        System.out.println(players.size() + " "+players);
       
        //play snake and ladder game
        snakeAndLadderUtil.play(snakes,ladders,players);
        
    }
    
    
}
