import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.security.auth.login.AccountNotFoundException;

public class TransactionManager {
    private final Map<Integer, Account> accounts;

    public TransactionManager(Map<Integer, Account> accounts) {
        this.accounts = new ConcurrentHashMap<>(accounts);
    }

    public Account getAccount(int id) throws AccountNotFoundException {
        if (!accounts.containsKey(id)) {
            throw new AccountNotFoundException("Account ID not found: " + id);
        }
        return accounts.get(id);
    }

    public boolean hasSufficientBalance(int id, double requestedAmount) throws AccountNotFoundException {
        double balance = getAccount(id).getBalance();
        return balance >= requestedAmount;
    }

    public synchronized boolean updateBalance(int id, double amount) throws AccountNotFoundException {
        Account account = getAccount(id);
        account.setBalance(account.getBalance() + amount);
        return true;
    }
}
