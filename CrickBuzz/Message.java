package CrickBuzz;

public enum Message {
    allout("All out");
    private final String message;
    Message(String message) {
        this.message = message;
    }
    
    String getMessage() {
        return this.message;
    }

}
