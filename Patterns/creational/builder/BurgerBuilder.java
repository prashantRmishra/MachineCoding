package Patterns.builder;

import java.util.ArrayList;
import java.util.List;



public class BurgerBuilder {
    List<String> ingredients = new ArrayList<>(); 

    public BurgerBuilder addCheese(String cheese){
        ingredients.add(cheese);
        return this;
    }
    public BurgerBuilder addTomato(String tomato){
        ingredients.add(tomato);
        return this;
    }
    public BurgerBuilder addPatty(String patty){
        ingredients.add(patty);
        return this;
    }
    public Burger build(){
        return new Burger(ingredients);
    }
    
}
