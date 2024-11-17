public class Venue {
    private int venueId;
    private String venueName;
    private Location location;
    public int getVenueId() {
        return venueId;
    }
    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }
    public String getVenueName() {
        return venueName;
    }
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public Venue(int venueId, String venueName, Location location) {
        this.venueId = venueId;
        this.venueName = venueName;
        this.location = location;
    }
    

}
