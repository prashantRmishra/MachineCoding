package bus_booking_service_like_redbus.model;

public class Seat {
    private int setNo;
    private BusUser user;
    private SeatStatus status;
    public Seat(int setNo, BusUser user) {
        this.setNo = setNo;
        this.user = user;
        status = SeatStatus.EMPTY;
    }
    public int getSetNo() {
        return setNo;
    }
    public BusUser getUser() {
        return user;
    }
    public SeatStatus getStatus() {
        return status;
    }

}
