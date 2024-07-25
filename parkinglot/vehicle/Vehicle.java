package machinecodingexamples.parkinglot.vehicle;

public class Vehicle {
    String registrationNumber ="n";
    String color = "n";
    public Vehicle(){}
    public Vehicle(String reg, String color){
        this.registrationNumber = reg;
        this.color = color;
    }
    public void setRegistrationNumber(String reg){
        this.registrationNumber = reg;
    }
    public String getRegistrationNumber(){
        return this.registrationNumber;
    }
    public void setColor(String color){
        this.color = color;
    }
    public String getColor(){
        return this.color;
    }
}
