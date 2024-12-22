package companySpecific.flipkart.ride_sharing_app.manager;

import java.util.HashMap;
import java.util.Map;

import companySpecific.flipkart.ride_sharing_app.model.RideTakeOffered;
import companySpecific.flipkart.ride_sharing_app.model.RideUser;

public class UserManager {
     private Map<String,RideTakeOffered> stats;
     private Map<Integer,RideUser> users;
     public UserManager(){
        this.stats = new HashMap<>();
        this.users = new HashMap<>();
     }

     public boolean addUser(RideUser user){
        if(!users.containsKey(user.getUserId())){
            users.put(user.getUserId(), user);
            //creating stats for this user as well
            stats.put(user.getUserName(), new RideTakeOffered(user.getUserName()));
            return true;
        }
        return false;
     }
     public void updateTakenStats(String user, int val){
        stats.get(user).incrementRideTake(val);
     }
     public void updateOfferedStats(String user, int val){
        stats.get(user).incrementRideOffered(val);
     }
     public void printStats(){
        for(Map.Entry<String,RideTakeOffered> entry : stats.entrySet()){
            System.out.println(entry.getValue());
        }
     }
}
