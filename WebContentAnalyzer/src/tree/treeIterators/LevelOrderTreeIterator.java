package tree.treeIterators;
import java.util.ArrayList;





/**
 * Used as a wrapper around a binary node object for easy moving through a binary tree.
 * 
 * This iterator follows a Level order traversal of the Binary Tree ( also called  Breadth-first)
 * 
 *         Goes through each node in a single level before moving on to the next level. Traversal starts at 
 *         level 1.
 *         
 *         basic algorithm 
 *         
 *            q = empty queue
 *            q.enqueue(root)
 *            while not q.empty do
 *            node := q.dequeue()
 *            visit(node)
 *            if node.left != null then
 *              q.enqueue(node.left)
 *            if node.right != null then
 *              q.enqueue(node.right)
 * 
 * @see http://en.wikipedia.org/wiki/Tree_traversal#Breadth-first
 * @author jkidney
 * @version 1 
 * 
 *      Created: Oct 17, 2013
 * Last Updated: Oct 17, 2013 - creation (jkidney)
 */
import tree.BinaryNode;

public class LevelOrderTreeIterator<keytype,datatype> extends BinaryTreeIterator<keytype,datatype>
{

	//using the operations of an array list to implement a queue data structure
	// add() = enqueue
	// if(!IsEmpty()) remove(0) = dequeue
	private ArrayList< BinaryNode<keytype, datatype> > nodeQueue = new ArrayList< BinaryNode<keytype, datatype> >();
	private void enqueueNode( BinaryNode<keytype, datatype> node) { nodeQueue.add(node); }
	private BinaryNode<keytype, datatype> deQueueNode()
	{
		BinaryNode<keytype, datatype> node = null;
		
		if(!nodeQueue.isEmpty())
			node = nodeQueue.remove(0);
		
		return node;
	}
	
    /**
     * Constructor 
     * @param current the node to start the traversal from
     */
	public LevelOrderTreeIterator(BinaryNode<keytype, datatype> current) 
	{
		super(current);
		addChildren(current);
	}

	/**
	 * Moves the iterator to the next step in the tree traversal. When the end of the
	 * traversal has been reached the method will return null.
	 * @return true if the iterator did not hit the end of the traversal, false otherwise
	 */
	public boolean moveToNext() 
	{
		boolean move = false;
		
		if(canMoveToNext())
		{
		  move = true;
		  
		  //move to the next node
		  current = deQueueNode();
		  
		  addChildren(current);
		  
		}
		else if(current != null)
		{
			current = null;
		}
		
		return move;
	}

	/**
	 * Adds any children of the current node to the queue
	 * @param node the node to et the children from
	 */
	private void addChildren(BinaryNode<keytype, datatype> node)
	{
		if(node != null)
		{
		  //add children of the current node to the queue to process later
		  if(node.hasLeftChild()) 
			  enqueueNode( current.getLeftChild() );
		  if(node.hasRightChild()) 
			  enqueueNode( current.getRightChild() );
		}
	}
	
	/**
	 * Determines if the iterator has hit the end of the traversal or not
	 * @return true if the iterator did not hit the end of the traversal, false otherwise
	 */
	public boolean canMoveToNext() 
	{
		return (!nodeQueue.isEmpty() || current != null);
	}
}
