package dList;

import comparisonObjects.CompareObjects;

public class DoubleLinkList<type> {
	private Node<type> start; // will always keep a reference to the first node
								// in the list
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
	public void addSorted(type data, CompareObjects<type> comparator)
    {
        Node<type> newNode = new Node<type>(data);
        if (start == null)
        {
            addToFront(newNode.getData());
        }
        else
        {
            Node<type> curr = start;
            while(comparator.compare(newNode.getData(),curr.getData()) == 1 && curr.getNext() != null)
            {
                curr = curr.getNext();
            }
            if(curr == start)
            {
                addToFront(newNode.getData());
            }
            else if(curr == end && comparator.compare(newNode.getData(),curr.getData()) == 1)
            {
                addToEnd(newNode.getData());
            }
            else
            {
                Node <type> change = curr.getPrev();
                change.setNext(newNode); 
                newNode.setPrev(change);
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

		  if(index >= size || index < 0)
	        {
	            
	                throw new Exception();
	            
	        }
		if (start == null) {

			throw new Exception();

		}
		
		curr = start;
		for (int i = 0; i < index; i++) {

			curr = curr.getNext();

		}	

		result = curr.getData();
		

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
	public type removeAt(int index) throws Exception
    {
        //TODO: Complete this method 
        type result = null;

        //node used for finding the node that we want ro remove
        Node <type> curr = new Node();

        //curr is set to start node
        curr = start;

        //making sure that the entered index is within the bounds of the list
        //if the integer is not in bounds we throw an exception (size starts at 1 and index starts at 0)
        if(index >= size || index < 0)
        {
            
                throw new Exception();
            
        }

        //0 node(s)
        if(curr == null)
        {
            throw new Exception();
        }

        //1 node(s)
        else if(size == 1)
        {
            //we get the node
            result = get(index);

            //no nodes to refer to anymore
            start = end = null;
            

            //size is decreased to 0
            size--;
        }
        //more than 1 node(s)
        else if(curr != null && end != null && size >= 0)
        {
            //temp counter
            int e = 0;

            //while there is a node to read next node and we have not reached our wanted index
            while(curr.getNext() != null && e != index)
            {    
                curr = curr.getNext();
                e++;
            }

            //if the node is not the start or end node
            if (curr != start && curr != end)
            {
                //data is assigned
                result = curr.getData();

                //temp previous variable
                Node <type> prev = null;

                //working with the previous node that came before the node we are removing
                prev = curr.getPrev();

                //temp next node variable
                Node <type> next = null;

                //working with the node that comes after the node we are removing
                next = curr.getNext();

                //linking the nodes together doubly
                prev.setNext(next);
                next.setPrev(prev);

                //size is decreased
                size--;
            }

            //if the node is the start node and there is more than 1 node in the list
            else if (curr == start && size > 1)
            {
                //the data we want is assigned
                result = curr.getData();

                //the start is moved one link down because we do not need to referance it anymore because its being deleted
                start = curr.getNext();

                //old start node is set to null
                curr.setNext(null);

                //size is decreased
                size--;
            }

            //if the node is the end and there is more than 1 node in the list
            else if (curr == end && size > 1)
            {
                //the data we want is assigned
            	result = curr.getData();

                //the end is moved back one spot
                end = curr.getPrev();
                
                //there is no node after the end so we set the nest node to null
                end.setNext(null);

                //old end is removed from memory
                curr.setNext(null);

                //size is decreased
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
