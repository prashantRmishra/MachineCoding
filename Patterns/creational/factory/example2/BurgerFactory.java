package Patterns.creational.factory.example2;

public class BurgerFactory {
    public Burger getBurgerForCustomer(String burgerType){
        switch (burgerType) {
            case "cheese": return  new CheeseBurger();
            case "delux": return new DeluxBurger();
            case "vegan": return new VeganBurger();
            default: return null;
             
        }
    }
}
