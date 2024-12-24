package bus_booking_service_like_redbus.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bus_booking_service_like_redbus.exception.NoBusFoundException;
import bus_booking_service_like_redbus.model.Bus;



public class SearchService {
    private Map<SourceDestination,List<Bus>> busesMap;
    public SearchService(Map<SourceDestination,List<Bus>> buses){
        this.busesMap =buses;
    }
    //add bus
    //remove bus
    //search buses on for given source destination and and travel date
    public List<Bus> searchBus(SourceDestination sourceDest, LocalDate travelDate) throws Exception{
        List<Bus> result = null;
        try {
            result = busesMap.get(sourceDest).stream().filter(a->a.getTravelDate().equals(travelDate)).toList();
        } catch (Exception e) {
            throw new NoBusFoundException();
        }
        return result;
    }
}
