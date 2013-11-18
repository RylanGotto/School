package tree.treeIterators;
import java.util.ArrayList;





/**
 * Used as a wrapper around a binary node object for easy moving through a binary tree.
 * 
 * This iterator follows an PostOrder traversal of the Binary Tree
 * 
 *  Recursive algorithm:
 *      inorder(node)
 *           if node == null then return
 *           inorder(node.left)
 *           inorder(node.right)
 *           visit(node)
 * 
 *Iterator algorithm:
 *         iterativePostorder(node)
 *               if node == null then return
 *               nodeStack = empty stack
 *               nodeStack.push(node)
 *               prevNode = null
 *               while not nodeStack.isEmpty()
 *                      currNode = nodeStack.peek()
 *                      if prevNode == null or prevNode.left == currNode or prevNode.right == currNode then
 *                               if currNode.left != null then
 *                                    nodeStack.push(currNode.left)
 *                               else if currNode.right != null then
 *                                    nodeStack.push(currNode.right)
 *                      else if currNode.left == prevNode then
 *                              if currNode.right != null then
 *                                  nodeStack.push(currNode.right)
 *                      else
 *                        visit(currNode)
 *                        nodeStack.pop()
 *                      prevNode = currNode
 * 
 * @see http://en.wikipedia.org/wiki/Tree_traversal#Depth-first_2
 * @author jkidney
 * @version 1 
 * 
 *      Created: Oct 17, 2013
 * Last Updated: Oct 17, 2013 - creation (jkidney)
 */
import tree.BinaryNode;

public class PostOrderTreeIterator<keytype,datatype> extends BinaryTreeIterator<keytype,datatype> {
	private ArrayList< BinaryNode<keytype, datatype>  > nodes = new ArrayList<BinaryNode<keytype, datatype>>();

	/**
	 * Constructor 
	 * @param current the node to start the traversal from
	 */
	public PostOrderTreeIterator(BinaryNode<keytype, datatype> current) 
	{
		super(current);
		runRecursivePostorder(current);
		loadFirst();
	}
	/**
	 * helper method that runs a full recursive in order traversal of the tree
	 * and builds a list of the visited nodes
	 */
	private void runRecursivePostorder(BinaryNode<keytype, datatype> node)
	{
		if(node!= null)
		{
			if(node.hasLeftChild())
				runRecursivePostorder(node.getLeftChild());

			if(node.hasRightChild())
				runRecursivePostorder(node.getRightChild());

			nodes.add(node);
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
