package trello;

import java.util.HashMap;
import java.util.List;

import trello.model.Board;
import trello.model.Card;
import trello.model.PrivacyType;
import trello.model.SubProject;
import trello.model.User;

public class TrelloUtil {
    private static String boardNamePrefix = "B_";
    private static String subProjectPrefix = "SubProject_";
    private static String cardPrefix = "card_";
    //caches for boards, subprojects, and cards
    private HashMap<String,Board> boards = new HashMap<>();
    private HashMap<String,SubProject> subProjectsorLists = new HashMap<>();
    private HashMap<String,Card> cardsMap = new HashMap<>();
    private HashMap<String,User> users = new HashMap<>();
    public TrelloUtil(){
        createUser(new User("user1", "prashant", "prashant@gmail.com"));
        createUser(new User("user2", "sandeep", "sandeep@gmail.com"));
        createUser(new User("user3", "ajay", "ajay@gmail.com"));
        createUser(new User("user4", "rahul", "rahul@gmail.com"));
    }
    private void createUser(User u){
        if(!users.containsKey(u.getUserId())){
            users.put(u.getUserId(), u);
            return;
        }
        System.out.println("Given user with id "+ u.getUserId()+" already exists!");
    }
    public void createBoard(String boardName){
        Board board = new Board(boardName, PrivacyType.PUBLIC);
        //taking hascode value of the object as id
        board.setId(boardNamePrefix+board.hashCode());
        boards.put(board.getId(), board);
        System.out.println("Created board:"+board.getId());
    }
    public void updatePrivacyOfBoard(String id, String type){
        PrivacyType defaultPrivacy = PrivacyType.PUBLIC;
        if(type.equals(PrivacyType.PRIVATE.toString())){
            defaultPrivacy = PrivacyType.PRIVATE;
        }
        if(boards.containsKey(id)){
            boards.get(id).updatePrivacyType(defaultPrivacy);
            return;
        }
        System.out.println("board with given id does not exists");
    }
    public void updateNameOfBoard(String id, String name){
        if(boards.containsKey(id)){
            boards.get(id).updateName(name);;
            return;
        }
        System.out.println("board with given id does not exists");
    }
    public void showBoard(){
        if(boards.size() ==0){
            System.out.println("No boards to show");
            return;
        }
        List<Board> b = boards.values().stream().toList();
        System.out.println(b);
    }
    public void showBoard(String id){
        Board board = null;
        try {
            board = boards.values().stream().filter(b-> b.getId().equals(id)).toList().get(0);
            System.out.println(board);
        } catch (Exception e) {
            System.out.println("board with given id:"+id+" does not exit!");
        }
    }
    public void addMember(String id, String userId){
        if(!users.containsKey(userId)){
            System.out.println("User does not exist");
            return;
        }
        if(boards.containsKey(id)){
            boards.get(id).addMemberToThisBoard(users.get(userId));
            System.out.println("user added to the given board");
            return;
        }
        System.err.println("given board does not exist!");
    }
    public void removeMember(String id, String userId){
        if(!users.containsKey(userId)){
            System.out.println("User does not exist");
            return;
        }
        if(boards.containsKey(id)){
            boards.get(id).removeMember(users.get(userId));
            System.out.println("user removed from the given board");
            return;
        }
        System.out.println("given board or user does not exist!");
    }
    public void deleteBoard(String id){
        if(boards.containsKey(id)){
            boards.remove(id);
            System.out.println("given board is removed");
            return;
        }
        System.err.println("given board does not exist");
    }

    public void createSubProjectOrList(String boardId, String subProjectName){
        if(boards.containsKey(boardId)){
            Board b = boards.get(boardId);
            SubProject subProject = new SubProject(subProjectName);
            String subProjectId = subProjectPrefix+subProject.hashCode();
            subProject.SetSubjectId(subProjectId);
            b.addSubProject(subProject);
            subProjectsorLists.put(subProjectId, subProject);
            System.out.println("List or SubProject created with id : "+subProject.getId());
            return;
        }
        System.out.println("given board with id "+boardId+" does not exist");
    }
    public void showSubProjectOrList(String subProjectId){
       if(subProjectsorLists.containsKey(subProjectId)){
        System.out.println(subProjectsorLists.get(subProjectId));
        return;
       }
        System.out.println("List with the given id does not exist!");
    }
    public void updateSubProjectorList(String id, String name){
        if(subProjectsorLists.containsKey(id)){
            subProjectsorLists.get(id).updateProjectName(name);
            System.out.println("name updated for the given list or SubProject id "+ id);
            return;
        }
        System.out.println("List or SubProject with the given id does not exist!");
    }
    public void createCard(String subProjectOrListId, String cardName){
        if(subProjectsorLists.containsKey(subProjectOrListId)){
            Card c = new Card(cardName);
            String cardId = cardPrefix+c.hashCode();
            c.setId(cardId);
            subProjectsorLists.get(subProjectOrListId).addCardInProject(c);
            System.out.println("card created "+ cardId);
            cardsMap.put(cardId, c);
            return;
        }
        System.out.println("subProject with given id does not exist");

    }
    public void updateCardName(String cardId, String name){
        if(cardsMap.containsKey(cardId)){
            cardsMap.get(cardId).updateName(name);
            System.out.println("card name updated");
            return;
        }
        System.out.println("card: "+cardId+" does not exists ");
    }
    public void updateCardDescription(String cardId, String des){
        if(cardsMap.containsKey(cardId)){
            cardsMap.get(cardId).updateDescription(des);
            System.out.println("card description udpated");
            return;
        }
        System.out.println("card: "+cardId+" does not exists ");
    }
    public void assignCard(String cardId, String userId){
        if(users.get(userId)==null) {
            System.out.println("Given user with id "+userId+" does not exits");
            return;
        }
        if(cardsMap.containsKey(cardId)){
            cardsMap.get(cardId).setAssignedUser(users.get(userId));
            System.out.println("User with id "+ userId+" has been assinged to card with id "+ cardId);
            return;
        }
        System.out.println("ard with id "+ cardId);
    }
    public void removeAssigneeFromCard(String cardId){
        if(cardsMap.containsKey(cardId)){
            cardsMap.get(cardId).unassign();
            System.out.println("card is unassigned now");
            return;
        }
        System.out.println("card with id "+ cardId+" does not exists!");
    }
    public void showCard(String id){
        if(!this.cardsMap.containsKey(id)) System.out.println("card with id does not exist");
        else System.out.println(cardsMap.get(id));
    }
    public void showOthers(String option, String id) {
        switch (option) {
            case "BOARD":{
                this.showBoard(id);
                break;
            }
            case "LIST":{
                this.showSubProjectOrList(id);
                break;
            }
            case "CARD":{
                this.showCard(id);
                break;
            }
            default:{
                System.out.println("Please enter valid entry");
            }
        }
    }
    public void moveCardToDifferentSubProject(String cardId, String destinationSubProjectId) {
        SubProject destination = subProjectsorLists.getOrDefault(destinationSubProjectId, null);
        Card card = cardsMap.getOrDefault(cardId, null);
        if(card!=null){
            destination.addCardInProject(card);
            for(SubProject subProject : subProjectsorLists.values()){
                if (subProject.getCards().contains(card)) {
                    subProject.getCards().remove(card);
                }
            }
            System.out.println("card have been moved to destionation SubProject");
            return;
        }
        System.out.println("card or given List does not exist");
    }
    
}
