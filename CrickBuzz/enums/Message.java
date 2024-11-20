package CrickBuzz.enums;

public enum Message {
    allout("All out");
    private final String message;
    Message(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return this.message;
    }

}
