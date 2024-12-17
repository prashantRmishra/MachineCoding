package revision.parkinglot.model;

public enum VehicleType {
    car(Constants.CAR_SPOTS),bike(Constants.BIKE_SPOTS),truck(Constants.TRUCK_SPOTS);
    private final int maxSize;
    VehicleType(int size){
        this.maxSize = size;
    }
    public int getSize(){
        return this.maxSize;
    }
}
