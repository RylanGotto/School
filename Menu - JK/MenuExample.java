public class MenuExample 
{ 
    private ConsoleCom com;
    private Menu exampleMenu;

    /**
     * Default Constructor
     */
    public MenuExample()
    {
        com = new ConsoleCom();
        exampleMenu = new Menu(com);
        setUpMenu();
    }

    /**
     * Does the basic setup of the menu options used by the program
     */
    private void setUpMenu()
    {
        exampleMenu.addMenuOption( new MenuOption("A", "Option A") ); 
        exampleMenu.addMenuOption( new MenuOption("B", "Option B") ); 
        exampleMenu.addMenuOption( new MenuOption("C", "Option C") ); 
        exampleMenu.addMenuOption( new MenuOption("Q", "Exit/Quit") ); 
    }

    /**
     * Runs the full example of the menu
     */
    public void run()
    {
        boolean end = false;

        while(!end)
        {
            // displays the users choice and returns 
            // the selected option as an object
            MenuOption selected = exampleMenu.getUserChoice(); 

            //check the users choice
            if(selected.isAMatch("Q"))
                end = true;
            else
            {
                System.out.println("Your selected choice is: " + selected);
            }
        }

        System.out.println("Done.");
    }

    /**
     * Main 
     */
    public static void main(String[] args)
    {
        MenuExample example = new MenuExample();
        example.run();
    }
}
