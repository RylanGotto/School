package entries;
import java.util.Scanner;




import comparisonObjects.CompareToDoList;
import dList.DoubleLinkList;
import dList.DoubleLinkListIterator;
import dList.xmList;

/**
 * all functionality related around managing a list of to do entries.
 * 
 * @author Rylan Gotto
 */
public class ToDoList {

	private DoubleLinkList<ToDoEntry> list;
	private DoubleLinkListIterator<ToDoEntry> listIt;
	private CompareToDoList comparator;
	private xmList stream;

	public ToDoList() {

		list = new DoubleLinkList<ToDoEntry>();
		comparator = new CompareToDoList();
		stream = new xmList();

	}
	/**
	 * returns true if user loaded list from file
	 * @return true if file name is set by user
	 */
	public boolean isFileNameEmpty(){
		
		boolean check = stream.isEmpty();
		
		return check;
	}

	/**
	 * return current state of link list
	 * @return current list
	 */
	public DoubleLinkList<ToDoEntry> getList() {

		return list;
	}

	public void setList(DoubleLinkList<ToDoEntry> list) {
		this.list = list;
	}

	public DoubleLinkListIterator<ToDoEntry> getListIt() {
		return listIt;
	}

	/**
	 * sets iterator to front of link list
	 * @param dll current state of link list 
	 */
	public void setListIt(DoubleLinkList<ToDoEntry> dll) {
		this.listIt = dll.getStartIterator(); //set iterator to start of list
	}

	/**
	 * check to see if list size is 0 and returns true if so
	 * @return true if list is empty
	 */
	public boolean isEmpty() {
		boolean check = false;
		if (list.getSize() == 0) {
			check = true;
		}
		return check;
	}

	/**
	 * Loads list from either file or creates new blank list
	 */
	public void loadList(){

	
		char choice;	
		boolean end = false;
		

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		while (!end) {
			System.out.println("a) Would you like to look for file? \n"
					+ "b) Start with blank list");

			choice = scan.next().toUpperCase().charAt(0);

			switch (choice) {

			case 'A':

				list = stream.readXmlFile();
				end = true;

				break;

			case 'B':
				end = true;
				break;
			default:
				System.out
						.print("Please make a correct choice A or B or a OR b");
				break;
			}

		}

	}

	/**
	 * removes node at user giving index
	 * @param index user giving input for node to remove
	 * @throws Exception if index does not exist
	 */
	public void remove(int index) throws Exception{
		if (index != 0) {
			index--;
		}
		list.removeAt(index);

	}

	/**
	 * adds to end of its resepctive priority list
	 * @param entry to do entry to add to list
	 */
	public void add(ToDoEntry entry) {
		list.addSortedEnd(entry, comparator); // adds item to list
		//takes ToDoList comparator to sort list

	}

	/**
	 * gets and returns data(entry) from user giving index
	 * @param index provided by user
	 * @return return entry from giving index
	 * @throws Exception if index is out of bounds
	 */
	public ToDoEntry get(int index) throws Exception{
		ToDoEntry data;

		data = list.get(index);

		return data;
	}

	/**
	 * Display contents of link list in a formatted string for good times
	 */
	public void display() {
		// accurate print out to console
		
		String format = ""; // string to hold format
		int printIndex = 1; // index for users refernce
		setListIt(list);//set iterator to start
		do {
			if (listIt.getdata().getPriority() == 1) {
				format = String.format("%d "  + "%s " + " - " + "%s", printIndex, "LOW", listIt.getdata().getDescription());
				System.out.println(format);
				
			} else if (listIt.getdata().getPriority() == 2) {
				format = String.format("%d " + "%s " + " - " + "%s", printIndex, "MED", listIt.getdata().getDescription());
				System.out.println(format);
			} else if (listIt.getdata().getPriority() == 3) {
				format = String.format("%d " + "%s " + "- " + "%s", printIndex, "HIGH", listIt.getdata().getDescription());
				System.out.println(format);
			}
			printIndex++;
		

		} while (listIt.moveToNext());

	}
	
	/**
	 * print contents of link list to file
	 * ask for file name if file name is empty
	 */
	public void printListToFile(){
		if(!isFileNameEmpty()){
			stream.printXmlFile(list, true);
		}else{
			stream.printXmlFile(list);
		}
	}

}
