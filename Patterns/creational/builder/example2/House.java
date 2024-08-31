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