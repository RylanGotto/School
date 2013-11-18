import comparisonObjects.*;

import dList.*;


public class ExampleUseStringList {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println("=========== add Front ============");
		DoubleLinkList<String> storage = new DoubleLinkList<String>();
		storage.addToFront("Hi");
		storage.addToFront("Bye");
		storage.print();

		System.out.println("=========== add End =============");
		storage.addToEnd("Next");
		storage.addToEnd("Cat");
		storage.print();

		System.out.println("=========== get ================");
		try
		{
			for(int index=0; index < storage.getSize(); index++)
				System.out.println(storage.get(index));
		}
		catch(Exception ex) {}

		
		System.out.println("=========== removeAt ============");
		try
		{
			storage.removeAt(0);
			storage.removeAt(1);
			storage.print();
		}
		catch(Exception ex) {}
		//Create a list where all elements are always inserted sorted
		DoubleLinkList<String> list = new DoubleLinkList<String>();
		String[] words = { "B" , "A" , "Z", "Apple", "Dog", "dog", "Cat" };
		
		//StringCaseSensitiveCompare comparator = new StringCaseSensitiveCompare();
		StringNonCaseSensitiveCompare comparator = new StringNonCaseSensitiveCompare();
		
		System.out.println("=========== Sorted Add ===========");
		for(int count=0; count <  words.length ; count++)
		{
			list.addSorted(words[count], comparator);
			list.print();
		}

		System.out.println("=========== Iterator ============");
		//Gets an iterator to the start of the list
		DoubleLinkListIterator<String> iter = list.getStartIterator();

		//moves the iterator to the end of the list
		do
			System.out.print("["+iter.getdata()+"]");
		while(iter.moveToNext());

		System.out.println();	

		//moves the iterator now from the end of the list to the start of the list
		do
			System.out.print("["+iter.getdata()+"]");
		while(iter.moveToPrev());
		System.out.println();	
	}
}
