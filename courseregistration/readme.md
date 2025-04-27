# University course registration system

Requirements
The course registration system should allow students to register for courses and view their registered courses.
Each course should have a course code, name, instructor, and maximum enrollment capacity.
Students should be able to search for courses based on course code or name.
The system should prevent students from registering for courses that have reached their maximum enrollment capacity.
The system should handle concurrent registration requests from multiple students.
The system should ensure data consistency and prevent race conditions.
The system should be extensible to accommodate future enhancements and new features.
----------------
Entities:
Student
Instructor
Course
CourseRegistrationService


```output
..........Before registration of any students.............
[UniversityStudent [name=Sandeep, email=sandeep@gmail.com, id=student-20254], UniversityStudent [name=Prashant, email=prashantrmishra@gmail.com, id=student-20253]]
[Instructor [name=Bhavkar, id=Instructor-20251], Instructor [name=DevarConda, id=Instructor-20252]]
[Course [code=physics, name=physics, instructor=Instructor [name=Bhavkar, id=Instructor-20251], currentStudents=0], Course [code=Math, name=Math, instructor=Instructor [name=Bhavkar, id=Instructor-20251], currentStudents=0]]
..........after registration of one students per course.............
[Course [code=physics, name=physics, instructor=Instructor [name=Bhavkar, id=Instructor-20251], currentStudents=1], Course [code=Math, name=Math, instructor=Instructor [name=DevarConda, id=Instructor-20252], currentStudents=1]]
..........trying to register a student to a full course .............
Math Course is full and can not register more students
..........removing the student from a course that is full and trying to add again a student to the same course .............
..........after registering a new student .............
{physics=[student-20253], Math=[student-20253]}
[Course [code=physics, name=physics, instructor=Instructor [name=Bhavkar, id=Instructor-20251], currentStudents=1], Course [code=Math, name=Math, instructor=Instructor [name=DevarConda, id=Instructor-20252], currentStudents=1]]
..........Search result of given course name.............
[Course [code=physics, name=physics, instructor=Instructor [name=Bhavkar, id=Instructor-20251], currentStudents=1]]
```
