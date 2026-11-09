
/**
 * Demonstrates the functionality of the Book and Library classes.
 * <p>
 * This application creates a library, adds books, searches by title,
 * borrows and returns books, removes a book, sorts books by publication
 * year, and retrieves books by index.
 *
 * @author Nasim Bidel
 * @version 1.0
 */
public class LibraryApp {

    /**
     * Main entry point of the Library application.
     * <p>
     * Demonstrates the main operations supported by the Library class:
     * <ul>
     *     <li>Adding books to the library</li>
     *     <li>Searching for books by title</li>
     *     <li>Borrowing and returning books</li>
     *     <li>Removing books using their ISBN</li>
     *     <li>Sorting books by publication year</li>
     *     <li>Accessing books by index</li>
     *     <li>Handling invalid array indexes</li>
     * </ul>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Library library = new Library();

        System.out.println("=== Adding books ===");
        library.addBook(new Book("001", "Clean Code", "Robert Martin", 2008));
        library.addBook(new Book("002", "Effective Java", "Joshua Bloch", 2001));
        library.addBook(new Book("003", "Head First Java", "Kathy Sierra", 2003));
        library.addBook(new Book("004", "The Pragmatic Programmer", "Hunt & Thomas", 1999));
        library.addBook(new Book("005", "Java Concurrency", "Brian Goetz", 2006));
        library.printAll();

        System.out.println("\n=== Search 'java' ===");
        for (Book b : library.searchByTitle("java")) {
            System.out.println("  " + b);
        }

        System.out.println("\n=== Borrow / return ===");
        System.out.println("Borrow 002: " + library.borrowBook("002"));
        System.out.println("Borrow 002 again: " + library.borrowBook("002"));
        System.out.println("Return 002: " + library.returnBook("002"));
        library.borrowBook("003");

        System.out.println("\n=== Remove 001 ===");
        System.out.println("Removed: " + library.removeBook("001"));
        library.printAll();

        System.out.println("\n=== Sorted by year ===");
        library.sortByYear();
        library.printAll();

        System.out.println("\nBook at index 0: " + library.get(0).getTitle());
        try {
            library.get(10);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Expected error: " + e.getMessage());
        }
    }
}
