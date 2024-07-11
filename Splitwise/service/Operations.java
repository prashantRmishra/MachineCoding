package Splitwise.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Splitwise.model.Debt;
import Splitwise.model.SplitType;
import Splitwise.model.User;

// singleton class, synchonized for multithreding
public class Operations {
    private static volatile Operations operations = null;
    private static Map<String,User> users =null;
    private static Debt debt = null;
    
    private Operations(){
        // not possible to intialize
        debt = new Debt();
        users = new HashMap<>();
    }

    public Operations getInstance(){
        if(operations ==null) {
            synchronized(Operations.class){
                if(operations ==null){
                    return new Operations();
                }
            }
        }
        return operations;
    }

    //replace below with builder patter
    public void creatUser(String name, String email, String phone){
        User user = new User(name, email, phone);
        user.setDebt(debt);
        users.put(email,user);
    } 

    public void expense(String userId, double expenseAmount,List<User> partUsers, SplitType type){

        if(type == SplitType.EQUALLY){
            double splitAmount = expenseAmount/partUsers.size();
            //userId paid for the bills, partUsers will owe this user
            users.get(userId).geDebt().setOwed(userId, splitAmount);
            for(User u : partUsers){
                users.get(userId).geDebt().setOwed(u.getId(), splitAmount);
            }
           



            

        }
        else if(type == SplitType.EXACT){

        }
        else if(type == SplitType.PERCENTAGE){

        }
        
    }
    public void showBalance(){

    }
}
