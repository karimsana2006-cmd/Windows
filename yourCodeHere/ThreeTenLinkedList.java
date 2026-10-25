//TODO: Linked list implementation (optional)
//      JavaDocs (not optional)
//      Static sorting methods (not optional)

import java.util.Comparator; //you'll need this
/**
 * ThreeTenLinkedList.
 *
 * <p>A linked list class that holds static sorting methods.</p>
 *
 * @param <T> the type of the elements in the list.
 */
class ThreeTenLinkedList<T> {
    /**
     * Checks if lst is sorted. Uses comparator.
     *
     * @param <X> type.
     * @param pairs head and tail nodes paired.
     * @param comp comparator.
     * @return if list is sorted, true.
     * @throws IllegalArgumentException if null.
     */
    static <X> boolean isSorted(NodePair<X> pairs, Comparator<X> comp) {

        if (pairs == null || comp == null) {
            throw new IllegalArgumentException();
        }

        for (Node<X> c = pairs.head; c != null && c.next != null; c = c.next) {

            if (comp.compare(c.value, c.next.value) > 0) {
                return false;
            }
        }

        return true;
    }
    /**
     * Sorts based on comparator.
     *
     * @param <X> type of elements.
     * @param pairs to be sorted.
     * @param comp comparator.
     * @return node pair that is sorted.
     */
    static <X> NodePair<X> sort(NodePair<X> pairs, Comparator<X> comp) {

        if (pairs == null || comp == null) {
            throw new IllegalArgumentException();
        }

        if (pairs.head == null) {
            return pairs;
        }

        Node<X> c = pairs.head.next;

        while (c != null) {
            X value = c.value;
            Node<X> p = c;

            while (p.prev != null
                    && comp.compare(p.prev.value, value) > 0) {
                p.value = p.prev.value;
                p = p.prev;
            }

            p.value = value;
            c = c.next;
        }

        return pairs;
    }

    /**
     * Holds the head and tail nodes of a list.
     *
     * @param <Y> element type.
     */
    public static class NodePair<Y> {
        /**
         * The first node in the list.
         */
        public Node<Y> head;
        /**
         * The last node in the list.
         */
        public Node<Y> tail;
        /**
         * Creates a pair from a head node and a tail node.
         *
         * @param head the first node in the list.
         * @param tail the last node in the list.
         */
        public NodePair(Node<Y> head, Node<Y> tail) {
            this.head = head;
            this.tail = tail;
        }
    }

}