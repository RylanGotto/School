package tree.treeIterators;
import java.util.Stack;

/**
 * Used as a wrapper around a binary node object for easy moving through a binary tree.
 * 
 * This iterator follows an PreOrder traversal of the Binary Tree
 * 
 *  Recursive algorithm:
 *      inorder(node)
 *           if node == null then return
 *           visit(node)
 *           inorder(node.left)
 *           inorder(node.right)
 * 
 *Iterator algorithm:
 *        iterativePreorder(node)
 *          parentStack = empty stack
 *          while not parentStack.isEmpty() or node != null
 *             if node != null then
 *                 visit(node)
 *                 if node.right != null then
 *                     parentStack.push(node.right)
 *                 node = node.left
 *             else
 *                 node = parentStack.pop()
 * 
 * @see http://en.wikipedia.org/wiki/Tree_traversal#Depth-first_2
 * @author jkidney
 * @version 1 
 * 
 *      Created: Oct 17, 2013
 * Last Updated: Oct 17, 2013 - creation (jkidney)
 */
import tree.BinaryNode;

public class PreOrderTreeIterator<keytype, datatype> extends BinaryTreeIterator<keytype, datatype> 
{

	private Stack< BinaryNode<keytype, datatype>  > nodeStack = new Stack<>();
	
    /**
     * Constructor 
     * @param current the node to start the traversal from
     */
	public PreOrderTreeIterator(BinaryNode<keytype, datatype> current) 
	{
		super(current);
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
	       
			if(current != null)
			{
				if(current.hasRightChild())
					nodeStack.push(current.getRightChild() );
				
				current = current.getLeftChild();
			}
			
			
			if(current == null && !nodeStack.isEmpty())
				        current = nodeStack.pop();
			
			move = true;	
		}
		return move;
	}
	
	/**
	 * Determines if the iterator has hit the end of the traversal or not
	 * @return true if the iterator did not hit the end of the traversal, false otherwise
	 */
	public boolean canMoveToNext() 
	{
		return (!nodeStack.isEmpty() || current != null);
	}

}
