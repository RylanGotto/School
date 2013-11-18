package tree.treeIterators;
import java.util.ArrayList;



/**
 * Used as a wrapper around a binary node object for easy moving through a binary tree.
 * 
 * This iterator follows an InOrder traversal of the Binary Tree
 * 
 *  Recursive algorithm:
 *      inorder(node)
 *           if node == null then return
 *           inorder(node.left)
 *           visit(node)
 *           inorder(node.right)
 * 
 *Iterator algorithm:
 *   iterativeInorder(node)
 *        parentStack = empty stack
 *        while not parentStack.isEmpty() or node != null
 *          if node != null then
 *             parentStack.push(node)
 *             node = node.left
 *          else
 *            node = parentStack.pop()
 *            visit(node)
 *            node = node.right
 * 
 * @see http://en.wikipedia.org/wiki/Tree_traversal#Depth-first_2
 * @author jkidney
 * @version 1 
 * 
 *      Created: Oct 17, 2013
 * Last Updated: Oct 17, 2013 - creation (jkidney)
 */
import tree.BinaryNode;

public class InOrderTreeIterator<keytype,datatype> extends BinaryTreeIterator<keytype,datatype>
{
	private ArrayList< BinaryNode<keytype, datatype>  > nodes = new ArrayList<BinaryNode<keytype, datatype>>();

	/**
	 * Constructor 
	 * @param current the node to start the traversal from
	 */
	public InOrderTreeIterator(BinaryNode<keytype, datatype> current) 
	{
		super(current);
		runRecursiveInorder(current);
		loadFirst();
	}
	/**
	 * helper method that runs a full recursive in order traversal of the tree
	 * and builds a list of the visited nodes
	 */
	private void runRecursiveInorder(BinaryNode<keytype, datatype> node)
	{
		if(node!= null)
		{
			if(node.hasLeftChild())
				runRecursiveInorder(node.getLeftChild());

			nodes.add(node);

			if(node.hasRightChild())
				runRecursiveInorder(node.getRightChild());
		}
	}
	/**
	 * Loads the first node in the nodes list into current, 
	 * sets current to null if not possible
	 */
	private void loadFirst()
	{
		if(!nodes.isEmpty())
		{
			current = nodes.remove(0);
		}
		else
			current = null;
	}
	/**
	 * Moves the iterator to the next step in the tree traversal. When the end of the
	 * traversal has been reached the method will return null.
	 * @return true if the iterator did not hit the end of the traversal, false otherwise
	 */
	public boolean moveToNext() 
	{
		boolean move = false;

		loadFirst();

		if(current != null)
			move = true;

		return move;
	}
	/**
	 * Determines if the iterator has hit the end of the traversal or not
	 * @return true if the iterator did not hit the end of the traversal, false otherwise
	 */
	public boolean canMoveToNext() 
	{
		return (!nodes.isEmpty() || current != null);
	}
}
