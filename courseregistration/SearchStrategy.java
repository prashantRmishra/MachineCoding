package courseregistration;

import java.util.List;
import java.util.Map;

public interface SearchStrategy {
    public List<Course> search(Map<String,Course> courses,String token);
}
