package BookMyShow;
public class CreditPayment extends Payment {
    public CreditPayment(){
    }
    @Override
    public boolean makePayment(Ticket t, User u) {
        paymentId = t.getTicketId()+u.getUserId();
        System.out.println("payment made via credit card");
        return true;
    }
    
}
