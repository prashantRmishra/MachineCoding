package courseregistration.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import courseregistration.model.Course;

public class SearchByCode implements SearchStrategy {

    @Override
    public List<Course> search(Map<String, Course> courses, String token) {
        List<Course> list =new ArrayList<>();
        for(String code : courses.keySet()){
            if(code.equals(token)) {
                list.add(courses.get(code));
                break;
            }
        }
        return list;
    }
    
}
