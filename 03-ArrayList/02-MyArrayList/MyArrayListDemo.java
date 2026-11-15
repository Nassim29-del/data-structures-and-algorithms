
/**
 * MyArrayListDemo demonstrates and tests the functionality
 * of the custom MyArrayList implementation.
 *
 * The program covers:
 * 1. Dynamic array resizing
 * 2. Adding, accessing, updating, and removing elements
 * 3. Searching for elements
 * 4. Iterating through the list
 * 5. Trimming array capacity
 * 6. Generic type support
 * 7. Exception handling
 * 8. Automated self-checks
 *
 * @author Nasim Bidel
 * @version 1.0
 */
public class MyArrayListDemo {

    // Counters used to track successful and failed tests
    private static int passed = 0;
    private static int failed = 0;

    /**
     * Checks whether a test condition is true.
     *
     * @param name the name of the test
     * @param condition the condition being evaluated
     */
    private static void check(String name, boolean condition) {

        if (condition) {
            passed++;
        } else {
            failed++;
            System.out.println("FAILED: " + name);
        }
    }

    /**
     * Main method demonstrating all MyArrayList operations.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        // ==========================================
        // 1. Testing dynamic array growth
        // ==========================================

        MyArrayList<String> list = new MyArrayList<>();

        System.out.println("=== Watching the array grow ===");

        String[] cities = {
                "Toronto", "Montreal", "Vancouver",
                "Calgary", "Ottawa", "Halifax"
        };

        // Add cities and observe size and capacity changes
        for (String city : cities) {

            list.add(city);

            System.out.printf(
                    "add %-10s size=%d capacity=%d%n",
                    city, list.size(), list.capacity()
            );
        }

        // ==========================================
        // 2. Testing basic operations
        // ==========================================

        System.out.println("\n=== Basic operations ===");

        // Display the complete list
        System.out.println("List:             " + list);

        // Access an element by index
        System.out.println("get(2):           " + list.get(2));

        // Insert an element at index 1
        list.add(1, "Edmonton");

        System.out.println("add(1, Edmonton): " + list);

        // Update an element and retrieve its old value
        String old = list.set(0, "Winnipeg");

        System.out.println("set(0) replaced " + old + ": " + list);

        // Remove an element by index
        list.remove(3);

        System.out.println("remove(3):        " + list);

        // Remove an element by value
        list.removeValue("Halifax");

        System.out.println("remove Halifax:   " + list);

        // Search for elements
        System.out.println("indexOf Ottawa:   " + list.indexOf("Ottawa"));
        System.out.println("contains Paris:   " + list.contains("Paris"));

        // ==========================================
        // 3. Testing iteration
        // ==========================================

        System.out.print("for-each loop:    ");

        // Uses the Iterator implementation internally
        for (String city : list) {
            System.out.print(city + " ");
        }

        System.out.println();

        // ==========================================
        // 4. Testing trimToSize()
        // ==========================================

        list.trimToSize();

        System.out.println(
                "After trimToSize: size=" + list.size()
                        + " capacity=" + list.capacity()
        );

        // ==========================================
        // 5. Testing generic type support
        // ==========================================

        System.out.println("\n=== Works with any type (generics) ===");

        MyArrayList<Integer> numbers = new MyArrayList<>(2);

        // Store square numbers in the custom list
        for (int i = 1; i <= 10; i++) {
            numbers.add(i * i);
        }

        // Calculate the sum using enhanced for loop
        int sum = 0;

        for (int number : numbers) {
            sum += number;
        }

        System.out.println("Squares: " + numbers + "  sum = " + sum);

        // ==========================================
        // 6. Testing exception handling
        // ==========================================

        System.out.println("\n=== Error handling ===");

        try {

            // Index 99 does not exist
            list.get(99);

        } catch (IndexOutOfBoundsException e) {

            System.out.println(
                    "Expected error: " + e.getMessage()
            );
        }

        // ==========================================
        // 7. Automated self-checks
        // ==========================================

        System.out.println("\n=== Self-checks ===");

        MyArrayList<Integer> t = new MyArrayList<>(1);

        // Test whether a new list starts empty
        check("starts empty", t.isEmpty());

        // Test adding elements and updating size
        t.add(10);
        t.add(20);
        t.add(30);

        check("size after 3 adds", t.size() == 3);

        // Test automatic capacity expansion
        check("grew past capacity 1", t.capacity() >= 3);

        // Test insertion at the beginning
        t.add(0, 5);

        check(
                "insert at front",
                t.get(0) == 5 && t.get(1) == 10
        );

        // Test insertion at the end
        t.add(t.size(), 40);

        check("insert at end", t.get(4) == 40);

        // Test removal and returned value
        check("remove returns value", t.remove(2) == 20);

        // Test element ordering after removal
        check(
                "order after remove",
                t.toString().equals("[5, 10, 30, 40]")
        );

        // Test updating and returning the old value
        check("set returns old value", t.set(1, 99) == 10);

        // Test searching for a missing element
        check("indexOf missing", t.indexOf(1234) == -1);

        // Test clearing the list
        t.clear();

        check(
                "clear empties",
                t.isEmpty() && t.size() == 0
        );

        // Test invalid insertion index
        boolean threw = false;

        try {
            t.add(5, 1);

        } catch (IndexOutOfBoundsException e) {
            threw = true;
        }

        check("bad insert index throws", threw);

        // ==========================================
        // 8. Final test results
        // ==========================================

        System.out.println("\n=== Test Results ===");

        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);

        System.out.println(
                "Total: " + (passed + failed)
        );

        if (failed == 0) {
            System.out.println("All tests passed!");
        } else {
            System.out.println("Some tests need attention.");
        }
    }
}
