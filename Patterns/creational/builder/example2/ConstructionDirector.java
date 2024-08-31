package Patterns.creational.builder.example2;

public class ConstructionDirector {
    Builder builder;
    public ConstructionDirector(Builder builder){
        this.builder = builder;
    }
    public void constructHouse(){
        builder.buildFoundation().buildWalls().addWindows().addGarage().buildPool();
    }
    public House getHouse(){
        return this.builder.getHouse();
    }
}
