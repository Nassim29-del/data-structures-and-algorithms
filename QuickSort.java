
import java.util.Arrays;

/**
 * Provides an implementation of the Quick Sort algorithm
 * for sorting integer arrays in ascending order.
 *
 * <p>Quick Sort is a divide-and-conquer sorting algorithm.
 * It selects a pivot element, partitions the array around
 * the pivot, and recursively sorts the resulting partitions.</p>
 *
 * <p>Time Complexity:
 * O(n log n) average case, O(n^2) worst case.</p>
 *
 * <p>Space Complexity:
 * O(log n) average case due to recursion.</p>
 *
 * @author Nasim Bidel
 * @version 1.0
 */
public final class QuickSort {

    /**
     * Private constructor to prevent instantiation
     * of this utility class.
     */
    private QuickSort() {
    }

    /**
     * Sorts the specified integer array in ascending order
     * using the Quick Sort algorithm.
     *
     * @param data the integer array to be sorted
     * @throws NullPointerException if the array is null
     */
    public static void quickSort(int[] data) {
        sort(data, 0, data.length - 1);
    }

    /**
     * Recursively sorts a portion of the array using
     * the Quick Sort partitioning process.
     *
     * <p>The method selects the middle element as the pivot,
     * partitions the array into elements smaller and larger
     * than the pivot, and recursively sorts both partitions.</p>
     *
     * @param data the integer array being sorted
     * @param low the starting index of the current partition
     * @param high the ending index of the current partition
     */
    public static void sort(int[] data, int low, int high) {

        if (low >= high) {
            return;
        }

        int pivot = data[low + (high - low) / 2];

        int left = low;
        int right = high;

        while (left <= right) {

            while (data[left] < pivot) {
                left++;
            }

            while (data[right] > pivot) {
                right--;
            }

            if (left <= right) {
                swap(data, left, right);
                left++;
                right--;
            }
        }

        sort(data, low, right);
        sort(data, left, high);
    }

    /**
     * Exchanges the elements at two specified positions
     * in the array.
     *
     * @param data the integer array containing the elements
     * @param a the index of the first element
     * @param b the index of the second element
     */
    private static void swap(int[] data, int a, int b) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    /**
     * Demonstrates the Quick Sort algorithm using
     * a sample integer array.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        int[] data = {29, 9, 48, 24, 35, 20};

        System.out.println("Before: " + Arrays.toString(data));

        quickSort(data);

        System.out.println("After: " + Arrays.toString(data));
    }
}
