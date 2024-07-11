package Patterns.builder;

import java.util.List;

public class Burger {
    List<String> ingredients = null;
    public Burger(List<String> ingredients){
        this.ingredients = ingredients;
    }

    public void print(){
        System.out.println("Ingredients in you burger are ");
        for(String s: ingredients){
            System.out.println(s);
        }
    }
}
