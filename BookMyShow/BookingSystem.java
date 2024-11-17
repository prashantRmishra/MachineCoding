
import java.util.List;
public class BookingSystem {
    private ReservationManger reservationManger;
    private PaymentProcessor paymentProcessor;
    private List<User> users;
    private List<Event> events;
    public BookingSystem(List<User> users,List<Event> events, ReservationManger manager,PaymentProcessor processor){
        this.users = users;
        this.paymentProcessor = processor;
        this.events = events;
        this.reservationManger = manager;
    }
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    public List<Event> getEvents() {
        return events;
    }
    public void setEvents(List<Event> events) {
        this.events = events;
    }
    public List<Event> findMovies(String movie) {
        return Catalog.findMovies(movie);
    }
    public Ticket reserveTicket(Event event, User user){
        return reservationManger.reserveTicket(event, user);
    }
    public void unReserveTicket(Ticket t){
        reservationManger.unReserveTicket(t.getTicketId());
    }
    public boolean makePayment(Ticket t, User u ){
        boolean status = paymentProcessor.makePayment(t, u);
        if(!status){
            t.setStatus(TicketStatus.available);
            unReserveTicket(t);
            System.out.println("Reservation removed for ticket for user" +u.getName());
            return false;
        }
        return true;
    }
}
