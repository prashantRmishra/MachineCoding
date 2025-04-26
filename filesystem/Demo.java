package filesystem;

public class Demo {
    public static void main(String[] args) {
        InMemoryFileSystemManager manager = new InMemoryFileSystemManager();

        
        System.out.println("mkdir /a");
        manager.makeDirectory("/a");
        System.out.println("mkdir /a/b");
        manager.makeDirectory("/a/b");
        System.out.println("mkdir /a/b/c");
        manager.makeDirectory("/a/b/c");
        System.out.println("mkdir /a/b/c/d/e");
        manager.makeDirectory("/a/b/c/d/e");
        
        System.out.println("addFile /a/b/c/file.txt (write data)");
        manager.addFile("/a/b/c/file.txt", InMemoryFileType.txt);
        System.out.println("addFile /a/b/user.txt (write data)");
        manager.addFile("/a/b/user.txt", InMemoryFileType.txt);
        System.out.println("addFile /a/b/log.txt (write data)");
        manager.addFile("/a/b/log.txt", InMemoryFileType.txt);
       
        System.out.println("readFile /a/b/c/file.txt");
        System.out.println(manager.getFile("/a/b/c/file.txt"));
        System.out.println("readFile /a/b/file.txt");
        System.out.println(manager.getFile("/a/b/user.txt"));
        System.out.println("ls /a/b");
        manager.show("/a/b");
       
        System.out.println("delete /a/b/c/file.txt");
        manager.removeFile("/a/b/c/file.txt");
        //rmdir /a/b/c 
        System.out.println("rmdir /a/b/c ");
        manager.deleteDirectory("/a/b/c"); 
    }
}
