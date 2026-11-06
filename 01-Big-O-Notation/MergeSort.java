
import java.util.Arrays;

/**
 * Provides an implementation of the Merge Sort algorithm
 * for sorting integer arrays in ascending order.
 *
 * <p>Merge Sort is a divide-and-conquer sorting algorithm.
 * It recursively divides an array into smaller subarrays,
 * sorts them, and merges them back together in sorted order.</p>
 *
 * <p>Time Complexity: O(n log n)</p>
 * <p>Space Complexity: O(n)</p>
 *
 * @author Nasim Bidel
 * @version 1.0
 */
public final class MergeSort {

    /**
     * Private constructor to prevent instantiation
     * of this utility class.
     */
    private MergeSort() {
    }

    /**
     * Sorts the specified integer array in ascending order
     * using the Merge Sort algorithm.
     *
     * @param data the integer array to be sorted
     * @throws NullPointerException if the array is null
     */
    public static void mergeSort(int[] data) {
        int[] temp = new int[data.length];
        sort(data, temp, 0, data.length - 1);
    }

    /**
     * Recursively divides the array into smaller subarrays
     * until each subarray contains zero or one element.
     *
     * @param data the original array being sorted
     * @param temp temporary array used during merging
     * @param low the starting index of the current subarray
     * @param high the ending index of the current subarray
     */
    private static void sort(int[] data, int[] temp,
                             int low, int high) {

        if (low >= high) {
            return;
        }

        int mid = low + (high - low) / 2;

        sort(data, temp, low, mid);
        sort(data, temp, mid + 1, high);

        merge(data, temp, low, mid, high);
    }

    /**
     * Merges two adjacent sorted subarrays into one sorted
     * subarray and copies the result back into the original array.
     *
     * <p>The first subarray ranges from low to mid, and the
     * second subarray ranges from mid + 1 to high.</p>
     *
     * @param data the original array containing the subarrays
     * @param temp temporary array used to store merged elements
     * @param low the starting index of the first subarray
     * @param mid the ending index of the first subarray
     * @param high the ending index of the second subarray
     */
    private static void merge(int[] data, int[] temp,
                              int low, int mid, int high) {

        int left = low;
        int right = mid + 1;
        int next = low;

        // Compare elements from both subarrays.
        while (left <= mid && right <= high) {
            if (data[left] <= data[right]) {
                temp[next++] = data[left++];
            } else {
                temp[next++] = data[right++];
            }
        }

        // Copy any remaining elements from the left subarray.
        while (left <= mid) {
            temp[next++] = data[left++];
        }

        // Copy any remaining elements from the right subarray.
        while (right <= high) {
            temp[next++] = data[right++];
        }

        // Copy the merged elements back into the original array.
        System.arraycopy(temp, low, data, low, high - low + 1);
    }

    /**
     * Demonstrates the Merge Sort algorithm using a sample array.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        int[] data = {29, 9, 48, 24, 35, 20};

        System.out.println("Before: " + Arrays.toString(data));

        mergeSort(data);

        System.out.println("After:  " + Arrays.toString(data));
    }
}
