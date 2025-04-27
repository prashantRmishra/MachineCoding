package courseregistration.exceptions;

public class CourseAlreadyExistsException extends RuntimeException {

    public CourseAlreadyExistsException(String string) {
        super(string);
    }

}
