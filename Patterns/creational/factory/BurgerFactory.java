package Patterns.creational.factory;

public class BurgerFactory {
    public Burger getCheeseBurger(){
        String ingredients[] = {"bun", "cheese", "tomato"};
        return new Burger(ingredients);
    } 
    public Burger getDeluxCheeseBurger(){
        String ingredients[] = {"bun", "cheese", "tomato","lettuce"};
        return new Burger(ingredients);
    }
    public Burger getVeganBurger(){
        String ingredients[] = {"bun", "special-sauce","veggie-patty"};
        return new Burger(ingredients);
    }
    
}
