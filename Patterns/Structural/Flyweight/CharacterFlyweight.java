package Patterns.Structural.Flyweight;

public class CharacterFlyweight implements Flyweight {
    private char ch;
    public CharacterFlyweight(char c){
        this.ch  = c;
    }
    @Override
    public void display(int x ,int y){
        System.out.println("[drawing character: "+this.ch+" at co-ordinates:("+x+","+y+")]");
    }
    
}
