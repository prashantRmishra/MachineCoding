package bus_booking_service_like_redbus.model;

import bus_booking_service_like_redbus.utility.BusServiceUtility;

public class BusUser {
    private int userId;
    private String userName;
    public BusUser(String name){
        this.userId = BusServiceUtility.getUniqueId();
        this.userName = name;
    }
    public String getUserName(){
        return this.userName;
    }
    public int getUserId(){
        return this.userId;
    }

}
