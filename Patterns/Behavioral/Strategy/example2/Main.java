package Patterns.Behavioral.Strategy.example2;

public class Main {
    public static void main(String args[]){
        Context context = new Context(new AdditionStrategy());
        int a = 9, b = 6;
        System.out.println("Add=>"+a+","+b+": "+ context.executeStrategy(a, b));
        Context context2 = new Context(new SubtractStrategy());
        System.out.println("Subtract=>"+a+","+b+": "+ context2.executeStrategy(a, b));
        Context context3 = new Context(new MultiplyStrategy());
        System.out.println("Multiply=>"+a+","+b+": "+ context3.executeStrategy(a, b));
    }
}
