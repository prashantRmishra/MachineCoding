package BookMyShow;
public class Location {
    private String street;
    private String city;
    private String state;
    private int pincode;
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public int getPincode() {
        return pincode;
    }
    public void setPincode(int pincode) {
        this.pincode = pincode;
    }
    public Location(String street, String city, String state, int pincode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }
    
}
