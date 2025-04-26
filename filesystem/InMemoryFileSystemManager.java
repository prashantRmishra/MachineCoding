package filesystem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryFileSystemManager {
    Map<String,Folder> root = new ConcurrentHashMap<>();
    public synchronized void makeDirectory(String location){
        String dirs[] = location.substring(1).split("/");

        if(dirs.length ==1){
            root.put(dirs[0], new Folder(dirs[0]));
        }
        //check if the dirs present
        Folder f = find(dirs,1,root.get(dirs[0]));
        if(f==null){
            System.out.println("Location is invalid");
            return;
        }
        //if folder is already present
        else if(f.folderName.equals(dirs[dirs.length-1])){
            System.out.println("Folder created");
        }
        else{
            //creating new folder
            f.createSubFolder(dirs[dirs.length-1]);
            f.folders.get(dirs[dirs.length-1]).setParent(f);
            System.out.println( "Sub folder "+ dirs[dirs.length-1]+" created within folder "+f.folderName);

        }
    }
    public synchronized void deleteDirectory(String location){
        String dirs[] = location.substring(1).split("/");
        //check if the dirs present
        Folder f = find(dirs,1,root.get(dirs[0]));
        if(f==null){
            System.out.println("Location is invalid");
            return;
        }
        //if folder is already present
        else if(f.folderName.equals(dirs[dirs.length-1])){
            Folder parent = f.parent;
            f.setParent(null);//not required as this will be garbage collected
            parent.folders.remove(f.folderName);
            System.out.println(f.folderName +" removed from the folder "+parent.folderName);
        }
        else{
            //not found
           System.out.println("Folder does not exist ");
        }
    }
    public synchronized void addFile(String locationAndFile,InMemoryFileType type){
        String filesLocation[] = locationAndFile.substring(1).split("/");
        
        if(filesLocation[filesLocation.length-1].indexOf('.')==-1) {
            System.out.println("Please provide valid file and location to be created");
            return;
        }
        else{
            String dirs[]  = new String[filesLocation.length-1];
            for(int i = 0;i< dirs.length;i++){
                dirs[i] = filesLocation[i];
            }
            Folder f = find(dirs,1,root.get(dirs[0]));
            if(f==null){
                System.out.println("Location is invalid");
                return;
            }
            //if folder is already present
            else if(f.folderName.equals(dirs[dirs.length-1])){
                f.createFile(filesLocation[filesLocation.length-1], type);
                System.out.println("File created: "+ f);
            }
            else{
                //creating new folder
                System.out.println("Folder "+f.folderName+" does not exists !");
            }
        }
    }
    public InMemoryFile getFile(String locationAndFile){
        String filesLocation[] = locationAndFile.substring(1).split("/");
        if(filesLocation[filesLocation.length-1].indexOf('.')==-1) {
            System.out.println("Please provide valid file and location to be created");
            return null;
        }
        else{
            String dirs[]  = new String[filesLocation.length-1];
            for(int i = 0;i< dirs.length;i++){
                dirs[i] = filesLocation[i];
            }
            Folder f = find(dirs,1,root.get(dirs[0]));
            if(f==null){
                System.out.println("Location is invalid");
                return null;
            }
            //if folder is already present
            else if(f.folderName.equals(dirs[dirs.length-1])){
                return f.files.getOrDefault(filesLocation[filesLocation.length-1], null);
            }
            else{
                //creating new folder
                System.out.println("Folder "+dirs[dirs.length-1]+" does not exists !");
            }
        }
        return null;
    }
    public synchronized void removeFile(String locationAndFile){
        String filesLocation[] = locationAndFile.substring(1).split("/");
        if(filesLocation[filesLocation.length-1].indexOf('.')==-1) {
            System.out.println("Please provide valid file and location to be deleted");
            return ;
        }
        else{
            String dirs[]  = new String[filesLocation.length-1];
            for(int i = 0;i< dirs.length;i++){
                dirs[i] = filesLocation[i];
            }
            Folder f = find(dirs,1,root.get(dirs[0]));
            if(f==null){
                System.out.println("Location is invalid");
                return ;
            }
            //if folder is already present
            else if(f.folderName.equals(dirs[dirs.length-1])){
                InMemoryFile file = f.files.getOrDefault(filesLocation[filesLocation.length-1], null);
                if(file==null){
                    System.out.println("File is not present at the given location");
                }
                else{
                    file.parent.files.remove(file.fileName);
                    file.parent =null;
                    System.out.println(file.fileName +" removed form the folder "+f.folderName );
                }
            }
            else{
                System.out.println("Folder "+f.folderName+" does not exists !");
            }
        }
        return ;
    }
    public void show(String location){
        String dirs[] = location.substring(1).split("/");
        //check if the dirs present
        Folder f = find(dirs,1,root.get(dirs[0]));
        if(f==null){
            System.out.println("Location is invalid");
            return;
        }
        //if folder is already present
        else if(f.folderName.equals(dirs[dirs.length-1])){
            System.out.println(f);
        }
        else{
            //creating new folder
            f.createSubFolder(dirs[dirs.length-1]);
        }
    }
    public Folder find(String dirs[], int index,Folder f){
        if(index == dirs.length) return f;
        if(f == null) {
            return null;
        }
        if(!f.folders.containsKey(dirs[index])){
            if(index == dirs.length -1) return f;
            return null;
        }
        return find(dirs, index+1,f.folders.get(dirs[index]));
    }
}
