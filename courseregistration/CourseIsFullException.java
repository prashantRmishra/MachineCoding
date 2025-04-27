package courseregistration;

public class CourseIsFullException extends RuntimeException{

    public CourseIsFullException(String string) {
        super(string);
    }

}
