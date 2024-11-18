import java.util.List;

public class Account {
    private int id;
    private String name;
    private Address address;
    private double balance;
    private Card card;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public Card getCard(){
        return this.card;
    }

    public Account(int id, String name, Address address, double balance,Card card) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.balance = balance;
        this.card = card;
    }
    
}


public class Address {

}


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

    public void updateDisplay(String s){
        this.display.updateText(s);
    }
    public boolean authenticateUser(Card card,String pin){
        return authManager.authenticateCard(card, pin, transactionManager);
    }
    public boolean hasSufficientBalance(int id, double amount){
        return transactionManager.hasSufficientBalance(id, amount);
    }
    public boolean updateBalance(double amount, int id){
        return transactionManager.updateBalance(amount, id);
    }
    public void dispenseMoney(double amount){
        dispenser.dispenseCash(amount);
    }
}


import java.time.Duration;
import java.time.LocalDate;

public class AuthenticationManager {
    public AuthenticationManager(){
    }

    public boolean authenticateCard(Card card,String pin,TransactionManager manager){
        //check if card is valid and not expired
        if(isExpired(card.getExpiryDate())) return false;
        //bank will have some sort of logic and might encrypt the given pin and match it against the saved encrypted pin in the database
        Account ac = manager.getAccounts().stream().filter(account-> {
            System.out.println(account.getId());
            if(account.getCard().getNo() == card.getNo()){
                return true;
               
            }
            return false;
        }).findFirst().orElse(null);
        if(ac ==null) return false;
        return true;
    }
    public boolean isExpired(LocalDate date){
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
}


public abstract class Button {
    boolean isPressed = false;
    public abstract void press();
}

import java.time.LocalDate;

public class Card {
    private int no;
    private String name;
    private LocalDate expiryDate;
    public Card(int no, String name, LocalDate expiryDate) {
        this.no = no;
        this.name = name;
        this.expiryDate = expiryDate;
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

}

public class CashDispenser {
    public void dispenseCash(double amount){
        System.out.println("Cash of amount "+ amount +" is dispensed");
    }
}

import java.util.List;

public class Display {
    private String textShow = null;
    private Button cancel;
    private Button exit;
    private List<Button> nums;
    public Display(){
        textShow = "Welcome to the ATM, please insert your card to begin";
    }

    public void updateText(String s){
        System.out.println(s);
        textShow = s;
    }
    public String getText(){
        return this.textShow;
    }
}


public class Encryption {
    public static String encryptPass(String pass){
        //use of encoding algos or hashing to encrypt the pass
        String encryptedPass = "";//algo to encrypt the pass
        return encryptedPass;
    }
}

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionFactory{

    public static TransactionManager getInstance(){
        List<Account> acs = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("12/11/2029",formatter);
        Account account1 = new Account(1, "prashant", null, 100, new Card(1, "prashant",date));
        Account account2 = new Account(2, "sandeep", null, 200, new Card(2, "sandeep", date));
        Account account3 = new Account(3, "ajay", null, 300, new Card(3, "ajay", date));
        acs.add(account1);
        acs.add(account2);
        acs.add(account3);
        System.out.println(acs.size());
        return new TransactionManager(acs);
    }
}

import java.util.List;

public class TransactionManager {
    volatile List<Account> accounts; //better to use HashMap as we will be doing search on it
    public TransactionManager(List<Account> list){
        this.accounts = list;
    }
    public double getBalance(int id){
        Account ac = this.accounts.stream().filter(account->account.getId()==id).findFirst().orElse(null);
        if(ac!=null){
            return ac.getBalance();
        }
        return -1;// if the userId is not present
    }
    public boolean hasSufficientBalance(int id, double requestedAmount){
        double amount = getBalance(id);
        if(amount < requestedAmount) return false;
        return true;
    }
    public Account getAccount(int id){
        return this.accounts.stream().filter(account->account.getId()==id).findFirst().orElse(null);
    }
    public List<Account> getAccounts(){
        return this.accounts;
    }
    public synchronized boolean updateBalance(double val,int id){
        Account ac = getAccount(id);
        if(ac!=null){
            ac.setBalance(val);
            return true;
        }
        return false;
    }
}
