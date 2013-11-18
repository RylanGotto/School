package tree;
/**
 * Basic Generic Binary Search Tree class.
 * 
 *  A Binary Search Tree is a node-based binary tree data structure which has the following properties:
 *         (1) The left subtree of a node contains only nodes with data that is less than the current node.
 *         (2) The right subtree of a node contains only nodes with data that is greater than the current node's.
 *         (3) The left and right subtree each must also be a binary search trees.
 *         (4) There must be no duplicate nodes.
 *         
 * @see http://en.wikipedia.org/wiki/Binary_search_tree
 * 
 * @author JKidney
 * @version 1 
 * 
 *      Created: Oct 17, 2013
 * Last Updated: Oct 17, 2013 - creation (jkidney)
 *               Oct 23, 2013 - implemented remove node method
 */
import tree.treeIterators.*;
import comparisonObjects.CompareObjects;

/*
 *  keytype = the type of the key that is used to store and retrieve data in the binary search tree
 * datatype = the type of the data being stored in the binary search tree
 */
public class BinarySearchTree<keytype, datatype>  
{
	private BinaryNode<keytype, datatype> root; // will always keep a reference to the root node of the tree
	private CompareObjects<keytype> comparator; // used to compare keys when adding and searching
	private int size;  // should always equal the number of nodes in the tree 

	/**
	 * Sets up the basics of the binary search tree
	 * @param comparator used to compare keys when adding and search the tree
	 */
	public BinarySearchTree(CompareObjects<keytype> comparator)
	{
		root = null;
		size = 0;
		this.comparator = comparator;
	}

	/**
	 * gives back the size of the list
	 * @return the number of nodes in the list
	 */
	public int getSize() { return size; }


	//CONSTANTS used when selecting a specific Traversal iterator 
	public static final int   PRE_TRAV  = 0; // PreOrder traversal
	public static final int    IN_TRAV  = 1; // InOrder traversal
	public static final int  POST_TRAV  = 2; // PostOrder traversal
	public static final int LEVEL_TRAV  = 3; // LevelOrder traversal

	/**
	 * Creates an iterator that can be used to do a specific traversal of a tree
	 * based upon the given type. Defaults to Level order if incorrect value is given
	 * @param type must be one of the constants provided by the class (PRE_TRAV,IN_TRAV,POST_TRAV,LEVEL_TRAV)
	 * @return the desired traversal iterator
	 */
	public BinaryTreeIterator<keytype, datatype> getTraversalIterator(int type)
	{
		//DO NOT CHANGE THIS METHOD
		BinaryTreeIterator<keytype, datatype> iter = null;

		switch(type)
		{
		case  PRE_TRAV: iter = new PreOrderTreeIterator<>(root); break;
		case   IN_TRAV: iter = new InOrderTreeIterator<>(root); break;
		case POST_TRAV: iter = new PostOrderTreeIterator<>(root); break;
		default:
			iter = new LevelOrderTreeIterator<>(root); break;  
		}

		return iter;
	}

	/**
	 * Adds the new data element to the binary search tree
	 *  
	 * Insertion begins as a search would begin (see the find method); if the key is not 
	 * equal to that of the root's key, we search the left or right subtrees as before. 
	 * Eventually, we will reach an external node and add the data as its right or left child, 
	 * depending on the node's key. In other words, we examine the root and recursively 
	 * (or iteratively) insert the new node to the left subtree if its key is less than 
	 * that of the root's key, or the right subtree if its key is greater than the root's key.
	 * if the key matches any keys in the tree we do not add it.
	 * 
	 * @see http://en.wikipedia.org/wiki/Binary_search_tree#Insertion
	 * @param key the key to use for comparisons on where to store the data in the tree
	 * @param data the new data to try to store in the tree
	 */

	public void add(keytype key, datatype data) {
		
		BinaryNode<keytype, datatype> newNode = new BinaryNode<keytype, datatype>(key, data);
		//Check to see if tree is not empty
		//if the tree is not empty call the insert method and pass in the root of the tree as well the new node to added to the tree
		if(root!=null){
			insert(newNode, root);
		}else{
		root = newNode;
		size++;
		}
	
	}
	/**
	 * Adds the new data element to the binary search tree
	 * 
	 * Insertion begins as a search would begin (see the find method); if the
	 * key is not equal to that of the root's key, we search the left or right
	 * subtrees as before. Eventually, we will reach an external node and add
	 * the data as its right or left child, depending on the node's key. In
	 * other words, we examine the root and recursively (or iteratively) insert
	 * the new node to the left subtree if its key is less than that of the
	 * root's key, or the right subtree if its key is greater than the root's
	 * key. if the key matches any keys in the tree we do not add it.
	 * @param newNode data provided by user to be added to the tree
	 * @param curr represents the curr node we are working with in the tree
	 */
	
	private void insert(BinaryNode<keytype, datatype> newNode, BinaryNode<keytype, datatype> curr) 
	{
		//Check to see if the newnodes key is less then the current nodes key
		
		if(comparator.compare(newNode.getKey(), curr.getKey()) < 0 )
				{
					if(!curr.hasLeftChild())//if the new nodes key is less than the currents nodes keys and the current node does not have a left child
						//set the newnode as the current nodes new left child and incremenet the size of the tree by one
					{
						curr.setLeftChild(newNode);
						size++;
					}else //if a left child does exist 
						//recurse to the left and pass in the left child as well as the newnode
						
					{
						insert(newNode, curr.getLeftChild());
					}
				}else if(comparator.compare(newNode.getKey(), curr.getKey()) > 0 )//if the new nodes key is less than the currents nodes keys and the current node does not have a left child
					//set the newnode as the current nodes new right child and incremenet the size of the tree by one
				{
					if(!curr.hasRightChild())//if a left child does exist 
						//recurse to the left and pass in the left child as well as the newnode
					{
						curr.setRightChild(newNode);
						size++;
					}else
					{
						insert(newNode, curr.getRightChild());
					}
				}
	}
	/**
	 * Determines if their is a node in the tree with the given key or not
	 * @param key the key to search for
	 * @return true if a node does exists , false otherwise
	 */
	public boolean containskey(keytype key)
	{
		return (find(key) != null);
	}
	
	/**
	 * Searches the tree for the given key, returns the fata from the first node that it finds
	 * with a matching key or null if not found.
	 *  
	 * We begin by examining the root node. If the tree is null, the data we are searching 
	 * for does not exist in the tree. Otherwise, if the data equals that of the root, the 
	 * search is successful. If the data is less than the root, search the left subtree. 
	 * Similarly, if it is greater than the root, search the right subtree. This process is 
	 * repeated until the data is found or the remaining subtree is null. If the searched data 
	 * is not found before a null subtree is reached, then the item must not be present in the tree.
	 * 
	 * @see http://en.wikipedia.org/wiki/Binary_search_tree#Searching
	 * @param key the key to use for comparisons on where to search for the data in the tree
	 * @return the found data object or null if it was not found in the tree
	 */
	public datatype find(keytype key)
	{
		datatype foundData = null;
		BinaryNode<keytype, datatype> foundNode = findNode(key);

		if(foundNode != null) foundData = foundNode.getData();

		return foundData;
	}
	

	/**
	 * helper method that searches the tree for the given data, returns the
	 * first node that it finds
	 * 
	 * @param key
	 *            the key to use for comparisons on where to search for the data
	 *            in the tree
	 * @return the found node or null if it was not found in the tree
	 **/
	private BinaryNode<keytype, datatype> findNode(keytype key)
	{
		boolean end = false;
		BinaryNode<keytype, datatype> curr = root;

		if(root != null) // tree is not empty so we can search for data
		{
			while(!end)
			{
				int cmpResult = comparator.compare(curr.getKey(), key);

				switch(cmpResult)
				{
				case 0: // they are the same so search has found the data
					end = true; 
					break; 
				case 1: // data is smaller than the current node
					curr = curr.getLeftChild();
					break;
				case -1: // data is larger than the current node
					curr = curr.getRightChild();
					break;

				}

				if(curr == null) // went past a leaf node so the data was not found
					end = true;
			}

		}

		return curr;
	}

	/**
	 * Removes a specific node from the tree that has a matching key value
	 * 
	 *  There are five possible cases to consider:
	 *      (1) the tree is empty ( root == null )
	 *      (2) only one node in the tree ( root = null )
	 *      (3) Deleting a leaf (node with no children): 
	 *                Deleting a leaf is easy, as we can simply remove it from the tree.
	 *      (4) Deleting a node with one child: 
	 *                Remove the node and replace it with its child.
	 *      (5) Deleting a node with two children: 
	 *                Call the node to be deleted N. Do not delete N. Instead, choose its 
	 *                in-order successor (R) . Replace the value of N with the value of R, then delete R.
	 *
	 *       As with all binary trees, a node's in-order successor is the left-most child of its right subtree.
	 * 
	 * @see http://en.wikipedia.org/wiki/Binary_search_tree#Deletion
	 * 
	 * @param key the key to use for the search
	 * @return the data at the node or null if no such node found
	 */
	public datatype remove(keytype key)
	{
		datatype data =  null;
		boolean removed = false;
		if(root != null)
		{

			BinaryNode<keytype, datatype> foundNode = findNode(key);

			if(foundNode != null) // we did find the node
			{
				data = foundNode.getData();
				removed = removeNodeFromTree(foundNode);
			}
		}

		if(removed) size--;
		return data;	
	}
	
	/**
	 * Removes the specified single node from the tree
	 * @param node the node to remove
	 */
	private boolean removeNodeFromTree(BinaryNode<keytype, datatype> node)
	{
		boolean removed = false;
		
		if(node != null) // we did find the node
		{
			if(node.isLeaf())
			{
				node.unLinkFromParent();
				if(node == root) root = null;
				removed = true;
			}// only one child, so propagate it to the role of parent 
			else if(node.childrenCount() == 1) 
			{	
				BinaryNode<keytype, datatype> childNode = null;	
				if(node.hasLeftChild()) childNode = node.getLeftChild();
				else childNode = node.getRightChild();
				node.replaceWith(childNode);
				removed = true;
			}// Two children ( we need to replace the current one with the in-order successor )
		     // once it has been properly removed from the tree
			else
			{
				BinaryNode<keytype, datatype> successor = getInOrderSuccessor(node);
				node.setData(successor.getData());
				node.setKey(successor.getKey());
				removed = true;
			}
			
		}
		
		return removed;
	}
	/**
	 * Returns the in order successor of the given node (removes it from the location it was found)
	 * @param node the node to get the successor from
	 * @return the successor , null if something went wrong
	 */
	private BinaryNode<keytype, datatype> getInOrderSuccessor(BinaryNode<keytype, datatype> node)
	{
		BinaryNode<keytype, datatype> successor = null;
		
		if(node != null)
		{
			//go down one to the right
			successor = node.getRightChild();
			
			//now go as far down left as is possible
			if(successor != null)
			{
	            while(successor.hasLeftChild())
	            	successor = successor.getLeftChild();
	            
	            
	            //now remove it from the tree
	            if(successor.isLeaf())
	            	successor.unLinkFromParent();
	            else // it has one child
	            {
	            	BinaryNode<keytype, datatype> childNode = null;	
	            	if(successor.hasLeftChild())
	            	{
	                    childNode = successor.getLeftChild();
	                    successor.getParent().setLeftChild(childNode);
	                    successor.setLeftChild(null);
	            	}
	            	else
	            	{
	                    childNode = successor.getRightChild();
	                    successor.getParent().setRightChild(childNode);
	                    successor.setRightChild(null);
	            	}
	            }
			}
		}
		return successor;
	}
	/**
	 * prints the tree nodes to the console/standard output using the specified traversal
	 * @param type must be one of the constants provided by the class (PRE_TRAV,IN_TRAV,POST_TRAV,LEVEL_TRAV)
	 */
	public void printAllNodes(int type)
	{
		print(type, PRINT_NODE);
	}
	/**
	 * prints the just the key from each node to the console/standard output using the specified traversal
	 * @param type must be one of the constants provided by the class (PRE_TRAV,IN_TRAV,POST_TRAV,LEVEL_TRAV)
	 */
	public void printJustKeys(int type)
	{
		print(type, PRINT_KEY);
	}

	/**
	 * prints the just the data values from each node to the console/standard output using the specified traversal
	 * @param type must be one of the constants provided by the class (PRE_TRAV,IN_TRAV,POST_TRAV,LEVEL_TRAV)
	 */
	public void printJustData(int type)
	{
		print(type, PRINT_DATA);
	}

	//constants used for the print
	private static final int PRINT_NODE = 0; // print out the entire node
	private static final int PRINT_KEY = 1; // print out just the key
	private static final int PRINT_DATA = 2; // print out just the data

	/**
	 * Helper method used to print out the tree with the given traversal
	 * @param type the type of traversal
	 * @param valueType the type of data output
	 */
	private void print(int type, int valueType)
	{
		BinaryTreeIterator<keytype, datatype> iter = getTraversalIterator(type);
		System.out.print("TREE[ ");
		while(iter.canMoveToNext())
		{

			String dataOutPut = "";
			switch(valueType)
			{
			case PRINT_NODE:  dataOutPut = iter.nodeToString(); break;
			case PRINT_DATA:  dataOutPut = ""+iter.getCurrentData(); break;
			default:  dataOutPut = ""+iter.getCurrentKey(); break;
			}

			System.out.print("("+dataOutPut+")");

			iter.moveToNext();

			if(iter.canMoveToNext())
				System.out.print(" , ");
		}
		System.out.println(" ]");

	}

}
