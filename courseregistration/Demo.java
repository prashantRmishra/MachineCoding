package courseregistration;

public class Demo {
    public static void main(String[] args)  throws Exception{

        CourseRegistrationService service  = new CourseRegistrationService();
        Instructor in1 = new Instructor("Bhavkar", "9986755433", "Airoli");
        Instructor in2 = new Instructor("DevarConda", "454545454", "Thane");
        Course c1 = new Course("physics", "physics", 4, in1);
        Course c2 = new Course("Math", "Math", 1, in1);
        UniversityStudent st1 = new UniversityStudent("Prashant", "prashantrmishra@gmail.com", "9786866544", "Thane") ;
        UniversityStudent st2 = new UniversityStudent("Sandeep", "sandeep@gmail.com", "46465455353", "Vikhroli") ;

        service.addInstructor(in1);
        service.addInstructor(in2);
        service.addStudent(st1);
        service.addStudent(st2);
        service.addCourse(c1);
        service.addCourse(c2);

        //before anything
        System.out.println("..........Before registration of any students.............");
        System.out.println(service.students.values());
        System.out.println(service.instructors.values());
        System.out.println(service.courses.values());

        service.registerInstructorToACourse(in1.id, c1.code);
        service.registerStudent(st1.id, c1.code);
        service.registerInstructorToACourse(in2.id, c2.code);
        service.registerStudent(st2.id, c2.code);
        System.out.println("..........after registration of one students per course.............");
        System.out.println(service.courses.values());
        System.out.println("..........trying to register a student to a full course .............");
        try {
            service.registerStudent(st1.id, c2.code);// exception
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        System.out.println("..........removing the student from a course that is full and trying to add again a student to the same course .............");
        service.removeStudentFromCourse(st2.id, c2.code);
        service.registerStudent(st1.id, c2.code);// now the st1 can be registered with the c2
        System.out.println("..........after registering a new student .............");
        System.out.println(service.registrationDetailsPerCourse);
        System.out.println(service.courses.values());
        
        System.out.println("..........Search result of given course name.............");
        System.out.println(service.get("physics", new SearchByName()));
    



    }
}
