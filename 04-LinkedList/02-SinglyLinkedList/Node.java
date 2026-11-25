/**
 * One element ("box") of a {@link SinglyLinkedList}.
 *
 * <p>Each node holds an {@code int} value and a reference to the next node
 * in the chain. The last node's {@code next} is {@code null}:</p>
 *
 * <pre>
 *   [data | next] -&gt; [data | next] -&gt; [data | next] -&gt; null
 * </pre>
 *
 * <p>The fields are package-private so that {@link SinglyLinkedList} can
 * link and unlink nodes directly.</p>
 *
 * @see SinglyLinkedList
 */
public class Node {

    /** The value stored in this node. */
    int data;

    /** The next node in the list, or {@code null} if this is the last node. */
    Node next;

    /**
     * Creates a node holding the given value, not yet linked to any other node.
     *
     * @param data the value to store in this node
     */
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}