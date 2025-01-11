package googledocs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document {
    
    @Override
    public String toString() {
        return "Document [content=" + content + "]";
    }
    private Map<Integer,Line> content;
    private int docId;
    private String name;
    private List<DocUser> listeners;

    public Document(String name){
        this.name = name;
        this.docId = DocUniqueNumberGenerator.getId();
        content = new HashMap<>();
        listeners = new ArrayList<>();
    }

    public Line getLine(int lineNo){
        if(content.containsKey(lineNo)){
            return content.get(lineNo);
        }
       throw new LineNotFoundException("The line is not present in the doc");
    }

    public void addListener(DocUser u){
        this.listeners.add(u);
    }
    public void removeListener(DocUser user){
        this.listeners.remove(user);
    }
    //thread safe
    public void updateName(String name){
        //this is atomic operation as reference assignment is atomic in java
        //hence no race condition, thread safe
        this.name = name;
    }
    public void updatedLine(int lineNumber, DocUser user){
        boolean isUpdated = false;
        if(!content.containsKey(lineNumber)) {
            //create the line if not present
            content.put(lineNumber, new Line("name", null, user, null));
        }
        isUpdated = content.get(lineNumber).update(user);
        // once a line is modified we need to modify all the listers of the file
        if(isUpdated){
            notify(lineNumber,user);
        }
    }
    private void notify(int lineNumber,DocUser u){
        for(DocUser user : listeners){
            if(u.equals(user)) continue;
            user.update(lineNumber);
        }
    }
    public DocMemento saveDocumentVersion(){
        DocMemento memento = new DocMemento(content);
        return memento;
    }
    public void restoreState(DocMemento memento){
        this.content = memento.getState();
    }
}
