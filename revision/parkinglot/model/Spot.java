package revision.parkinglot.model;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Spot {
    private int spotId;
    private AtomicBoolean isFree = new AtomicBoolean(false);
    private VehicleType type;

    public Spot(int id,VehicleType type){
        this.spotId = id;
        this.isFree.set(true);
        this.type = type;
    }

    public void bookSpot(){
        this.isFree.set(false);
    }
    public boolean isFree(){
        return this.isFree.get();
    }
    public void freeSpot(){
        this.isFree.set(true);
    }
    public int getSpotId(){
        return this.spotId;
    }
    public VehicleType getSpotType(){
        return this.type;
    }
}
