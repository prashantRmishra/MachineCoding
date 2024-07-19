package parkinglot.service;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import parkinglot.vehicle.Bike;
import parkinglot.vehicle.Car;
import parkinglot.vehicle.DisplayType;
import parkinglot.vehicle.FloorSpecificVehicle;
import parkinglot.vehicle.Truck;
import parkinglot.vehicle.Vehicle;
import parkinglot.vehicle.VehicleType;

public class parkingLotUtility {
    // tree map to keep then sorted based on floor no.
    TreeMap<Integer,Floor> parkingLot = new TreeMap<>();
    HashMap<String,FloorSpecificVehicle> parkedVehicle  = new HashMap<>();
    String parkingId = "PR1234";
    
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
                    parkingId = park(floor.getKey(),floor.getValue(), vehicleType,regNumber,color);
                }
            }
        }
        //then find available slot in that floor of type vehicleType then return parkingId
        return !parkingId.equals("PR1234") ? parkingId: null;
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
        Vehicle vehicle = null;
        if(vehicleType == VehicleType.Bike){
            vehicle = new Bike(reg,color);
            floor.getBikeSlots().add(vehicle);
            parkingId = parkingId+"_"+floorNumber+"_"+ floor.getBikeSlots().size();
        }
        if(vehicleType == VehicleType.Car){
            vehicle = new Car(reg,color);
            floor.getCarSlots().add(vehicle);
            parkingId = parkingId+"_"+floorNumber+"_"+floor.getBikeSlots().size();
        }
        if(vehicleType == VehicleType.Truck){
            vehicle = new Truck(reg, color);
            floor.getTruckSlots().add(vehicle);
            parkingId = parkingId+"_"+floorNumber+"_"+floor.getTruckSlots().size();
        }
        checkIfFloorIsFull(floor);
        parkedVehicle.put(parkingId,new FloorSpecificVehicle(floor, vehicle));
        return parkingId;
    }
    
    public void checkIfFloorIsFull(Floor floor){
        int totalVehicleParked = floor.getBikeSlots().size() + floor.getCarSlots().size() + floor.getTruckSlots().size();
            if(totalVehicleParked == floor.getNoOfSlots()) {
                floor.setFloorFull();
            }
    }
    public void unparkVehicle(String parkingId){
        if(parkedVehicle.containsKey(parkingId)){
            FloorSpecificVehicle floorSpecificVehicle  = parkedVehicle.get(parkingId);
            if(floorSpecificVehicle.getVehicle() instanceof Car){
                floorSpecificVehicle.getFloor().getCarSlots().remove(floorSpecificVehicle.getVehicle());
            }
            else if(floorSpecificVehicle.getVehicle() instanceof Bike){
                floorSpecificVehicle.getFloor().getBikeSlots().remove(floorSpecificVehicle.getVehicle());
            }
            floorSpecificVehicle.getFloor().getTruckSlots().remove(floorSpecificVehicle.getVehicle());
        }
        else System.out.println("parkingId "+ parkingId +" does not exists");
    }

    public void display(String displayType, VehicleType vehicleType){
        switch (displayType) {
            case "free_count":{
                getFreeCountOfVehicle(vehicleType);
            }
            case "free_slots":{}
            case "occupied_slots":{}
        
            default:{

            }
        }
    }
    private void getFreeCountOfVehicle(VehicleType vehicleType) {
        int free = 0;
        int floor =0;
        if(vehicleType.equals("Car")){
            for(Map.Entry<Integer,Floor> floors  : parkingLot.entrySet()){
                free = floors.getValue().carCount-floors.getValue().getCarSlots().size();
                floor = floors.getKey();
            }
        }
        if(vehicleType.equals("Truck")){
            for(Map.Entry<Integer,Floor> floors  : parkingLot.entrySet()){
                free = floors.getValue().truckCount-floors.getValue().getTruckSlots().size();
                floor = floors.getKey();
            }
        }
        else{
            for(Map.Entry<Integer,Floor> floors  : parkingLot.entrySet()){
                free = floors.getValue().bikeCount-floors.getValue().getBikeSlots().size();
                floor = floors.getKey();
            }
        }
        print(floor, free,vehicleType);
    }
    private void print(int floor, int free,VehicleType vehicleType){
        System.out.println("No. of free slots for "+vehicleType.toString()+" on Floor "+floor+":"+free+"The above will be printed for each floor.");
    }


}



