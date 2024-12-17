package revision.parkinglot.model;


import java.util.List;
import java.util.Map;

import revision.parkinglot.factory.SpotFactory;
import revision.parkinglot.service.TicketService;

public class Floor {
    private Map<VehicleType,List<Spot>> spots = null;
    private TicketService ticketService;
    private final int totalVehicleSpots;
    private int totalVehicleParked = 0;
    private int floorNo;
    public Floor(TicketService ticketService,int floorNo){
        spots = SpotFactory.createTheFloorSpots();
        this.ticketService = ticketService;
        totalVehicleSpots = getTotalVehiclesOnFloor();
        this.floorNo = floorNo;
    }

    public int getFloorNo(){return this.floorNo;}
    //park vehicle 
    public synchronized Ticket parkVehicle(Vehicle v){
        Ticket t = null;
        ///find spot for the give vehicle
        List<Spot> availableSpots = spots.get(v.getVehicleType());
        for(Spot s: availableSpots){
            if(s.isFree()){
                s.bookSpot();
                //create ticket
                t = ticketService.createTicket(s, v,this.getFloorNo());
                return t;
            }
        }
        totalVehicleParked++;
        return null; //unable to park the vehicle no parking spot available in any of the floors
        /// park the vehicle
        /// return ticket
    }


    //unpark vehicle
    public boolean unparkVehicle(Ticket t){
        Ticket ticket = ticketService.deleteTicket(t.getTicketId());
        if(ticket==null) return false;
        ticket.getSpot().freeSpot();
        //we can calculate fees as well
        totalVehicleParked--;
        return true;
    }
    private int getTotalVehiclesOnFloor(){
        int totalSpots = 0;
        VehicleType[] type  =  VehicleType.values();
        for(int i =0;i<VehicleType.values().length;i++){
           totalSpots+= type[i].getSize();
        }
        return totalSpots;
    }

    public boolean isFloorFull(){
        return totalVehicleParked == totalVehicleSpots;
    }

    ///calculate fees

}
