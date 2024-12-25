package bus_booking_service_like_redbus.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import bus_booking_service_like_redbus.exception.BookingDoesNotExistsException;
import bus_booking_service_like_redbus.exception.FailedToBookBusSeatException;
import bus_booking_service_like_redbus.exception.FailedToCancelBusBookingException;
import bus_booking_service_like_redbus.model.Booking;
import bus_booking_service_like_redbus.model.Bus;
import bus_booking_service_like_redbus.model.BusUser;
import bus_booking_service_like_redbus.model.Seat;
import bus_booking_service_like_redbus.model.SeatStatus;

public class BookingService {
    //book seat in the given bus
    private PaymentService paymentService;
    private Map<BusUser,List<Booking>> bookings;
    private ReentrantLock lock = new ReentrantLock();
    public BookingService(PaymentService service){
        this.paymentService = service;
        bookings = new HashMap<>();
    }
    public Booking bookSeat(Bus bus,int seatNo,BusUser user) throws FailedToBookBusSeatException{
        Booking booking = null;
        List<Booking> b = bookings.getOrDefault(user, new ArrayList<>());
        Seat seat = bus.getSeats().get(seatNo-1);
        lock.lock();
        //critical section
        try {
            
            if(seat.getStatus().equals(SeatStatus.EMPTY)){
                //update status to reserved 
                seat.updateStatus(SeatStatus.RESERVED);
                seat.updateUser(user);
                //reserve seat and go for payment
                //assuming payment is always success in this case
                if(paymentService.doPayment(user,bus.getFare())){
                    booking = new Booking(user, bus, seatNo, seat);
                    seat.updateStatus(SeatStatus.BOOKED);
                    if(bookings.containsKey(user)){
                        bookings.get(user).add(booking);
                    }
                    else{
                        b.add(booking);
                        bookings.put(user, b);
                    }
                }
            }
            //end critical section
        } finally{
            if(booking ==null){
                seat.updateStatus(SeatStatus.EMPTY);
                seat.updateUser(null);
                throw new FailedToBookBusSeatException();
            }
            lock.unlock();
        }
        return booking;
    }
    public boolean cancelBooking(BusUser user, Booking booking){
        boolean isCancelled = false;
        lock.lock();
        try{
            List<Booking> userBookings = bookings.get(user);

            if(!userBookings.contains(booking)){
                throw new BookingDoesNotExistsException();
            }
            else{
                booking.getSeat().updateStatus(SeatStatus.EMPTY);
                booking.getSeat().updateUser(null);
                isCancelled = true;
            }
        }
        finally{
            if(!isCancelled){
                throw new FailedToCancelBusBookingException();
            }
        }
        return isCancelled;
    }
    public Map<BusUser,List<Booking>> getAllBookingDetails(){
        return bookings;
    }
    public List<Booking> getAllBookingForUser(BusUser user) throws Exception{
        List<Booking> books = this.bookings.get(user);
        if(books ==null || books.isEmpty()){
            throw new BookingDoesNotExistsException();
        } 
        return books;
    }
}
