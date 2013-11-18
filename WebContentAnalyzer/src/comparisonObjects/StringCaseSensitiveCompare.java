package comparisonObjects;


public class StringCaseSensitiveCompare implements CompareObjects<String> 
{
	@Override
	public int compare(String obj1, String obj2) 
	{
		return obj1.compareTo(obj2);
	}

}
