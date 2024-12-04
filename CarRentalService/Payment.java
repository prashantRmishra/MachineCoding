package CarRentalService;

public class Payment {
    
    
    private Bill bill;
    public Payment(Bill b){
        this.bill = b;
    }
    public boolean makePayment(){
        if(this.bill.isPaid()){
            double amount = bill.getBillAmount();
            ///api call with amount and user payment details will be given to third party payment service
            //payment logic //using payment gateway apis like razor pay for online payment
        //payment can also be made via cash, credit card etc.
            return true;
        }
        return false;
    }
}
