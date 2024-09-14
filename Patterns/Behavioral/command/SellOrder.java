package Patterns.Behavioral.command;

public class SellOrder implements Order {
    private Stock stock;
    public SellOrder(Stock s){
        this.stock = s;
    }
    @Override
    public void execute(){
        stock.sell();
    }
}
