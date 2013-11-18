package dList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.*;

import entries.ToDoEntry;

/**
 * xstream class handles all reading and writing from xstream
 * @author rylan
 */
/**
 * @author rylan
 * 
 */
public class xmList {
	private String fileName;
	private DoubleLinkList<ToDoEntry> storage;

	public xmList() {

		fileName = ""; // hold user giving file name
	}

	/**
	 * prints contents of list to xml file whille asking for a file name
	 * 
	 * @param entry
	 *            list is passed in to be wrote to xml file
	 */
	public void printXmlFile(DoubleLinkList<ToDoEntry> entry) {
		XStream xstream = new XStream(new StaxDriver());
		fileName = nameFile();
		DoubleLinkList<ToDoEntry> storage = entry;

		try {
			PrintStream outFile = new PrintStream(
					new FileOutputStream(fileName));
			outFile.print(xstream.toXML(storage));

			outFile.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	/**
	 * prints contents of list to xml file while using preset filename
	 * 
	 * @param entry
	 *            list is passed in to be wrote to xml file
	 */
	public void printXmlFile(DoubleLinkList<ToDoEntry> entry, boolean check) {
		XStream xstream = new XStream(new StaxDriver());
		DoubleLinkList<ToDoEntry> storage = entry;

		try {
			PrintStream outFile = new PrintStream(
					new FileOutputStream(fileName));
			outFile.print(xstream.toXML(storage));

			outFile.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	/**
	 * asks for file name and returns a todolist if file exists
	 * 
	 * @return list read in from xml file
	 */
	public DoubleLinkList<ToDoEntry> readXmlFile() {
		fileName = nameFile();
		try {
			XStream xstream = new XStream(new StaxDriver());
			DoubleLinkList<ToDoEntry> storage = null;

			Scanner input = new Scanner(new File(fileName));

			storage = (DoubleLinkList<ToDoEntry>) xstream.fromXML(input
					.nextLine());

			
			return storage;

		} catch (Exception ex) {
			return storage;
		}

	}

	/**
	 * returns true if file name is not set by user
	 * 
	 * @return true if file name is not set
	 */
	public boolean isEmpty() {

		boolean check = false;

		if (fileName.compareToIgnoreCase("") == 0) {
			check = true;
		}
		return check;
	}

	/**
	 * returns file name from user
	 * 
	 * @return string from user
	 */
	public String nameFile() { // gets user input for text file to load or load
								// // to

		Scanner scan = new Scanner(System.in);

		System.out.print("Enter File Name: ");
		String userInput = scan.nextLine();

		fileName = userInput;
		return userInput;

	}

}
