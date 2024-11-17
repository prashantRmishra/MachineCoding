import java.util.ArrayList;
import java.util.List;

public class Factory {
    public static List<Event> getEvents(){
        List<Event> events  = new ArrayList<>();
        events.add(new Event(1, "movie1", "movie1", getVenueForEvent1(), getTicketsEvent1(),getArtistOfEvent1()));
        events.add(new Event(1, "movie1", "movie1", getVenueForEvent2(), getTicketsEvent2(), getArtistOfEvent2()));
        return events;
    }
    public static List<Ticket>  getTicketsEvent1(){
        List<Ticket> tickets  = new ArrayList<>();
        return tickets;

    }
    public static List<Ticket>  getTicketsEvent2(){
        List<Ticket> tickets  = new ArrayList<>();
        return tickets;

    }
    public static Artist getArtistOfEvent1(){
        return new Artist(1, "Shreya Ghoshal");
    }
    public static Artist getArtistOfEvent2(){
        return new Artist(1, "Arijit Singh");
    }
    public static Venue getVenueForEvent1(){
        return new Venue(22, "venue1", new Location("1234", "mumbai", "maharashatra", 1234));
    }
    public static Venue getVenueForEvent2(){
        return new Venue(23, "venue2", new Location("2345", "bangalore", "Karnataka", 4343434));
    }

    public static List<User> getUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User(2222, "prashant"));
        users.add(new User(34343, "ajay"));
        users.add(new User(33333, "sandeep"));
        users.add(new User(31212, "Neeraj"));
        return users;
    }
}
