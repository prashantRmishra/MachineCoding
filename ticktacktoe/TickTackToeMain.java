package machinecodingexamples.ticktacktoe;

import java.util.Scanner;

public class TickTackToeMain {
    public static void main(String[] args){
        try (Scanner sc = new Scanner(System.in)) {
            boolean exit = false;
            output("Enter no. of players");
            int noOfplayers = Integer.parseInt(sc.nextLine());
      
            output("Ente size of board size like 3*3 or 9*9 or something similar");
            String gridSize[] = sc.nextLine().split("\\*");
            TickTackToeMethod tickTackToeMethod = new TickTackToeMethod(gridSize);

            output("Enter details of player like Piece PlayerName, example : X Prashant");
            while(noOfplayers-->0){
                try {
                    String input[]  = sc.nextLine().split(" ");
                    tickTackToeMethod.updateUser(input[0], input[1]);
                } catch (Exception e) {
                    output("Give valid input like X Prashant");
                }
            }
            //initialize change
            tickTackToeMethod.initializeChance();

            while(!exit){
                try {

                    tickTackToeMethod.getGrid();
                    System.out.println("Enter next move position for user "+ tickTackToeMethod.getUserDetails().get(TickTackToeMethod.getChance()) +" with "+TickTackToeMethod.getChance());
                    String pos[] = sc.nextLine().split(" ");
                    String output = tickTackToeMethod.move(pos[0], pos[1]);
                    if(output.equals("W")){
                        tickTackToeMethod.getGrid();
                        output(tickTackToeMethod.getUserDetails().get(TickTackToeMethod.getChance())+" Won the game") ;
                        break;
                    }
                    else if(output.equals("O")){
                        tickTackToeMethod.getGrid();
                        output("Game Over");
                        break;
                    }
                    else if(output.equals("I")) invalidInput();

                } catch (Exception e) {
                   invalidInput();
                }
            }
        } catch (NumberFormatException e) {
            invalidInput();
        }
    }
    static void invalidInput(){
        System.out.println("Please enter valid input");
    }
    static void output(String output){
        System.out.println(output);
    }
}
