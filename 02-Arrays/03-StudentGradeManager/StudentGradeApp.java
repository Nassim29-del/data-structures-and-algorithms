
/**
 * Runs the Student Grade Book application.
 * <p>
 * The StudentGradeApp class is the entry point of the program.
 * It creates students, adds them to a GradeBook, and demonstrates
 * the grade book's features, including:
 * <ul>
 *     <li>Adding students</li>
 *     <li>Printing student reports</li>
 *     <li>Sorting students by average grade</li>
 *     <li>Finding the top student</li>
 *     <li>Searching for a student by ID</li>
 *     <li>Calculating subject averages</li>
 *     <li>Displaying the grade distribution</li>
 * </ul>
 *
 * @author Nasim Bidel
 * @version 1.0
 */
public class StudentGradeApp {

    /**
     * Starts the Student Grade Book application.
     * <p>
     * Creates a GradeBook with a capacity of five students,
     * adds sample students, and demonstrates the available
     * GradeBook operations.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        String[] subjects = {"Math", "Science", "English", "History"};
        GradeBook book = new GradeBook(5, subjects);

        // Add students to the grade book
        book.addStudent(new Student(101, "Sara",  new double[]{92, 88, 95, 90}));
        book.addStudent(new Student(102, "Ali",   new double[]{75, 80, 68, 72}));
        book.addStudent(new Student(103, "Maya",  new double[]{85, 91, 78, 88}));
        book.addStudent(new Student(104, "John",  new double[]{55, 62, 58, 60}));
        book.addStudent(new Student(105, "Leila", new double[]{98, 94, 97, 99}));
        book.addStudent(new Student(106, "Extra", new double[]{70, 70, 70, 70})); // GradeBook is full

        // Display the report in insertion order
        System.out.println("\n=== Report (insertion order) ===");
        book.printReport();

        // Sort students by average grade and display the sorted report
        book.sortByAverageDesc();
        System.out.println("\n=== Report (sorted by average) ===");
        book.printReport();

        // Display the student with the highest average
        System.out.println("\nTop student: " + book.getTopStudent().getName());

        // Search for a student using their ID
        Student found = book.findById(103);
        System.out.println("Find ID 103: " +
                (found != null ? found.getName() : "not found"));

        // Display the average score for each subject
        System.out.println("\n=== Subject averages ===");
        double[] averages = book.getSubjectAverages();

        for (int i = 0; i < subjects.length; i++) {
            System.out.printf("%-8s %.2f%n", subjects[i], averages[i]);
        }

        // Display the number of students in each grade category
        System.out.println("\n=== Grade distribution ===");
        char[] letters = {'A', 'B', 'C', 'D', 'F'};
        int[] dist = book.getGradeDistribution();

        for (int i = 0; i < letters.length; i++) {
            System.out.println(letters[i] + ": " +
                    "*".repeat(dist[i]) + " (" + dist[i] + ")");
        }
    }
}
