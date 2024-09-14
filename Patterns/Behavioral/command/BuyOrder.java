package Patterns.Behavioral.command;

public class BuyOrder implements Order {
    private Stock stock;
    public BuyOrder(Stock s){
        this.stock = s;
    }
    @Override
    public void execute(){
        stock.buy();
    }
}
