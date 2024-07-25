package machinecodingexamples.parkinglot;
import java.util.Scanner;

import machinecodingexamples.parkinglot.service.*;
import machinecodingexamples.parkinglot.vehicle.DisplayType;
import machinecodingexamples.parkinglot.vehicle.VehicleType;

public class Main {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        /* create_parking_lot PR1234 2 6
         * park_vehicle Car KA-01-DB-1234 black
         * display free_count Car
         * display free_slots Car
         * display occupied_slots Car
         * unpark_vehicle PR1234_0_1
         * exit
        */
        
        parkingLotUtility utility = new parkingLotUtility();
        boolean exit =  true;
        while(exit){
            String commands[] = sc.nextLine().split(" ");
            switch(commands[0]){
                case "create_parking_lot":{
                    utility.createParkingLot(commands[1], Integer.parseInt(commands[2]), Integer.parseInt(commands[3]));
                    break;
                }
                case "park_vehicle":{
                    VehicleType type  = commands[1].equals(VehicleType.Bike.toString())  ? VehicleType.Bike : (commands[1].equals(VehicleType.Car.toString()) ? VehicleType.Car : VehicleType.Truck);
                    System.out.println("Parking id for vehicle "+commands[2]+": "+utility.parkVehicle(type, commands[2], commands[3]));;
                    break;
                }
                case "display":{
                    String displayType  = commands[1].equals(DisplayType.free_count.toString())  ? DisplayType.free_count.toString() : (commands[1].equals(DisplayType.free_slots.toString()) ? DisplayType.free_slots.toString(): DisplayType.occupied_slots.toString());
                    VehicleType vehicleType  = commands[2].equals(VehicleType.Bike.toString())  ? VehicleType.Bike : (commands[2].equals(VehicleType.Car.toString()) ? VehicleType.Car : VehicleType.Truck);
                    utility.display(displayType, vehicleType);
                    break;
                }
                case "unpark_vehicle":{
                    utility.unparkVehicle(commands[1]);
                    break;
                }
                case "exit":{
                    exit = false;
                    break;
                }
                default :{
                    System.out.println("Entry is invalid or for exit enter exit");
                }
            }
        }
        
    }
}
