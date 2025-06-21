import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

enum SeatStatus{
    booked,available,locked;
}
class Seat{
    String seatId;
    SeatStatus status;
    String lockedByUserId;
    long lockTime;
    public Seat(String id){
        this.seatId = id;
        this.status = SeatStatus.available;
    }
}
class Screen{
    String screenId;
    List<Seat> seats;
    public Screen(String id, int n){
        seats = new ArrayList<>();

        for(int i =1;i<=n;i++){
            seats.add(new Seat("S"+i));
        }
    }
}
class Movie{
    String movieId;
    String movieName;
    public Movie(String movieId, String name){
        this.movieId = movieId;
        this.movieName = name;
    }
}
class Show{
    String showId;
    Screen screen;
    Movie movie;
    LocalDateTime startTime;
    public Show(String id, Screen screen,Movie movie){
        this.showId = id;
        this.screen = screen;
        this.movie = movie;
        this.startTime = LocalDateTime.now();
    }
}
class Theater{
    String theaterId;
    String name;
    String city;
    List<Screen> screens;
    public Theater(String id, String name, String city, List<Screen> screens){
        this.theaterId = id;
        this.name = name;
        this.city = city;
        this.screens = screens;
    }

}
class Booking{
    String bookingId;
    String showId;
    String userId;
    String screenId;
    List<String> seatIds;
    public Booking(String bId, String sId, String uId, List<String> seIds){
        this.bookingId = bId;
        this.showId = sId;
        this.userId = uId;
        this.seatIds = seIds;
    }
}
class SeatLockingService{
    long lockTimeOut = 0;// in seconds
    Map<String,Map<String, Seat>> showSeatMaps = new ConcurrentHashMap<>();

    public SeatLockingService(long lockTimeOut){
        this.lockTimeOut = lockTimeOut;
    }
    public void createShowSeatMap(Show show){
        Map<String,Seat> seatMapping = new ConcurrentHashMap<>();
        for(Seat seat : show.screen.seats){
            seatMapping.put(seat.seatId, new Seat(seat.seatId));
        }
        showSeatMaps.put(show.showId, seatMapping);
    }

    public boolean lockSeats(List<String>  seatIds, String userId, String showId){
        
        //check if seats are already locked or booked
        for(String  seatId : seatIds){
            Seat seat = showSeatMaps.get(showId).get(seatId);
            if(!seat.status.equals(SeatStatus.available)) return false;
        }
        synchronized(this){
            for(String  seatId : seatIds){
                Seat seat = showSeatMaps.get(showId).get(seatId);
                seat.status = SeatStatus.locked;
                seat.lockedByUserId = userId;
                seat.lockTime = System.currentTimeMillis();
            }
        }
        return true;
    }

    public boolean bookSeats(List<String> seatIds, String userId, String showId){
        for(String seatId : seatIds){
            Seat seat = showSeatMaps.get(showId).get(seatId);
            if(seat.status!=SeatStatus.locked || !seat.lockedByUserId.equals(userId)) return false;
            if(System.currentTimeMillis() - seat.lockTime > lockTimeOut) return false; //timeout happened 
        }
        for(String seatId : seatIds){
            Seat seat = showSeatMaps.get(showId).get(seatId);
            seat.status = SeatStatus.booked;
        }
        return true;
    }
    public void makeSeatsAvailable(List<String> seats, String showId){
        for(String seatId : seats){
            Seat seat = showSeatMaps.get(showId).get(seatId);
            seat.status = SeatStatus.available;
        }
    }
    public List<String> getAvailableSeats(String showId){
        List<String> seats = new ArrayList<>();
        for(Seat seat : showSeatMaps.get(showId).values()){
            if(seat.status.equals(SeatStatus.available)){
                seats.add(seat.seatId);
            }
        }
        return seats;
    }
}
class BookingService{
    Map<String, Booking> bookings;
    SeatLockingService seatLockingService;
    public BookingService(SeatLockingService seatLockingService){
        this.seatLockingService =seatLockingService;
        bookings = new ConcurrentHashMap<>();
    }

    public Booking bookTicket(List<String> seats, String showId, String userId){
        boolean locked = seatLockingService.lockSeats(seats, userId, showId);
        if(!locked) {
            System.out.println("failed to lock seats, as these seats are not available");
            return null;
        }
        boolean booked = seatLockingService.bookSeats(seats, userId, showId);
        if(!booked) {
            System.out.println("failed to book seats, as the lock time is lapsed");
            seatLockingService.makeSeatsAvailable(seats, showId);
            return null;
        }

        Booking booking = new Booking(UUID.randomUUID().toString(), showId, userId, seats);
        bookings.put(booking.bookingId, booking);
        return booking;
    }

    public List<Booking> getBookings(String userId){
        List<Booking> userBookings = new ArrayList<>();
        for(Booking b : bookings.values()){
            if(b.userId.equals(userId)) userBookings.add(b);
        }
        return userBookings;
    }
}
class User{
    String userId;
    String name;
    public User(String email, String name){
        this.userId = email;
        this.name = name;
    }
}
public class BookMyShow {
    public static void main(String[] args) {
        Movie movie = new Movie("m1", "3 Idiots");
        Screen sc1 = new Screen("sc1", 20);
        Screen sc2 = new Screen("sc2", 70);
        List<Screen> screens = List.of(sc1,sc2);
        Show s1 = new Show("s1", sc1,movie);
        Show s2 = new Show("s2", sc2,movie);

        Theater theater = new Theater("th1", "CinePolis", "thane", screens);


        SeatLockingService seatLockingService = new SeatLockingService(120);
        seatLockingService.createShowSeatMap(s1);
        seatLockingService.createShowSeatMap(s2);



        BookingService bookingService = new BookingService(seatLockingService);
        User u = new User("email@gmail", "prashant");
        List<String> selectedSeats = List.of("S1","S2");

        Booking booking  = bookingService.bookTicket(selectedSeats, s1.showId  , u.userId);
        if(booking == null) System.out.println("booking failed");
        else System.out.println("booking sucess "+ booking.bookingId);

    }
    
}
/*
output:

booking sucess 20d809de-f634-4466-841b-2c25871677b3
*/