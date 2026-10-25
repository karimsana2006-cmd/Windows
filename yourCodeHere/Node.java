
//TODO: Add JavaDocs ONLY. No other Editing.
/**
 * Represents a node in a doubly linked list.
 *
 * @param <T> the type of element stored in the node
 */
class Node<T> {
	/**
	 * The value in node.
	 */
	public T value;

	/**
	 * The next node.
	 */
	public Node<T> next;

	/**
	 * The previous node.
	 */
	public Node<T> prev;

	/**
	 * Creates an empty node.
	 */
	public Node() { }

	/**
	 * Creates a node that stores the value.
	 *
	 * @param value the value to store in the node
	 */
	public Node(T value) {
		this.value = value;
	}
}
