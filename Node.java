/**
 * Node represents a node in a linked list.
 *
 * @author Java Foundations, mvail
 * @version 4.0
 */
public class Node<E> {
	private Node<E> next;
	private Node<E> previous;
	private E element;

	/**
  	 * Creates an empty node.
  	 */
	public Node() {
		next = null;
		previous = null;
		element = null;
	}

	/**
  	 * Creates a node storing the specified element.
 	 *
  	 * @param elem
  	 *            the element to be stored within the new node
  	 */
	public Node(E elem) {
		next = null;
		previous = null;
		element = elem;
	}

	/**
 	 * Returns the node that follows this one.
  	 *
  	 * @return the node that follows the current one
  	 */
	public Node<E> getNext() {
		return next;
	}
	
	/**
	 * Returns the node that is before this one.
	 * 
	 * @return the node that is before the current one
	 */
	public Node<E> getPrevious() {
		return previous;
	}
	
	/**
 	 * Sets the node that follows this one.
 	 *
 	 * @param node
 	 *            the node to be set to follow the current one
 	 */
	public void setNext(Node<E> node) {
		next = node;
	}

	/**
	 * Sets the node that precursors this one.
	 * 
	 * @param node
	 * 			  the node to be set to precede the current one
	 */
	public void setPrevious(Node<E> node) {
		previous = node;
	}
	
	/**
 	 * Returns the element stored in this node.
 	 *
 	 * @return the element stored in this node
 	 */
	public E getElement() {
		return element;
	}

//	/**
//	 * This method is meant to clear the previous and next values to null
//	 * if not sure if this is needed but I like it for convenience 
//	 */
//	public void clearRoute() { I thought I needed this but I'm not sure now
//		previous = null;
//		next = null;
//	}
	
	/**
 	 * Sets the element stored in this node.
  	 *
  	 * @param elem
  	 *            the element to be stored in this node
  	 */
	public void setElement(E elem) {
		element = elem;
	}

	@Override
	public String toString() {
		// return "Element: " + element.toString() + " Has next: " + (next != null);
		return "Element: " + element.toString();
	}
}

