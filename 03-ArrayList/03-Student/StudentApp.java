
/**
 * StudentApp is the entry point of the student management application.
 *
 * <p>This class demonstrates the functionality provided by StudentManager,
 * including adding students, searching, sorting, updating GPA values,
 * generating reports, and deleting students.</p>
 *
 * @author
 * @version 1.0
 */
public class StudentApp {

    /**
     * Main method: starts the student management application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // Create a StudentManager to manage the student records.
        StudentManager manager = new StudentManager();

        // Add students to the manager.
        manager.addStudent(new Student(101, "Sara", "Computer Science", 3.85));
        manager.addStudent(new Student(102, "Ali", "Mathematics", 3.10));
        manager.addStudent(new Student(103, "Maya", "Computer Science", 3.55));
        manager.addStudent(new Student(104, "John", "Biology", 2.40));
        manager.addStudent(new Student(105, "Leila", "Mathematics", 3.95));
        manager.addStudent(new Student(106, "Omid", "Biology", 1.90));
        manager.addStudent(new Student(107, "Emma", "Computer Science", 2.75));

        // Attempt to add a student with an existing ID.
        // The manager should reject this duplicate ID.
        manager.addStudent(new Student(103, "Copy", "Physics", 3.00));

        // Display all students.
        StudentManager.print("All students", manager.getAll());

        // Display students sorted alphabetically by name.
        StudentManager.print("Sorted by name", manager.sortedByName());

        // Sort by major alphabetically, then by GPA in descending order.
        StudentManager.print(
            "Sorted by major, then GPA (high to low)",
            manager.sortedByMajorThenGpa()
        );

        // Find and display students whose major is Computer Science.
        StudentManager.print(
            "Computer Science majors",
            manager.findByMajor("computer science")
        );

        // Display students with a GPA of at least 3.5.
        StudentManager.print(
            "Honor roll (GPA >= 3.5)",
            manager.honorRoll(3.5)
        );

        // Demonstrate searching for students by ID.
        System.out.println("\n=== Search ===");

        // Optional.map() extracts the student's name if the student exists.
        // Optional.orElse() provides "not found" if no student is returned.
        System.out.println(
            "Find 105: " +
            manager.findById(105).map(Student::getName).orElse("not found")
        );

        System.out.println(
            "Find 999: " +
            manager.findById(999).map(Student::getName).orElse("not found")
        );

        // Demonstrate updating a student's GPA.
        System.out.println("\n=== Update ===");

        // Update John's GPA using his student ID.
        manager.updateGpa(104, 2.90);

        // Retrieve John and display his updated GPA.
        System.out.println(
            "John's new GPA: " + manager.findById(104).get().getGpa()
        );

        // Generate summary reports.
        System.out.println("\n=== Reports ===");

        // Calculate and display the average GPA of all students.
        System.out.printf("Average GPA: %.2f%n", manager.averageGpa());

        // Display the number of students in each major.
        System.out.println("Students per major: " + manager.countByMajor());

        // Demonstrate deleting students.
        System.out.println("\n=== Delete ===");

        // Remove the student with ID 102.
        System.out.println("Remove ID 102: " + manager.removeById(102));

        // Remove students whose GPA is below 2.0.
        System.out.println(
            "Removed on probation (GPA < 2.0): " +
            manager.removeBelowGpa(2.0)
        );

        // Display the remaining students after deletion.
        StudentManager.print("Final list", manager.getAll());
    }
}
