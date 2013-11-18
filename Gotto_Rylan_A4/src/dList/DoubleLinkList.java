package dList;

import comparisonObjects.CompareObjects;

public class DoubleLinkList<type> {
	private Node<type> start; // will always keep a reference to the first node
								// // in the list
	private Node<type> end; // will always keep a reference to the last node in
							// the list
	private int size;

	/**
	 * default constructor
	 */
	public DoubleLinkList() {
		start = end = null;
		size = 0;
	}

	/**
	 * gives back the size of the list
	 * 
	 * @return the number of nodes in the list
	 */
	public int getSize() {
		return size;
	}

	/**
	 * creates an Iterator object that begins at the start of the list
	 * 
	 * @return the iterator object
	 */
	public DoubleLinkListIterator<type> getStartIterator() {
		// DO NOT CHANGE THIS METHOD
		return new DoubleLinkListIterator<type>(start);
	}

	/**
	 * creates an Iterator object that begins at the end of the list
	 * 
	 * @return the iterator object
	 */
	public DoubleLinkListIterator<type> getEndIterator() {
		// DO NOT CHANGE THIS METHOD
		return new DoubleLinkListIterator<type>(end);
	}

	/**
	 * This method will create a new node to contain the data and add it to the
	 * front of the list
	 * 
	 * @param data
	 *            the data element to add to the start of the list
	 */
	public void addToFront(type data) {
		Node<type> newNode = new Node<type>(data);

		if (start == null) {
			start = end = newNode;
		} else {
			Node<type> front = start;

			front.setPrev(newNode);
			newNode.setNext(front);

			start = newNode;
		}
		size++;
	}

	/**
	 * This method will create a new node to contain the data and add it to the
	 * end of the list You should try to do this add in O(1)
	 * 
	 * @param data
	 *            the data element to add to the end of the list
	 */
	public void addToEnd(type data) {

		Node<type> newNode = new Node<type>(data);
		Node<type> curr = end;
		if (start == null) {
			start = end = newNode;
		} else {

			curr.setNext(newNode);
			newNode.setPrev(curr);

			end = newNode;
		}
		size++;
	}

	/**
	 * This method will create a new node to contain the data and add it sorted
	 * into the list. It should apply an insertion sort step to place the node
	 * properly in the list. The first node should be the smallest and the end
	 * node should be the largest. Note: This method only works if all
	 * insertions to the list are done using this method rather then the other
	 * add methods.
	 * 
	 * @param data
	 *            the data element to add to the list
	 * @param comparator
	 *            the compare object used to determine the proper insertion
	 *            point
	 * @see CompareObjects
	 */
	public void addSorted(type data, CompareObjects<type> comparator) {// adds
																		// to
																		// front
																		// priority
																		// list
		Node<type> next;
		Node<type> newNode = new Node<type>(data);
		if (start == null) {// add to empty list
			addToFront(newNode.getData());
		} else {
			Node<type> curr = start;
			while (comparator.compare(newNode.getData(), curr.getData()) == 1
					&& curr.getNext() != null) {
				curr = curr.getNext();
			}
			if (curr == start) {
				addToFront(newNode.getData());
			} else if (curr == end
					&& comparator.compare(newNode.getData(), curr.getData()) == 1) {
				addToEnd(newNode.getData());
			} else {
				next = curr.getPrev();
				next.setNext(newNode);
				newNode.setPrev(next);
				newNode.setNext(curr);
				curr.setPrev(newNode);
				size++;
			}

		}
	}

	public void addSortedEnd(type data, CompareObjects<type> comparator) {// adds
																			// to
																			// the
																			// end
																			// of
																			// priority

		Node<type> next;
		Node<type> newNode = new Node<type>(data);

		if (start == null) { // add to empty list

			addToFront(newNode.getData());

		} else {
			Node<type> curr = start;
			while (comparator.compare(newNode.getData(), curr.getData()) != 1
					&& curr.getNext() != null) {// iterate to end of priority
												// list well currents next is
												// not null
				curr = curr.getNext();
			}
			if (curr == start) {
				addToFront(newNode.getData());
			} else if (curr == end
					&& comparator.compare(newNode.getData(), curr.getData()) != 1) {// current
																					// node
																					// is
																					// at
																					// end
																					// add
																					// new
																					// to
																					// between
																					// end
																					// and
																					// current
				addToEnd(newNode.getData());
			} else {
				next = curr.getPrev();
				next.setNext(newNode);
				newNode.setPrev(next);
				newNode.setNext(curr);
				curr.setPrev(newNode);

				size++;
			}

		}
	}

	/**
	 * Retrieves the data stored at the given index/node in the list. The first
	 * node is considered to be stored at index 0
	 * 
	 * @param index
	 *            the index to get data from
	 * @return the data element stored at the given location if the index is
	 *         within the bounds
	 * @throws Exception
	 *             if the index is out of bounds or the list is empty
	 */
	public type get(int index) throws Exception {

		Node<type> curr = null;
		type result = null;
		int e = 0;

		if (index >= size || index < 0)// make sure that the user input is > 0
										// and user input is not > that the size
										// of the list
		{

			throw new Exception();

		}
		if (start == null) {// throw exception if list is empty

			throw new Exception();

		}

		curr = start;
		for (int i = 0; i < index; i++) {// iterate to user input index

			curr = curr.getNext();

		}

		result = curr.getData();// get ToDoEntry object for retyurn

		return result;

	}

	/**
	 * Removes the data stored at the given index/node in the list. The first
	 * node is considered to be stored at index 0. The data from this index will
	 * be removed.
	 * 
	 * @param index
	 *            the index to get data/node to remove
	 * @return the data element stored at the given location if the index is
	 *         within the bounds
	 * @throws Exception
	 *             if the index is out of bounds or the list is empty
	 */
	public type removeAt(int index) throws Exception {
		type result = null;
		Node<type> prev;
		Node<type> next;
		Node<type> curr = start;

		if (index >= size || index < 0) {// make sure that the user input is > 0
											// and user input is not > that the
											// size of the list

			throw new Exception("Out of bounds yo");
		}
		if (curr == null) {
			throw new Exception("Nothing to remove");
		} else if (size == 1) { // check to see if only one node is present
			result = get(index);
			start = end = null;
			size--;
		} else if (curr != null && end != null && 0 < size) { // check to see if
																// there is
																// multiple node
																// in the list
			int currIndex = 0;
			while (curr.getNext() != null && currIndex < index) { // iterate to
																	// given
																	// index or
																	// end of
																	// the lst
				curr = curr.getNext();
				currIndex++;
			}
			if (curr != start && curr != end) {
				result = curr.getData();// get ToDoEntry for return
				prev = curr.getPrev();
				next = curr.getNext();
				prev.setNext(next);
				next.setPrev(prev);
				size--;
			} else if (curr == start && size >= 1) {// multiple nodes in the
													// list remove at start

				result = curr.getData();
				start = curr.getNext();
				curr.setNext(null);
				size--;
			} else if (curr == end && size > 1) {// multiple nodes in the list
													// remove at end
				result = curr.getData();
				end = curr.getPrev();
				end.setNext(null);
				curr.setNext(null);
				size--;
			}
		}

		return result;
	}

	/**
	 * prints the list to console/standard output from the first node to the
	 * last node
	 */
	public void print() {
		Node<type> curr = start;

		System.out.print("Start->");

		while (curr != null) {
			System.out.print("[" + curr.getData() + "]->");
			curr = curr.getNext();
		}

		System.out.println("null");
	}

}
