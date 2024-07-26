package machinecodingexamples.parkinglot.slot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.swing.RowFilter.Entry;

import machinecodingexamples.parkinglot.vehicle.Vehicle;

public class Slot {
    // this will contains list of slots holding Vehicles
    Map<Integer,Vehicle> map = new TreeMap<>();
    // this is the size of the list that is allowed
    int size = 0;
    public Slot(int size){
        this.size = size;
       createSlot();
    }
    private void createSlot() {
        int s = this.size;
        while(s-->0){
            this.map.put(s, null);
        }
    }
    public Map<Integer,Vehicle> getVehicleSlots(){
        return this.map;
    }
    public int addNewVehicle(Vehicle v){
        int slotNumber  = -1;
        // find first slot that is empty and part the Vehicle v their
        for(Map.Entry<Integer,Vehicle> slot : map.entrySet()){
            if(slot.getValue()!=null){
                slotNumber  = slot.getKey();
            }
        }
        this.map.put(slotNumber, v);
        return slotNumber;
    }
    public void unparkVehicle(Vehicle v){
        int slotNumber = -1;
        for(Map.Entry<Integer,Vehicle> slot : map.entrySet()){
            if(slot.getValue().equals(v)){
                slotNumber  = slot.getKey();
                break;
            }
        }
        this.map.put(slotNumber, null);// slot is made empty by removing the vehicle

    }
    public long getFreeSlotsCount(){
        return this.map.entrySet().stream().filter(slot-> slot.getValue()==null).count();
    }
    public long getOccupiedSlotsCount(){
        return this.map.entrySet().stream().filter(slot-> slot.getValue()!=null).count();
    }
    public List<Integer> getAllFreeSlotNumbers(){
        List<Integer> freeSlots = new ArrayList<>();
        this.map.entrySet().stream().filter(slot->slot.getValue()==null).forEach(slot->{
            freeSlots.add(slot.getKey());
        });
        return freeSlots;
    }public List<Integer> getAllOccupiedSlotNumbers(){
        List<Integer> occupiedSlots = new ArrayList<>();
        this.map.entrySet().stream().filter(slot->slot.getValue()!=null).forEach(slot->{
            occupiedSlots.add(slot.getKey());
        });
        return occupiedSlots;
    }
    public int getSize(){
        return this.size;
    }
    public boolean isSlotFull(){
        int count = getVehicleCountInSlot();
        return count == this.getSize();
    }
    private int getVehicleCountInSlot() {
        int count  =0;
        for(Vehicle v : this.map.values()) if(v!=null) count++;
        return count;
    }
}
