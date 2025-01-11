package googledocs;

import java.util.HashMap;
import java.util.Map;

public class DocMemento {
    private Map<Integer,Line> content;
    public DocMemento(Map<Integer,Line> content) {
        this.content =new HashMap<>();
        for(Map.Entry<Integer,Line> e  : content.entrySet()){
            //deep copy of line should be create to have true immutable snapshot
            this.content.put(e.getKey(), e.getValue().deepCopy());
        }
    }

    public Map<Integer,Line> getState(){
        return this.content;
    }
}
