package BookMyShow;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationManger {
    private Map<Integer,Reservation>  reservations;

    public ReservationManger(){
        reservations = new HashMap<>();
    }
    public Reservation getReservation(int id){
        return reservations.getOrDefault(id, null);
    }
    public synchronized Ticket reserveTicket(Event event,User user){
        List<Ticket> tickets = event.getTickets();
        Ticket ticket = tickets.stream().filter(t-> t.getStatus().equals(TicketStatus.available)).findFirst().orElse(null);
        ticket.setStatus(TicketStatus.reserved);
        ticket.setUserId(user.getUserId());
        Reservation reservation =new Reservation(ticket.getTicketId(), ticket);
        reservations.put(reservation.getReservationId(), reservation);
        return ticket;
    }
    public Reservation unReserveTicket(int id){
        return reservations.remove(id);
    }
}
