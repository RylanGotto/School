import java.util.*;
import javax.swing.JOptionPane;
/**
 * GameBoardController is the user interface,it starts the game as well it handles any calls made to the gameboard class which impact the cards in any of the cells. 
 * 
 * @author (Rylan Gotto) 
 * @version (2/18/2013)
 */
public class GameBoardController 
{
    private GameBoard game ;
    private Scanner scan ;
    private Stack<Undo> undoStack;

    /**
     * GameBoardController Constructor
     *
     */
    public GameBoardController(){  
        game = new GameBoard();
        scan = new Scanner(System.in);
        undoStack = new Stack<Undo>();
    }

    /**
     * Method startGame
     * Start the game, by calling required methods from the GameBoard class
     */
    public void startGame(){
        game.populateGameCells();
        game.printGameBoard();
        GamePrompt();
        gameMenu();
    }

    /**
     * Method gameMenu
     * Handles all interaction with the user, and any direct modification the the game, holding, foundation cells
     */
    public void gameMenu(){
        char choice;// User input
        int  row; //indicated number of where to draw card from
        int  row2; //indicated number of where to place card

        System.out.println("What would you like to do?");
        System.out.println("A: Move Card from Stack of Cards to another Stack of Cards\n"
            + "B: Move Card from Stack to Holding Row\n"
            + "C: Move Card from Stacks to Final Row\n" 
            + "D: Move Card from Holding Row to Stack of Cards\n"            
            + "E: Move Card from Holding Row to Final Row\n"
            + "F: Undo\n"
            + "G: GamePrompt\n"
            + "H: Exit\n");

        choice = scan.next().toUpperCase().charAt(0);// User input
        try{
            switch (choice) {// Test user input

                case 'A': 
                System.out.println("Enter Row nubmer to get card from");
                row = scan.nextInt();
                System.out.println("Enter Row numer to place card on");
                row2 = scan.nextInt();
                game.rowToRow(row2, row); //try modfication to game cells
                undoStack.push(new Undo(0, row, row2)); //add undo object to undoStack
                break;

                case 'B':
                System.out.println("Enter Row nubmer to get card from");
                row = scan.nextInt();
                System.out.println("Enter holding cell nubmer to place card on");
                row2 = scan.nextInt();
                game.rowToHoldingCell(row2, row);  //try modfication to holding cells
                undoStack.push(new Undo(1, row, row2)); //add undo object to undoStack
                break;

                case 'C':
                System.out.println("Enter Row nubmer to get card from");
                row = scan.nextInt();
                System.out.println("Enter final cell nubmer to place card on");
                row2 = scan.nextInt();
                game.rowToFinalCell(row2, row);  //try modfication to foundation cells
                undoStack.push(new Undo(3, row, row2)); //add undo object to undoStack
                break;

                case 'D':
                System.out.println("Enter Holding Row nubmer to get card from");
                row = scan.nextInt();
                System.out.println("Enter Row of Stack of Card to put card on");
                row2 = scan.nextInt();
                game.holdingCellToRow(row2, row);  //try modfication to game cells
                undoStack.push(new Undo(2, row, row2));  //add undo object to undoStack 
                break;

                case 'E':
                System.out.println("Enter Holding Row nubmer to get card from");
                row = scan.nextInt();
                System.out.println("Enter Final row number to put card on");
                row2 = scan.nextInt();
                game.holdingCellToFinalCell(row2, row);  //try modfication to foundation cells
                undoStack.push(new Undo(4, row, row2));//add undo object to undoStack
                break;

                case 'F':
                undo(); //Call undo function
                break;

                case 'G':
                GamePrompt(); //Call game prompt 
                break;

                case 'H':
                System.exit(0);
                break;

                default: 
                JOptionPane.showMessageDialog(null, "The right letters are required!");
                break;

            }
        }catch(InputMismatchException e){
            JOptionPane.showMessageDialog(null, "Numbers are required!");
        }

        System.out.print('\f');//Clear consol
        game.printGameBoard();

        gameMenu();

    }
    
    /**
     * Method undo
     * undo tests the int from on top stack, calls the inversed method of 
     * what move was last made by the user from the corresponding number
     */
    public void undo(){

        try{
            int choice = undoStack.peek().getMethodNumber();//number to determine what inverse method to call

            switch (choice) {
                case 0: 
                game.rowToRowIn(undoStack.peek().getTo(), undoStack.peek().getFrom());
                break;
                case 1: 
                game.rowToHoldingCellIn(undoStack.peek().getTo(), undoStack.peek().getFrom());
                break;
                case 2: 
                game.holdingCellToRowIn(undoStack.peek().getTo(), undoStack.peek().getFrom());
                break;
                case 3: 
                game.rowToFinalCellIn(undoStack.peek().getTo(), undoStack.peek().getFrom());
                break;
                case 4: 
                game.holdingCellToFinalIn(undoStack.peek().getTo(), undoStack.peek().getFrom());
                break;

            }
            undoStack.pop();

        }catch(EmptyStackException e){
            JOptionPane.showMessageDialog(null, "You have reached the beginning of the game!");
        }
    }

    /**
     * Method GamePrompt
     * Prompts user with rules and legend to reference at any time
     */
    public void GamePrompt(){   

        String rules = "Rules for BakersGame\n"
            +"Construction and layout:\n"
            +"One standard 52-card deck is used.\n"
            +"There are four open holding cells and four open foundations.\n" 
            +"The entire deck is dealt out left to right into eight stacks, four of which comprise seven cards and four of which comprise six.\n"
            +"The top card of each cascade begins a tableau.\n"
            +"Tableaux must be built down by the same suit.\n"
            +"Foundations are built up by suit.\n"
            +"Moves:\n"
            +"Any cell card or top card of any stack may be moved to build on a tableau, or moved to an empty cell, an empty stack, or its foundation.\n"
            +"Victory:\n"
            +"The game is won after all cards are moved in ascending number by suit to their foundation piles.\n";

        String legend = "Legend\n"
            +"Cards:\n H = Hearts\n D = Diamonds\n C = Clubs\n S = Spades\n"
            +" 0 = Ace\n 10 = Jack\n 11 = Queen\n 12 = King\n"
            +"Cells:\nH(0,1,2,3) = Holding Cells - referenced by number only\n"
            +"F(0,1,2,3) = Foundation Cells - referenced by number only\n";

        String letters = "Letters are not case sensitive!";

        JOptionPane.showMessageDialog(null, rules);
        JOptionPane.showMessageDialog(null, legend);
        JOptionPane.showMessageDialog(null, letters);

    }
    
    /**
     * Undo is an anonymous inner class which is used to identify which method was called as well what value were use in that call.
     * 
     * @author (Rylan Gotto) 
     * @version (2/18/2013)
     */
    private class Undo
    {
        int methodNumber;
        int row;
        int row2;

        /**
         * Constructor for objects of class undo
         */
        public Undo(int num, int from, int to)
        {

            methodNumber = num;
            row = from;
            row2 = to;

        }

        public int getMethodNumber(){
            return methodNumber;
        }

        public int getFrom(){
            return row;
        }

        public int getTo(){
            return row2;
        }

    }
}