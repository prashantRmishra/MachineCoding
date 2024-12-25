package bus_booking_service_like_redbus;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bus_booking_service_like_redbus.controller.BusBookingController;
import bus_booking_service_like_redbus.model.Booking;
import bus_booking_service_like_redbus.model.Bus;
import bus_booking_service_like_redbus.model.BusType;
import bus_booking_service_like_redbus.model.BusUser;
import bus_booking_service_like_redbus.model.Operator;
import bus_booking_service_like_redbus.model.Seat;
import bus_booking_service_like_redbus.service.BookingService;
import bus_booking_service_like_redbus.service.PaymentService;
import bus_booking_service_like_redbus.service.SearchService;
import bus_booking_service_like_redbus.service.SourceDestination;
import bus_booking_service_like_redbus.utility.Functions;
import bus_booking_service_like_redbus.utility.Logger;

public class Driver {
    public static void main(String[] args) {
        //creating actors i.e users
        BusUser u1 = new BusUser("prashant");
        BusUser u2 = new BusUser("sandeep");
        BusUser u3 = new BusUser("ajay");
        //creating Operator
        Operator op1 = new Operator("Laxmi Travelers");
        Operator op2 = new Operator("Tirupati Travelers");

        List<Seat> seatsAllocation = new ArrayList<>();
        for(int i =0;i<40;i++){
            seatsAllocation.add(new Seat(i+1, null));
        }
        //creating buses
        Bus b1 = new Bus(123, op1, "mumbai", "bangalore", 4, 500 , BusType.AC,Functions.getDate("29/12/2024"),LocalTime.of(23,0,0), LocalTime.of(17,0,0),seatsAllocation, new BusUser("Umesh D."));
        Bus b2 = new Bus(34343, op2, "delhi", "bangalore", 4, 900 , BusType.AC,Functions.getDate("01/01/2025"),LocalTime.of(00,0,0), LocalTime.of(17,0,0),seatsAllocation, new BusUser("Ramesh D."));
        

        List<Bus> mumbaiToBangaloreBusList = new ArrayList<>();
        mumbaiToBangaloreBusList.add(b1);
        List<Bus> delhiToBangaloreBusList = new ArrayList<>();
        delhiToBangaloreBusList.add(b2);

        Map<SourceDestination,List<Bus>> busesMap =new HashMap<>();
        SourceDestination sd1 = new SourceDestination("mumbai", "bangalore");
        SourceDestination sd2 = new SourceDestination("delhi", "bangalore");

        busesMap.put(sd1, mumbaiToBangaloreBusList);
        busesMap.put(sd2, delhiToBangaloreBusList);


        //searching bus
        SearchService searchService = new SearchService(busesMap);
        BookingService bookingService =new BookingService(new PaymentService());
        BusBookingController controller = new BusBookingController(searchService,bookingService);
        System.out.println(controller.searchBus("mumbai", "bangalore", Functions.getDate("29/12/2024")));


        //booking seat in a bus
        Booking booking1 = controller.bookSeat(b2, 23, u1);
        Logger.log(booking1.toString());
        //get all bookings
        controller.getAllBookings();
        //get bookings made by user u1
        controller.getBookingForUser(u1);
        //cancel booking1 of u1
        Logger.log(controller.cancelBooking(u1, booking1)? "cancelled ": "failed to cancel the given booking");


        
    }
}
