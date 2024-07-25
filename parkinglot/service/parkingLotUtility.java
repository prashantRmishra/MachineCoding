package parkinglot.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import parkinglot.Floor;
import parkinglot.vehicle.Bike;
import parkinglot.vehicle.Car;
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
            System.out.println("Slots can not be less than 3");
            return;
        }
        setParkingId(parkingId);
        while(noOfFloors-->0){
            parkingLot.put(noOfFloors, new Floor(noOfSlotsPerFloor));
        }
        System.out.println("Parking lot has been created");
    }
    private void setParkingId(String parkingId) {
        this.parkingId = parkingId;
    }
    private String getParkingId(){
        return this.parkingId;
    }
    public String parkVehicle(VehicleType vehicleType, String regNumber, String color){
        //first find empty floor 
        String parkingTicket = "";
        for(Map.Entry<Integer,Floor> floor : parkingLot.entrySet()){
            if(!floor.getValue().isFloorFull()){
                if(slotOfVehileTypeAvailable(floor.getValue(),vehicleType)){
                    parkingTicket = park(floor.getKey(),floor.getValue(), vehicleType,regNumber,color,parkingTicket);
                    break;
                }
            }
        }
        //then find available slot in that floor of type vehicleType then return parkingId
        return !parkingTicket.equals("PR1234") ? parkingTicket: null;
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
            System.out.println("Enter valid VehicleType ");
            return false;
        }

    }

    public String park( int floorNumber, Floor floor, VehicleType vehicleType, String reg, String color,String parkingTicket){
        Vehicle vehicle = null;
        
        if(vehicleType.equals(VehicleType.Bike)){
            vehicle = new Bike(reg,color);
            floor.getBikeSlots().add(vehicle);
            parkingTicket = parkingId+"_"+floorNumber+"_"+ floor.getBikeSlots().size();
            
        }
        if(vehicleType.equals(VehicleType.Car)){
            vehicle = new Car(reg,color);
            floor.getCarSlots().add(vehicle);
            parkingTicket = parkingId+"_"+floorNumber+"_"+floor.getCarSlots().size();
            
        }
        if(vehicleType.equals(VehicleType.Truck)){
            vehicle = new Truck(reg, color);
            floor.getTruckSlots().add(vehicle);
            parkingTicket = parkingId+"_"+floorNumber+"_"+floor.getTruckSlots().size();
        }
        checkIfFloorIsFull(floor);
        parkedVehicle.put(parkingTicket,new FloorSpecificVehicle(floor, vehicle));
        return parkingTicket;
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
            else if(floorSpecificVehicle.getVehicle() instanceof Truck) floorSpecificVehicle.getFloor().getTruckSlots().remove(floorSpecificVehicle.getVehicle());
            System.out.println("Unparked vehicle");

        }
        else System.out.println("ParkingId "+ parkingId +" does not exists");
    }

    public void display(String displayType, VehicleType vehicleType){
        switch (displayType) {
            case "free_count":{
                getFreeCountOfVehicle(vehicleType);
                break;
            }
            case "free_slots":{
                getFreeSlotsOfVehicle(vehicleType);
                break;
            }
            case "occupied_slots":{
                getOccupiedSlots(vehicleType);
                break;
            }
        
            default:{
                System.out.println("Enter valid DisplayType/VehicleType");
            }
        }
    }
    private void getOccupiedSlots(VehicleType vehicleType){
        int occupied = 0;
        int floor =0;
        ArrayList<Integer> list = new ArrayList<>();
        if(vehicleType.toString().equals("Car")){
            for(Map.Entry<Integer,Floor> floors  : parkingLot.entrySet()){
                occupied = floors.getValue().getCarSlots().size();
                floor = floors.getKey();
                if(occupied>0){
                    int slot = 1;
                    while(occupied-->0){
                        list.add(slot++);
                    }
                    printFreeOrOccupiedSlots(floor,list,vehicleType,true);
                    list.clear();
                }
                    
            }
        }
        if(vehicleType.toString().equals("Truck")){
            for(Map.Entry<Integer,Floor> floors  : parkingLot.entrySet()){
                occupied = floors.getValue().getTruckSlots().size();
                floor = floors.getKey();
                if(occupied>0){
                    int slot = 1;
                    while(occupied-->0){
                        list.add(slot++);
                    }
                    printFreeOrOccupiedSlots(floor,list,vehicleType,true);
                    list.clear();
                }
            }
        }
        else if(vehicleType.toString().equals("Bike")){
            for(Map.Entry<Integer,Floor> floors  : parkingLot.entrySet()){
                occupied = floors.getValue().getBikeSlots().size();
                floor = floors.getKey();
                if(occupied>0){
                    int slot = 1;
                    while(occupied-->0){
                        list.add(slot++);
                    }
                    printFreeOrOccupiedSlots(floor,list,vehicleType,true);
                    list.clear();
                }
            }
        }
       
    }
    private void getFreeSlotsOfVehicle(VehicleType vehicleType){
        int free = 0;
        int floor =0;
        ArrayList<Integer> list = new ArrayList<>();
        if(vehicleType.toString().equals("Car")){
            for(Map.Entry<Integer,Floor> floors  : parkingLot.entrySet()){
                free = floors.getValue().carCount-floors.getValue().getCarSlots().size();
                floor = floors.getKey();
                if(free>0){
                    int slotNo  = floors.getValue().getCarSlots().size()+1;
                    while(free-->0){
                        list.add(slotNo++);
                    }
                    printFreeOrOccupiedSlots(floor,list,vehicleType,false);
                    list.clear();
                }
                    
            }
        }
        if(vehicleType.toString().equals("Truck")){
            for(Map.Entry<Integer,Floor> floors  : parkingLot.entrySet()){
                free = floors.getValue().truckCount-floors.getValue().getTruckSlots().size();
                floor = floors.getKey();
                
                if(free>0){
                    int slotNo  = floors.getValue().getTruckSlots().size()+1;
                    while(free-->0){
                        list.add(slotNo++);
                    }
                    printFreeOrOccupiedSlots(floor,list,vehicleType,false);
                    list.clear();
                }
            }
        }
        else if(vehicleType.toString().equals("Bike")){
            for(Map.Entry<Integer,Floor> floors  : parkingLot.entrySet()){
                free = floors.getValue().bikeCount-floors.getValue().getBikeSlots().size();
                floor = floors.getKey();
                if(free>0){
                    int slotNo  = floors.getValue().getBikeSlots().size()+1;
                    while(free-->0){
                        list.add(slotNo++);
                    }
                    printFreeOrOccupiedSlots(floor,list,vehicleType,false);
                    list.clear();
                }
            }
        }
    }
    private void getFreeCountOfVehicle(VehicleType vehicleType) {
        int free = 0;
        int floor =0;
        if(vehicleType.toString().equals("Car")){
            for(Map.Entry<Integer,Floor> floors  : parkingLot.entrySet()){
                free = floors.getValue().carCount-floors.getValue().getCarSlots().size();
                floor = floors.getKey();
                if(free>0)
                    printSlot(floor, free,vehicleType);
            }
        }
        if(vehicleType.toString().equals("Truck")){
            for(Map.Entry<Integer,Floor> floors  : parkingLot.entrySet()){
                free = floors.getValue().truckCount-floors.getValue().getTruckSlots().size();
                floor = floors.getKey();
                if(free>0)
                    printSlot(floor, free,vehicleType);
            }
        }
        if(vehicleType.toString().equals("Bike")){
            for(Map.Entry<Integer,Floor> floors  : parkingLot.entrySet()){
                free = floors.getValue().bikeCount-floors.getValue().getBikeSlots().size();
                floor = floors.getKey();
                if(free>0)
                    printSlot(floor, free,vehicleType);
            }
        }
    }
    private void printSlot(int floor, int free,VehicleType vehicleType){
        System.out.println("No. of free slots for "+vehicleType.toString()+" on Floor "+floor+":"+free);
    }
    private void printFreeOrOccupiedSlots(int floor,ArrayList<Integer> list, VehicleType vehicleType,boolean forOccupied){
        if(forOccupied){
            System.out.print("Occupied slots for "+vehicleType.toString()+" on Floor "+floor+":"+list);
        }
        else
            System.out.print("Free slots for "+vehicleType.toString()+" on Floor "+floor+":"+list);
        System.out.println();
        
    }


}



