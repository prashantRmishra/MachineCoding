package courseregistration.exceptions;

public class UserAlreadyPresentException extends RuntimeException {

    public UserAlreadyPresentException(String string) {
        super(string);
    }

}
