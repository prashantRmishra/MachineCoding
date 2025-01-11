package googledocs;

import java.util.ArrayList;
import java.util.List;

public class VersionManager {
    private List<DocMemento> mementos;

    public VersionManager(){
        this.mementos = new ArrayList<>();
    }
    public void add(DocMemento memento){
        this.mementos.add(memento);
    }
    public DocMemento getVersion(int index){
        if(index> mementos.size()-1) return null;
        return mementos.get(index);
    }
}
