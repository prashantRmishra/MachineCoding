package Splitwise;

public class Split {
    String userid;
    double splitAmount;
    public Split(String u, double a){
        this.userid =u;
        this.splitAmount = a;
    }

    public void setSplitAmount(double amount){
        this.splitAmount = amount;
    }
    public double getSplitAmount(){
        return this.splitAmount;
    }
    public String getUserId(){
        return this.userid;
    }
}
