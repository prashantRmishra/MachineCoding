import java.util.List;
import java.util.stream.Collectors;

public class Catalog {
    public static List<Event> findMovies(String name){
        return Factory.getEvents().stream().filter(event-> event.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }
    public static List<Event> findMoviesBasedOnLocation(Location location){
        return Factory.getEvents().stream().filter(event -> {
        Location l = event.getVenue().getLocation();
        return l.getCity().equals(location.getCity()) || 
               l.getPincode() == location.getPincode() || 
               l.getStreet().equals(location.getStreet());
    }).toList();
    }
}
