package trello;

import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        try (Scanner sc = new Scanner(System.in)) {
            TrelloUtil util = new TrelloUtil();// users will be created;
            boolean exit  = false;
            while(!exit){
                String commands[] = sc.nextLine().split(" ");
                switch (commands[0]) {
                    case "SHOW":{
                        if(commands.length ==1) util.showBoard();
                        else util.showOthers(commands[1],commands[2]);
                        break;
                    }
                    case "BOARD":{
                        //BOARD create name
                        if(commands.length ==3) {
                            if(commands[1].equals("CREATE")) util.createBoard(commands[2]);
                            else if(commands[1].equals("DELETE")) util.deleteBoard(commands[2]);
                            else System.out.println("invalid input");
                        }
                        else if(commands[2].equals("name")){
                            util.updateNameOfBoard(commands[1], commands[3]);
                        }
                        else if(commands[2].equals("privacy")){
                            util.updatePrivacyOfBoard(commands[1], commands[3]);
                        }
                        else if(commands[2].equals("ADD_MEMBER")){
                            util.addMember(commands[1], commands[3]);
                        }
                        else if(commands[2].equals("REMOVE_MEMBER")){
                            util.removeMember(commands[1], commands[3]);
                        }
                        else System.out.println("invalid input");
                        break;
                    }
                    case "LIST":{
                        if(commands[1].equals("CREATE")){
                            String listName = "";
                            for(int i =3;i<commands.length;i++){
                                listName+=commands[i]+" ";
                            }
                            util.createSubProjectOrList(commands[2], listName.trim());
                        }
                        else if(commands[2].equals("name")){
                            String listName = "";
                            for(int i =3;i<commands.length;i++){
                                listName+=commands[i]+" ";
                            }
                            util.updateSubProjectorList(commands[1], listName.trim());
                        }
                        else System.out.println("invalid input");
                        break;
                    }
                    case "CARD":{
                        if(commands[1].equals("CREATE")){
                            util.createCard(commands[2], commands[3]);
                        }
                        else if(commands[2].equals("name")){
                            String name = "";
                            for(int i =3;i<commands.length;i++){
                                name+=commands[i]+" ";
                            }
                            util.updateCardName(commands[1], name.trim());
                        }
                        else if(commands[2].equals("description")){
                            String description = "";
                            for(int i =3;i<commands.length;i++){
                                description+=commands[i]+" ";
                            }
                            util.updateCardDescription(commands[1], description.trim());
                        }
                        else if(commands[2].equals("ASSIGN")){
                            util.assignCard(commands[1], commands[3]);
                        }
                        else if(commands[2].equals("UNASSIGN")){
                            util.removeAssigneeFromCard(commands[1]);
                        }
                        else if(commands[2].equals("MOVE")){
                            util.moveCardToDifferentSubProject(commands[1],commands[3]);
                        }
                        break;
                    }
                    case "EXIT": {
                        exit = true;
                        break;
                    }
                    default :{
                        System.out.println("please given valid input");
                    }
                }
            }
        }
        catch(Exception e ){
            System.out.println("Invalid input, please refer the input GuideLine >resouces/inputGuide.txt ");
        }
    }
}
