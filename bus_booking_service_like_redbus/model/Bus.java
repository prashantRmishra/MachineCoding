package bus_booking_service_like_redbus.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Bus {
    private int busNo;
    private Operator operator;
    private String source;
    private String destination;
    private int rating;
    private double fare;
    private BusType busType;
    private LocalDate travelDate;
    @Override
    public String toString() {
        return "Bus [busNo=" + busNo + ", operator=" + operator.getOperatorName() + ", source=" + source + ", destination=" + destination
                + ", rating=" + rating + ", fare=" + fare + ", busType=" + busType + ", travelDate=" + travelDate
                + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", seats=" + seats.size() + ", driver="
                + driver.getUserName() + "]";
    }
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private List<Seat> seats;
    private BusUser driver;
    
    public Bus(int busNo, Operator operator, String source, String destination, int rating, double fare,
            BusType busType, LocalDate travelDate, LocalTime departureTime, LocalTime arrivalTime, List<Seat> seats,BusUser driver) {
        this.busNo = busNo;
        this.operator = operator;
        this.source = source;
        this.destination = destination;
        this.rating = rating;
        this.fare = fare;
        this.busType = busType;
        this.travelDate = travelDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.seats = seats;
        this.driver = driver;
    }
    public int getBusNo() {
        return busNo;
    }
    public BusUser getDriver(){
        return this.driver;
    }
    public Operator getOperator() {
        return operator;
    }
    public String getSource() {
        return source;
    }
    public String getDestination() {
        return destination;
    }
    public int getRating() {
        return rating;
    }
    public double getFare() {
        return fare;
    }
    public BusType getBusType() {
        return busType;
    }
    public LocalDate getTravelDate() {
        return travelDate;
    }
    public LocalTime getDepartureTime() {
        return departureTime;
    }
    public LocalTime getArrivalTime() {
        return arrivalTime;
    }
    public List<Seat> getSeats() {
        return seats;
    }

}
