package Patterns.creational.builder;

public class Main {

    /*
buns are: normal, sesame
patty: potato,fish-patty, etc
cheese:desi, swiss-cheese,etc
tomota: india, american,etc,
sauce: mint,masalasauce,etc
*/
    public static void main(String args[]){
        BurgerBuilder burgerBuilder = new BurgerBuilder();
        //note you can tak
        Burger burger = burgerBuilder.addCheese("userinput1").addPatty("userinput2").addTomato("userinput3").build();
        burger.print();
    }
}
