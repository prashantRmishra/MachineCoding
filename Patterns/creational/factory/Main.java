package Patterns.creational.factory;

public class Main {

    public static void main(String args[]){
        BurgerFactory factory  =new BurgerFactory();
        factory.getCheeseBurger().print();
        factory.getDeluxCheeseBurger().print();
        factory.getVeganBurger().print();
    }
}
