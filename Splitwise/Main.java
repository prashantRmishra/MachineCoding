import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        SplitwiseUtil util  = new SplitwiseUtil();
        util.addUser("u1", "u1", "454545");
        util.addUser("u2", "u2", "54333");
        util.addUser("u3", "u3", "78787");
        util.addUser("u4", "u4", "787866");
        
        Scanner sc = new Scanner(System.in);
        /* EXPENSE u1 1250 2 u2 u3 EQUAL
         * EXPENSE u1 1250 2 u2 u3 EXACT 370 880
         * EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20
         * SHOW 
         * SHOW u1
        */
        String commands[]  =sc.nextLine().split(" ");

        switch(commands[0]){
            case "EXPENSE":{
                String paidBy  = commands[1];
                double amount  = Double.parseDouble(commands[2]);
                int size = Integer.parseInt(commands[3]);
                ArrayList<Split> list = new ArrayList<>();
                int index  = 0;
                while(index<size){
                    list.add(new Split(commands[3+index+1], 0.0));
                    index++;

                }
                switch(commands[4+list.size()]){
                    case "EQUAL":{
                        double equalSplit = amount/list.size();
                        for(Split s : list){
                            s.setSplitAmount(equalSplit);
                        }
                        util.operationE(paidBy, amount, list);
                        break;
                    }
                    case "EXACT":{
                        for(int i =0;i< list.size();i++){
                            list.get(index).setSplitAmount(Double.parseDouble(commands[4+list.size()+i+1]));
                        }
                        util.operationE(paidBy, amount, list);
                        break;
                    }
                    case "PERCENTAGE":{
                        for(int i =0;i< list.size();i++){
                            double splitAmount = amount * Double.parseDouble(commands[4+list.size()+i+1]) *0.01;
                            list.get(index).setSplitAmount(splitAmount);
                        }
                        util.operationE(paidBy, amount, list);
                        break;
                    }
                }
            }
            case "SHOW":{
                if(commands.length ==1){
                    util.show();
                    break;
                }
                util.show(commands[1]);
                break;
            }
        }
    }
}
