package bus_booking_service_like_redbus.controller;

import java.time.LocalDate;
import java.util.List;


import bus_booking_service_like_redbus.model.Bus;
import bus_booking_service_like_redbus.service.SearchService;
import bus_booking_service_like_redbus.service.SourceDestination;
import bus_booking_service_like_redbus.utility.Logger;

public class BusBookingController {
    private SearchService searchService;

    public BusBookingController(SearchService searchService) {
        this.searchService = searchService;
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
}
