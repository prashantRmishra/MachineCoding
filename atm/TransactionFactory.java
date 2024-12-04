package atm;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionFactory{

    /**
     * @return
     */
    public static TransactionManager getInstance(){
        Map<Integer,Account> acs = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("12/11/2029",formatter);
        Account account1 = new Account(1, "prashant", null, 100, new Card(1, "prashant",date));
        Account account2 = new Account(2, "sandeep", null, 200, new Card(2, "sandeep", date));
        Account account3 = new Account(3, "ajay", null, 300, new Card(3, "ajay", date));
        acs.put(account1.getId(),account1);
        acs.put(account2.getId(),account2);
        acs.put(account3.getId(),account3);
        return new TransactionManager(acs);
    }
}