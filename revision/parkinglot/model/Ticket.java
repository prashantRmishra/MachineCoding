package revision.parkinglot.model;

import revision.parkinglot.factory.UniqueNumberGenerator;

public class Ticket {
    private int ticketId;
    private Vehicle vehicle;
    private Spot spot;
    private int floorNo;
    public Ticket(Vehicle vehicle, Spot spot, int floorNo){
        ticketId = UniqueNumberGenerator.getUniqueId();
        this.spot  =spot;
        this.vehicle = vehicle;
        this.floorNo = floorNo;
    }
    public int getTicketId(){
        return this.ticketId;
    }
    public Spot getSpot(){
        return this.spot;
    }
    public Vehicle getVehicle(){
        return this.vehicle;
    }
    public int getFloorNo(){return this.floorNo;}
}
