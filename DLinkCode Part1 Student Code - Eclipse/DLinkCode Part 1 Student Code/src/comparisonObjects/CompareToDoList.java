package comparisonObjects;



public class CompareToDoList implements CompareObjects<ToDoList>{

	@Override
	public int compare(ToDoEntry s1, ToDoEntry s2) {

		int len1 = s1.getPriority();
		int len2 = s2.getPriority();

		if (len1 < len2) { // comparing for Collections.sort
			return 1;
		} else if (len1 > len2) {// comparing for Collections.sort
			return -1;
		}
		return 0;

	}

}
