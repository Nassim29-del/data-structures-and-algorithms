
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Basics
 *
 * Demonstrates the Java Collections Framework using LinkedList
 * and compares its performance with ArrayList.
 *
 * Topics covered:
 * 1. Basic LinkedList operations
 * 2. Queue implementation (FIFO)
 * 3. Stack implementation (LIFO)
 * 4. Deque operations
 * 5. ListIterator (forward and backward traversal)
 * 6. Descending Iterator
 * 7. Safe removal using removeIf()
 * 8. ArrayList vs LinkedList performance comparison
 *
 * @author Nasim Bidel
 * @version 1.0
 */
public class Basics {

    public static void main(String[] args) {

        // ---------------------------------------------------------
        // 1. BASIC LINKEDLIST OPERATIONS
        // ---------------------------------------------------------

        // Create a LinkedList and add elements at different positions.
        LinkedList<String> list = new LinkedList<>();

        list.add("B");
        list.add("C");
        list.addFirst("A");  // Add element at the beginning.
        list.addLast("Z");   // Add element at the end.
        list.add(2, "R");    // Insert element at a specific index.

        System.out.println("1. List: " + list);

        // Retrieve the first and last elements.
        System.out.println("First / Last: "
                + list.getFirst() + " / " + list.getLast());

        // Remove elements by value and from both ends.
        list.remove("Z");
        list.removeFirst();
        list.removeLast();

        System.out.println("After Removes: " + list);


        // ---------------------------------------------------------
        // 2. LINKEDLIST AS A QUEUE (FIFO)
        // ---------------------------------------------------------

        // FIFO = First In, First Out.
        // The first element added is the first one removed.

        LinkedList<String> queue = new LinkedList<>();

        queue.offer("Customer 1");
        queue.offer("Customer 2");
        queue.offer("Customer 3");

        System.out.println("\n2. Queue: " + queue);

        // peek() retrieves the first element without removing it.
        System.out.println("Peek: " + queue.peek());

        // poll() retrieves and removes the first element.
        System.out.println("Poll: " + queue.poll());
        System.out.println("Poll: " + queue.poll());

        System.out.println("Left in queue: " + queue);


        // ---------------------------------------------------------
        // 3. LINKEDLIST AS A STACK (LIFO)
        // ---------------------------------------------------------

        // LIFO = Last In, First Out.
        // The last element added is the first one removed.

        LinkedList<String> stack = new LinkedList<>();

        // push() adds elements to the beginning of the list.
        stack.push("Page1.html");
        stack.push("Page2.html");
        stack.push("Page3.html");

        System.out.println("\n3. Stack: " + stack + " (top is first)");

        // pop() removes and returns the top element.
        System.out.println("Pop: " + stack.pop());

        // peek() views the top element without removing it.
        System.out.println("Peek: " + stack.peek());


        // ---------------------------------------------------------
        // 4. DEQUE (DOUBLE-ENDED QUEUE)
        // ---------------------------------------------------------

        // A Deque allows insertion and removal from both ends.

        LinkedList<Integer> deque = new LinkedList<>();

        deque.offerFirst(2);
        deque.offerFirst(1);
        deque.offerFirst(3);
        deque.offerFirst(4);

        System.out.println("\n4. Deque: " + deque);

        // Remove the first and last elements.
        System.out.println("Poll First / Last: "
                + deque.pollFirst() + " / " + deque.pollLast());

        System.out.println("Remaining: " + deque);


        // ---------------------------------------------------------
        // 5. LISTITERATOR: FORWARD AND BACKWARD TRAVERSAL
        // ---------------------------------------------------------

        // ListIterator supports:
        // - Forward traversal using next()
        // - Backward traversal using previous()
        // - Adding elements using add()
        // - Updating elements using set()

        LinkedList<String> words = new LinkedList<>(
                List.of("one", "two", "three", "four"));

        ListIterator<String> it = words.listIterator();

        // Traverse the list from beginning to end.
        while (it.hasNext()) {

            String w = it.next();

            // Insert a new element after "two".
            if (w.equals("two")) {
                it.add("two-and-a-half");
            }

            // Replace "four" with "FOUR".
            if (w.equals("four")) {
                it.set("FOUR");
            }
        }

        System.out.println("\n5. After edits: " + words);

        // Traverse the same list backward.
        System.out.println("Backwards:");

        while (it.hasPrevious()) {
            System.out.println(it.previous() + " ");
        }


        // ---------------------------------------------------------
        // 6. DESCENDING ITERATOR
        // ---------------------------------------------------------

        // descendingIterator() traverses a LinkedList
        // from the last element to the first element.

        Iterator<String> desc = words.descendingIterator();

        System.out.print("Descending Iterator: ");

        while (desc.hasNext()) {
            System.out.print(desc.next() + " ");
        }

        System.out.println();


        // ---------------------------------------------------------
        // 7. REMOVE ELEMENTS SAFELY USING removeIf()
        // ---------------------------------------------------------

        // Create a list containing numbers from 1 to 8.
        LinkedList<Integer> nums = new LinkedList<>(
                List.of(1, 2, 3, 4, 5, 6, 7, 8));

        // Remove all even numbers.
        // The condition n % 2 == 0 identifies even numbers.
        nums.removeIf(n -> n % 2 == 0);

        System.out.println("\n7. Odd numbers: " + nums);


        // ---------------------------------------------------------
        // 8. ARRAYLIST VS LINKEDLIST: PERFORMANCE COMPARISON
        // ---------------------------------------------------------

        // Compare insertion at the beginning and indexed access.
        // Note: Actual execution times depend on the computer
        // and Java runtime environment.

        System.out.println("\n8. Timing (n = 50,000):");

        compare(50_000);

        System.out.println("-> LinkedList is generally faster for "
                + "insertion at the front.");

        System.out.println("-> ArrayList is generally faster for "
                + "random indexed access.");

        System.out.println("(Exact times vary by machine.)");
    }


    /**
     * Compares the performance of ArrayList and LinkedList.
     *
     * Tests two operations:
     * 1. Inserting elements at the beginning of each list.
     * 2. Accessing elements using get(index).
     *
     * @param n number of elements to insert and test
     */
    private static void compare(int n) {

        // Create the two list implementations.
        List<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();


        // ---------------------------------------------------------
        // TEST 1: INSERTION AT THE BEGINNING
        // ---------------------------------------------------------

        long t0 = System.nanoTime();

        // ArrayList shifts existing elements when inserting at index 0.
        for (int i = 0; i < n; i++) {
            arrayList.add(0, i);
        }

        long t1 = System.nanoTime();

        // LinkedList inserts elements at the beginning efficiently.
        for (int i = 0; i < n; i++) {
            linkedList.addFirst(i);
        }

        long t2 = System.nanoTime();


        // ---------------------------------------------------------
        // TEST 2: RANDOM INDEXED ACCESS
        // ---------------------------------------------------------

        long sum = 0;

        // Measure ArrayList indexed access.
        long t3 = System.nanoTime();

        for (int i = 0; i < n; i += 10) {
            sum += arrayList.get(i);
        }

        long t4 = System.nanoTime();

        // Measure LinkedList indexed access.
        long t5 = System.nanoTime();

        for (int i = 0; i < n; i += 10) {
            sum += linkedList.get(i);
        }

        long t6 = System.nanoTime();


        // ---------------------------------------------------------
        // DISPLAY PERFORMANCE RESULTS
        // ---------------------------------------------------------

        // Convert nanoseconds into milliseconds.
        System.out.printf(
                "Insert at front: ArrayList %6.1f ms | "
                        + "LinkedList %6.1f ms%n",
                (t1 - t0) / 1e6,
                (t2 - t1) / 1e6
        );

        System.out.printf(
                "get(i) x %d: ArrayList %6.1f ms | "
                        + "LinkedList %6.1f ms%n",
                n / 10,
                (t4 - t3) / 1e6,
                (t6 - t5) / 1e6
        );

        // Prevent the calculated sum from being entirely unused.
        if (sum == -1) {
            System.out.println();
        }
    }
}
