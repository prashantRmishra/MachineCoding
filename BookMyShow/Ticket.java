public class Ticket {
    private int ticketId;
    private Event event;
    private int userId;
    private TicketStatus status;
    private double price;

    public Ticket(int ticketId, Event event, int userId, TicketStatus status, double price) {
        this.ticketId = ticketId;
        this.event = event;
        this.userId = userId;
        this.status = status;
        this.price = price;
    }

    public int getTicketId() {
        return ticketId;
    }
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public TicketStatus getStatus() {
        return status;
    }
    public void setStatus(TicketStatus status) {
        this.status = status;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    } 
}
