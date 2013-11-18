
	import java.io.FileNotFoundException;
import java.util.*;

import javax.swing.JOptionPane;

import entries.ToDoEntry;
import entries.ToDoList;

	/**
	 * Main controller for program handle all user input to List interaction
	 * 
	 * @author Rylan Gotto
	 * 
	 */
	public class Menu {

		private Scanner scan; // scanner object
		private ToDoList list; // handles all functionality related around
									// managing a list of to do entries.

		private char choice2; // menu and submenu user input choice
		private char choice1; // menu and submenu user input choice

		private int userInput; // menu and submenu user input choice
		private String userInput1; // menu and submenu user input choice

		/**
		 * @throws Exception 
		 */
		public Menu() throws Exception {

			list = new ToDoList();
			list.loadList(); // starts program
			
		}

		/**
		 * master menu handles all interfacing for all options
		 * @throws Exception 
		 * 
		 */
		public void listMenu() throws Exception {
			boolean end = false;
			Scanner scan = new Scanner(System.in); // reset scanner

			
			while(!end){
				System.out.println("\nTo Do List Manager:\n"); // Master Menu
				System.out.println("d) display list\n" + "a) add item\n"
						+ "r) remove item\n" + "u) update item priority\n"
						+ "q) quit\n" + "\n" + "choice:");

				choice1 = scan.next().toUpperCase().charAt(0); // Sanitizes for switch
																// test
			switch (choice1) {
			case 'D':
				if (!list.isEmpty()) {
					list.display(); // Display list
					
				} else {
					
					System.out.println("To do list is empty");
				}

				break;
			case 'A':
				addMenu(); // Call add submenu
				
				break;
			case 'R':
				removeMenu(); // call remove submenu
				
				break;
			case 'U':
				
				updateMenu(); // call update submenu
				
				break;
			case 'Q':
				
				list.printListToFile();
				
				end = true; // prints link list to text file, and shuts down
									// program
				break;
			default:
				System.out.println(
						"The right letters are required!"); // Default warning
				
				break;

			}
			}
			System.out.println("Done.");
		}

		
		
		/**
		 * submenu handles all interfacing for update option
		 */
		private void addMenu() {
			scan = new Scanner(System.in);
			System.out.println("describe new item: ");
			userInput1 = scan.nextLine();
			System.out.println("enter priority (h/m/l): ");
			choice2 = scan.nextLine().toUpperCase().charAt(0); // verification user
																// input +
																// sanitization

			switch (choice2) { // test to see what priority to assign new entry

			case 'H':
				list.add(new ToDoEntry(3, userInput1)); // add high priority entry
				break;
			case 'M':
				list.add(new ToDoEntry(2, userInput1)); // add med priority entry
				break;

			case 'L':
				list.add(new ToDoEntry(1, userInput1)); // add low priority entry

				break;
			default:
				System.out.println(
						"The right letters are required!"); // incorrect input
															// message
				break;
			}
		}

		
		/**
		 * submenu handles all interfacing for remove option
		 * @throws Exception 
		 */
		private void removeMenu() throws Exception {
			
			int userInput2;
			scan = new Scanner(System.in);
			if (!list.isEmpty()) {
				System.out.println("enter item number: ");
				try {
					userInput = scan.nextInt();
					userInput2 = userInput;
					userInput2 -= 1; // place holder variable for next lines
										// formatting
					System.out.println("Remove " + "\""
									
							+ list.get(userInput2).getDescription() + "\"" // remove
																					// menu
							+ " (y/n)?");
					choice1 = scan.next().toUpperCase().charAt(0); // verification
																	// user
																	// input +
																	// sanitization
					if (choice1 == 'Y') {
						list.remove(userInput);
					}
				} catch (InputMismatchException | IndexOutOfBoundsException e) {
					System.out.println(
							"The right numbers are required!"); // incorrect input
																// message
				}
			} else {
				System.out.println("To do list is empty");
			}

		}
		
		
		
		
		
		
		
		
		
		
		/**
		 * submenu handles all interfacing for update option
		 * @throws Exception 
		 */
		private void updateMenu() throws Exception {
			scan = new Scanner(System.in);
			if (!list.isEmpty())  {
				System.out.println("enter item number: ");
				try {

					userInput = scan.nextInt();
					userInput -= 1;

					System.out.println("Reprioritize \""
							+ list.get(userInput).getDescription() + "\"" // update
																					// location
																					// menu
							+ " (y/n)?");

					choice2 = scan.next().toUpperCase().charAt(0); // verification
																	// user
																	// input +
																	// sanitization

					if (choice2 == 'Y') {

						System.out.println("enter priority (h/m/l): ");

						choice2 = scan.next().toUpperCase().charAt(0); // verification
																		// user
																		// input +
																		// sanitization

						switch (choice2) {

						case 'H':
								ToDoEntry entry3 = new ToDoEntry(3, list.get(userInput).getDescription() );
								userInput += 1;
								list.remove(userInput);
								list.add(entry3);
								
								
								// priority
																		// high
							break;
						case 'M':
							ToDoEntry entry2 = new ToDoEntry(2, list.get(userInput).getDescription() );
							userInput += 1;
							list.remove(userInput);						// priority
							list.add(entry2);									// med
							break;

						case 'L':
							ToDoEntry entry1 = new ToDoEntry(1, list.get(userInput).getDescription() );
							userInput += 1;
							list.remove(userInput);	
							list.add(entry1); // low

							break;
						default:
							System.out.println(
									"The right letters are required!"); // incorrect
																		// input
																		// message
							break;
						}
					}
				} catch (InputMismatchException | IndexOutOfBoundsException e) {
					System.out.println(
							"The right numbers are required!"); // incorrect input
																// message
				}
			} else {
				System.out.println( "To do list is empty");
			}
		}

	}
		


