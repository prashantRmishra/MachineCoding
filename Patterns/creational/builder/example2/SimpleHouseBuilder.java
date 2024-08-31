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
