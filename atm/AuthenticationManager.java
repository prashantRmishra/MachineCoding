import java.time.Duration;
import java.time.LocalDate;

import javax.security.auth.login.AccountNotFoundException;

public class AuthenticationManager {
    public AuthenticationManager(){
    }

    public boolean authenticateCard(Card card,String pin,TransactionManager manager) throws AccountNotFoundException{
        //check if card is valid and not expired
        if(isExpired(card.getExpiryDate())) return false;
        //bank will have some sort of logic and might encrypt the given pin and match it against the saved encrypted pin in the database
        Account ac = manager.getAccount(card.getNo());
        if(ac ==null) return false;
        return true;
    }
    public boolean isExpiredDepricated(LocalDate date){
        int yd = date.getYear()-LocalDate.now().getYear();
        int md = date.getMonthValue()-LocalDate.now().getMonthValue() ;
        int days =date.getDayOfMonth() - LocalDate.now().getDayOfMonth();
       
        if( yd >0) return false;
        if(yd ==0){
            if(md > 0) return false;
            if(md ==0){
                if(days > 0) return false;
            }
        }
        System.out.println("card is expired  ");
        return true;
        
    }
    public boolean isExpired(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }
}
