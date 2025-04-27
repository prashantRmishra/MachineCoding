package courseregistration.exceptions;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(String string) {
        super(string);
    }

}
