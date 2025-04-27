package courseregistration.strategy;

import java.util.List;
import java.util.Map;

import courseregistration.model.Course;

public interface SearchStrategy {
    public List<Course> search(Map<String,Course> courses,String token);
}
