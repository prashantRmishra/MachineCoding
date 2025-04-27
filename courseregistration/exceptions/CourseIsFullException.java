package courseregistration.exceptions;

public class CourseIsFullException extends RuntimeException{

    public CourseIsFullException(String string) {
        super(string);
    }

}
