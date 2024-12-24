package bus_booking_service_like_redbus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bus_booking_service_like_redbus.controller.BusBookingController;
import bus_booking_service_like_redbus.model.Bus;
import bus_booking_service_like_redbus.model.BusType;
import bus_booking_service_like_redbus.model.BusUser;
import bus_booking_service_like_redbus.model.Operator;
import bus_booking_service_like_redbus.model.Seat;
import bus_booking_service_like_redbus.service.SearchService;
import bus_booking_service_like_redbus.service.SourceDestination;

public class Driver {
    public static void main(String[] args) {
        //creating Operator
        Operator op1 = new Operator("Laxmi Travelers");
        Operator op2 = new Operator("Tirupati Travelers");

        List<Seat> seatsAllocation = new ArrayList<>();
        for(int i =0;i<40;i++){
            seatsAllocation.add(new Seat(i+1, null));
        }
        //creating buses
        Bus b1 = new Bus(123, op1, "mumbai", "bangalore", 4, 500 , BusType.AC,LocalDate.of(2024,12,29),LocalTime.of(23,0,0), LocalTime.of(17,0,0),seatsAllocation, new BusUser("Umesh D."));
        Bus b2 = new Bus(34343, op2, "delhi", "bangalore", 4, 900 , BusType.AC,LocalDate.of(2025,1,1),LocalTime.of(00,0,0), LocalTime.of(17,0,0),seatsAllocation, new BusUser("Ramesh D."));
        
        List<Bus> mumbaiToBangaloreBusList = new ArrayList<>();
        mumbaiToBangaloreBusList.add(b1);
        List<Bus> delhiToBangaloreBusList = new ArrayList<>();
        delhiToBangaloreBusList.add(b2);

        Map<SourceDestination,List<Bus>> busesMap =new HashMap<>();
        busesMap.put(new SourceDestination("mumbai", "bangalore"), mumbaiToBangaloreBusList);
        busesMap.put(new SourceDestination("delhi", "bangalore"), delhiToBangaloreBusList);

        SearchService searchService = new SearchService(busesMap);
        BusBookingController controller = new BusBookingController(searchService);
        System.out.println(controller.searchBus("mumbai", "bangalore", LocalDate.now()).size());
        for(Bus bus : controller.searchBus("mumbai", "bangalore", LocalDate.now())){
            
            System.out.println("here");
        }
    }
}
