package Patterns.Behavioral.command;

public class Stock {
    private String name ;
    private int quantity;
    public Stock(String n, int q){
        this.name = n;
        this.quantity = q;
    }
    public void sell(){
        System.out.println("[Sell order of quantity "+ quantity +" for stock "+name +" has been performed]");
    }
    public void buy(){
        System.out.println("[Buy order of quantity "+ quantity +" for stock "+name +" has been performed]");
    }
}
