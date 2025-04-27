package courseregistration;

public class CourseAlreadyExistsException extends RuntimeException {

    public CourseAlreadyExistsException(String string) {
        super(string);
    }

}
