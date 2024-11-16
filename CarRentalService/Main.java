import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String args[]){
        VehicleRentalSystem rentalSystem = new VehicleRentalSystem();
        User u1 = new User(1, "prashant", "1211");
        User u2 = new User(2, "sandeep", "2323wdww");

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("123", VehicleType.car, Status.active, 200, 1000));
        vehicles.add(new Vehicle("234", VehicleType.car, Status.active, 400, 2000));
        List<Vehicle> vehicles2 = new ArrayList<>();
        vehicles2.add(new Vehicle("333", VehicleType.car, Status.active, 200, 1000));
        vehicles2.add(new Vehicle("5454", VehicleType.car, Status.active, 400, 2000));
        
        List<Vehicle> vehicles3 = new ArrayList<>();
        vehicles3.add(new Vehicle("3454533", VehicleType.car, Status.active, 200, 1000));
        vehicles3.add(new Vehicle("232323", VehicleType.car, Status.active, 400, 2000));
        
        Store store1 = new Store(22,new Location("mg", "pipeline", "bangalore", 1234),vehicles);
        Store store2 = new Store(33, new Location("abc", "chawk", "delhi", 2345),vehicles2);
        Store store3 = new Store(33, new Location("abc", "chawk", "bangalore", 3454),vehicles3);
        
        rentalSystem.addStore(store1);
        rentalSystem.addStore(store2);
        rentalSystem.addStore(store3);

        rentalSystem.addUser(u1);
        rentalSystem.addUser(u2);

        //user will search for vehicle in the given location based on type of vehicle
        //User can choose between these vehicle 
        List<Vehicle> vehiclesInGivenCity  = new ArrayList<>();
        for(Store store : rentalSystem.getStore("bangalore")){
            vehiclesInGivenCity.addAll(store.getVehicles(VehicleType.car));
        }
        //let say use chooses 1 vehicle
        Vehicle vehicle = vehiclesInGivenCity.get(0);
        SimpleDateFormat format  = new SimpleDateFormat("dd-mm-yyyy");
        
    
        Reservation reservation = new Reservation(2222, vehicle, u2,"16-11-2024", "17-11-2024", "19-11-2024", ReservationStatus.SCHEDULED,null,null);
        vehicle.getStore().reservations.add(reservation);
        //generate bill
        Bill bill = new Bill(123, reservation, false);
        
        //make payment from the gateway
        Payment payment = new Payment(bill);
        bill.setPaid(true);
        //updated the reservation status to in_progress
        reservation.updatedReservationStatus(ReservationStatus.IN_PROGRESS);
        //finally user returns the vehicle
        //once the vehicle is freed or the trip is completed 
        ///mark the reservation status to completed
        reservation.updatedReservationStatus(ReservationStatus.COMPLETED);
        /// free the vehicle
        /// done
    }
}
