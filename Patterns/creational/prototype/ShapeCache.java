package Patterns.creational.prototype;

import java.util.HashMap;

public class ShapeCache {
    public static HashMap<String,Shape> cache = new HashMap<>();

    public static Shape cloneObject(String id){
        return (Shape)cache.get(id);
    }
    public static void addShapeInCache(Shape shape){
        cache.put(shape.getId(),shape);
    }
    
}
