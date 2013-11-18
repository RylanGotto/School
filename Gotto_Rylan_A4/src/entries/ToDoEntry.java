package entries;
/**
 * holds information and functionality about a single to do list entry
 * 
 * @author Rylan Gotto
 */
public class ToDoEntry {

	private int priority; // priority of entry
	private String description; // description of entry

	/**
	 * main constructor
	 * 
	 * @param priority
	 *            priority of entry
	 * @param description
	 *            description of entry
	 */
	public ToDoEntry(int priority, String description) {
		this.priority = priority;
		this.description = description;
	}

	/**
	 * returns priority of Entry
	 * 
	 * @return priority of Entry
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * sets priority of Entry
	 * 
	 * @param priority
	 *            of Entry
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * returns Descriptions of Entry
	 * 
	 * @return Descriptions of Entry
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * sets Description of Entry
	 * 
	 * @param description
	 *            Description of Entry
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return priority + " " + description;
	}
}
