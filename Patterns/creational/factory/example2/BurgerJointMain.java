package Patterns.creational.factory.example2;

public class BurgerJointMain {
    public static void main(String args[]){
        BurgerFactory factory = new BurgerFactory();
        Burger burger = factory.getBurgerForCustomer("cheese");// use can ask for "vegan" or "delux" burger as well
        burger.getBurger();

    }
}
