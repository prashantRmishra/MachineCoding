package BookMyShow;
public class Reservation {
    private int reservationId;
    private Ticket ticket;
    public Reservation(int reservationId, Ticket ticket) {
        this.reservationId = reservationId;
        this.ticket = ticket;
    }
    public int getReservationId() {
        return reservationId;
    }
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }
    public Ticket getTicket() {
        return ticket;
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
