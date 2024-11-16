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
    public void setCity(String landmark) {
        this.city = landmark;
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
    public Location(String street, String landmark, String state, int pincode) {
        this.street = street;
        this.city = landmark;
        this.state = state;
        this.pincode = pincode;
    }
    

}
