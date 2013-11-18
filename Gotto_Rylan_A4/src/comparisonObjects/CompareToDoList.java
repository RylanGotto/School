package comparisonObjects;
import entries.ToDoEntry;



public class CompareToDoList implements CompareObjects<ToDoEntry>{

	@Override
	public int compare(ToDoEntry obj1, ToDoEntry obj2) 
	{
		int result = 0;
		
		if(obj1.getPriority() > obj2.getPriority() ) result = 1;
		else if(obj1.getPriority() == obj2.getPriority() ) result = 0;
		else if (obj1.getPriority() < obj2.getPriority() ) result = -1;
		
		return result;
	}

}
