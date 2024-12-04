package atm;
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
    public String getName() {
        return name;
    }
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}
