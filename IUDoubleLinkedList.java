import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
 * This class is a double linked list that uses nodes and has a selections of methods to
 * add/remove/modify/collect data. It also has a ListIterator that is capable of moving back and fourth between
 * all the elements in the list.
 * 
 * @author BrianWu
 *
 * @param <T> type to store
 */
public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {

	private Node<T> head, tail;
	private int size;
	private int modCount;

	/** Creates an empty list */
	public IUDoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
		modCount = 0;
	}

	/**
	 * This method adds an element to the front of the list
	 */
	@Override
	public void addToFront(T element) {
		Node<T> newData = new Node<T>(element);
		if(head != null) {
			newData.setNext(head);
			head.setPrevious(newData);
			head = newData;
		}else {
			head = newData; 
			tail = newData;
			head.setNext(null); //Possibly redundant line
		}
		size++;
		modCount++;

	}

	/**
	 * This method adds an element to the rear of the list
	 */
	@Override
	public void addToRear(T element) {
		Node<T> newData = new Node<T>(element);
		if(tail != null) {
			tail.setNext(newData);
			newData.setPrevious(tail);
			tail = newData;
		}else {
			head = newData;
			tail = newData;
			// head.setNext(null); //Possibly redundant line
		}
		size++;
		modCount++;
	}

	//This method adds an element to the rear of the list
	@Override
	public void add(T element) { //This method follows everything that the the addToRear() method has 
		Node<T> newData = new Node<T>(element);
		if(tail != null) {
			tail.setNext(newData);
			newData.setPrevious(tail);
			tail = newData;
		}else {
			head = tail = newData;
			head.setNext(null); //Possibly redundant line
		}
		size++;
		modCount++;		
	}

	/**
	 * This method adds an element after a specified target
	 */
	@Override
	public void addAfter(T element, T target) {
		Node<T> locater = head;
		while(locater != null && !locater.getElement().equals(target)) {
			locater = locater.getNext();
		}
		if(locater == null) {
			throw new NoSuchElementException();
		}
		Node<T> newData = new Node<T>(element);
		if(locater.getNext() == null) {
			locater.setNext(newData);
			newData.setPrevious(locater);
			tail = newData;
			newData.setNext(null); //Possibly redundant line
		}else {
			Node<T> nextNode = locater.getNext();
			locater.setNext(newData);
			newData.setNext(nextNode);
			newData.setPrevious(nextNode);
			nextNode.setPrevious(newData);
		}
		size++;
		modCount++;
	}

	/**
	 * This method adds an element at a specified index
	 */
	@Override
	public void add(int index, T element) {
		Node<T> locater = head;
		Node<T> newData = new Node<T>(element);
		int indexCounter = 0;
		if(index > size || index < 0) { //Range checker
			throw new IndexOutOfBoundsException();
		}
		if(index == 0 || locater == null) { //If the list is empty or is being added to the frost
			if(locater == null) {
				head = newData;
				tail = newData;
			}else if(index == 0){
				newData.setNext(locater);
				locater.setPrevious(newData);
				head = newData;
			}
		}else {
			//I believe this should usually be effective in adding elements with indexes
			while(indexCounter + 1 != index) {
				locater = locater.getNext();
				indexCounter++;
				if(locater == null) {
					locater = locater.getPrevious();
				}
			}
			if(locater.getNext() == null) {
//				if(indexCounter >= size) {
//					throw new IndexOutOfBoundsException();
//				}
				locater.setNext(newData);
				newData.setPrevious(locater);
				tail = newData;
				newData.setNext(null); //Possibly redundant line	
			}
		}
		size++;
		modCount++;
	}

	/**
	 * This method removes an element at the front of the list and returns said removed element
	 * @return <T> the element at the front of the list
	 */
	@Override
	public T removeFirst() {
		Node<T> temp = head;
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		if(size > 1) { //If there is more than 1 element
			head = head.getNext();
		}
		if(size == 1) { //If there is just 1 and only 1 element
			head = null;
			tail = null;
		}
		size--;
		modCount++;
		return temp.getElement();
	}

	/**
	 * This method removes the last element in the list and returns it
	 * @return <T> the element at the end of the list
	 */
	@Override
	public T removeLast() { //This is one of the key changes when switching from a single to double linked list
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		Node<T> last = tail;
		if(size == 1) { //If there is only uno nodo
			head = null;
			tail = null;
			size--;
			modCount++;
			return last.getElement();
		}else { //More than 1 element
			tail.getPrevious().setNext(null);
			tail = tail.getPrevious();		
		}
		size--;
		modCount++;
		return last.getElement();
	}

	/**
	 * This method removes an element of a specific value and returns it
	 * @return <T> the element of the specified value
	 */
	@Override
	public T remove(T element) {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}

		boolean found = false; 
		Node<T> previous = null;
		Node<T> current = head;

		while(current != null && !found) {
			if(element.equals(current.getElement())) {
				found = true;
			} else {
				previous = current;
				current = current.getNext();
			}
		}

		if(!found) {
			throw new NoSuchElementException();
		}

		if(size() == 1) { //1 node
			head = null; 
			tail = null;
		} else if (current == head) { //front of list
			head = current.getNext();
		} else if (current == tail) { //end of list
			tail = previous;
			tail.setNext(null);
		}else { //middle of list
			current.getNext().setPrevious(previous);
			previous.setNext(current.getNext());
		}

		size--;
		modCount++;

		return current.getElement();
	}

	/**
	 * This method removes an element at a specified index and returns it
	 * @return <T> the element at the specified index
	 */
	@Override
	public T remove(int index) {

		Node<T> removed = null;

		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) { // At the head
			removed = new Node<T>(head.getElement());
			head = head.getNext();
		} else if (index == size - 1) { // At the tail
			removed = new Node<T>(tail.getElement());
			tail = tail.getPrevious();
			tail.setNext(null);
		} else { // In the middle
			Node<T> current = head;
			int indexCounter = 0;

			while (indexCounter != index) {
				indexCounter++;
				current = current.getNext();
			}

			removed = new Node<T>(current.getElement());
			Node<T> previous = current.getPrevious();
			Node<T> next = current.getNext();

			previous.setNext(next);
			next.setPrevious(previous);
		}

		size--;
		modCount++;

		return removed.getElement();
	}


	/**
	 * This method goes to a specified index and changes the element to a requested value
	 */
	@Override
	public void set(int index, T element) {
		Node<T> current = head;
		// int indexCounter = 0;

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}

		current.setElement(element);
		modCount++;




		// if(index < 0 || index >= size) {
		// 	if(index == size && index != 0) {
		// 		Node<T> newData = new Node<T>(element);
		// 		tail.setNext(newData);
		// 		newData.setPrevious(tail);
		// 		tail = newData;
		// 		size++;
		// 		modCount++;
		// 	} else {
		// 		throw new IndexOutOfBoundsException();
		// 	}
		// }
		// while(indexCounter != index) {
		// 	indexCounter++;
		// 	current = current.getNext();
		// }
		// if(indexCounter == index) {
		// 	current.setElement(element);
		// }
		// modCount++; I've thought about this and I'm not sure if this is needed 
	}

	/**
	 * This method gets an element at a specified index and returns it
	 * @return <T> the element at the specified index
	 */
	@Override
	public T get(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> current = head;
		int indexCounter = 0;
		while(indexCounter != index) {
			indexCounter++;
			current = current.getNext();
		}
		return current.getElement();
	}

	/**
	 * This method returns the index of a specified element on the list
	 * @return int the index of the element specified
	 */
	@Override
	public int indexOf(T element) {
		Node<T> current = head;
		int indexCount = 0;
		if(current == null) {
			return -1;
		}
		while(current.getElement() != element) {
			indexCount++;
			if(current.getNext() == null) {
				return -1;
			}
			current = current.getNext();
		}
		return indexCount;
	}

	/**
	 * This method returns the element at the start of the list
	 * @return <T> the element at the front
	 */
	@Override
	public T first() {
		Node<T> first = head;
		if(first == null) {
			throw new NoSuchElementException();
		}
		return first.getElement();
	}

	/**
	 * This method returns the element at the rear of the list
	 * @return <T> the element at the end
	 */
	@Override
	public T last() {
		Node<T> last = tail;
		if(last == null) {
			throw new NoSuchElementException();
		}
		return last.getElement();
	}

	/**
	 * This method returns a boolean value whether the list contains the specified element
	 * @return boolean whether the list contains target value
	 */
	@Override
	public boolean contains(T target) {
		Node<T> current = head;
		while(current != null) {
			if(current.getElement() == target) {
				return true;
			}
			current = current.getNext();
		}
		return false;
	}

	/**
	 * This method determines whether the list is empty using a complex algorithm and returns whether it is true or false
	 * @return boolean if size == 0
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * This method returns the size of the list
	 * @return the number of elements on the list
	 */
	@Override
	public int size() {
		int elements = size;
		return elements;
	}

	/**
	 * This method returns the list in a literal string
	 * @return string of output
	 */
	public String toString() {
		String output = "[";
		Node<T> current = head;
		while(current != null) {
			output += current.toString() + ", ";
			current = current.getNext();
		}
		output += "]";
		return output;
	}

	@Override
	public Iterator<T> iterator() {
		throw new UnsupportedOperationException();


		// return new DLLIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();

		// return new DLLListIterator();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {

		throw new UnsupportedOperationException();

		// return new DLLListIterator(startingIndex);
	}

	private class DLLIterator implements Iterator<T> {

		private Node<T> nextNode;
		private Node<T> toRemove;
		private int iterModCount;

		public DLLIterator() {
			nextNode = head;
			toRemove = null;
			iterModCount = modCount;
		}
	

		@Override
		public boolean hasNext() {

			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}

			if(nextNode == null) {
				return false;
			}
			return true;
		}

		@Override
		public T next() {

			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}

			if (hasNext()) {
				toRemove = nextNode;
				nextNode = nextNode.getNext();
				return toRemove.getElement();
			} else {
				throw new NoSuchElementException();
			}
		}

		public void remove() {

			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}

			if (toRemove == null) {
				throw new IllegalStateException();
			}
			
			if (!hasNext()) { // At the end
				if (toRemove.getPrevious() == null) { // Only element
					head = null;
					tail = null;
				} else {
					tail = toRemove.getPrevious();
					tail.setNext(null);
				}
			} else if (toRemove.getPrevious() == null) { // At the start
				head = toRemove.getNext();
			} else { // In the middle
				Node<T> prevNode = toRemove.getPrevious();
				prevNode.setNext(nextNode);
				nextNode.setPrevious(prevNode);
			}

			toRemove = null;	
		}
		
	}

	/** Iterator for IUSingleLinkedList */
	private class DLLListIterator implements ListIterator<T> {
		private Node<T> nextNode;
		private Node<T> previousNode;
		private int iterModCount;
		private int index;
		private int executionState; 
		//I think this is a (^^^^)possible useful value
		//its meant to track what was previously called
		//0 = standard operation
		//1 = next has been called
		//2 = previous has been called
		//3 = remove has been called


		/** Creates a new list iterator for the list */
		public DLLListIterator() {
			nextNode = head;
			previousNode = null;
			iterModCount = modCount;
			index = 0;
			executionState = 0;
		}

		/** Creates a new list iterator at a specific index */
		public DLLListIterator(int index) { //This constructor uses a starting index
			if(index < 0 || index >= size) {
				if(index == 0) {
					nextNode = head;
					previousNode = null;
				}else if(index == 1) {
					previousNode = head;
					nextNode = previousNode.getNext();
				}else {
					throw new IndexOutOfBoundsException();
				}
			}else {						//so the goal of this idea is to move the pointers
				Node<T> current = head;     //to the specific index
				int indexCounter = 0;       
				while(indexCounter != index) {	
					indexCounter++;
					current = current.getNext();
				}
				//System.out.println("The nextNode is: " + current.toString());
				nextNode = current;
				//System.out.println("The previousNode is: " + current.getPrevious().toString());
				previousNode = current.getPrevious();
			}
			iterModCount = modCount;
			this.index = index;
			executionState = 0;
		}

		/**
		 * This method determines if the list iterator has a next value
		 * @return boolean whether there is a next one
		 */
		@Override
		public boolean hasNext() {
			if(nextNode == null) {
				return false;
			}
			return true;
		}

		/**
		 * This method determines if the list iterator has a previous value
		 * @return boolean whether there is a previous one
		 */
		public boolean hasPrevious() {
			if(previousNode == null) {
				return false;
			}
			return true;
		}

		/**
		 * This method returns the element of the nextNode and iterates it to the next value
		 * @return <T> the element of the nextNode
		 */
		@Override
		public T next() {
			executionState = 1;
			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if(hasNext()) {
				previousNode = nextNode;
				nextNode = nextNode.getNext();
			}else {
				
				throw new NoSuchElementException();
			}
			index++;
			return previousNode.getElement();
		}

		/**
		 * This method removes an element after a previous call to the next method or the previous method and removes that element that was returned
		 */
		@Override
		public void remove() {
			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if(executionState == 1 || executionState == 2) {
				if(executionState == 1) { //this branch is when the next was called before
					if(size == 1) { //if the list has one element
						head = null; 
						tail = null;
					}else if(size == 2){ //if the list has two elements
						head = previousNode.getNext();
						previousNode = null;
						nextNode.setPrevious(null);
					}else {
						previousNode.getPrevious().setNext(nextNode);
						nextNode.setPrevious(previousNode.getPrevious());
						previousNode = previousNode.getPrevious();
					}
				}else { //this branch is when the previous was called before
					if(size == 1) {
						head = null;
						tail = null;
					}else if(size == 2) {
						tail = nextNode.getPrevious();
						nextNode = null;
						previousNode.setNext(null);
					}else {
						nextNode.getNext().setPrevious(previousNode);
						previousNode.setNext(nextNode.getNext());
						nextNode = nextNode.getNext();
					}
				}
			}else {
				throw new IllegalStateException();
			}
			size--;
			executionState = 3; //this is at the end because it needs to check its previous action first
			iterModCount++; //I belive these two lines will make it so list iterators cannot intervene
			modCount++;
		}

		/**
		 * This method returns the element of the previousNode and iterates it to the previous value
		 * @return <T> the element of the previousNode
		 */
		@Override
		public T previous() {
			executionState = 2;
			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if(hasPrevious()) {
				nextNode = previousNode;
				previousNode.getPrevious();
			}else {
				throw new NoSuchElementException();
			}
			index--;
			return nextNode.getElement();
		}

		/**
		 * This method returns the index of the next value
		 * @return int the index value of the next value
		 */
		@Override
		public int nextIndex() {
			if(!hasNext()) {
				return size;
			}

			return index + 1;
		}

		/**
		 * This method returns the index of the previous value
		 * @return int the index value of the previous value
		 */
		@Override
		public int previousIndex() {
			if(nextNode == head) {
				return -1;
			}

			return index - 1;
		}

		/**
		 * This method sets the current element that was previously called by the next or previous method
		 */
		@Override
		public void set(T e) {
			if(executionState == 1 || executionState == 2) {
				if(executionState == 1) {
					previousNode.setElement(e);
				}else {
					nextNode.setElement(e);
				}
			}else {
				throw new IllegalStateException();
			}
		}

		/**
		 * This method inserts a new value into the list between the current previousNode and nextNode by setting the previousNode
		 * to the new value and setting up all the previous and next iterative settings
		 */
		@Override
		public void add(T e) { 
			executionState = 0;
			Node<T> newData = new Node<T>(e);
			if(isEmpty()) {
				head = newData;
				tail = newData;
			} else if (nextNode == head) { //if the node is being added at the start
				previousNode = newData;
				previousNode.setNext(head);
				head.setPrevious(previousNode);
			} else if (previousNode == tail) { //if the node is being added to the end
				previousNode = newData;
				tail.setNext(previousNode);
				previousNode.setPrevious(tail);
				previousNode.setNext(nextNode);
				nextNode.setPrevious(previousNode);
			} else { //if it is adding somewhere in the middle of the list
				previousNode.setNext(newData);
				newData.setNext(nextNode);
				newData.setPrevious(previousNode);
				nextNode.setPrevious(newData);
				previousNode = previousNode.getNext();
			}

			size++;
			iterModCount++;
			modCount++;
		}
	}
}
