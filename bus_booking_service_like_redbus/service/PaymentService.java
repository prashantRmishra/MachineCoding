package bus_booking_service_like_redbus.service;

import bus_booking_service_like_redbus.model.BusUser;

public class PaymentService {
    public synchronized boolean doPayment(BusUser user, double fare){
        //third party payment logic 
        return true;
    }
}
