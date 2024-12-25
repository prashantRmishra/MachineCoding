package bus_booking_service_like_redbus.model;

import bus_booking_service_like_redbus.BusServiceUtility;

public class Booking {
    private int bookingId;
    private BusUser passenger;
    private Bus bus;
    private double fare;
    private Seat seat;
    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId + ", bus=" + bus.getBusNo()+", date "+ bus.getTravelDate()+", source "+ bus.getSource()+", dest "+ bus.getDestination() + ", fare=" + fare
                + ", seat=" + seat.getSetNo() + "]";
    }
    public Booking(BusUser passenger, Bus bus, double fare, Seat seat) {
        this.passenger = passenger;
        this.bus = bus;
        this.fare = fare;
        this.seat = seat;
        this.bookingId = BusServiceUtility.getUniqueId();
    }
    public int getBookingId() {
        return bookingId;
    }
    public BusUser getPassenger() {
        return passenger;
    }
    public Bus getBus() {
        return bus;
    }
    public double getFare() {
        return fare;
    }
    public Seat getSeat() {
        return seat;
    }
}
