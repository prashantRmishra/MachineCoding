package atm;

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
