package atm;
public enum ATMMessage {
    WELCOME("Welcome to the ATM, please insert your card to begin"),
    AUTH_SUCCESS("Authentication successful"),
    AUTH_FAILURE("Authentication failed"),
    INSUFFICIENT_BALANCE("Insufficient balance. Please try a lower amount."),
    TRANSACTION_SUCCESS("Transaction successful! Thank you."),
    TRANSACTION_FAILURE("Transaction failed. Please try again."),
    ACCOUNT_NOT_FOUND("Account not found"),
    CHOOSE_FROM_AVAILABLE_OPTIONS("1. Balance Enquiry\n2. Withdraw amount"), 
    ENTER_PIN("Enter your pin"),
    ENTER_AMOUNT("Enter withdrawal amount");

    private final String message;

    ATMMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
