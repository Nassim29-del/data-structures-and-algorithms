/**
 * Command-line demo that runs every operation and algorithm of
 * {@link SinglyLinkedList} and prints the results.
 *
 * <p>Sections covered, in order: building the list, reading, removing,
 * iterative and recursive reversal, two-pointer tricks (middle and n-th from
 * end), Floyd's cycle detection, removing duplicates from a sorted list,
 * palindrome checking, merging two sorted lists, and error handling.</p>
 *
 * <p>Run it with:</p>
 * <pre>
 *   javac Node.java SinglyLinkedList.java SinglyLinkedListDemo.java
 *   java SinglyLinkedListDemo
 * </pre>
 *
 * @see SinglyLinkedList
 */
public class SinglyLinkedListDemo {

    /**
     * Creates the demo. Not normally needed; run {@link #main(String[])} instead.
     */
    public SinglyLinkedListDemo() {
    }

    /**
     * Runs the demo and prints each step to standard output.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        System.out.println("=== Building the list ===");
        list.addLast(20);
        list.addLast(30);
        list.addFirst(10);
        list.addLast(50);
        list.insertAt(3, 40);
        System.out.println(list + "   size = " + list.size());

        System.out.println("\n=== Reading ===");
        System.out.println("get(2):       " + list.get(2));
        System.out.println("indexOf(40):  " + list.indexOf(40));
        System.out.println("contains(99): " + list.contains(99));

        System.out.println("\n=== Removing ===");
        System.out.println("removeFirst() -> " + list.removeFirst() + ":  " + list);
        System.out.println("removeAt(1)   -> " + list.removeAt(1) + ":  " + list);
        list.removeValue(50);
        System.out.println("removeValue(50):      " + list);

        System.out.println("\n=== Reverse ===");
        SinglyLinkedList nums = SinglyLinkedList.of(1, 2, 3, 4, 5, 6);
        System.out.println("Original:            " + nums);
        nums.reverse();
        System.out.println("Iterative reverse:   " + nums);
        nums.reverseRecursive();
        System.out.println("Recursive reverse:   " + nums);
        nums.addLast(7);
        System.out.println("addLast(7) still OK: " + nums + "  (tail was updated)");

        System.out.println("\n=== Two-pointer tricks ===");
        System.out.println("Middle of " + nums + " = " + nums.findMiddle());
        System.out.println("2nd from end = " + nums.nthFromEnd(2));

        System.out.println("\n=== Cycle detection (Floyd) ===");
        SinglyLinkedList loop = SinglyLinkedList.of(1, 2, 3, 4, 5);
        System.out.println("Before: hasCycle = " + loop.hasCycle());
        loop.createCycleTo(2);   // 5 now points back to 3
        System.out.println("After linking 5 -> 3: hasCycle = " + loop.hasCycle());

        System.out.println("\n=== Remove duplicates (sorted list) ===");
        SinglyLinkedList dups = SinglyLinkedList.of(1, 1, 2, 3, 3, 3, 4, 5, 5);
        System.out.println("Before: " + dups);
        dups.removeDuplicatesSorted();
        System.out.println("After:  " + dups + "  size = " + dups.size());

        System.out.println("\n=== Palindrome check ===");
        SinglyLinkedList p1 = SinglyLinkedList.of(1, 2, 3, 2, 1);
        SinglyLinkedList p2 = SinglyLinkedList.of(1, 2, 3, 4);
        System.out.println(p1 + "  palindrome? " + p1.isPalindrome());
        System.out.println(p2 + "  palindrome? " + p2.isPalindrome());
        System.out.println("List unchanged after check: " + p1);

        System.out.println("\n=== Merge two sorted lists ===");
        SinglyLinkedList a = SinglyLinkedList.of(1, 4, 7, 10);
        SinglyLinkedList b = SinglyLinkedList.of(2, 3, 8, 12);
        System.out.println(SinglyLinkedList.mergeSorted(a, b));

        System.out.println("\n=== Error handling ===");
        try {
            new SinglyLinkedList().removeFirst();
        } catch (IllegalStateException e) {
            System.out.println("Expected error: " + e.getMessage());
        }
    }
}