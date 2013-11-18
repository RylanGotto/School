package dList;
/**
 * Used as a raper around a node object for easy moving through a list.
 * If the list is not emptu this iterator can move forwards and backwards
 * in the this and will never exit it.
 * @author JKidney
 * @param <type>
 */
public class DoubleLinkListIterator<type> 
{
	private Node<type> current;

	/**
	 * Constructor
	 * @param current the starting node for the iterator to work with
	 */
	public DoubleLinkListIterator(Node<type> current)
	{
		this.current = current;
	}


	/**
	 * Moves the iterator one step forwards in the list. When it hits the end it will not
	 * move past the end of the list.
	 * @return true if the iterator did not hit the end of the list, false otherwise
	 */
	public boolean moveToNext()
	{
		boolean result = false;

		if(current != null)
		{
			if(current.getNext() != null)
			{
				current = current.getNext();
				result = true;
			}
		}

		return result;
	}



	/**
	 * Moves the iterator one step backwards in the list. When it hits the end it will not
	 * move past the start of the list.
	 * @return true if the iterator did not hit the start of the list, false otherwise
	 */
	public boolean moveToPrev()
	{
		boolean result = false;

		if(current != null)
		{
			if(current.getPrev() != null)
			{
				current = current.getPrev();
				result = true;
			}

		}
		return result;
	}
	/**
	 * returns the data at the current node
	 * @return¨the data
	 */
	public type getdata()
	{
		type data = null;

		if(current != null) data = current.getData();

		return data;
	}
}
