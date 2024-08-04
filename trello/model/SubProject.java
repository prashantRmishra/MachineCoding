package trello.model;

import java.util.ArrayList;
import java.util.List;

public class SubProject {
    private String id;
    private String name;
    private List<Card> cards;
    public SubProject(String name){
        this.name = name;
        cards = new ArrayList<>();
    }
    public void SetSubjectId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public List<Card> getCards(){
        return this.cards;
    }
    public String getSubProjectName(){
        return this.name;
    }
    public void updateProjectName(String newName){
        this.name = newName;
    }
    public void addCardInProject(Card c){
        this.cards.add(c);
    }
    public String removeCardInProject(Card c){
        return this.cards.remove(c)? "Card removed":"Card "+c.toString()+" does not exists!";
    }

    public String toString(){
        String s = "id:"+id+", name:"+name+", cards:"+cards;
        return s;
    }
}
