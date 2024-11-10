public class Book {
    private String id;
    private String bookName;
    private String authorId;
    private String dateOfPublication;
    public Book(String bookName, String authorId, String dateOfPublication) {
        this.bookName = bookName;
        this.authorId = authorId;
        this.dateOfPublication = dateOfPublication;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getAuthorId() {
        return authorId;
    }
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
    public String getDateOfPublication() {
        return dateOfPublication;
    }
    public void setDateOfPublication(String dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

}
