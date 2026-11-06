
import java.util.Arrays;

/**
 * Provides an implementation of the Insertion Sort algorithm
 * for sorting integer arrays in ascending order.
 *
 * <p>Insertion Sort works by dividing the array into a sorted
 * and an unsorted portion. It takes each element from the
 * unsorted portion and inserts it into its correct position
 * within the sorted portion.</p>
 *
 * <p>Time Complexity:
 * O(n) best case, O(n^2) average and worst case.</p>
 *
 * <p>Space Complexity: O(1)</p>
 *
 * @author Nasim Bidel
 * @version 1.0
 */
public final class InsertionSort {

    /**
     * Private constructor to prevent instantiation
     * of this utility class.
     */
    private InsertionSort() {
    }

    /**
     * Sorts the specified integer array in ascending order
     * using the Insertion Sort algorithm.
     *
     * <p>The algorithm iterates through the array, comparing
     * each element with the elements before it. Larger elements
     * are shifted one position to the right to make room for
     * the current element.</p>
     *
     * @param data the integer array to be sorted
     * @throws NullPointerException if the array is null
     */
    public static void InsertionSort(int[] data) {

        for (int i = 0; i < data.length; i++) {

            // Store the current element.
            int current = data[i];

            // Start comparing with the previous element.
            int j = i - 1;

            // Shift elements greater than current one position right.
            while (j >= 0 && data[j] > current) {
                data[j + 1] = data[j];
                j--;
            }

            // Insert the current element into its correct position.
            data[j + 1] = current;
        }
    }

    /**
     * Demonstrates the Insertion Sort algorithm using
     * a sample integer array.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        int[] data = {29, 9, 48, 24, 35, 20};

        System.out.println("Before: " + Arrays.toString(data));

        InsertionSort(data);

        System.out.println("After: " + Arrays.toString(data));
    }
}
