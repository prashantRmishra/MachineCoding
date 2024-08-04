package parkinglot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import parkinglot.floor.Floor;
import parkinglot.floor.FloorSpecificVehicle;
import parkinglot.vehicle.Bike;
import parkinglot.vehicle.Car;
import parkinglot.vehicle.Truck;
import parkinglot.vehicle.Vehicle;
import parkinglot.vehicle.VehicleType;

public class parkingLotUtility {
    // tree map to keep then sorted based on floor no.
    TreeMap<Integer, Floor> parkingLot = new TreeMap<>();
    HashMap<String, FloorSpecificVehicle> parkedVehicle = new HashMap<>();
    String parkingId = "PR1234";

    public void createParkingLot(String parkingId, int noOfFloors, int noOfSlotsPerFloor) {
        if (noOfSlotsPerFloor < 3) {
            System.out.println("Slots can not be less than 3");
            return;
        }
        setParkingId(parkingId);
        while (noOfFloors-- > 0) {
            parkingLot.put(noOfFloors, new Floor(noOfSlotsPerFloor));
        }
        System.out.println("Parking lot has been created");
    }

    private void setParkingId(String parkingId) {
        this.parkingId = parkingId;
    }

    public String parkVehicle(VehicleType vehicleType, String regNumber, String color) {
        // first find empty floor
        String parkingTicket = "";
        for (Map.Entry<Integer, Floor> floor : parkingLot.entrySet()) {
            if (!floor.getValue().isFloorFull()) {
                if (!slotOfVehileTypeAvailable(floor.getValue(), vehicleType)) {
                    parkingTicket = park(floor.getKey(), floor.getValue(), vehicleType, regNumber, color,
                            parkingTicket);
                    break;
                }
            }
        }
        // then find available slot in that floor of type vehicleType then return
        // parkingId
        return !parkingTicket.equals("PR1234") ? parkingTicket : null;
    }

    public boolean slotOfVehileTypeAvailable(Floor floor, VehicleType vehicleType) {
        if (vehicleType == VehicleType.Bike) {
            // note on the given floor bike is at index 0, then truck then car
            return floor.getSlots().get(0).isSlotFull();
        }
        if (vehicleType == VehicleType.Car) {
            return floor.getSlots().get(2).isSlotFull();
        } else if (vehicleType == VehicleType.Truck) {
            return floor.getSlots().get(1).isSlotFull();
        }
        return false;
    }

    public String park(int floorNumber, Floor floor, VehicleType vehicleType, String reg, String color,
            String parkingTicket) {
        Vehicle vehicle = null;

        if (vehicleType.equals(VehicleType.Bike)) {
            vehicle = new Bike(reg, color);
            int slotNumber = floor.getSlots().get(0).addNewVehicle(vehicle);
            parkingTicket = parkingId + "_" + floorNumber + "_" + slotNumber;
        }
        if (vehicleType.equals(VehicleType.Car)) {
            vehicle = new Car(reg, color);
            int slotNumber = floor.getSlots().get(2).addNewVehicle(vehicle);
            parkingTicket = parkingId + "_" + floorNumber + "_" + slotNumber;
        }
        if (vehicleType.equals(VehicleType.Truck)) {
            vehicle = new Truck(reg, color);
            int slotNumber = floor.getSlots().get(1).addNewVehicle(vehicle);
            parkingTicket = parkingId + "_" + floorNumber + "_" + slotNumber;
        }
        checkIfFloorIsFull(floor);
        parkedVehicle.put(parkingTicket, new FloorSpecificVehicle(floor, vehicle));
        return parkingTicket;
    }

    public void checkIfFloorIsFull(Floor floor) {
        long totalVehicleParked = floor.getSlots().get(0).getOccupiedSlotsCount() +
                floor.getSlots().get(1).getOccupiedSlotsCount() + floor.getSlots().get(2).getOccupiedSlotsCount();
        if (totalVehicleParked == floor.getNoOfSlots()) {
            floor.setFloorFull();
        } else
            floor.setFloorNotFull();
    }

    public void unparkVehicle(String parkingId) {
        if (parkedVehicle.containsKey(parkingId)) {
            FloorSpecificVehicle floorSpecificVehicle = parkedVehicle.get(parkingId);
            if (floorSpecificVehicle.getVehicle() instanceof Car) {
                floorSpecificVehicle.getFloor().getSlots().get(2).unparkVehicle(floorSpecificVehicle.getVehicle());

            } else if (floorSpecificVehicle.getVehicle() instanceof Bike) {
                floorSpecificVehicle.getFloor().getSlots().get(0).unparkVehicle(floorSpecificVehicle.getVehicle());
            } else if (floorSpecificVehicle.getVehicle() instanceof Truck)
                floorSpecificVehicle.getFloor().getSlots().get(1).unparkVehicle(floorSpecificVehicle.getVehicle());
            checkIfFloorIsFull(floorSpecificVehicle.getFloor());
            System.out.println("Unparked vehicle");

        } else
            System.out.println("ParkingId " + parkingId + " does not exists");
    }

    public void display(String displayType, VehicleType vehicleType) {
        switch (displayType) {
            case "free_count": {
                getFreeCountOfVehicle(vehicleType);
                break;
            }
            case "free_slots": {
                getFreeSlotsOfVehicle(vehicleType);
                break;
            }
            case "occupied_slots": {
                getOccupiedSlots(vehicleType);
                break;
            }
            default: {
                System.out.println("Enter valid DisplayType/VehicleType");
            }
        }
    }

    private void getOccupiedSlots(VehicleType vehicleType) {
        int floor = 0;
        if (vehicleType.toString().equals("Car")) {
            for (Map.Entry<Integer, Floor> floors : parkingLot.entrySet()) {
                floor = floors.getKey();
                List<Integer> list = floors.getValue().getSlots().get(2).getAllOccupiedSlotNumbers();
                printFreeOrOccupiedSlots(floor, list, vehicleType, true);
            }
        }
        if (vehicleType.toString().equals("Truck")) {
            for (Map.Entry<Integer, Floor> floors : parkingLot.entrySet()) {
                floor = floors.getKey();
                List<Integer> list = floors.getValue().getSlots().get(1).getAllOccupiedSlotNumbers();
                printFreeOrOccupiedSlots(floor, list, vehicleType, true);
            }
        } else if (vehicleType.toString().equals("Bike")) {
            for (Map.Entry<Integer, Floor> floors : parkingLot.entrySet()) {
                floor = floors.getKey();
                List<Integer> list = floors.getValue().getSlots().get(0).getAllOccupiedSlotNumbers();
                printFreeOrOccupiedSlots(floor, list, vehicleType, true);
            }
        }
    }

    private void getFreeSlotsOfVehicle(VehicleType vehicleType) {
        int floor = 0;
        if (vehicleType.toString().equals("Car")) {
            for (Map.Entry<Integer, Floor> floors : parkingLot.entrySet()) {
                floor = floors.getKey();
                List<Integer> list = floors.getValue().getSlots().get(2).getAllFreeSlotNumbers();
                printFreeOrOccupiedSlots(floor, list, vehicleType, false);
            }
        }
        if (vehicleType.toString().equals("Truck")) {
            for (Map.Entry<Integer, Floor> floors : parkingLot.entrySet()) {
                floor = floors.getKey();
                List<Integer> list = floors.getValue().getSlots().get(1).getAllFreeSlotNumbers();
                printFreeOrOccupiedSlots(floor, list, vehicleType, false);
            }
        } else if (vehicleType.toString().equals("Bike")) {
            for (Map.Entry<Integer, Floor> floors : parkingLot.entrySet()) {
                floor = floors.getKey();
                List<Integer> list = floors.getValue().getSlots().get(0).getAllFreeSlotNumbers();
                printFreeOrOccupiedSlots(floor, list, vehicleType, false);
            }
        }
    }

    private void getFreeCountOfVehicle(VehicleType vehicleType) {
        long free = 0;
        int floor = 0;
        if (vehicleType.toString().equals("Car")) {
            for (Map.Entry<Integer, Floor> floors : parkingLot.entrySet()) {
                free = floors.getValue().getSlots().get(2).getFreeSlotsCount();
                floor = floors.getKey();
                if (free > 0)
                    printSlot(floor, free, vehicleType);
            }
        }
        if (vehicleType.toString().equals("Truck")) {
            for (Map.Entry<Integer, Floor> floors : parkingLot.entrySet()) {
                free = floors.getValue().getSlots().get(1).getFreeSlotsCount();
                floor = floors.getKey();
                if (free > 0)
                    printSlot(floor, free, vehicleType);
            }
        }
        if (vehicleType.toString().equals("Bike")) {
            for (Map.Entry<Integer, Floor> floors : parkingLot.entrySet()) {
                free = floors.getValue().getSlots().get(0).getFreeSlotsCount();
                floor = floors.getKey();
                if (free > 0)
                    printSlot(floor, free, vehicleType);
            }
        }
    }

    private void printSlot(int floor, long free, VehicleType vehicleType) {
        System.out.println("No. of free slots for " + vehicleType.toString() + " on Floor " + floor + ":" + free);
    }

    private void printFreeOrOccupiedSlots(int floor, List<Integer> list, VehicleType vehicleType, boolean forOccupied) {
        if (forOccupied) {
            System.out.print("Occupied slots for " + vehicleType.toString() + " on Floor " + floor + ":" + list);
        } else
            System.out.print("Free slots for " + vehicleType.toString() + " on Floor " + floor + ":" + list);
        System.out.println();
    }
}
