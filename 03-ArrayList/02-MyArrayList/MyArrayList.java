
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A custom generic ArrayList implementation built using a dynamic array.
 *
 * This class demonstrates how Java's ArrayList works internally,
 * including resizing, insertion, deletion, searching, and iteration.
 *
 * @param <T> the type of elements stored in this list
 *
 * @author Nasim Bidel
 * @version 1.0
 */
public class MyArrayList<T> implements Iterable<T> {

    // Default capacity when no initial capacity is provided
    private static final int DEFAULT_CAPACITY = 4;

    // Internal array used to store elements
    private Object[] data;

    // Number of elements currently stored in the list
    private int size;

    /**
     * Creates an empty MyArrayList with the default capacity.
     */
    public MyArrayList() {
        data = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Creates an empty MyArrayList with a specified capacity.
     *
     * @param initialCapacity the initial capacity of the list
     * @throws IllegalArgumentException if capacity is not positive
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException(
                    "Capacity must be positive: " + initialCapacity
            );
        }

        data = new Object[initialCapacity];
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param value the element to add
     */
    public void add(T value) {
        ensureCapacity();
        data[size++] = value;
    }

    /**
     * Inserts an element at the specified index.
     * Existing elements are shifted one position to the right.
     *
     * @param index the insertion position
     * @param value the element to insert
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public void add(int index, T value) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size
            );
        }

        ensureCapacity();

        // Shift elements to the right to make space
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = value;
        size++;
    }

    /**
     * Ensures that the internal array has enough capacity.
     * If the array is full, its capacity is doubled.
     */
    private void ensureCapacity() {

        if (size == data.length) {

            // Double the current capacity
            Object[] bigger = new Object[data.length * 2];

            // Copy existing elements into the new array
            System.arraycopy(data, 0, bigger, 0, size);

            data = bigger;
        }
    }

    /**
     * Returns the element at the specified index.
     *
     * @param index the position of the element
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {

        checkIndex(index);

        return (T) data[index];
    }

    /**
     * Replaces the element at the specified index.
     *
     * @param index the position to update
     * @param value the new element
     * @return the previous element
     */
    public T set(int index, T value) {

        T old = get(index);

        data[index] = value;

        return old;
    }

    /**
     * Removes the element at the specified index.
     * Remaining elements are shifted one position to the left.
     *
     * @param index the position of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if index is invalid
     */
    @SuppressWarnings("unchecked")
    public T remove(int index) {

        checkIndex(index);

        // Save the element before removing it
        T removed = (T) data[index];

        // Shift elements to the left
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        // Decrease size and remove the unused reference
        data[--size] = null;

        return removed;
    }

    /**
     * Removes the first occurrence of the specified value.
     *
     * @param value the element to remove
     * @return true if the element was found and removed
     */
    public boolean removeValue(T value) {

        int index = indexOf(value);

        if (index == -1) {
            return false;
        }

        remove(index);

        return true;
    }

    /**
     * Searches for the first occurrence of an element.
     *
     * @param value the element to search for
     * @return the index if found, otherwise -1
     */
    public int indexOf(T value) {

        for (int i = 0; i < size; i++) {

            if (value == null
                    ? data[i] == null
                    : value.equals(data[i])) {

                return i;
            }
        }

        return -1;
    }

    /**
     * Checks whether the list contains a specified element.
     *
     * @param value the element to search for
     * @return true if the element exists
     */
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the current size
     */
    public int size() {
        return size;
    }

    /**
     * Checks whether the list is empty.
     *
     * @return true if the list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the current capacity of the internal array.
     *
     * @return the array capacity
     */
    public int capacity() {
        return data.length;
    }

    /**
     * Removes all elements from the list.
     * The internal array keeps its current capacity.
     */
    public void clear() {

        // Remove references to existing elements
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }

        size = 0;
    }

    /**
     * Reduces the internal array capacity to match the list size.
     * Keeps a minimum capacity of one.
     */
    public void trimToSize() {

        if (size < data.length) {

            Object[] smaller = new Object[Math.max(size, 1)];

            System.arraycopy(data, 0, smaller, 0, size);

            data = smaller;
        }
    }

    /**
     * Checks whether an index is within the valid element range.
     *
     * @param index the index to validate
     * @throws IndexOutOfBoundsException if index is invalid
     */
    private void checkIndex(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size
            );
        }
    }

    /**
     * Returns an iterator for traversing the list.
     *
     * @return an Iterator over the elements
     */
    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            private int cursor = 0;

            /**
             * Checks whether another element is available.
             *
             * @return true if another element exists
             */
            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            /**
             * Returns the next element in the list.
             *
             * @return the next element
             * @throws NoSuchElementException if no elements remain
             */
            @Override
            @SuppressWarnings("unchecked")
            public T next() {

                if (!hasNext()) {
                    throw new NoSuchElementException(
                            "No more elements in the list"
                    );
                }

                return (T) data[cursor++];
            }
        };
    }

    /**
     * Returns a string representation of the list.
     *
     * @return the list elements enclosed in square brackets
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < size; i++) {

            sb.append(data[i]);

            if (i < size - 1) {
                sb.append(", ");
            }
        }

        return sb.append("]").toString();
    }
}
