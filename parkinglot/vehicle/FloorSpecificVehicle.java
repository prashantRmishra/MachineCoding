package parkinglot.vehicle;

import parkinglot.Floor;

public class FloorSpecificVehicle {
    Floor floor;
    Vehicle vehicle;
    public FloorSpecificVehicle(Floor floor, Vehicle vehicle){
        this.floor = floor;
        this.vehicle = vehicle;
    }
    public Floor getFloor(){
        return this.floor;
    }
    public Vehicle getVehicle(){
        return this.vehicle;
    }
}
