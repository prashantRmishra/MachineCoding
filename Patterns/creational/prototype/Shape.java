package Patterns.creational.prototype;


public class Shape implements Cloneable {
    private String id;
    protected String shape;
    
    @Override
    public String toString() {
        return "Shape [id=" + id + ", shape=" + shape + "]";
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getShape() {
        return shape;
    }

    @Override
    public Object clone(){
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
