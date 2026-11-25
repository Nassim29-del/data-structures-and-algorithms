import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic <em>doubly</em> linked list built from scratch, following the
 * same idea as {@link java.util.LinkedList}. Each node points to both of its
 * neighbours:
 *
 * <pre>
 *   null &lt;- [prev|A|next] &lt;-&gt; [prev|B|next] &lt;-&gt; [prev|C|next] -&gt; null
 *            head                                  tail
 * </pre>
 *
 * <p>Indexes are zero-based. {@code null} values are allowed.</p>
 *
 * <table border="1">
 *   <caption>Time complexity of the main operations</caption>
 *   <tr><th>Operation</th><th>Time</th><th>Notes</th></tr>
 *   <tr><td>{@link #addFirst(Object)} / {@link #addLast(Object)}</td><td>O(1)</td><td></td></tr>
 *   <tr><td>{@link #removeFirst()} / {@link #removeLast()}</td><td>O(1)</td>
 *       <td>a singly linked list can't do {@code removeLast} in O(1)</td></tr>
 *   <tr><td>{@link #get(int)} / {@link #add(int, Object)} / {@link #remove(int)}</td><td>O(n)</td>
 *       <td>walks from the closer end, so at most n/2 steps</td></tr>
 * </table>
 *
 * <p>This class is not thread-safe, and its iterator does not detect changes
 * made to the list while iterating.</p>
 *
 * @param <T> the type of the values stored in the list
 * @see java.util.LinkedList
 */
public class MyLinkedList<T> implements Iterable<T> {

    /**
     * One element of the list, holding a value and links to both neighbours.
     * Nested and private because only {@code MyLinkedList} needs to know
     * about nodes.
     *
     * @param <T> the type of the value stored in the node
     */
    private static class Node<T> {
        /** The value stored in this node; may be {@code null}. */
        T data;

        /** The previous node, or {@code null} if this is the first node. */
        Node<T> prev;

        /** The next node, or {@code null} if this is the last node. */
        Node<T> next;

        /**
         * Creates an unlinked node holding the given value.
         *
         * @param data the value to store; may be {@code null}
         */
        Node(T data) {
            this.data = data;
        }
    }

    /** The first node, or {@code null} if the list is empty. */
    private Node<T> head;

    /** The last node, or {@code null} if the list is empty. */
    private Node<T> tail;

    /** The number of values currently in the list. */
    private int size;

    /**
     * Creates an empty list.
     */
    public MyLinkedList() {
    }

    // ---- add -------------------------------------------------------

    /**
     * Adds a value at the front of the list.
     *
     * <p>Runs in O(1) time.</p>
     *
     * @param value the value to add; may be {@code null}
     */
    public void addFirst(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    /**
     * Adds a value at the end of the list.
     *
     * <p>Runs in O(1) time.</p>
     *
     * @param value the value to add; may be {@code null}
     */
    public void addLast(T value) {
        Node<T> node = new Node<>(value);
        if (tail == null) {
            head = tail = node;
        } else {
            node.prev = tail;
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
     * {@link #size()} adds at the end. Otherwise the list is walked from
     * whichever end is closer, so this takes at most about n/2 steps.</p>
     *
     * @param index the position to insert at, from {@code 0} to {@code size()} inclusive
     * @param value the value to insert; may be {@code null}
     * @throws IndexOutOfBoundsException if {@code index < 0} or {@code index > size()}
     */
    public void add(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) { addFirst(value); return; }
        if (index == size) { addLast(value); return; }

        Node<T> after = nodeAt(index);
        Node<T> before = after.prev;
        Node<T> node = new Node<>(value);
        node.prev = before;          // four links to update
        node.next = after;
        before.next = node;
        after.prev = node;
        size++;
    }

    // ---- remove ----------------------------------------------------

    /**
     * Removes and returns the first value in the list.
     *
     * <p>Runs in O(1) time.</p>
     *
     * @return the value that was at the front of the list
     * @throws NoSuchElementException if the list is empty
     */
    public T removeFirst() {
        if (head == null) throw new NoSuchElementException("List is empty");
        return unlink(head);
    }

    /**
     * Removes and returns the last value in the list.
     *
     * <p>Runs in O(1) time, because the tail node knows its previous node.</p>
     *
     * @return the value that was at the end of the list
     * @throws NoSuchElementException if the list is empty
     */
    public T removeLast() {
        if (tail == null) throw new NoSuchElementException("List is empty");
        return unlink(tail);
    }

    /**
     * Removes and returns the value at the given position, shifting every
     * later value one place to the left.
     *
     * <p>Walks from whichever end is closer, so this takes at most about n/2
     * steps.</p>
     *
     * @param index the position of the value to remove, from {@code 0} to {@code size() - 1}
     * @return the value that was removed
     * @throws IndexOutOfBoundsException if {@code index < 0} or {@code index >= size()}
     */
    public T remove(int index) {
        checkIndex(index);
        return unlink(nodeAt(index));
    }

    /**
     * Removes the first occurrence of the given value, if it is present.
     * Values are compared with {@link Object#equals(Object)}; a {@code null}
     * argument removes the first {@code null} value.
     *
     * <p>Runs in O(n) time.</p>
     *
     * @param value the value to remove; may be {@code null}
     * @return {@code true} if a value was removed, {@code false} if the value
     *         was not in the list
     */
    public boolean removeValue(T value) {
        for (Node<T> cur = head; cur != null; cur = cur.next) {
            if (value == null ? cur.data == null : value.equals(cur.data)) {
                unlink(cur);
                return true;
            }
        }
        return false;
    }

    /**
     * Unlinks a node from the list in O(1) time, since the node knows both
     * of its neighbours. Updates {@code head}, {@code tail} and {@code size}
     * as needed, and clears the node's own links.
     *
     * @param node the node to remove; must be a node of this list
     * @return the value the removed node held
     */
    private T unlink(Node<T> node) {
        if (node.prev == null) head = node.next; else node.prev.next = node.next;
        if (node.next == null) tail = node.prev; else node.next.prev = node.prev;
        node.prev = node.next = null;
        size--;
        return node.data;
    }

    // ---- read ------------------------------------------------------

    /**
     * Returns the value at the given position.
     *
     * <p>Walks from whichever end is closer, so this takes at most about n/2
     * steps.</p>
     *
     * @param index the position of the value, from {@code 0} to {@code size() - 1}
     * @return the value at {@code index}
     * @throws IndexOutOfBoundsException if {@code index < 0} or {@code index >= size()}
     */
    public T get(int index) {
        checkIndex(index);
        return nodeAt(index).data;
    }

    /**
     * Returns the first value without removing it.
     *
     * @return the value at the front of the list
     * @throws NoSuchElementException if the list is empty
     */
    public T getFirst() {
        if (head == null) throw new NoSuchElementException("List is empty");
        return head.data;
    }

    /**
     * Returns the last value without removing it.
     *
     * @return the value at the end of the list
     * @throws NoSuchElementException if the list is empty
     */
    public T getLast() {
        if (tail == null) throw new NoSuchElementException("List is empty");
        return tail.data;
    }

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
     * Returns the node at the given position, walking from the head if the
     * index is in the first half and from the tail otherwise. The caller is
     * responsible for checking that {@code index} is valid.
     *
     * @param index the position of the node, from {@code 0} to {@code size() - 1}
     * @return the node at {@code index}
     */
    private Node<T> nodeAt(int index) {
        if (index < size / 2) {
            Node<T> cur = head;
            for (int i = 0; i < index; i++) cur = cur.next;
            return cur;
        } else {
            Node<T> cur = tail;
            for (int i = size - 1; i > index; i--) cur = cur.prev;
            return cur;
        }
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

    /**
     * Reverses the list in place by swapping the {@code prev} and
     * {@code next} links of every node, then swapping {@code head} and
     * {@code tail}. Both directions stay correct afterwards.
     *
     * <p>Runs in O(n) time with O(1) extra space.</p>
     */
    public void reverse() {
        Node<T> cur = head;
        while (cur != null) {
            Node<T> next = cur.next;
            cur.next = cur.prev;
            cur.prev = next;
            cur = next;
        }
        Node<T> oldHead = head;
        head = tail;
        tail = oldHead;
    }

    // ---- iteration -------------------------------------------------

    /**
     * Returns an iterator over the values from first to last, which lets the
     * list be used in a for-each loop.
     *
     * <p>The iterator does not support {@code remove()}, and its behaviour is
     * undefined if the list is changed while iterating.</p>
     *
     * @return an iterator over the values in order from head to tail
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> cur = head;

            @Override
            public boolean hasNext() { return cur != null; }

            @Override
            public T next() {
                if (cur == null) throw new NoSuchElementException();
                T value = cur.data;
                cur = cur.next;
                return value;
            }
        };
    }

    /**
     * Returns the list as a string walked from tail to head using the
     * {@code prev} links, for example {@code "null <- C <- B <- A"}. Useful
     * for checking that the backward links are correct. An empty list is
     * {@code "null"}.
     *
     * @return a text picture of the list, read backwards
     */
    public String toStringBackward() {
        StringBuilder sb = new StringBuilder("null");
        for (Node<T> cur = tail; cur != null; cur = cur.prev) {
            sb.append(" <- ").append(cur.data);
        }
        return sb.toString();
    }

    /**
     * Returns the list as a string walked from head to tail, for example
     * {@code "null <- A <-> B <-> C -> null"}. An empty list is
     * {@code "(empty)"}.
     *
     * @return a text picture of the list
     */
    @Override
    public String toString() {
        if (head == null) return "(empty)";
        StringBuilder sb = new StringBuilder("null <- ");
        for (Node<T> cur = head; cur != null; cur = cur.next) {
            sb.append(cur.data);
            if (cur.next != null) sb.append(" <-> ");
        }
        return sb.append(" -> null").toString();
    }
}