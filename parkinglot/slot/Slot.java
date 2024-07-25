package machinecodingexamples.parkinglot.slot;

import java.util.ArrayList;
import java.util.List;

import machinecodingexamples.parkinglot.vehicle.Vehicle;

public class Slot {
    // this will contains list of slots holding Vehicles
    List<Vehicle> list = null;
    // this is the size of the list that is allowed
    int size = 0;
    public Slot(ArrayList<Vehicle> list,int size){
        this.list = list;
        this.size  = size;
    }
    public List<Vehicle> getVehicleSlots(){
        return this.list;
    }
    public int getSize(){
        return this.size;
    }
    public boolean isSlotFull(){
        return list.size() == this.getSize();
    }
}
