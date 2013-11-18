import tree.BinarySearchTree;

import comparisonObjects.StringCaseSensitiveCompare;


public class TestName {

	public static void main(String[] args) {
		
		StringCaseSensitiveCompare stringComp = new StringCaseSensitiveCompare();
		BinarySearchTree<String,String> bSt = new BinarySearchTree<String,String>(stringComp);
		
		
		bSt.add("f","123");
		bSt.add("d","123");
		bSt.add("k","123");
		bSt.add("a","123");
		bSt.add("o","123");
		bSt.add("g","123");
		bSt.add("e","123");
		
		
		
	
		
		bSt.printAllNodes(3);

	}

}
