/**
 * A singly linked list of {@code int} values built from scratch, plus the
 * classic interview algorithms on it.
 *
 * <p>The list keeps references to both its first node ({@code head}) and its
 * last node ({@code tail}), so adding at either end takes constant time.
 * Indexes are zero-based.</p>
 *
 * <table border="1">
 *   <caption>Time complexity of the main operations</caption>
 *   <tr><th>Operation</th><th>Time</th><th>Notes</th></tr>
 *   <tr><td>{@link #addFirst(int)}</td><td>O(1)</td><td></td></tr>
 *   <tr><td>{@link #addLast(int)}</td><td>O(1)</td><td>uses the tail pointer</td></tr>
 *   <tr><td>{@link #insertAt(int, int)} / {@link #removeAt(int)}</td><td>O(n)</td><td></td></tr>
 *   <tr><td>{@link #removeValue(int)} / {@link #contains(int)}</td><td>O(n)</td><td></td></tr>
 *   <tr><td>{@link #reverse()}</td><td>O(n)</td><td>O(1) extra space</td></tr>
 *   <tr><td>{@link #findMiddle()}</td><td>O(n)</td><td>fast/slow pointers</td></tr>
 *   <tr><td>{@link #nthFromEnd(int)}</td><td>O(n)</td><td>two pointers, one pass</td></tr>
 *   <tr><td>{@link #hasCycle()}</td><td>O(n)</td><td>Floyd's tortoise and hare</td></tr>
 * </table>
 *
 * <p>This class is not thread-safe.</p>
 *
 * @see Node
 */
public class SinglyLinkedList {

    /** The first node of the list, or {@code null} if the list is empty. */
    private Node head;

    /** The last node of the list, or {@code null} if the list is empty. */
    private Node tail;

    /** The number of values currently in the list. */
    private int size;

    /**
     * Creates an empty list.
     */
    public SinglyLinkedList() {
    }

    // ---- add -------------------------------------------------------

    /**
     * Adds a value at the front of the list.
     *
     * <p>Runs in O(1) time.</p>
     *
     * @param value the value to add
     */
    public void addFirst(int value) {
        Node node = new Node(value);
        node.next = head;
        head = node;
        if (tail == null) tail = node;       // list was empty
        size++;
    }

    /**
     * Adds a value at the end of the list.
     *
     * <p>Runs in O(1) time because the list keeps a tail pointer.</p>
     *
     * @param value the value to add
     */
    public void addLast(int value) {
        Node node = new Node(value);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    /**
     * Inserts a value at the given position, shifting the value currently at
     * that position (and every value after it) one place to the right.
     *
     * <p>An index of {@code 0} adds at the front; an index equal to
     * {@link #size()} adds at the end. Runs in O(n) time.</p>
     *
     * @param index the position to insert at, from {@code 0} to {@code size()} inclusive
     * @param value the value to insert
     * @throws IndexOutOfBoundsException if {@code index < 0} or {@code index > size()}
     */
    public void insertAt(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) { addFirst(value); return; }
        if (index == size) { addLast(value); return; }

        Node prev = nodeAt(index - 1);       // stop one BEFORE the position
        Node node = new Node(value);
        node.next = prev.next;               // 1. new node points to the rest
        prev.next = node;                    // 2. previous node points to new node
        size++;
    }

    // ---- remove ----------------------------------------------------

    /**
     * Removes and returns the first value in the list.
     *
     * <p>Runs in O(1) time.</p>
     *
     * @return the value that was at the front of the list
     * @throws IllegalStateException if the list is empty
     */
    public int removeFirst() {
        if (head == null) throw new IllegalStateException("List is empty");
        int value = head.data;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return value;
    }

    /**
     * Removes and returns the value at the given position, shifting every
     * later value one place to the left.
     *
     * <p>Runs in O(n) time.</p>
     *
     * @param index the position of the value to remove, from {@code 0} to {@code size() - 1}
     * @return the value that was removed
     * @throws IndexOutOfBoundsException if {@code index < 0} or {@code index >= size()}
     */
    public int removeAt(int index) {
        checkIndex(index);
        if (index == 0) return removeFirst();
        Node prev = nodeAt(index - 1);
        Node target = prev.next;
        prev.next = target.next;             // skip over the target
        if (target == tail) tail = prev;
        size--;
        return target.data;
    }

    /**
     * Removes the first occurrence of the given value, if it is present.
     *
     * <p>Runs in O(n) time.</p>
     *
     * @param value the value to remove
     * @return {@code true} if a value was removed, {@code false} if the value
     *         was not in the list
     */
    public boolean removeValue(int value) {
        if (head == null) return false;
        if (head.data == value) { removeFirst(); return true; }
        Node prev = head;
        while (prev.next != null && prev.next.data != value) prev = prev.next;
        if (prev.next == null) return false;
        if (prev.next == tail) tail = prev;
        prev.next = prev.next.next;
        size--;
        return true;
    }

    // ---- read ------------------------------------------------------

    /**
     * Returns the value at the given position.
     *
     * <p>Runs in O(n) time, since the list must be walked from the head.</p>
     *
     * @param index the position of the value, from {@code 0} to {@code size() - 1}
     * @return the value at {@code index}
     * @throws IndexOutOfBoundsException if {@code index < 0} or {@code index >= size()}
     */
    public int get(int index) {
        checkIndex(index);
        return nodeAt(index).data;
    }

    /**
     * Returns the position of the first occurrence of the given value.
     *
     * <p>Runs in O(n) time.</p>
     *
     * @param value the value to search for
     * @return the index of the first occurrence of {@code value}, or
     *         {@code -1} if it is not in the list
     */
    public int indexOf(int value) {
        int i = 0;
        for (Node cur = head; cur != null; cur = cur.next, i++) {
            if (cur.data == value) return i;
        }
        return -1;
    }

    /**
     * Tells whether the list contains the given value.
     *
     * <p>Runs in O(n) time.</p>
     *
     * @param value the value to search for
     * @return {@code true} if the value is in the list, {@code false} otherwise
     */
    public boolean contains(int value) { return indexOf(value) != -1; }

    /**
     * Returns the number of values in the list.
     *
     * @return the size of the list
     */
    public int size() { return size; }

    /**
     * Tells whether the list has no values.
     *
     * @return {@code true} if the list is empty, {@code false} otherwise
     */
    public boolean isEmpty() { return size == 0; }

    /**
     * Walks from the head and returns the node at the given position.
     * The caller is responsible for checking that {@code index} is valid.
     *
     * @param index the position of the node, from {@code 0} to {@code size() - 1}
     * @return the node at {@code index}
     */
    private Node nodeAt(int index) {
        Node cur = head;
        for (int i = 0; i < index; i++) cur = cur.next;
        return cur;
    }

    /**
     * Checks that an index refers to an existing value.
     *
     * @param index the index to check
     * @throws IndexOutOfBoundsException if {@code index < 0} or {@code index >= size()}
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // ---- classic algorithms ----------------------------------------

    /**
     * Reverses the list in place, iteratively, by flipping each node's
     * {@code next} reference so it points to the previous node:
     *
     * <pre>
     *   before: 1 -&gt; 2 -&gt; 3 -&gt; null
     *   after:  3 -&gt; 2 -&gt; 1 -&gt; null
     * </pre>
     *
     * <p>Runs in O(n) time with O(1) extra space. The tail pointer is
     * updated, so {@link #addLast(int)} keeps working afterwards.</p>
     *
     * @see #reverseRecursive()
     */
    public void reverse() {
        Node prev = null;
        Node cur = head;
        tail = head;
        while (cur != null) {
            Node next = cur.next;    // 1. remember the rest
            cur.next = prev;         // 2. flip the arrow
            prev = cur;              // 3. move prev forward
            cur = next;              // 4. move cur forward
        }
        head = prev;
    }

    /**
     * Reverses the list in place, recursively: reverse the rest of the list,
     * then hook the current node onto its end.
     *
     * <p>Runs in O(n) time but uses O(n) stack space, so a very long list can
     * cause a {@link StackOverflowError}. Prefer {@link #reverse()} for large
     * lists.</p>
     *
     * @see #reverse()
     */
    public void reverseRecursive() {
        tail = head;
        head = reverseFrom(head);
    }

    /**
     * Recursively reverses the chain of nodes starting at {@code node}.
     *
     * @param node the first node of the chain to reverse; may be {@code null}
     * @return the new first node of the reversed chain (the old last node),
     *         or {@code null} if {@code node} was {@code null}
     */
    private Node reverseFrom(Node node) {
        if (node == null || node.next == null) return node;
        Node newHead = reverseFrom(node.next);
        node.next.next = node;
        node.next = null;
        return newHead;
    }

    /**
     * Returns the middle value of the list using the fast/slow pointer
     * technique: the slow pointer moves one step while the fast pointer moves
     * two, so when the fast pointer reaches the end the slow pointer is in
     * the middle.
     *
     * <p>For a list with an even number of values, the second of the two
     * middle values is returned (for {@code 1 -> 2 -> 3 -> 4}, the result is
     * {@code 3}). Runs in O(n) time in a single pass.</p>
     *
     * @return the middle value
     * @throws IllegalStateException if the list is empty
     */
    public int findMiddle() {
        if (head == null) throw new IllegalStateException("List is empty");
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.data;
    }

    /**
     * Returns the n-th value from the end of the list, where {@code n = 1}
     * is the last value.
     *
     * <p>Uses two pointers: the lead pointer is moved {@code n} steps ahead,
     * then both move together until the lead falls off the end, leaving the
     * trailing pointer on the answer. Runs in O(n) time in one pass.</p>
     *
     * @param n the position counted from the end, from {@code 1} to {@code size()}
     * @return the value {@code n} places from the end
     * @throws IllegalArgumentException if {@code n <= 0} or {@code n > size()}
     */
    public int nthFromEnd(int n) {
        if (n <= 0 || n > size) throw new IllegalArgumentException("n out of range: " + n);
        Node lead = head, trail = head;
        for (int i = 0; i < n; i++) lead = lead.next;
        while (lead != null) {
            lead = lead.next;
            trail = trail.next;
        }
        return trail.data;
    }

    /**
     * Tells whether the list contains a cycle, using Floyd's "tortoise and
     * hare" algorithm: a slow pointer moves one step and a fast pointer moves
     * two; if there is a loop, the fast pointer eventually laps the slow one.
     *
     * <p>Runs in O(n) time with O(1) extra space.</p>
     *
     * @return {@code true} if following {@code next} references from the head
     *         loops forever, {@code false} if it reaches {@code null}
     */
    public boolean hasCycle() {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    /**
     * Links the tail back to the node at the given position, creating a
     * cycle. Intended for demonstrating {@link #hasCycle()} only.
     *
     * <p><b>Warning:</b> after this call the list is corrupted. Methods that
     * walk the whole list, such as {@link #toString()}, {@link #indexOf(int)}
     * for a missing value, or {@link #reverse()}, will loop forever.</p>
     *
     * @param index the position of the node the tail should point to, from
     *              {@code 0} to {@code size() - 1}
     * @throws NullPointerException if the list is empty
     */
    void createCycleTo(int index) {
        tail.next = nodeAt(index);
    }

    /**
     * Removes duplicate values from a <em>sorted</em> list in one pass,
     * keeping the first occurrence of each value.
     *
     * <p>The list must already be sorted (or at least have equal values next
     * to each other); duplicates that are not adjacent are not removed.
     * Runs in O(n) time with O(1) extra space.</p>
     */
    public void removeDuplicatesSorted() {
        Node cur = head;
        while (cur != null && cur.next != null) {
            if (cur.data == cur.next.data) {
                cur.next = cur.next.next;
                size--;
            } else {
                cur = cur.next;
            }
        }
        tail = cur;
    }

    /**
     * Tells whether the list reads the same forwards and backwards.
     *
     * <p>Finds the middle, reverses the second half in place, compares the two
     * halves, then reverses the second half again so the list is left exactly
     * as it was. Runs in O(n) time with O(1) extra space. Empty and
     * one-element lists are palindromes.</p>
     *
     * @return {@code true} if the list is a palindrome, {@code false} otherwise
     */
    public boolean isPalindrome() {
        if (head == null || head.next == null) return true;
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node secondHalf = reverseFrom(slow.next);
        Node p1 = head, p2 = secondHalf;
        boolean result = true;
        while (p2 != null) {
            if (p1.data != p2.data) { result = false; break; }
            p1 = p1.next;
            p2 = p2.next;
        }
        slow.next = reverseFrom(secondHalf);   // restore the original list
        return result;
    }

    /**
     * Merges two sorted lists into a new sorted list.
     *
     * <p>Neither input list is modified. When values are equal, the value
     * from {@code a} comes first. Runs in O(n + m) time, where {@code n} and
     * {@code m} are the sizes of the two lists.</p>
     *
     * @param a the first sorted list; must not be {@code null}
     * @param b the second sorted list; must not be {@code null}
     * @return a new list containing every value of {@code a} and {@code b},
     *         in sorted order
     * @throws NullPointerException if {@code a} or {@code b} is {@code null}
     */
    public static SinglyLinkedList mergeSorted(SinglyLinkedList a, SinglyLinkedList b) {
        SinglyLinkedList result = new SinglyLinkedList();
        Node p = a.head, q = b.head;
        while (p != null && q != null) {
            if (p.data <= q.data) { result.addLast(p.data); p = p.next; }
            else { result.addLast(q.data); q = q.next; }
        }
        for (; p != null; p = p.next) result.addLast(p.data);
        for (; q != null; q = q.next) result.addLast(q.data);
        return result;
    }

    /**
     * Creates a list containing the given values, in order.
     *
     * <pre>
     *   SinglyLinkedList list = SinglyLinkedList.of(1, 2, 3);   // 1 -&gt; 2 -&gt; 3 -&gt; null
     * </pre>
     *
     * @param values the values to add; may be empty
     * @return a new list holding {@code values} in the given order
     */
    public static SinglyLinkedList of(int... values) {
        SinglyLinkedList list = new SinglyLinkedList();
        for (int v : values) list.addLast(v);
        return list;
    }

    /**
     * Returns the list as a string of arrows, for example
     * {@code "1 -> 2 -> 3 -> null"}. An empty list is {@code "null"}.
     *
     * @return a text picture of the list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node cur = head; cur != null; cur = cur.next) {
            sb.append(cur.data).append(" -> ");
        }
        return sb.append("null").toString();
    }
}