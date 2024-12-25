package bus_booking_service_like_redbus.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import bus_booking_service_like_redbus.model.Booking;
import bus_booking_service_like_redbus.model.Bus;
import bus_booking_service_like_redbus.model.BusUser;
import bus_booking_service_like_redbus.model.Seat;
import bus_booking_service_like_redbus.service.BookingService;
import bus_booking_service_like_redbus.service.SearchService;
import bus_booking_service_like_redbus.service.SourceDestination;
import bus_booking_service_like_redbus.utility.Logger;

public class BusBookingController {
    private SearchService searchService;
    private BookingService bookingService;

    public BusBookingController(SearchService searchService,BookingService bookingService) {
        this.searchService = searchService;
        this.bookingService = bookingService;
    }

    public List<Bus> searchBus(String source, String destination, LocalDate travelDate) {
        List<Bus> result = null;
        try {
            result = searchService.searchBus(new SourceDestination(source, destination), travelDate);
        } catch (Exception e) {
            Logger.log(e.getMessage());
        }
        return result;
    }

    public Booking bookSeat(Bus bus,int seatNo, BusUser user){
        Booking booking = null;
        try {
            booking =  bookingService.bookSeat(bus, seatNo, user);
        } catch (Exception e) {
            Logger.log(e.getMessage());
        }
        return booking;
    }

    public  boolean cancelBooking(BusUser user, Booking booking){
        boolean isCancelled = false;
        try {
            isCancelled = bookingService.cancelBooking(user, booking);
        } catch (Exception e) {
            Logger.log(e.getMessage());
        }
        return isCancelled;
    }

    public void getAllBookings(){
        for(Map.Entry<BusUser,List<Booking>> entry : bookingService.getAllBookingDetails().entrySet()){
            Logger.log("Booking of " + entry.getKey().getUserName());
            for(Booking b : entry.getValue()){
                Logger.log(b.toString());
            }
        }
    }

    public void getBookingForUser(BusUser user){
        List<Booking> bookings  = null;
        try {
            bookings = bookingService.getAllBookingForUser(user);
            for(Booking b : bookings){
                Logger.log(b.toString());
            }
        } catch (Exception e) {
            Logger.log(e.getMessage());
        }
    }

}
