
import java.util.Arrays;

/**
 * A collection of basic array operations.
 * <p>
 * This class demonstrates common array algorithms, including searching,
 * inserting, deleting, reversing, rotating, finding minimum and maximum
 * values, calculating sums, and moving zeros to the end of an array.
 *
 * @author NasimBidel
 * @version 1.0
 */
public class ArrayOperations {

    /**
     * Searches for a target value in an array using linear search.
     *
     * @param arr the array to search
     * @param target the value to find
     * @return the index of the target if found, or -1 if not found
     * @throws NullPointerException if the array is null
     */
    public static int linearSearch(int[] arr, int target) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Inserts a new value at the specified index.
     * <p>
     * Creates and returns a new array containing the inserted value.
     * The original array remains unchanged.
     *
     * @param arr the original array
     * @param index the position where the value will be inserted
     * @param value the value to insert
     * @return a new array containing the inserted value
     * @throws IndexOutOfBoundsException if the index is invalid
     * @throws NullPointerException if the array is null
     */
    public static int[] insert(int[] arr, int index, int value) {

        if (index < 0 || index > arr.length) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        int[] result = new int[arr.length + 1];

        // Copy elements before the insertion position.
        for (int i = 0; i < index; i++) {
            result[i] = arr[i];
        }

        // Insert the new value.
        result[index] = value;

        // Shift the remaining elements one position to the right.
        for (int i = index; i < arr.length; i++) {
            result[i + 1] = arr[i];
        }

        return result;
    }

    /**
     * Deletes an element at the specified index.
     * <p>
     * Creates and returns a new array without the deleted element.
     * The original array remains unchanged.
     *
     * @param arr the original array
     * @param index the position of the element to delete
     * @return a new array without the selected element
     * @throws IndexOutOfBoundsException if the index is invalid
     * @throws NullPointerException if the array is null
     */
    public static int[] delete(int[] arr, int index) {

        if (index < 0 || index >= arr.length) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        int[] result = new int[arr.length - 1];
        int j = 0;

        // Copy every element except the one being deleted.
        for (int i = 0; i < arr.length; i++) {
            if (i != index) {
                result[j++] = arr[i];
            }
        }

        return result;
    }

    /**
     * Reverses the elements of an array in place.
     *
     * @param arr the array to reverse
     * @throws NullPointerException if the array is null
     */
    public static void reverse(int[] arr) {
        reverse(arr, 0, arr.length - 1);
    }

    /**
     * Reverses the elements between two specified positions.
     * <p>
     * This is a private helper method used by the reverse
     * and rotateRight methods.
     *
     * @param arr the array to modify
     * @param left the starting index
     * @param right the ending index
     */
    private static void reverse(int[] arr, int left, int right) {

        // Swap elements until the two positions meet.
        while (left < right) {

            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }

    /**
     * Rotates the elements of an array to the right by k positions.
     * <p>
     * The rotation is performed in place using three reverse operations.
     * Negative rotation values rotate the array to the left.
     *
     * @param arr the array to rotate
     * @param k the number of positions to rotate
     * @throws NullPointerException if the array is null
     */
    public static void rotateRight(int[] arr, int k) {

        if (arr.length == 0) {
            return;
        }

        // Normalize k to handle values larger than the array length
        // and negative rotation values.
        k = ((k % arr.length) + arr.length) % arr.length;

        // Reverse the entire array.
        reverse(arr, 0, arr.length - 1);

        // Reverse the first k elements.
        reverse(arr, 0, k - 1);

        // Reverse the remaining elements.
        reverse(arr, k, arr.length - 1);
    }

    /**
     * Finds the largest value in an array.
     *
     * @param arr the array to examine
     * @return the largest value in the array
     * @throws IllegalArgumentException if the array is empty
     * @throws NullPointerException if the array is null
     */
    public static int findMax(int[] arr) {

        if (arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }

        int max = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        return max;
    }

    /**
     * Finds the smallest value in an array.
     *
     * @param arr the array to examine
     * @return the smallest value in the array
     * @throws IllegalArgumentException if the array is empty
     * @throws NullPointerException if the array is null
     */
    public static int findMin(int[] arr) {

        if (arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }

        int min = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        return min;
    }

    /**
     * Finds the second largest distinct value in an array.
     *
     * @param arr the array to examine
     * @return the second largest distinct value
     * @throws IllegalArgumentException if the array has fewer than
     *         two distinct values
     * @throws NullPointerException if the array is null
     */
    public static int secondLargest(int[] arr) {

        int first = 0;
        int second = 0;

        boolean hasFirst = false;
        boolean hasSecond = false;

        for (int i = 0; i < arr.length; i++) {

            if (!hasFirst || arr[i] > first) {

                second = first;
                hasSecond = hasFirst;

                first = arr[i];
                hasFirst = true;

            } else if (arr[i] < first &&
                       (!hasSecond || arr[i] > second)) {

                second = arr[i];
                hasSecond = true;
            }
        }

        if (!hasSecond) {
            throw new IllegalArgumentException(
                "No second largest value"
            );
        }

        return second;
    }

    /**
     * Calculates the sum of all elements in an array.
     *
     * @param arr the array containing the numbers
     * @return the sum of all array elements
     * @throws NullPointerException if the array is null
     */
    public static int sum(int[] arr) {

        int total = 0;

        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
        }

        return total;
    }

    /**
     * Moves all zeros to the end of an array.
     * <p>
     * Non-zero elements maintain their original relative order.
     * The operation modifies the original array without creating
     * another array.
     *
     * @param arr the array to modify
     * @throws NullPointerException if the array is null
     */
    public static void moveZerosToEnd(int[] arr) {

        int pos = 0;

        // Move all non-zero elements to the beginning.
        for (int i = 0; i < arr.length; i++) {

            if (arr[i] != 0) {
                arr[pos] = arr[i];
                pos++;
            }
        }

        // Fill the remaining positions with zeros.
        while (pos < arr.length) {
            arr[pos] = 0;
            pos++;
        }
    }

    /**
     * Demonstrates the array operations using sample arrays.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        int[] numbers = {12, 45, 7, 23, 56, 89, 34};

        System.out.println("Original:        "
                + Arrays.toString(numbers));

        System.out.println("Search 23:       index "
                + linearSearch(numbers, 23));

        System.out.println("Search 100:      index "
                + linearSearch(numbers, 100));

        numbers = insert(numbers, 2, 99);

        System.out.println("Insert 99 at 2:  "
                + Arrays.toString(numbers));

        numbers = delete(numbers, 0);

        System.out.println("Delete index 0:  "
                + Arrays.toString(numbers));

        System.out.println("Max: " + findMax(numbers)
                + ", Min: " + findMin(numbers)
                + ", Sum: " + sum(numbers));

        System.out.println("Second largest:  "
                + secondLargest(numbers));

        reverse(numbers);

        System.out.println("Reversed:        "
                + Arrays.toString(numbers));

        rotateRight(numbers, 2);

        System.out.println("Rotate right 2:  "
                + Arrays.toString(numbers));

        int[] withZeros = {0, 1, 0, 3, 12, 0, 5};

        moveZerosToEnd(withZeros);

        System.out.println("Zeros to end:    "
                + Arrays.toString(withZeros));
    }
}
