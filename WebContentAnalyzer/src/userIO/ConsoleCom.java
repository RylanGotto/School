package userIO;
/**
 * Handles basic input/output with the console 
 * 
 * @author JKidney
 * @version 2.0
 * 
 * last Modified: Sept 24, 2012 - JKidney Added new functionality into the class
 */

import java.util.*;

public class ConsoleCom
{
	public static final char NO_CHAR_INPUT = ' ';	//default for character input
	private  Scanner input;

	/**
	 * default constructor
	 */
	public ConsoleCom()
	{
		input = new Scanner(System.in); 
	}

	/**
	 * helper method to contain printing of messages to the console
	 * @param message the message to print
	 */
	public void print(String message)
	{
		System.out.print(message); 
	}

	/**
	 * helper method to contain printing of messages to the console with a new line
	 * @param message the message to print
	 */
	public void println(String message)
	{
		System.out.println(message); 
	}


	/**
	 * Displays a message and waits to read a line of text from the user
	 * @param message the message to display
	 * @return the line inputed by the user
	 * */
	public String getInputString(String message)
	{
		print(message);
		return input.nextLine().trim();
	}

	/**
	 * Displays a message and waits to read an integer from the user
	 * @param message the message to display
	 * @return the integer inputed by the user
	 */
	public int getInputInt(String message)
	{
		int userInput = 0;
		boolean exit = true;
		print(message);

		do
		{
			try
			{
				userInput = input.nextInt();
				input.nextLine();
			}
			catch(Exception ex)
			{
				print("Not a number, please enter again: ");
				clearInputLine();
				exit = false;
			}
		}
		while(!exit);

		return userInput;   
	}

	/**
	 * Displays a message and waits to read an integer from the user within the given range
	 * @param message the message to display
	 * @param low the low end of the range (inclusive)
	 * @param high the high end of the range (inclusive)
	 * @return the integer inputed by the user ( and verified to be within range )
	 */
	public int getInputInRangeInt(String message, int low, int high)
	{
		int userInput = 0;
		boolean exit = false;

		do
		{
			userInput = getInputInt(message);

			if(userInput < low || userInput > high)
				print("Not within proper range ("+low+"-"+high+"), please enter again: ");
			else
				exit = true;
		}
		while(!exit);

		return userInput;   

	}
	/**
	 * clears one line from the input console
	 */
	public void clearInputLine()
	{
		input.nextLine();  
	}

	/**
	 * reads one character from the input
	 * @param message the message to display
	 * @return the entered character or NO_CHAR_INPUT if no input
	 */
	public char getInputChar(String message)
	{
		char result = NO_CHAR_INPUT;
		print(message);
		String inputLine = input.nextLine().trim().toLowerCase();

		if(inputLine.length() > 0)
			result = inputLine.charAt(0);

		return result;
	}

	/**
	 *  reads one character from the input and validates that it is a character in validChars
	 * @param message the message to display
	 * @param validChars a string that contains all valid chars for input by the user
	 * @return the entered character validated 
	 */
	public char getInputCharValidate(String message, String validChars)
	{

		char userInput = NO_CHAR_INPUT;
		boolean exit = false;
		String valid = validChars.toUpperCase();

		do
		{
			userInput = Character.toUpperCase( getInputChar(message) );

			if(valid.indexOf(userInput) == -1)
				println("Invalid choice ( must be one of "+validChars+"), please enter again: ");
			else
				exit = true;
		}
		while(!exit);

		return userInput;   

	}

	/**
	 * Asks the user a yes no answer and returns the result
	 * @param message the message to display to the user ( y/n) will be tacked on
	 * @return true if the user entered 'y' false otherwise
	 */
	public boolean getInputYesNo(String message)
	{
		boolean result = false;
		char input = Character.toUpperCase( getInputChar(message + " (y,n)") );
		if(input == 'Y') result = true;
		return result;
	}


	/**
	 * Request a file name from the user and removes any tags in the file name
	 * @param message the message to print tot he user
	 * @return the file name
	 */
	public String getFileName_removeTags(String message)
	{
		boolean error = false;
		String fileName = "";
		String[] parts = null;
		do
		{
			fileName = getInputString(message);
			
			
			parts = fileName.split("\\.");
	
					
			if(fileName.startsWith("."))
			{
				fileName = "";
				error = true;
				println("Invalid file name"); 
			}
			else 
			{
				if(parts.length > 0)
				  fileName = parts[0].trim();
				error = false;
			}
		}
		while(error);

		return fileName;
	}

	/**
	 * pauses until the user hits enter to continue
	 */
	public void pauseUntilHitEnter()
	{
		getInputString("<hit enter to continue>");
	}
}
