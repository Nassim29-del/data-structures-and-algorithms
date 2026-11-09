
/**
 * Represents a book in a library system.
 * <p>
 * A book contains information such as its ISBN, title, author,
 * publication year, and borrowing status.
 *
 * @author Nasim Bidel
 * @version 1.0
 */
public class Book {

    private String isbn;
    private String title;
    private String author;
    private boolean borrowed;
    private int year;

    /**
     * Constructs a new Book with the specified information.
     * A newly created book is not borrowed by default.
     *
     * @param isbn   the ISBN number of the book
     * @param author the author of the book
     * @param year   the publication year of the book
     * @param title  the title of the book
     */
    public Book(String isbn, String title, String author, int year) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.borrowed = false;
    }
    /**
     * Returns the ISBN number of the book.
     *
     * @return the book's ISBN
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Returns the title of the book.
     *
     * @return the book's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the author of the book.
     *
     * @return the book's author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Checks whether the book is currently borrowed.
     *
     * @return true if the book is borrowed, false otherwise
     */
    public boolean isBorrowed() {
        return borrowed;
    }

    /**
     * Returns the publication year of the book.
     *
     * @return the book's publication year
     */
    public int getyear() {
        return year;
    }

    /**
     * Returns a formatted string containing the book's information.
     * The output includes the ISBN, title, author, publication year,
     * and borrowing status if the book is borrowed.
     *
     * @return a formatted string representing the book
     */
    @Override
    public String toString() {
        return String.format("[%s] %-28s %-20s %d %s",
                isbn, title, author, year, borrowed ? "(borrowed)" : "");
    }
}
