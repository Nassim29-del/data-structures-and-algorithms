
/**
 * Represents a student with an ID, name, and an array of scores.
 * <p>
 * The Student class provides methods to retrieve student information,
 * calculate the average and highest score, and determine the
 * student's letter grade.
 *
 * @author Nasim Bidel
 * @version 1.0
 */
public class Student {

    private int id;
    private String name;
    private double[] scores;

    /**
     * Constructs a Student with an ID, name, and scores.
     * <p>
     * A defensive copy of the scores array is created to prevent
     * external modifications to the student's scores.
     *
     * @param id the student's unique identification number
     * @param name the student's name
     * @param scores an array containing the student's scores
     * @throws NullPointerException if scores is null
     */
    public Student(int id, String name, double[] scores) {
        this.id = id;
        this.name = name;
        this.scores = scores.clone();
    }

    /**
     * Returns the student's ID.
     *
     * @return the student's identification number
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the student's name.
     *
     * @return the student's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a copy of the student's scores.
     * <p>
     * A cloned array is returned to prevent callers from
     * modifying the original scores.
     *
     * @return a copy of the student's scores
     */
    public double[] getScores() {
        return scores.clone();
    }

    /**
     * Calculates the student's average score.
     *
     * @return the average of all scores;
     *         returns 0 if the student has no scores
     */
    public double getAverage() {
        if (scores.length == 0) {
            return 0;
        }

        double total = 0;

        for (double s : scores) {
            total += s;
        }

        return total / scores.length;
    }

    /**
     * Finds the highest score achieved by the student.
     *
     * @return the highest score
     * @throws ArrayIndexOutOfBoundsException if the scores array is empty
     */
    public double getHighestScore() {
        double max = scores[0];

        for (double s : scores) {
            if (s > max) {
                max = s;
            }
        }

        return max;
    }

    /**
     * Determines the student's letter grade based on their average.
     * <p>
     * The grading scale is:
     * <ul>
     *     <li>A: 90 or above</li>
     *     <li>B: 80 to 89.99</li>
     *     <li>C: 70 to 79.99</li>
     *     <li>D: 60 to 69.99</li>
     *     <li>F: below 60</li>
     * </ul>
     *
     * @return the student's letter grade (A, B, C, D, or F)
     */
    public char getLetterGrade() {
        double avg = getAverage();

        if (avg >= 90) {
            return 'A';
        } else if (avg >= 80) {
            return 'B';
        } else if (avg >= 70) {
            return 'C';
        } else if (avg >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    /**
     * Returns a formatted string containing the student's ID,
     * name, average score, and letter grade.
     *
     * @return a formatted representation of the student
     */
    @Override
    public String toString() {
        return String.format("%-4d %-10s avg: %6.2f  grade: %c",
                id, name, getAverage(), getLetterGrade());
    }
}
