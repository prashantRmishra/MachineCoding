package bus_booking_service_like_redbus.exception;

public class FailedToCancelBusBookingException extends RuntimeException {
    public FailedToCancelBusBookingException(){
        super("failed to cancel bus booking or the given booking does not exists!!");
    }
}
