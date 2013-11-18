package comparisonObjects;


public class StringNonCaseSensitiveCompare implements CompareObjects<String> 
{
	@Override
	public int compare(String obj1, String obj2) 
	{
		int cmpNum = obj1.toLowerCase().compareTo(obj2.toLowerCase());
		int result = 0;
		
		if( cmpNum < 0 ) result = -1;
		else if(cmpNum == 0) result = 0;
		else if (cmpNum > 0  ) result = 1;
		
		return result;
	}

}
