package Patterns.creational.builder.example2;

public interface Builder {
    public Builder buildFoundation();
    public Builder buildWalls();
    public Builder addWindows();
    public Builder buildPool();
    public Builder addGarage();
    public House getHouse();
}
