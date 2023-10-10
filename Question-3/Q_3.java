import java.util.ArrayList;
import java.util.List;

class Student {
    private String studentName;
    private int studentId;

    public Student(String name, int id) {
        studentName = name;
        studentId = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void displayInfo() {
        System.out.println("Student Name: " + studentName);
        System.out.println("Student ID: " + studentId);
    }
}

class Course {
    private String courseName;
    private int maxCapacity;
    private List<Enrollment> enrollments;

    public Course(String name, int capacity) {
        courseName = name;
        maxCapacity = capacity;
        enrollments = new ArrayList<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public boolean enrollStudent(Student student) {
        if (enrollments.size() < maxCapacity) {
            Enrollment enrollment = new Enrollment(student, this);
            enrollments.add(enrollment);
            System.out.println(student.getStudentName() + " enrolled in " + courseName);
            return true;
        } else {
            System.out.println("Sorry, " + courseName + " is full. Cannot enroll " + student.getStudentName());
            return false;
        }
    }

    public boolean isCourseFull() {
        return enrollments.size() >= maxCapacity;
    }

    public void displayInfo() {
        System.out.println("Course Name: " + courseName);
        System.out.println("Max Capacity: " + maxCapacity);
        System.out.println("Enrollments: ");
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment.getStudent().getStudentName() + " (ID: " + enrollment.getStudent().getStudentId() + ")");
        }
    }
}

class Enrollment {
    private Student student;
    private Course course;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }
}

class CollegeManager {
    private List<Student> students;
    private List<Course> courses;

    public CollegeManager() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public boolean enrollStudentInCourse(Student student, Course course) {
        if (students.contains(student) && courses.contains(course)) {
            return course.enrollStudent(student);
        }
        return false;
    }

    public List<Student> getStudentsEnrolledInCourse(Course course) {
        List<Student> enrolledStudents = new ArrayList<>();
        if (courses.contains(course)) {
            for (Enrollment enrollment : course.getEnrollments()) {
                enrolledStudents.add(enrollment.getStudent());
            }
        }
        return enrolledStudents;
    }

    public List<Course> getCoursesEnrolledByStudent(Student student) {
        List<Course> enrolledCourses = new ArrayList<>();
        if (students.contains(student)) {
            for (Course course : courses) {
                if (course.getEnrollments().stream().anyMatch(enrollment -> enrollment.getStudent() == student)) {
                    enrolledCourses.add(course);
                }
            }
        }
        return enrolledCourses;
    }
}

public class Q_3 {
    public static void main(String[] args) {
        CollegeManager collegeManager = new CollegeManager();

        // Adding students
        Student student1 = new Student("Alice", 1);
        Student student2 = new Student("Bob", 2);
        Student student3 = new Student("Charlie", 3);
        collegeManager.addStudent(student1);
        collegeManager.addStudent(student2);
        collegeManager.addStudent(student3);

        // Adding courses
        Course course1 = new Course("Mathematics", 2);
        Course course2 = new Course("Science", 3);
        Course course3 = new Course("History", 1);
        collegeManager.addCourse(course1);
        collegeManager.addCourse(course2);
        collegeManager.addCourse(course3);

        // Enrolling students in courses
        collegeManager.enrollStudentInCourse(student1, course1);
        collegeManager.enrollStudentInCourse(student2, course1);
        collegeManager.enrollStudentInCourse(student3, course2);

        // Displaying students enrolled in a course
        System.out.println("Students enrolled in Mathematics:");
        List<Student> mathEnrolledStudents = collegeManager.getStudentsEnrolledInCourse(course1);
        for (Student student : mathEnrolledStudents) {
            student.displayInfo();
        }

        // Displaying courses a student is enrolled in
        System.out.println("\nCourses enrolled by Alice:");
        List<Course> aliceEnrolledCourses = collegeManager.getCoursesEnrolledByStudent(student1);
        for (Course course : aliceEnrolledCourses) {
            course.displayInfo();
        }
    }
}
