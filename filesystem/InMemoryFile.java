package filesystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InMemoryFile {
    String fileName;
    LocalDateTime createdTime;
    LocalDateTime updatedTime;
    StringBuilder content;
    InMemoryFileType type;
    @Override
    public String toString() {
        return "[fileName=" + fileName + ", updatedTime=" + updatedTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + ", content=" + content + ", type=" + type + "]";
    }

    Folder parent;
    public InMemoryFile(String fileName,InMemoryFileType type){
        this.fileName = fileName;
        this.createdTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
        content = new StringBuilder();
        this.type = type;
    }
    public synchronized void setParent(Folder f){
        this.parent  = f;
    }
    public synchronized void append(String s){
        this.content.append(s);
        this.updatedTime = LocalDateTime.now();
    }

    public String getContent(){
        return this.content.toString();
    }
}
