public class Fine {
    private String memberId;
    private String bookId;
    private int noOfDays;
    private double amount;
    public Fine(String memberId, String bookId, int noOfDays, double amount) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.noOfDays = noOfDays;
        this.amount = amount;
    }
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    public String getBookId() {
        return bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    public int getNoOfDays() {
        return noOfDays;
    }
    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    

}
