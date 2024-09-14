package Patterns.Behavioral.Strategy.example2;

public class SubtractStrategy implements Strategy {
    @Override
    public int doOperation(int a, int b){
        return a-b;
    }
}
