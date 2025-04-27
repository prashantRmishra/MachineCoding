package courseregistration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchByName implements SearchStrategy {

    @Override
    public List<Course> search(Map<String, Course> courses,String name) {
        List<Course> list = new ArrayList<>();
        for(Course c : courses.values()){
            if(c.name.equals(name) || name.contains(c.name) || c.name.contains(name)) list.add(c);
        }
        return list;
    }
    
}
