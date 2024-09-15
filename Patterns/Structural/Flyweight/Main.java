package Patterns.Structural.Flyweight;

public class Main {
    public static void main(String args[]){
        Flyweight flyweight1 = FlyweightFactory.getFlyweight('a');
        Flyweight flyweight2 = FlyweightFactory.getFlyweight('b');
        Flyweight flyweight3 = FlyweightFactory.getFlyweight('a');// will use the same object that is referenced by flyweight1

        flyweight1.display(1, 2);//'a' displayed at 1,2
        flyweight2.display(3, 4);//'b' displayed at 3,4
        flyweight3.display(5, 7); // 'a'(shared) displayed at 5,7
    }
}
