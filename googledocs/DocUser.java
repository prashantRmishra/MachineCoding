package googledocs;

public class DocUser {

    private String email;
    private String userName;
    //a version of the doc for this user
    private Document doc;
    public DocUser(String userName,Document doc){
        this.userName =userName;
        this.doc = doc;
        // add this user as a listener of this doc
        doc.addListener(this);
    }
    public String getUserName(){
        return this.userName;
    }
    public String getEmail(){
        return this.email;
    }

    public void update(int line){
        //see the updated line in as it is updated
        System.out.println("update for this user "+ this.getUserName()+": " +this.doc.getLine(line));
    }


}
