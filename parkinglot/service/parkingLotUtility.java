package parkinglot.service;


import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import parkinglot.vehicle.Bike;
import parkinglot.vehicle.Car;
import parkinglot.vehicle.Truck;
import parkinglot.vehicle.VehicleType;

public class parkingLotUtility {
    // tree map to keep then sorted based on floor no.
    TreeMap<Integer,Floor> parkingLot = new TreeMap<>();
    
    public void createParkingLot(String parkingId, int noOfFloors, int noOfSlotsPerFloor){
        if(noOfSlotsPerFloor < 3) {
            System.out.println("slots can not be less than 3");
            return;
        }
        while(noOfFloors-->0){
            parkingLot.put(noOfFloors, new Floor(noOfSlotsPerFloor));
        }
        System.out.println("Parking lot has been created");
    }
    public String parkVehicle(VehicleType vehicleType, String regNumber, String color){
        //first find empty floor 
        for(Map.Entry<Integer,Floor> floor : parkingLot.entrySet()){
            if(!floor.getValue().isFloorFull()){
                if(slotOfVehileTypeAvailable(floor.getValue(),vehicleType)){
                    String parkingTicket = park(floor.getKey(),floor.getValue(), vehicleType,regNumber,color);
                }
            }
        }
        //then find available slot in that floor of type vehicleType then return parkingId
        return null;
    }
    public boolean slotOfVehileTypeAvailable(Floor floor,VehicleType vehicleType){
        if(vehicleType == VehicleType.Bike){
            return floor.bikeCount-floor.getBikeSlots().size() > 0;
        }
        if(vehicleType == VehicleType.Car){
            return floor.carCount - floor.getCarSlots().size() > 0;
        }
        else if (vehicleType == VehicleType.Truck){
            return floor.truckCount-floor.getTruckSlots().size() > 0;
        }
        else{
            System.out.println("enter valid vehicle type ");
            return false;
        }

    }

    public String park( int floorNumber, Floor floor, VehicleType vehicleType, String reg, String color){
        String parkingId = "PR1234";

        if(vehicleType == VehicleType.Bike){
            floor.getBikeSlots().add(new Bike(reg,color));
            parkingId = parkingId+"_"+floorNumber+"_"+ floor.getBikeSlots().size();
        }
        if(vehicleType == VehicleType.Car){
            floor.getCarSlots().add(new Car(reg,color));
            parkingId = parkingId+"_"+floorNumber+"_"+floor.getBikeSlots().size();
        }
        if(vehicleType == VehicleType.Truck){
            floor.getTruckSlots().add(new Truck(reg, color));
            parkingId = parkingId+"_"+floorNumber+"_"+floor.getTruckSlots().size();
        }
        checkIfFloorIsFull(floor);
        return null;
    }
    public void checkIfFloorIsFull(Floor floor){
        int totalVehicleParked = floor.getBikeSlots().size() + floor.getCarSlots().size() + floor.getTruckSlots().size();
            if(totalVehicleParked == floor.getNoOfSlots()) {
                floor.setFloorFull();
            }
    }


    public void unparkVehicle(String parkingId){
        
    }
}



