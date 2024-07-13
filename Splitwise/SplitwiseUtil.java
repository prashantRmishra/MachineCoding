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
    }
    public void operationE(String paidBy, double amount,List<Split> list ){
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
        for(Map.Entry<String,HashMap<String,Double>> entry : registry.entrySet()){
            for(Map.Entry<String,Double> inner : entry.getValue().entrySet()){
                System.out.println(
                    inner.getKey() + " owes "+ entry.getKey() +": "+inner.getValue()
                );
            }
        }
    }
    public void show(String id){
        for(Map.Entry<String, Double> balance :registry.get(id).entrySet()){
            System.out.println(
                balance.getKey() + " owes "+ id +": "+balance.getValue()
            );
        }
        
    }

}
