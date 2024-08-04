package parkinglot.floor;

import java.util.ArrayList;
import java.util.List;
import parkinglot.slot.BikeSlot;
import parkinglot.slot.CarSlot;
import parkinglot.slot.Slot;
import parkinglot.slot.TruckSlot;

public class Floor {
    boolean isFull = false;
    List<Slot> slots = null;
    int noOfSlots = 0;

    public Floor(int noOfSlots) {
        this.noOfSlots = noOfSlots;
        slots = new ArrayList<>();
        slots.add(new BikeSlot(1));
        slots.add(new TruckSlot(2));
        slots.add(new CarSlot(noOfSlots - 3));
    }

    public List<Slot> getSlots() {
        return this.slots;
    }

    public int getNoOfSlots() {
        return this.noOfSlots;
    }

    public void setFloorFull() {
        this.isFull = true;
    }

    public void setFloorNotFull() {
        this.isFull = false;
    }

    public boolean isFloorFull() {
        return this.isFull;
    }
}
