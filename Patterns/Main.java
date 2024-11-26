package Patterns;

import java.util.HashMap;
import java.util.Map;
abstract class Flyweight{
    protected char c;
    public Flyweight(char c){
        this.c = c;
    }
    public char getChar(){
        return this.c;
    }
    public void render(int x, int y){
        //render the char c at location x and y on the editor screen
    }
}
class CharObject extends Flyweight {
    public CharObject(char c){
        super(c);
    }
}
class CharacterCache{
    public static Map<Character,Flyweight> map = new HashMap<>();

    public static Flyweight getChar(char c){
        Flyweight charObject = map.get(c);
        if(charObject ==null){
            charObject = new CharObject(c);
            addCharToCache(charObject);
        }
        return charObject;
    }
    public static void addCharToCache(Flyweight obj){
        map.put(obj.getChar(), obj);
    }
}



public class Main {
    public static void main(String[] args) {
        Flyweight charObj1 = CharacterCache.getChar('a');
        charObj1.render(12, 32);/// extrinsic property
        Flyweight charObj2 = CharacterCache.getChar('a');// same as previous one
        charObj2.render(54, 65);
    }
}
