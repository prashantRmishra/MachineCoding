public class User {
    private int userId;
    private String name;
    private String licenseNumber;
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLicenseNumber() {
        return licenseNumber;
    }
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
    public User(int userId, String name, String licenseNumber) {
        this.userId = userId;
        this.name = name;
        this.licenseNumber = licenseNumber;
    }
    
}
