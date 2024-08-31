package Patterns.creational.prototype;

public class Main {
    public static void main(String args[]){
        Shape circle = new Circle();
        circle.setId("1");
        Shape rectangle = new Rectangle();
        rectangle.setId("2");
        
        ShapeCache.addShapeInCache(rectangle);
        ShapeCache.addShapeInCache(circle);

        Shape copyShape1 = (Shape) ShapeCache.cache.get(circle.getId());
        Shape copyShape2 =(Shape) ShapeCache.cache.get(rectangle.getId());

        System.out.println(copyShape1);
        System.out.println(copyShape2);


        /**
         * both Circle and Rectangle are following Liskov Substitution principle(SOLID principle) which states that
         * Object should be replaceable with their subtypes without affecting the correctness of the code
         * Other solid principle applicable are :
         * Single responsibility 
         * 
        */
    }

}
