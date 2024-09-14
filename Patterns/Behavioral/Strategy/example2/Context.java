package Patterns.Behavioral.Strategy.example2;

public class Context {
    private Strategy strategy;
    public Context(Strategy s){
        this.strategy = s;
    }
    public int executeStrategy(int a, int b){
        return strategy.doOperation(a, b);
    }
}
