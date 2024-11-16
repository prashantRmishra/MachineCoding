import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {

    private int reservationId;
    private Vehicle vehicle;
    private User user;
    private LocalDate bookingDate;
    private LocalDate bookedFrom;
    private LocalDate bookedTo;
    private ReservationStatus reservationStatus;
    private Location pickupLocation;
    private Location dropLocation;

    public Reservation(int reservationId, Vehicle vehicle, User user, String bookingDate, String bookedFrom,
            String bookedTo, ReservationStatus reservationStatus, Location pickupLocation, Location dropLocation) {
        this.reservationId = reservationId;
        this.vehicle = vehicle;
        this.user = user;
        this.bookingDate = getDate(bookingDate);
        this.bookedFrom = getDate(bookedFrom);
        this.bookedTo = getDate(bookedFrom);
        this.reservationStatus = reservationStatus;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
    }
    public LocalDate getDate(String date){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-mm-yyyy"));
    }

    // some ops relation to reservation

    public void updatedReservationStatus(ReservationStatus status){
        this.reservationStatus = status;
    }
    public int getNoOfDaysBookedFor(){
        return this.bookedFrom.getDayOfMonth() -this.bookedTo.getDayOfMonth();
    }

    public double getDailyCost() {
        return vehicle.getDailyCost();
    }

}
