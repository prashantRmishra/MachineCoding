import javax.security.auth.login.AccountNotFoundException;

public class ATMSystem {
    private TransactionManager transactionManager;
    private AuthenticationManager authManager;
    private Display display;
    private CashDispenser dispenser;
    public ATMSystem(TransactionManager instance, AuthenticationManager authenticationManager, CashDispenser cashDispenser) {
        this.transactionManager = instance;
        this.authManager = authenticationManager;
        display = new Display();
        this.dispenser = cashDispenser;
    }

    public void updateDisplay(ATMMessage message){
        this.display.updateText(message);
    }
    public boolean authenticateUser(Card card,String pin){
        try {
            boolean isAuthenticated = authManager.authenticateCard(card, pin, transactionManager);
            updateDisplay(isAuthenticated ? ATMMessage.AUTH_SUCCESS  : ATMMessage.AUTH_FAILURE);
            return isAuthenticated;
        } catch (AccountNotFoundException e) {
            System.out.println(ATMMessage.AUTH_FAILURE.getMessage());
        }
        return false;
    }
    public boolean hasSufficientBalance(int id, double amount){
        try {
            boolean hasSufficientBalance = transactionManager.hasSufficientBalance(id, amount);
            return hasSufficientBalance;
        } catch (AccountNotFoundException e) {
            System.out.println(ATMMessage.ACCOUNT_NOT_FOUND.getMessage());
        }
        return false;
    }
    public boolean updateBalance(double amount, int id){
        try {
            boolean updateBalance = transactionManager.updateBalance(id, -amount);
            updateDisplay(updateBalance ? ATMMessage.TRANSACTION_SUCCESS: ATMMessage.TRANSACTION_FAILURE);
            return updateBalance;
        } catch (AccountNotFoundException e) {
            System.out.println(ATMMessage.ACCOUNT_NOT_FOUND.getMessage());
        }
        return false;
    }
    public void dispenseMoney(double amount){
        dispenser.dispenseCash(amount);
    }
}
