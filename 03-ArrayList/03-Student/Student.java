
/**
 * Represents a student with personal academic information.
 *
 * This class demonstrates:
 * 1. Encapsulation using private fields
 * 2. Constructor initialization
 * 3. Getter methods
 * 4. Setter methods
 * 5. Input validation
 * 6. Method overriding using toString()
 *
 * @author Nasim Bidel
 * @version 1.0
 */
public class Student {

    // Student information
    // final means these fields cannot be reassigned after initialization
    private final int id;
    private final String name;
    private final String major;

    // GPA can be updated using the setter method
    private double gpa;

    /**
     * Constructs a Student object with the specified information.
     *
     * @param id the student's unique ID
     * @param name the student's name
     * @param major the student's academic major
     * @param gpa the student's grade point average
     * @throws IllegalArgumentException if GPA is outside 0.0 to 4.0
     */
    public Student(int id, String name, String major, double gpa) {

        // Validate GPA before assigning it
        if (gpa < 0 || gpa > 4.0) {
            throw new IllegalArgumentException(
                    "GPA must be between 0.0 and 4.0"
            );
        }

        // Initialize object fields using constructor parameters
        this.id = id;
        this.name = name;
        this.major = major;
        this.gpa = gpa;
    }

    /**
     * Returns the student's ID.
     *
     * @return the student ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the student's name.
     *
     * @return the student name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the student's academic major.
     *
     * @return the student's major
     */
    public String getMajor() {
        return major;
    }

    /**
     * Returns the student's GPA.
     *
     * @return the current GPA
     */
    public double getGpa() {
        return gpa;
    }

    /**
     * Updates the student's GPA.
     *
     * GPA must remain between 0.0 and 4.0.
     *
     * @param gpa the new GPA value
     * @throws IllegalArgumentException if GPA is outside 0.0 to 4.0
     */
    public void setGpa(double gpa) {

        // Validate the new GPA before updating the field
        if (gpa < 0 || gpa > 4.0) {
            throw new IllegalArgumentException(
                    "GPA must be between 0.0 and 4.0"
            );
        }

        this.gpa = gpa;
    }

    /**
     * Returns a formatted string containing student information.
     *
     * @return formatted student details
     */
    @Override
    public String toString() {

        return String.format(
                "%-4d %-8s %-18s GPA %.2f",
                id, name, major, gpa
        );
    }
}
