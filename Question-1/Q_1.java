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
    private List<Student> enrolledStudents;

    public Course(String name, int capacity) {
        courseName = name;
        maxCapacity = capacity;
        enrolledStudents = new ArrayList<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void enrollStudent(Student student) {
        if (enrolledStudents.size() < maxCapacity) {
            enrolledStudents.add(student);
            System.out.println(student.getStudentName() + " enrolled in " + courseName);
        } else {
            System.out.println("Sorry, " + courseName + " is full. Cannot enroll " + student.getStudentName());
        }
    }

    public boolean isCourseFull() {
        return enrolledStudents.size() >= maxCapacity;
    }

    public void displayInfo() {
        System.out.println("Course Name: " + courseName);
        System.out.println("Max Capacity: " + maxCapacity);
        System.out.println("Enrolled Students: ");
        for (Student student : enrolledStudents) {
            System.out.println(student.getStudentName() + " (ID: " + student.getStudentId() + ")");
        }
    }
}

public class Q_1 {
    public static void main(String[] args) {
        // Create students
        Student student1 = new Student("Alice", 1);
        Student student2 = new Student("Bob", 2);
        Student student3 = new Student("Charlie", 3);

        // Create courses
        Course course1 = new Course("Mathematics", 2);
        Course course2 = new Course("Science", 3);

        // Enroll students in courses
        course1.enrollStudent(student1);
        course1.enrollStudent(student2);
        course1.enrollStudent(student3); // This should print a message that the course is full

        course2.enrollStudent(student2);
        course2.enrollStudent(student3);

        // Display student and course information
        System.out.println("\nStudent Information:");
        student1.displayInfo();
        student2.displayInfo();
        student3.displayInfo();

        System.out.println("\nCourse Information:");
        course1.displayInfo();
        course2.displayInfo();

        // Check if a course is full
        System.out.println("\nIs " + course1.getCourseName() + " full? " + course1.isCourseFull());
        System.out.println("Is " + course2.getCourseName() + " full? " + course2.isCourseFull());
    }
}
