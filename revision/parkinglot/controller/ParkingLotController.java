package revision.parkinglot.controller;

import java.util.HashMap;
import java.util.Map;

import revision.parkinglot.model.Floor;
import revision.parkinglot.model.Ticket;
import revision.parkinglot.model.Vehicle;
import revision.parkinglot.service.TicketService;

public class ParkingLotController {
    private int floorNumber = 1;
    Map<Integer,Floor> floors;
    TicketService ticketService;
    public ParkingLotController(){
        floors =  new HashMap<>();
        ticketService = new TicketService();
    }
    //create floors in the parking building
    public void addFloor(){
        floors.put(floorNumber, new Floor(ticketService,floorNumber++));
    }
    /**
     * 
     * @param vehicle
     * @return Ticket after parking the vehicle
     */
    public Ticket parkVehicle(Vehicle vehicle){
        Ticket t = null;
        for(Map.Entry<Integer,Floor> entry : floors.entrySet()){
            Floor f = entry.getValue();
            if(f.isFloorFull()) continue;
            else{
                t = f.parkVehicle(vehicle);
                if(t ==null) continue;// no parking space for this type of vehicle on this floor
                else return t;
            }
        }
        return t;
    }
    /***
     * 
     * @param t : ticket to unpark the vehicle
     * @return the true or false (depends if the ticket is valid ticket)
     */
    public boolean unparkVehicle(Ticket t){
        return floors.get(t.getFloorNo()).unparkVehicle(t);
    }
    //search for spot of vehicle type 
    //display status of parking spot for all the floor
    //print()

}
