package bus_booking_service_like_redbus.exception;

public class NoBusFoundException extends RuntimeException {
    public NoBusFoundException(){
        super("No bus found for the given source and destination and travel date");
    }
}
