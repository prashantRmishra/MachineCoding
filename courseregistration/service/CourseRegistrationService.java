package courseregistration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import courseregistration.exceptions.CourseAlreadyExistsException;
import courseregistration.exceptions.CourseIsFullException;
import courseregistration.exceptions.CourseNotFoundException;
import courseregistration.exceptions.StudentNotRegisteredForCourseException;
import courseregistration.exceptions.UserAlreadyPresentException;
import courseregistration.exceptions.UserNotFoundException;
import courseregistration.model.Course;
import courseregistration.model.Instructor;
import courseregistration.model.UniversityStudent;
import courseregistration.strategy.SearchByName;
import courseregistration.strategy.SearchStrategy;

public class CourseRegistrationService {
    public Map<String,List<String>> registrationDetailsPerStudent;
    public Map<String,List<String>> registrationDetailsPerCourse;
    public Map<String,Course> courses;
    public Map<String,UniversityStudent> students;
    public Map<String,Instructor> instructors;
    SearchStrategy searchStrategy;
    private ReentrantLock lock = new ReentrantLock();

    public CourseRegistrationService(){
        this.registrationDetailsPerStudent =new ConcurrentHashMap<>();
        this.courses = new ConcurrentHashMap<>();
        this.students = new ConcurrentHashMap<>();
        this.instructors = new ConcurrentHashMap<>();
        searchStrategy = new SearchByName();
        registrationDetailsPerCourse = new ConcurrentHashMap<>();
        registrationDetailsPerStudent = new ConcurrentHashMap<>();
        
    }
    public void addStudent(UniversityStudent student) throws Exception{
        if(lock.tryLock()){
            try {
                if(isStudentPresent(student.id)){
                    throw new UserAlreadyPresentException("Student is already present or admitted in the University");
                }
                students.put(student.id, student);
            }
            finally{
                lock.unlock();
            }
        }
    }
    //add student to a course
    public void registerStudent(String studentId, String courseCode) throws Exception{
        //check if course is present and has capacity to register student
        if(!isStudentPresent(studentId)){
            throw new UserNotFoundException("Student with the given Id does not exists");
        }
        if(!isCoursePresent(courseCode)){
            throw new CourseNotFoundException("Course with the id is not present");
        }
        else if(courses.get(courseCode).isCourseFull()){
            throw new CourseIsFullException(courseCode + " Course is full and can not register more students");
        }

        //register the student, update the appropriate objects
        if(lock.tryLock()){
            try{
                registrationDetailsPerStudent.putIfAbsent(studentId, new ArrayList<>());
                registrationDetailsPerStudent.get(studentId).add(courseCode);
                registrationDetailsPerCourse.putIfAbsent(courseCode, new ArrayList<>());
                registrationDetailsPerCourse.get(courseCode).add(studentId);
                courses.get(courseCode).incrementCurrentStudents();
            }
            finally{
                lock.unlock();
            }
        }
    }
    //register instructor to a course
    public void registerInstructorToACourse(String instructorId, String courseCode) throws Exception{
        //check if course is present and has capacity to register student
        if(!isInstructorPresent(instructorId)){
            throw new UserNotFoundException("Instructor with the given Id does not exists");
        }
        if(!isCoursePresent(courseCode)){
            throw new CourseNotFoundException("Course with the id is not present");
        }
        courses.get(courseCode).instructor = instructors.get(instructorId);
    }

    //remove student from course
    public void removeStudentFromCourse(String studentId, String courseId){
        if(!isStudentPresent(studentId)){
            throw new UserNotFoundException("Student with the given Id does not exists");
        }
        if(!isCoursePresent(courseId)){
            throw new CourseNotFoundException("Course with the id is not present");
        }
        if(!registrationDetailsPerCourse.get(courseId).contains(studentId)){
            throw new StudentNotRegisteredForCourseException("Student is not registered for the given course");
        }
        if(lock.tryLock()){
            try{
                //remove student from respective objects
                registrationDetailsPerCourse.get(courseId).remove(studentId);
                registrationDetailsPerStudent.get(studentId).remove(courseId);
                courses.get(courseId).decrementStudentCount();
            }finally{
                lock.unlock();
            }
        }
    }
    //add instructor
    public void addInstructor(Instructor instructor){
        if(isInstructorPresent(instructor.id)) throw new UserAlreadyPresentException("Instructor is already admitted in the University");
        if(lock.tryLock()){
           try{
            instructors.put(instructor.id, instructor);
           }
           finally{
            lock.unlock();
           }
        }
    }
    //remove instructor
    public void removeInstructor(String id){
        if(!isInstructorPresent(id)) throw new UserNotFoundException("Instructor with the id is not present");
        if(lock.tryLock()){
            try{
                instructors.remove(id);
            }finally{
                lock.unlock();
            }
        }
    }
    //add new course
    public void addCourse(Course c) throws Exception{
        if(isCoursePresent(c.code)){
            throw new CourseAlreadyExistsException("Course with same code already present");
           
        }
        if(lock.tryLock()){
            try{
                this.courses.put(c.code, c);
            }
            finally{
                lock.unlock();
            }
        }
    }
    //remove  course
    public void removeCourse(String courseId) throws Exception{
        if(!isCoursePresent(courseId)){
            throw new CourseNotFoundException("Course with the id is not present");
        }
        if(lock.tryLock()){
            try{
                courses.remove(courseId);
            }
            finally{
                lock.unlock();
            }
        }
    }

    private boolean isCoursePresent(String courseId){
        return courses.containsKey(courseId);
    }
    private boolean isStudentPresent(String studentId){
        return students.containsKey(studentId);
    }
    private boolean isInstructorPresent(String instructorId){
        return instructors.containsKey(instructorId);
    }
    public Map<String,Course> getAllCourses(){
        return courses;
    }

    //view registered courses for a given student

    //search for course
    public List<Course> get(String token, SearchStrategy strategy){
        this.searchStrategy = strategy;
        return strategy.search(courses, token);
    }

}
