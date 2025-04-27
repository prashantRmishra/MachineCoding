package courseregistration;

import java.util.concurrent.atomic.AtomicInteger;

public class Course {
    String code;
    String name;
    AtomicInteger maxCapacity;
    Instructor instructor;
    @Override
    public String toString() {
        return "Course [code=" + code + ", name=" + name + ", instructor=" + instructor + ", currentStudents="
                + currentStudents + "]";
    }
    int currentStudents = 0;

    public Course(String code, String name, int maxCapacity, Instructor instructor){
        this.code =code;
        this.name = name;
        this.maxCapacity =new AtomicInteger(maxCapacity);
        this.instructor =instructor;
    }
    public boolean isCourseFull(){
        return currentStudents == maxCapacity.get();
    }
    public synchronized void incrementCurrentStudents(){
        currentStudents++;
    }
    public synchronized void decrementStudentCount(){
        currentStudents--;
    }
    public int getCapacity(){
        return this.maxCapacity.get();
    }
    public  int getCurrentStudents(){
        return currentStudents;
    }
}
