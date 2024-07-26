package machinecodingexamples.parkinglot.floor;

import java.util.ArrayList;
import java.util.List;

import machinecodingexamples.parkinglot.slot.BikeSlot;
import machinecodingexamples.parkinglot.slot.CarSlot;
import machinecodingexamples.parkinglot.slot.Slot;
import machinecodingexamples.parkinglot.slot.TruckSlot;
import machinecodingexamples.parkinglot.vehicle.Bike;
import machinecodingexamples.parkinglot.vehicle.Car;
import machinecodingexamples.parkinglot.vehicle.Truck;
import machinecodingexamples.parkinglot.vehicle.Vehicle;


public class Floor {
    boolean isFull = false;
    public int bikeCount = 0;
    public int carCount = 0;
    public int truckCount =0;
    List<Vehicle> bikeSlots =  null;
    List<Vehicle> carSlots = null;
    List<Vehicle> truckSlots = null;
    List<Slot> slots = null;
    int noOfSlots = 0;
    public Floor(int noOfSlots){
       bikeCount = 1;
       bikeSlots = new ArrayList<>();
       truckCount = 2;
       truckSlots = new ArrayList<>();
       carCount = noOfSlots-3;
       this.noOfSlots = noOfSlots;
       carSlots = new ArrayList<>();


       //for slot class
       slots.add(new BikeSlot(1));
       slots.add(new TruckSlot(2));
       slots.add(new CarSlot(noOfSlots-3));
       
       ///ends here
    }
    public List<Slot> getSlots(){
        return this.slots;
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
