package Patterns.Behavioral.Strategy.example2;

public class AdditionStrategy implements Strategy {
    @Override
    public int doOperation(int a, int b){
        return a+b;
    }
}
