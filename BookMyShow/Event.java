import java.util.List;

public class Event {
    private int eventId;
    private String name;
    private String description;
    private Venue venue;
    private List<Ticket> tickets;
    private Artist artist;
    public Event(int eventId, String name, String description, Venue venue, List<Ticket> tickets, Artist artist) {
        this.eventId = eventId;
        this.name = name;
        this.description = description;
        this.venue = venue;
        this.tickets = tickets;
        this.artist = artist;
    }
    public int getEventId() {
        return eventId;
    }
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Venue getVenue() {
        return venue;
    }
    public void setVenue(Venue venue) {
        this.venue = venue;
    }
    public List<Ticket> getTickets() {
        return tickets;
    }
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    public Artist getArtist() {
        return artist;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

}
