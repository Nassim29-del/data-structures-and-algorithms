/**
 * Command-line demo that exercises {@link MyLinkedList} and checks the
 * results.
 *
 * <p>The first part prints the list as it is built, emptied from both ends,
 * reversed and iterated with a for-each loop. The second part runs a set of
 * self-checks and prints how many passed and failed; each failure is also
 * printed by name.</p>
 *
 * <p>Run it with:</p>
 * <pre>
 *   javac MyLinkedList.java MyLinkedListDemo.java
 *   java MyLinkedListDemo
 * </pre>
 *
 * @see MyLinkedList
 */
public class MyLinkedListDemo {

    /** Number of self-checks that have passed so far. */
    private static int passed = 0;

    /** Number of self-checks that have failed so far. */
    private static int failed = 0;

    /**
     * Creates the demo. Not normally needed; run {@link #main(String[])} instead.
     */
    public MyLinkedListDemo() {
    }

    /**
     * Records the result of one self-check, and prints its name if it failed.
     *
     * @param name      a short description of what is being checked
     * @param condition {@code true} if the check passed
     */
    private static void check(String name, boolean condition) {
        if (condition) {
            passed++;
        } else {
            failed++;
            System.out.println("   FAILED: " + name);
        }
    }

    /**
     * Runs the demo and the self-checks, printing each step to standard output.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        MyLinkedList<String> days = new MyLinkedList<>();

        System.out.println("=== Building ===");
        days.addLast("Tue");
        days.addLast("Thu");
        days.addFirst("Mon");
        days.add(2, "Wed");
        days.addLast("Fri");
        System.out.println("Forward:  " + days);
        System.out.println("Backward: " + days.toStringBackward());
        System.out.println("first = " + days.getFirst() + ", last = " + days.getLast()
                + ", get(3) = " + days.get(3));

        System.out.println("\n=== Removing from both ends in O(1) ===");
        System.out.println("removeFirst -> " + days.removeFirst());
        System.out.println("removeLast  -> " + days.removeLast());
        System.out.println("Now: " + days);

        days.removeValue("Wed");
        System.out.println("removeValue(Wed): " + days);

        System.out.println("\n=== Reverse ===");
        MyLinkedList<Integer> nums = new MyLinkedList<>();
        for (int i = 1; i <= 6; i++) nums.addLast(i);
        System.out.println("Before:   " + nums);
        nums.reverse();
        System.out.println("After:    " + nums);
        System.out.println("Backward: " + nums.toStringBackward() + "  (prev links still correct)");

        System.out.print("\nfor-each: ");
        int sum = 0;
        for (int n : nums) {
            System.out.print(n + " ");
            sum += n;
        }
        System.out.println(" sum = " + sum);

        System.out.println("\n=== Self-checks ===");
        MyLinkedList<Integer> t = new MyLinkedList<>();
        check("starts empty", t.isEmpty());
        t.addLast(2); t.addFirst(1); t.addLast(4); t.add(2, 3);
        check("order after adds", t.get(0) == 1 && t.get(1) == 2 && t.get(2) == 3 && t.get(3) == 4);
        check("get from the back half", t.get(3) == 4);
        check("remove middle", t.remove(1) == 2 && t.size() == 3);
        check("removeLast", t.removeLast() == 4 && t.getLast() == 3);
        check("removeFirst", t.removeFirst() == 1 && t.getFirst() == 3);
        check("remove only element", t.removeFirst() == 3 && t.isEmpty());
        t.addFirst(9);
        check("works after emptying", t.getFirst() == 9 && t.getLast() == 9);
        check("removeValue missing", !t.removeValue(100));
        boolean threw = false;
        try { new MyLinkedList<String>().removeLast(); } catch (java.util.NoSuchElementException e) { threw = true; }
        check("empty removeLast throws", threw);
        System.out.println(passed + " passed, " + failed + " failed");
    }
}