
/**
 * Represents a library that stores books using a regular array.
 * <p>
 * The array grows automatically when it becomes full by creating
 * a new array with double the previous capacity and copying the
 * existing books into it.
 * <p>
 * This implementation demonstrates how a dynamic array, such as
 * Java's ArrayList, works internally.
 *
 * <h2>Time Complexity</h2>
 * <pre>
 * Operation          Time Complexity
 * -----------------------------------
 * Add book           O(1) amortized, O(n) when resizing
 * Remove by ISBN     O(n)
 * Search by title    O(n)
 * Get by index       O(1)
 * Borrow/Return      O(n)
 * Sort by year       O(n^2)
 * </pre>
 *
 * @author Nasim Bidel
 * @version 1.0
 */
public class Library {

    private Book[] books;
    private int size;

    /**
     * Constructs a Library with an initial capacity of 2.
     * <p>
     * A small capacity is used intentionally to demonstrate
     * how the array grows when it becomes full.
     */
    public Library() {
        this(2);
    }

    /**
     * Constructs a Library with the specified initial capacity.
     *
     * @param initialCapacity the initial number of books the array can hold
     * @throws NegativeArraySizeException if the capacity is negative
     */
    public Library(int initialCapacity) {
        books = new Book[initialCapacity];
        size = 0;
    }

    /**
     * Adds a book to the library.
     * <p>
     * If the array is full, its capacity is doubled before
     * the new book is added.
     *
     * @param book the book to add
     */
    public void addBook(Book book) {
        if (size == books.length) {
            resize(books.length * 2);
        }
        books[size++] = book;
    }

    /**
     * Resizes the internal array to the specified capacity.
     * <p>
     * All existing books are copied into the new array.
     *
     * @param newCapacity the new capacity of the array
     */
    private void resize(int newCapacity) {
        System.out.println("  -> resizing array from " + books.length + " to " + newCapacity);
        Book[] bigger = new Book[newCapacity];

        for (int i = 0; i < size; i++) {
            bigger[i] = books[i];
        }

        books = bigger;
    }

    /**
     * Removes a book from the library using its ISBN.
     * <p>
     * After finding the book, all following elements are shifted
     * one position to the left to close the gap.
     *
     * @param isbn the ISBN of the book to remove
     * @return true if the book was found and removed,
     *         false if no book with that ISBN exists
     */
    public boolean removeBook(String isbn) {
        int index = indexOf(isbn);

        if (index == -1) {
            return false;
        }

        for (int i = index; i < size - 1; i++) {
            books[i] = books[i + 1];
        }

        books[--size] = null;
        return true;
    }

    /**
     * Searches for a book using its ISBN.
     *
     * @param isbn the ISBN to search for
     * @return the index of the matching book, or -1 if not found
     */
    private int indexOf(String isbn) {
        for (int i = 0; i < size; i++) {
            if (books[i].getIsbn().equals(isbn)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Returns the book at the specified index.
     *
     * @param index the position of the book in the array
     * @return the book at the specified index
     * @throws IndexOutOfBoundsException if the index is negative
     *         or greater than or equal to the current size
     */
    public Book get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        return books[index];
    }

    /**
     * Searches for books whose titles contain the specified keyword.
     * <p>
     * The search is case-insensitive. A new array containing only
     * the matching books is returned.
     *
     * @param keyword the keyword to search for in book titles
     * @return an array containing all matching books;
     *         an empty array if no matches are found
     */
    public Book[] searchByTitle(String keyword) {
        String key = keyword.toLowerCase();
        int matches = 0;

        // First pass: count matching books
        for (int i = 0; i < size; i++) {
            if (books[i].getTitle().toLowerCase().contains(key)) {
                matches++;
            }
        }

        // Second pass: fill the result array
        Book[] result = new Book[matches];

        for (int i = 0, j = 0; i < size; i++) {
            if (books[i].getTitle().toLowerCase().contains(key)) {
                result[j++] = books[i];
            }
        }

        return result;
    }

    /**
     * Borrows a book using its ISBN.
     * <p>
     * A book can only be borrowed if it exists in the library
     * and is not already borrowed.
     *
     * @param isbn the ISBN of the book to borrow
     * @return true if the book was successfully borrowed,
     *         false if the book does not exist or is already borrowed
     */
    public boolean borrowBook(String isbn) {
        int i = indexOf(isbn);

        if (i == -1 || books[i].isBorrowed()) {
            return false;
        }

        books[i].setBorrowed(true);
        return true;
    }

    /**
     * Returns a borrowed book using its ISBN.
     * <p>
     * A book can only be returned if it exists in the library
     * and is currently borrowed.
     *
     * @param isbn the ISBN of the book to return
     * @return true if the book was successfully returned,
     *         false if the book does not exist or is not borrowed
     */
    public boolean returnBook(String isbn) {
        int i = indexOf(isbn);

        if (i == -1 || !books[i].isBorrowed()) {
            return false;
        }

        books[i].setBorrowed(false);
        return true;
    }

    /**
     * Sorts all books by publication year in ascending order.
     * <p>
     * The oldest books appear first. This method uses
     * the selection sort algorithm.
     */
    public void sortByYear() {
        for (int i = 0; i < size - 1; i++) {
            int minIdx = i;

            for (int j = i + 1; j < size; j++) {
                if (books[j].getYear() < books[minIdx].getYear()) {
                    minIdx = j;
                }
            }

            Book temp = books[i];
            books[i] = books[minIdx];
            books[minIdx] = temp;
        }
    }

    /**
     * Returns the current number of books in the library.
     *
     * @return the number of books currently stored
     */
    public int size() {
        return size;
    }

    /**
     * Returns the current capacity of the internal array.
     *
     * @return the maximum number of books the array can hold
     *         before resizing is required
     */
    public int capacity() {
        return books.length;
    }

    /**
     * Prints all books currently stored in the library.
     * <p>
     * Also displays the current size and capacity of the array.
     */
    public void printAll() {
        for (int i = 0; i < size; i++) {
            System.out.println("  " + books[i]);
        }

        System.out.println("  size = " + size + ", capacity = " + books.length);
    }
}
