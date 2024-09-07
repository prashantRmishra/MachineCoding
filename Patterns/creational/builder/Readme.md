Builder is creational design pattern that is used to create object of the class with a lot of optional attributes.
Consider an example of creating a House that requires building foundation, building walls, windows and finally roof.
Note: above attributes are mandatory for creating a house, but certain attributes like creating pool, creating garage could be optional.
So, when such mandatory and optional parameter are mixed in creating an object, it is best to use builder design pattern.

For example A House having pool and garage can be considered as a Luxury House and a House without such amenities can be considered as a Simple House.

**Builder pattern has following parts**:
Builder: This interface defines all the steps required to build a product.
Concrete Builder: Implements the Builder interface to construct and assemble parts of the product.
Director: Oversees the construction process and ensures that all the steps are followed in order.
Product: The complex object that is being built.

Lets understand Builder pattern with the same above example

`House`

```java
package Patterns.creational.builder.example2;

public class House{
    private String foundation;
    private String walls;
    private String windows;
    private boolean hasPool;
    private boolean hasGarage;
    @Override
    public String toString() {
        return "House [foundation=" + foundation + ", walls=" + walls + ", windows=" + windows + ", hasPool=" + hasPool
                + ", hasGarage=" + hasGarage + "]";
    }
    public String getFoundation() {
        return foundation;
    }
    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }
    public String getWalls() {
        return walls;
    }
    public void setWalls(String walls) {
        this.walls = walls;
    }
    public String getWindows() {
        return windows;
    }
    public void setWindows(String windows) {
        this.windows = windows;
    }
    public boolean isHasPool() {
        return hasPool;
    }
    public void setHasPool(boolean hasPool) {
        this.hasPool = hasPool;
    }
    public boolean isHasGarage() {
        return hasGarage;
    }
    public void setHasGarage(boolean hasGarage) {
        this.hasGarage = hasGarage;
    }

}
```

`Builder`

```java
package Patterns.creational.builder.example2;

public interface Builder {
    public Builder buildFoundation();
    public Builder buildWalls();
    public Builder addWindows();
    public Builder buildPool();
    public Builder addGarage();
    public House getHouse();
}
```

`Concrete Builders`

`SimpleHouseBuilder`

```java
package Patterns.creational.builder.example2;

public class SimpleHouseBuilder implements Builder {
    House house;
    public SimpleHouseBuilder(){
        house = new House();
    }
    @Override
    public Builder buildFoundation() {
        this.house.setFoundation("Good quality foundation set");
        return this;
    }
    @Override
    public Builder buildWalls() {
        this.house.setWalls("new wall has been added");
        return this;
    }
    @Override
    public Builder addWindows() {
        this.house.setWindows("new windows has been installed");
        return this;
    }
    @Override
    public Builder buildPool() {
        this.house.setHasPool(false);
        return this;
    }
    @Override
    public Builder addGarage() {
       this.house.setHasGarage(false);
       return this;
    }
    @Override
    public House getHouse() {
        return this.house;
    }
    
}
```

`LuxuryHouseBuilder`

```java
package Patterns.creational.builder.example2;

public class LuxuryHouseBuilder implements Builder {
    House house;
    public LuxuryHouseBuilder(){
        house = new House();
    }
    @Override
    public Builder buildFoundation() {
        this.house.setFoundation("Good quality luxury foundation set");
        return this;
    }
    @Override
    public Builder buildWalls() {
        this.house.setWalls("new wall of has been added that can handle nuclear explosion");
        return this;
    }
    @Override
    public Builder addWindows() {
        this.house.setWindows("new windows has been installed that are bullet proof");
        return this;
    }
    @Override
    public Builder buildPool() {
        this.house.setHasPool(true);
        return this;
    }
    @Override
    public Builder addGarage() {
       this.house.setHasGarage(true);
       return this;
    }
    @Override
    public House getHouse() {
        return this.house;
    }

}
```

`Director`

```java
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
```
`Main`

```java
package Patterns.creational.builder.example2;

public class HouseMakerMain {
   public static void main(String args[]){
   //builder pattern without director class 
    Builder simpleHouseBuilder = new SimpleHouseBuilder();
    Builder luxuryHouseBuilder = new LuxuryHouseBuilder();
    House  simpleHouse = simpleHouseBuilder.addGarage().addWindows().buildFoundation().buildPool().buildWalls().getHouse();
    House  luxuryHouse = luxuryHouseBuilder.addGarage().addWindows().buildFoundation().buildPool().buildWalls().getHouse();
    System.out.println(simpleHouse);
    System.out.println(luxuryHouse);

    //with director class that oversees the creation of objects
    ConstructionDirector constructionDirectorOfSimpleHouse = new ConstructionDirector(new SimpleHouseBuilder());
    ConstructionDirector constructionDirectorOfLuxuryHouse = new ConstructionDirector(new LuxuryHouseBuilder());
    constructionDirectorOfSimpleHouse.constructHouse();
    System.out.println(constructionDirectorOfSimpleHouse.getHouse());
    constructionDirectorOfLuxuryHouse.constructHouse();
    System.out.println(constructionDirectorOfLuxuryHouse.getHouse());

   }
}
```

`Output`

```console
House [foundation=Good quality foundation set, walls=new wall has been added, windows=new windows has been installed, hasPool=false, hasGarage=false]
House [foundation=Good quality luxury foundation set, walls=new wall of has been added that can handle nuclear explosion, windows=new windows has been installed that are bullet proof, hasPool=true, hasGarage=true]
House [foundation=Good quality foundation set, walls=new wall has been added, windows=new windows has been installed, hasPool=false, hasGarage=false]
House [foundation=Good quality luxury foundation set, walls=new wall of has been added that can handle nuclear explosion, windows=new windows has been installed that are bullet proof, hasPool=true, hasGarage=true]
```
