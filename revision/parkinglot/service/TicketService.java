package revision.parkinglot.service;

import java.util.HashMap;
import java.util.Map;

import revision.parkinglot.model.Spot;
import revision.parkinglot.model.Ticket;
import revision.parkinglot.model.Vehicle;

public class TicketService {
    private Map<Integer,Ticket> tickets;
    public TicketService(){
        this.tickets = new HashMap<>();
    }
    //create ticket
    public synchronized Ticket createTicket(Spot s, Vehicle v,int floorNo){
        Ticket ticket = new Ticket(v, s,floorNo);
        //save ticket 
        tickets.put(ticket.getTicketId(), ticket);
        return ticket;
    }
    //delete ticket
    public synchronized Ticket deleteTicket(int ticketId){
        return tickets.remove(ticketId);
    }

}
