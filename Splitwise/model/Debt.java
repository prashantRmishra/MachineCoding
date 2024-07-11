package Splitwise.model;

import java.util.HashMap;
import java.util.Map;

public class Debt {
    Map<String,Double> owesMap = null;
    Map<String,Double> owedMap = null;

    public Debt(){
        owesMap = new HashMap<>();
        owedMap = new HashMap<>();
    }
    public double getOwed(String userId){
        return this.owedMap.get(userId);
    }
    public void setOwed(String userId, double amount){
        double currentOwedAmount = this.owedMap.getOrDefault(userId, 0.0);
        currentOwedAmount+=amount;
        this.owedMap.put(userId, currentOwedAmount);
    }
    public void setOwes(String userId, double amount){
        double currentOwesAmount = this.owesMap.getOrDefault(userId, 0.0);
        currentOwesAmount+=amount;
        this.owesMap.put(userId, currentOwesAmount);
    }
  
    public double getOwes(String userId){
        return this.owesMap.get(userId);
    }
}
