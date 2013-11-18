package dList;
/**
 * Basic Generic Node class used to hold data in a Link List
 * @author JKidney
 * @param <type>
 */
public class Node<type> 
{
	private type data;
	private Node<type> next;
	private Node<type> prev;
	
	public Node() { next=prev=null; }

	/**
	 * Constructor used to insert data at the time of creation
	 * @param data
	 */
	public Node(type data)
	{
		super();
		this.data = data;
	}

	public type getData() { return data; }
	public void setData(type data) { this.data = data; }

	public Node<type> getNext() { return next; }
	public void setNext(Node<type> next) { this.next = next; }

	public Node<type> getPrev() { return prev; } //poop :D
	public void setPrev(Node<type> prev) { this.prev = prev; }
}

