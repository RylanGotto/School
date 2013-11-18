package userIO;
/**
 * Represents a single menu option that can be printed and selected by the user
 * @author  jkidney
 * @version 1.1
 * Created on: March 11, 2013
 * Last Modified: Sept 24, 2013 - JKidney added getOption method  
 */
public class MenuOption 
{
	private String option;
	private String description;

	/**
	 * Constructor 
	 * @param option the option/character the user will select
	 * @param description the description for this option
	 */
	public MenuOption(String option, String description) 
	{
		this.option = option.toLowerCase();
		this.description = description;
	}

	/**
	 * Determines if the given character matches the current menu option object
	 * @param usersChoice the char given by the user
	 * @return true for a match, false otherwise
	 */
    public boolean isAMatch(String usersChoice )
    {
      return (option.compareToIgnoreCase(usersChoice) == 0);	
    }
    
	/**
	 *  Formated string for use when displaying all options for the menu
	 */
	public String toString()
	{
		return String.format("%4s: %-20s", option, description);
	}
	
	public String getOption() { return option; }
}
