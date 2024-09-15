package Patterns.Structural.Flyweight;

import java.util.HashMap;

public class FlyweightFactory {
    private static HashMap<Character,Flyweight> flyweights = new HashMap<>();
    public static Flyweight getFlyweight(char c){
        Flyweight flyweight = flyweights.getOrDefault(c,null);
        if(null==flyweight){
            flyweight = new CharacterFlyweight(c);
            flyweights.put(c,flyweight);
        }
        return flyweight;
    }
}
