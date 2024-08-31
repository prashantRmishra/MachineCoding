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
