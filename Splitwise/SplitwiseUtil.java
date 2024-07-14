import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitwiseUtil {
    HashMap<String,HashMap<String,Double>> registry = null;
    HashMap<String, User> users = new HashMap<>();
    public SplitwiseUtil(){
        this.registry  =new HashMap<>();
        
    }

    public void addUser(String n, String email,String phone){
        User user = new User(n, email, phone);
        if(!users.containsKey(email)){
            users.put(email, user);
        }
        //add these details in registry as well
        for(Map.Entry<String,User> entry : users.entrySet()){
            registry.put(entry.getKey(), new HashMap<>());
        }
    }
    public void operation(String paidBy, double amount,List<Split> list ){
        //if the users has already paid for any user in the list
        
        for(Split split : list){
          HashMap<String,Double> balance = registry.get(paidBy);
          if(!balance.containsKey(split.getUserId())){
            balance.put(split.getUserId(), 0.0);
          }
          balance.put(split.getUserId(), balance.get(split.getUserId()) + split.getSplitAmount());

          balance = registry.get(split.getUserId());
          if(!balance.containsKey(paidBy)){
            balance.put(paidBy, 0.0);
          }
          balance.put(paidBy, balance.get(paidBy)-split.getSplitAmount());
        }
    }
    public void show(){
        for(Map.Entry<String,HashMap<String,Double>> allbalance : registry.entrySet()){
            for(Map.Entry<String,Double> userbalance : allbalance.getValue().entrySet()){
                printBalance(allbalance.getKey(), userbalance.getKey(), userbalance.getValue());
            }
        }
    }
    public void show(String id){
        for(Map.Entry<String, Double> userbalance :registry.get(id).entrySet()){
            printBalance(id, userbalance.getKey(),userbalance.getValue());
        }
        
    }
    public void printBalance(String user1, String user2, double amount){
        if(amount > 0){
            System.out.println(user2 + " owes "+ user1 +" :"+amount);
        }
        if(amount < 0){
            System.out.println(user1 + " owes "+ user2 +" :"+ Math.abs(amount));
        }
    }

}
