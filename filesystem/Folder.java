package filesystem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Folder {
    Map<String,InMemoryFile> files;
    String folderName;
    Map<String,Folder> folders;
    Folder parent;
    
    @Override
    public String toString() {
        return "[Folder name="+folderName +", files=" + files.keySet()+ ", folders=" + folders.keySet() + "]";
    }

    public Folder(String name){
        this.folderName = name;
        files = new ConcurrentHashMap<>();
        folders = new ConcurrentHashMap<>();
        this.parent = null;
    }
    public void setParent(Folder f){
        this.parent = f;
    }
    public boolean isFolderPresent(String folderName){
        return folders.containsKey(folderName);
    }
    public  synchronized void createSubFolder(String name){
        if(folders.containsKey(name)){
            System.out.println("Subfolder with the same name already exists");
            return;
        }
        //store folder
        folders.putIfAbsent(name, new Folder(name));
    }
    public synchronized InMemoryFile createFile(String fileName,InMemoryFileType type){
        InMemoryFile file = null;
        if(!isFilePresent(fileName)){
            file = new InMemoryFile(fileName,type);
            file.parent = this;
            //store file
            files.put(fileName, file);
            return file;
        }
        return file;
    }
    public boolean isFilePresent(String fileName){
        return this.files.containsKey(fileName);
    }
}
