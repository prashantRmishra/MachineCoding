package CarRentalService;

public class Bill {
    private int billId;
    private Reservation reservation;
    private boolean isPaid;
    private double billAmount;
    public int getBillId() {
        return billId;
    }
    public void setBillId(int billId) {
        this.billId = billId;
    }
    public Reservation getReservation() {
        return reservation;
    }
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    public boolean isPaid() {
        return isPaid;
    }
    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }
    public Bill(int billId, Reservation reservation, boolean isPaid) {
        this.billId = billId;
        this.reservation = reservation;
        this.isPaid = isPaid;
        this.billAmount = calculateBill();
    }
    public double calculateBill(){
        int days = reservation.getNoOfDaysBookedFor();
        if(days <=0) return reservation.getDailyCost();
        else return reservation.getDailyCost()*days;
    }
    public double getBillAmount(){
        return this.billAmount;
    }

    
}
