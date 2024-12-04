package BookMyShow;
public abstract class Payment {
    int paymentId;
    public abstract boolean makePayment(Ticket t, User u);
}
