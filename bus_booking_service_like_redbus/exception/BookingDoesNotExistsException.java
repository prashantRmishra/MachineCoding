package bus_booking_service_like_redbus.exception;

public class BookingDoesNotExistsException extends RuntimeException {
    public BookingDoesNotExistsException(){
        super("the given booking does not exists!!");
    }
}
