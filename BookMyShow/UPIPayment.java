package BookMyShow;
public class UPIPayment extends Payment{

    @Override
    public boolean makePayment(Ticket t, User u) {
        paymentId = t.getTicketId()+u.getUserId();
        System.out.println("payment made via UPI");
        return true;
    }
    
}
