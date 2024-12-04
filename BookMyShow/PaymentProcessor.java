package BookMyShow;
public class PaymentProcessor {

    private Payment payment;
    public PaymentProcessor(Payment payment){
        this.payment = payment;
    }

    public boolean makePayment(Ticket ticket,User user) {
        boolean response = this.payment.makePayment(ticket,user);
        if(response)
            ticket.setStatus(TicketStatus.booked);
        else{
           return false;
        }
        return true;

    }
}
