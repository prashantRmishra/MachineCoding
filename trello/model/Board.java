package trello.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * This Board represents an independent Project of the company
*/
public class Board {
    private int boardCounter = 0;
    private String id;
    private String name;
    private PrivacyType type;
    private HashMap<String, SubProject> lists;
  
    private HashMap<String, User> members;
    
    public Board(String id, String name,PrivacyType type,int counter){
        this.id = id;
        this.name = name;
        this.type = type;
        boardCounter = counter;
        lists = new HashMap<>();
        members = new HashMap<>();
    }
    public int getCounter(){
        return this.boardCounter;
    }
    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public PrivacyType getType(){
        return this.type;
    }
    public void updatePrivacyType(PrivacyType type){
        this.type = type;
    }
    public void updateName(String name){
        this.name = name;
    }
    public void addMemberToThisBoard(User u){
        if(!members.containsKey(u.getUserId())){
            members.put(u.getUserId(), u);
            return;
        }
        System.out.println("User with the given id already exists!");
    }
    public void addSubProject(SubProject subProject){
        if(!lists.containsKey(subProject.getId())){
            lists.put(subProject.getId(), subProject);
            return;
        }
        System.out.println("SubProject with the given id already exists");
    }
    public void removeSubProject(SubProject subProject){
        if(lists.containsKey(subProject.getId())){
            lists.remove(subProject.getId());
            return ;
        }
        System.out.println("Given SubProject or list does not exists in this board ");
    }
    public void removeMember(User u){
        if(members.containsKey(u.getUserId())){
            members.remove(u.getUserId());
            return;
        }
        System.out.println("Given Member is not part of this Board!");
    }
    public HashMap<String,SubProject> getLists(){
        return this.lists;
    }
    public HashMap<String,User> getMembers(){
        return this.members;
    }
    public String toString(){
        String s = "id:"+id+", name:"+name+", privacy:"+type.toString()+", members:"+members+", listOfSubProjects:"+lists;
        return s;
    }
}
