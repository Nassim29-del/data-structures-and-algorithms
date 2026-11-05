```java
import java.util.Arrays;

/**
 * Provides an implementation of the Bubble Sort algorithm
 * for sorting integer arrays in ascending order.
 *
 * @author
 */
public class BubbleSort {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private BubbleSort() {
    }

    /**
     * Sorts the given integer array in ascending order using
     * the Bubble Sort algorithm.
     *
     * Repeatedly compares adjacent elements and swaps them if
     * they are in the wrong order. The algorithm stops early
     * if no swaps are made during a pass.
     *
     * @param data the integer array to be sorted
     */
    public static void bubbleSort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            boolean swapped = false;

            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j] > data[j + 1]) {
                    swap(data, j, j + 1);
                    swapped = true;
                }
            }

            if (!swapped) {
                return;
            }
        }
    }

    /**
     * Swaps two elements in the given integer array.
     *
     * @param data the integer array containing the elements
     * @param a the index of the first element
     * @param b the index of the second element
     */
    public static void swap(int[] data, int a, int b) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    /**
     * Demonstrates the Bubble Sort algorithm by sorting
     * an example integer array and printing the results
     * before and after sorting.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        int[] data = {29, 9, 48, 24, 35, 20};

        System.out.println("Before: " + Arrays.toString(data));

        bubbleSort(data);

        System.out.println("After: " + Arrays.toString(data));
    }
}
```