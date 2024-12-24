package bus_booking_service_like_redbus.model;

public class Operator {
    private String operatorName;
    public Operator(String name){
        this.operatorName = name;
    }
    public String getOperatorName(){
        return this.operatorName;
    }
}
