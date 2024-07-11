package Patterns.creational.factory;

public class Burger {
    String ingredients[] =null;
    public Burger(String...a){
        this.ingredients = a;
    }
    public void print(){
        System.out.println("Ingredients in you burger are ");
        for(String s: ingredients){
            System.out.println(s);
        }
    }
}
