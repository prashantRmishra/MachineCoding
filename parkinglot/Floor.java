package parkinglot;

import java.util.ArrayList;
import java.util.List;
import parkinglot.vehicle.Bike;
import parkinglot.vehicle.Car;
import parkinglot.vehicle.Truck;
import parkinglot.vehicle.Vehicle;


public class Floor {
    boolean isFull = false;
    public int bikeCount = 0;
    public int carCount = 0;
    public int truckCount =0;
    List<Vehicle> bikeSlots =  null;
    List<Vehicle> carSlots = null;
    List<Vehicle> truckSlots = null;
    int noOfSlots = 0;
    public Floor(int noOfSlots){
       bikeCount = 1;
       bikeSlots = new ArrayList<>();
       truckCount = 2;
       truckSlots = new ArrayList<>();
       carCount = noOfSlots-3;
       this.noOfSlots = noOfSlots;
       carSlots = new ArrayList<>();
    }
    public int getNoOfSlots(){
        return this.noOfSlots;
    }
    public void setFloorFull(){
        this.isFull  = true;
    }
    public void setFloorNotFull(){
        this.isFull = false;
    }
    public boolean isFloorFull(){
        return this.isFull;
    }

    public List<Vehicle> getBikeSlots(){
        return this.bikeSlots;
    }
    public List<Vehicle> getCarSlots(){
        return this.carSlots;
    }
    public List<Vehicle> getTruckSlots(){
        return this.truckSlots;
    }
    public void setBikeSlot(Bike b){
        bikeSlots.add(b);
    }
    public void setCarSlot(Car c){
        carSlots.add(c);
    }
    public void setTruckSlot(Truck t){
        truckSlots.add(t);
    }
}
