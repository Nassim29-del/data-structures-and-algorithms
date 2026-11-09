
/**
 * Represents a grade book that stores students and their subjects.
 * <p>
 * The GradeBook class allows students to be added, searched, sorted,
 * and analyzed. It can calculate subject averages, display grade
 * distributions, and identify the student with the highest average.
 *
 * @author Nasim Bidel
 * @version 1.0
 */
public class GradeBook {

    private Student[] students;
    private String[] subjects;
    private int count;

    /**
     * Constructs a GradeBook with a specified capacity and list of subjects.
     *
     * @param capacity the maximum number of students the grade book can store
     * @param subjects the subjects used to evaluate each student
     * @throws NegativeArraySizeException if capacity is negative
     * @throws NullPointerException if subjects is null
     */
    public GradeBook(int capacity, String[] subjects) {
        this.students = new Student[capacity];
        this.subjects = subjects.clone();
        this.count = 0;
    }

    /**
     * Adds a student to the grade book.
     * <p>
     * A student can only be added if there is available space and
     * the number of their scores matches the number of subjects.
     *
     * @param s the student to add
     * @return true if the student was successfully added;
     *         false if the grade book is full
     * @throws IllegalArgumentException if the student's number of scores
     *         does not match the number of subjects
     * @throws NullPointerException if the student is null
     */
    public boolean addStudent(Student s) {
        if (count == students.length) {
            System.out.println("GradeBook is full, cannot add " + s.getName());
            return false;
        }

        if (s.getScores().length != subjects.length) {
            throw new IllegalArgumentException(
                    "Expected " + subjects.length + " scores");
        }

        students[count++] = s;
        return true;
    }

    /**
     * Searches for a student using their ID.
     *
     * @param id the ID of the student to find
     * @return the matching Student object if found;
     *         null if no student has the specified ID
     */
    public Student findById(int id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId() == id) {
                return students[i];
            }
        }
        return null;
    }

    /**
     * Finds the student with the highest average grade.
     *
     * @return the student with the highest average;
     *         null if the grade book contains no students
     */
    public Student getTopStudent() {
        if (count == 0) {
            return null;
        }

        Student top = students[0];

        for (int i = 1; i < count; i++) {
            if (students[i].getAverage() > top.getAverage()) {
                top = students[i];
            }
        }

        return top;
    }

    /**
     * Calculates the average score for each subject across all students.
     *
     * @return an array containing the average score for each subject;
     *         returns an array of zeros if there are no students
     */
    public double[] getSubjectAverages() {
        double[] averages = new double[subjects.length];

        for (int i = 0; i < count; i++) {
            double[] scores = students[i].getScores();

            for (int j = 0; j < subjects.length; j++) {
                averages[j] += scores[j];
            }
        }

        for (int j = 0; j < averages.length; j++) {
            averages[j] = count == 0 ? 0 : averages[j] / count;
        }

        return averages;
    }

    /**
     * Calculates the number of students in each letter-grade category.
     * <p>
     * The returned array follows this order:
     * A, B, C, D, F.
     *
     * @return an integer array containing the number of students
     *         in each letter-grade category
     */
    public int[] getGradeDistribution() {
        char[] letters = {'A', 'B', 'C', 'D', 'F'};
        int[] counts = new int[letters.length];

        for (int i = 0; i < count; i++) {
            char g = students[i].getLetterGrade();

            for (int k = 0; k < letters.length; k++) {
                if (letters[k] == g) {
                    counts[k]++;
                }
            }
        }

        return counts;
    }

    /**
     * Sorts students by their average grade in descending order.
     * <p>
     * Students with the highest averages appear first.
     * This method uses insertion sort.
     */
    public void sortByAverageDesc() {
        for (int i = 0; i < count; i++) {
            Student key = students[i];
            int j = i - 1;

            while (j >= 0 &&
                    students[j].getAverage() < key.getAverage()) {
                students[j + 1] = students[j];
                j--;
            }

            students[j + 1] = key;
        }
    }

    /**
     * Prints a report containing each student's ID, name,
     * average score, and letter grade.
     */
    public void printReport() {
        System.out.println("ID   Name       Average      Grade");
        System.out.println("----------------------------------");

        for (int i = 0; i < count; i++) {
            System.out.println(students[i]);
        }
    }

    /**
     * Returns a copy of the subjects array.
     *
     * @return a cloned array containing the subjects
     */
    public String[] getSubjects() {
        return subjects.clone();
    }

    /**
     * Returns the current number of students in the grade book.
     *
     * @return the number of students currently stored
     */
    public int size() {
        return count;
    }
}
