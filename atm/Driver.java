package atm;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Driver {
    public static void main(String args[]){
        ATMSystem system = new ATMSystem(TransactionFactory.getInstance(), new AuthenticationManager(), new CashDispenser());

        //user will insert card
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("12/12/2034", formatter);
        Card card = new Card(1, "prashant", date);

        //user's will be show to choose option
        system.updateDisplay(ATMMessage.CHOOSE_FROM_AVAILABLE_OPTIONS);
        //user will choose to withdraw

        //use will give pin
        system.updateDisplay(ATMMessage.ENTER_PIN);
        //user will be authenticated based on card details and the pin ( double authentication to save the round trip)
        boolean validUser = system.authenticateUser(card, Encryption.encryptPass("userPin"));
        //user will be asked to Enter the amount
        if(validUser){
            system.updateDisplay(ATMMessage.ENTER_AMOUNT);
            //user enters the amount
            double amount = 100;
            boolean transactionStatus = false;
             //user's amount will be validated against the available balance 
            if(system.hasSufficientBalance(card.getNo(), amount)){
                transactionStatus = system.updateBalance(amount, card.getNo());
            }
            //if success then the amount will be dispensed and transaction will end;
            if(transactionStatus){
                system.dispenseMoney(amount);
            }
            else{
                system.updateDisplay(ATMMessage.INSUFFICIENT_BALANCE);
            }
        }
        else system.updateDisplay(ATMMessage.AUTH_FAILURE);

        //out of scope is the receipt generation, email,or text message update for the transaction



    }
}
