package courseregistration;

public class StudentNotRegisteredForCourseException extends RuntimeException {

    public StudentNotRegisteredForCourseException(String string) {
        super(string);
    }

}
