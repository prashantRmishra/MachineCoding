package BookMyShow;
import java.util.List;

public class Driver {
    public static void main(String args[]){
        //basic flow
        //Init
        BookingSystem  system = new BookingSystem(Factory.getUsers(), Factory.getEvents(), new ReservationManger(),new PaymentProcessor(new UPIPayment()));
        //user searches for movie (out of scope is the location based searching)
        User user = system.getUsers().get(0);
        List<Event> events = system.findMovies("movie");
        //user chooses a movie 
        Event event= events.get(0);
        //user reserves the seat/ticket
        Ticket ticket = system.reserveTicket(event,user);
        //user makes the payment and ticket is booked
        boolean paymentDone = system.makePayment(ticket,user); 
        //user gets the ticket and enjoys the movie
        if(paymentDone) System.out.println("payment is successful, user will receive the ticket on the mail");

    }
}
