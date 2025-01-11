package googledocs;


import java.time.LocalDateTime;
import java.util.ConcurrentModificationException;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Line {
    private ReentrantLock lock = new ReentrantLock();
    public Line(String lineEntry, LocalDateTime lastUpdatedTime, DocUser currentUser, DocUser lastModifiedBy) {
        this.lineEntry = lineEntry;
        this.lastUpdatedTime = lastUpdatedTime;
        this.currentUser = currentUser;
        this.lastModifiedBy = lastModifiedBy;
    }
    private String lineEntry;
    private LocalDateTime lastUpdatedTime;
    private DocUser currentUser;
    private DocUser lastModifiedBy;
    public void setLineEntry(String lineEntry) {
        this.lineEntry = lineEntry;
    }
    public void setLastUpdatedTime(LocalDateTime lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
    public void setCurrentUser(DocUser currentUser) {
        this.currentUser = currentUser;
    }
    public void setLastModifiedBy(DocUser lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
    public String getLineEntry() {
        return lineEntry;
    }
    public LocalDateTime getLastUpdatedTime() {
        return lastUpdatedTime;
    }
    public DocUser getCurrentUser() {
        return currentUser;
    }
    @Override
    public String toString() {
        return "Line [lineEntry=" + lineEntry + "]";
    }
    public DocUser getLastModifiedBy() {
        return lastModifiedBy;
    }


    public boolean update(DocUser user){
        //if the line is free from modification then it can be updated 
        if(lock.tryLock()){
            try{
                // take use input
                String input =userInputs();// some user input received 
                lineEntry = input; // this is atomic operation
                return true;
            }
            finally{
                lock.unlock();
                lastUpdatedTime  = LocalDateTime.now();
                lastModifiedBy = user;
                //notify all the listeners of the doc about the lie change updated in this doc
            }
        }
        else{
            //lock can not be achieved 
            //give appropriate message
           
            throw new ConcurrentModificationException("lock is already held by another user, try after some time");
        }
    }

    //dummy user inputs 
    private String userInputs(){
        String inputs[] = {"this is the line give by some user","new line formation","this is a great doc!!"};
        return inputs[new Random().nextInt(2)];
    }

    // Deep copy method
    public Line deepCopy() {
        return new Line(
            this.lineEntry,
            this.lastUpdatedTime != null ? LocalDateTime.from(this.lastUpdatedTime) : null, // LocalDateTime is immutable
            this.currentUser, // Assuming DocUser is immutable, otherwise handle deep copy for DocUser
            this.lastModifiedBy // Same as above
        );
    }
}
