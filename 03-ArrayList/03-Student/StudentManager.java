
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * StudentManager manages a collection of Student objects.
 *
 * <p>It provides CRUD operations (Create, Read, Update, Delete),
 * searching, sorting, filtering, and reporting functionality.</p>
 *
 * <p>Students are stored internally in an ArrayList.</p>
 *
 * @author Nasim Bidel
 * @version 1.0
 */
public class StudentManager {

    // Internal collection used to store student records.
    private final List<Student> students = new ArrayList<>();

    // ------------------------------------------------------------
    // CREATE
    // ------------------------------------------------------------

    /**
     * Adds a student to the collection if their ID is unique.
     *
     * @param s the student to add
     * @return true if the student was added, false if the ID already exists
     */
    public boolean addStudent(Student s) {

        // Check whether a student with the same ID already exists.
        if (findById(s.getId()).isPresent()) {
            System.out.println(
                "Duplicate ID " + s.getId() + ": " +
                s.getName() + " not added"
            );
            return false;
        }

        // Add the student if the ID is unique.
        students.add(s);
        return true;
    }

    // ------------------------------------------------------------
    // READ / SEARCH
    // ------------------------------------------------------------

    /**
     * Searches for a student using their ID.
     *
     * @param id the student ID to search for
     * @return an Optional containing the student if found,
     *         or an empty Optional otherwise
     */
    public Optional<Student> findById(int id) {

        // Iterate through the collection to find a matching ID.
        for (Student s : students) {
            if (s.getId() == id) {
                return Optional.of(s);
            }
        }

        // Return an empty Optional if no matching student exists.
        return Optional.empty();
    }

    /**
     * Finds all students belonging to a specified major.
     *
     * @param major the major to search for
     * @return a list containing all matching students
     */
    public List<Student> findByMajor(String major) {

        List<Student> result = new ArrayList<>();

        // Compare majors without considering uppercase or lowercase letters.
        for (Student s : students) {
            if (s.getMajor().equalsIgnoreCase(major)) {
                result.add(s);
            }
        }

        return result;
    }

    /**
     * Finds students whose GPA meets or exceeds the minimum GPA.
     *
     * <p>Results are sorted by GPA in descending order.</p>
     *
     * @param minGpa the minimum GPA required
     * @return a list of qualifying students, sorted by GPA
     */
    public List<Student> honorRoll(double minGpa) {

        // Use a stream to filter and sort the students.
        return students.stream()
                .filter(s -> s.getGpa() >= minGpa)
                .sorted(
                    Comparator.comparingDouble(Student::getGpa).reversed()
                )
                .toList();
    }

    // ------------------------------------------------------------
    // UPDATE
    // ------------------------------------------------------------

    /**
     * Updates the GPA of a student identified by their ID.
     *
     * @param id the ID of the student to update
     * @param newGpa the new GPA value
     * @return true if the student was found and updated,
     *         false if the student does not exist
     */
    public boolean updateGpa(int id, double newGpa) {

        // Search for the student and store the result in an Optional.
        Optional<Student> s = findById(id);

        // Update the GPA only if the student exists.
        s.ifPresent(student -> student.setGpa(newGpa));

        return s.isPresent();
    }

    // ------------------------------------------------------------
    // DELETE
    // ------------------------------------------------------------

    /**
     * Removes a student using their ID.
     *
     * <p>An Iterator is used to safely remove an element
     * while traversing the collection.</p>
     *
     * @param id the ID of the student to remove
     * @return true if the student was removed, false if not found
     */
    public boolean removeById(int id) {

        // Create an Iterator to traverse the student collection.
        Iterator<Student> it = students.iterator();

        while (it.hasNext()) {

            // Retrieve the next student and check their ID.
            if (it.next().getId() == id) {

                // Safely remove the current element.
                it.remove();
                return true;
            }
        }

        return false;
    }

    /**
     * Removes all students whose GPA is below a specified threshold.
     *
     * @param minGpa the minimum GPA students must have
     * @return the number of students removed
     */
    public int removeBelowGpa(double minGpa) {

        // Store the collection size before removing students.
        int before = students.size();

        // removeIf() removes every student matching the condition.
        students.removeIf(s -> s.getGpa() < minGpa);

        // Calculate and return the number of removed students.
        return before - students.size();
    }

    // ------------------------------------------------------------
    // SORT
    // ------------------------------------------------------------

    /**
     * Returns a copy of the students sorted alphabetically by name.
     *
     * <p>The original collection order is not changed.</p>
     *
     * @return a new sorted list of students
     */
    public List<Student> sortedByName() {

        // Create a copy so the original collection remains unchanged.
        List<Student> copy = new ArrayList<>(students);

        // Sort students alphabetically by name.
        copy.sort(Comparator.comparing(Student::getName));

        return copy;
    }

    /**
     * Returns students sorted by major alphabetically,
     * then by GPA in descending order within each major.
     *
     * @return a new sorted list of students
     */
    public List<Student> sortedByMajorThenGpa() {

        List<Student> copy = new ArrayList<>(students);

        // First compare majors alphabetically.
        // If majors are equal, compare GPA from highest to lowest.
        copy.sort(
            Comparator.comparing(Student::getMajor)
                .thenComparing(
                    Comparator.comparingDouble(Student::getGpa).reversed()
                )
        );

        return copy;
    }

    // ------------------------------------------------------------
    // REPORTS
    // ------------------------------------------------------------

    /**
     * Calculates the average GPA of all students.
     *
     * @return the average GPA, or 0.0 if there are no students
     */
    public double averageGpa() {

        // Avoid division by zero when the collection is empty.
        if (students.isEmpty()) {
            return 0.0;
        }

        double total = 0;

        // Calculate the sum of all GPAs.
        for (Student s : students) {
            total += s.getGpa();
        }

        // Divide the total GPA by the number of students.
        return total / students.size();
    }

    /**
     * Counts how many students belong to each major.
     *
     * @return a map containing each major and its student count
     */
    public Map<String, Integer> countByMajor() {

        Map<String, Integer> counts = new HashMap<>();

        // Count the students belonging to each major.
        for (Student s : students) {

            String major = s.getMajor();

            // Increase the existing count or start at 1.
            counts.put(major, counts.getOrDefault(major, 0) + 1);
        }

        return counts;
    }

    // ------------------------------------------------------------
    // GENERAL METHODS
    // ------------------------------------------------------------

    /**
     * Returns an unmodifiable snapshot of all students.
     *
     * <p>The returned list cannot be structurally modified,
     * but its Student objects are still mutable.</p>
     *
     * @return an unmodifiable copy of the student list
     */
    public List<Student> getAll() {
        return List.copyOf(students);
    }

    /**
     * Returns the total number of students.
     *
     * @return the number of students in the collection
     */
    public int size() {
        return students.size();
    }

    /**
     * Prints a titled list of students to the console.
     *
     * @param title the heading to display
     * @param list the list of students to print
     */
    public static void print(String title, List<Student> list) {

        System.out.println(
            "\n=== " + title + " (" + list.size() + ") ==="
        );

        // Print each student using their toString() method.
        for (Student s : list) {
            System.out.println("  " + s);
        }
    }
}
