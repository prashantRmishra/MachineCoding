import java.util.List;

public class Member {
    private String id;
    private String name;
    private List<Book> reservedBook;
    public String getId() {
        return id;
    }
    public Member(String name, List<Book> reservedBook) {
        this.name = name;
        this.reservedBook = reservedBook;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Book> getReservedBook() {
        return reservedBook;
    }
    public void setReservedBook(List<Book> reservedBook) {
        this.reservedBook = reservedBook;
    }
}
