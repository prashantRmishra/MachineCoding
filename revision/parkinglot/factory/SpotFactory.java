package revision.parkinglot.factory;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import revision.parkinglot.model.CarSpot;
import revision.parkinglot.model.MotorCycleSpot;
import revision.parkinglot.model.Spot;
import revision.parkinglot.model.TruckSpot;
import revision.parkinglot.model.VehicleType;

//this class follows single responsibility principle as it has only one reason of change 
//i.e Change in vehicle type
public class SpotFactory {
    public static Spot createSpot(VehicleType type){
        switch (type) {
            case car: return new CarSpot(UniqueNumberGenerator.getUniqueId());
            case bike: return new MotorCycleSpot(UniqueNumberGenerator.getUniqueId());
            case truck: return  new TruckSpot(UniqueNumberGenerator.getUniqueId());

            default:return null;
        }
    }

    public static List<Spot> createAllSpots(VehicleType type){
        List<Spot> list = new ArrayList<>();
        for(int i =0;i<type.getSize();i++){
            list.add(createSpot(type));
        }
        return list;
    }
    public static Map<VehicleType,List<Spot>> createTheFloorSpots(){
        Map<VehicleType,List<Spot>> map = new HashMap<>();
        for(VehicleType type : VehicleType.values()){
            map.put(type, createAllSpots(type));
        }
        return map;
    }
}
