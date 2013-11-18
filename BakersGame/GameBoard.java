import java.util.*;
import javax.swing.JOptionPane;
/**
 * GameBoard creates and displays locations for 52 cards as well as handles any movement of card between game, holding, and foundation cells
 * 
 * @author (Rylan Gotto) 
 * @version (02/18/2013)
 */
public class GameBoard 
{
    private ArrayList<Stack<Card>> gameCells; // 8 GameCells which hold 52 cards
    private ArrayList<Stack<Card>> holdingCells; // 4 empty holding cells
    private ArrayList<Stack<Card>> finalCells ; //4 empty final or foundation cells
    private Deck theDeck; //Create a the Deck of 52 cards
    private final int CARDCELLS = 8;    //amount of game cells
    private final int HFCELLS = 4;      //amount of holding and foundation cells

    /**
     * GameBoard Constructor
     *
     */
    public GameBoard(){
        gameCells = new ArrayList<Stack<Card>>();  //Creating cells for foundation, game, and holding
        holdingCells = new ArrayList<Stack<Card>>();
        finalCells = new ArrayList<Stack<Card>>(); 
        theDeck = new Deck(); //Create the Deck
        //create stacks to hold cards in gamecells
        for(int i = 0;i<CARDCELLS;i++){
            gameCells.add( new Stack<Card>());
        }
        //create stacks to hold cards in foundation, and holding cells
        for(int i = 0;i<HFCELLS;i++){
            holdingCells.add( new Stack<Card>());
            finalCells.add( new Stack<Card>());
        }

    }

    /**
     * Method populateGameCells
     * Populates game Cell with 52 cards
     */
    public void populateGameCells(){
        int end = 7; // determine how many cards are delt into each stack
        int i = 0;   //determines what number of game cell card is being delt to
        int j = 0;   //determines the index of arraylist deck to draw card from

        while ( i < CARDCELLS ) {

            if ( i <3){ //determines how many card are being dealt, cells 1-3 recieve 7 while remaining recieve 6 
                while ( j < end ) {

                    gameCells.get(i).push(theDeck.dealCards(j));
                    j++;
                }
                end += 7;
            }else{
                
                while ( j < end && j < 52 ) {

                    gameCells.get(i).push(theDeck.dealCards(j));
                    j++;
                }
                end += 6;
            }
            i++;
        }
    }

    /**
     * Method printGameBoard
     * Calls display methods for game board
     */
    public void printGameBoard(){
        printCardStacks();
        printHoldingStacks();
        printFinalStacks();
    }

    /**
     * Method rowToRow
     * Moves card from Stack of card to other Stack of cards
     * @param x A parameter indicates what cell card is going to
     * @param y A parameter indicates what cell card is coming from
     */
    public void rowToRow(int x, int y){
        int cardCheck; // value to check to see if card will be accepted by previous card
        try{
            cardCheck = gameCells.get(y).peek().getValue() + 1;// creates value to check to see if card will be accepted by previous card
            if(gameCells.get(x).peek().getSuit().equals(gameCells.get(y).peek().getSuit()) && gameCells.get(x).peek().getValue() == cardCheck ){//if suit and value is euqal to card check the card is moved

                gameCells.get(x).push(gameCells.get(y).pop());

            }else{
                JOptionPane.showMessageDialog(null, "In order for a card to be placed on this row, it must be of same suit and one less the value of the current face card!");
            }
        }catch(EmptyStackException e){
            JOptionPane.showMessageDialog(null, "Row is empty!");
        }catch(IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Wrong number!");
        }
    }

    /**
     * Method rowToRowIn
     * Reverses move made by rowToRow method call
     * @param x A parameter indicates what cell card is going to
     * @param y A parameter indicates what cell card is coming from
     */
    public void rowToRowIn(int x, int y){
        try{

            gameCells.get(y).push(gameCells.get(x).pop());
        }catch(IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Nothing will be undone, this move was not valid due to incorrect user input");
        }
    }

    /**
     * Method rowToHoldingCell
     * Moves card to holding cell from stack of cards
     * @param x A parameter indicates what cell card is going to
     * @param y A parameter indicates what cell card is coming from
     */
    public void rowToHoldingCell(int x, int y){
        try{
            if(holdingCells.get(x).isEmpty()){ //Only allows one card in this cell at a time.

                holdingCells.get(x).push(gameCells.get(y).pop());

            }else{
                JOptionPane.showMessageDialog(null, "Illegal moves will not be tolerated. Only one card per hold row, thank you =)");
            }

        }catch(EmptyStackException e){
            JOptionPane.showMessageDialog(null, "Row is empty!");
        }catch(IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Wrong number!");
        }
    }

    /**
     * Method rowToHoldingCellIn
     * Reverses move made by rowToHoldingCell method call
     * @param x A parameter indicates what cell card is going to
     * @param y A parameter indicates what cell card is coming from
     */
    public void rowToHoldingCellIn(int x, int y){
        try{
            gameCells.get(y).push(holdingCells.get(x).pop());
        }catch(IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Nothing will be undone, this move was not valid due to incorrect user input");
        }
    }

    /**
     * Method holdingCellToRow
     * Moves card from holding cell to stack of cards
     * @param x A parameter indicates what cell card is going to
     * @param y A parameter indicates what cell card is coming from
     */
    public void holdingCellToRow(int x, int y){
        int cardCheck; // value to check to see if card will be accepted by previous card
        try{
            if(!holdingCells.get(y).isEmpty()){
                cardCheck = holdingCells.get(y).peek().getValue() + 1 ;//creates vale to check if card is allowed to go onto previous card
                if(cardCheck == gameCells.get(x).peek().getValue() && holdingCells.get(y).peek().getSuit().equals(gameCells.get(x).peek().getSuit())){//check to makes sure card is of same suit and of lesser value

                    gameCells.get(x).push(holdingCells.get(y).pop());

                }else{
                    JOptionPane.showMessageDialog(null, "In order for a card to be placed on this row, it must be of same suit and one less of the value of the current face card!");
                }

            }else{
                JOptionPane.showMessageDialog(null, "Row is empty!");
            }
        }catch( IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Thats not a row :(");
        }
    }

    /**
     * Method holdingCellToRowIn
     * Reverses move made by holdingCellToRow method call
     * @param x A parameter indicates what cell card is going to
     * @param y A parameter indicates what cell card is coming from     
     */
    public void holdingCellToRowIn(int x, int y){
        try{
            holdingCells.get(y).push(gameCells.get(x).pop());
        }catch(IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Nothing will be undone, this move was not valid due to incorrect user input");
        }
    }

    /**
     * Method holdingCellToFinalCell
     * Moves card from holding cell to foundation cell
     * @param x A parameter indicates what cell card is going to
     * @param y A parameter indicates what cell card is coming from 
     */
    public void holdingCellToFinalCell(int x, int y){
        int cardCheck;// value to check to see if card will be accepted by previous card
        try{
            if(finalCells.get(x).isEmpty()){
                if(holdingCells.get(y).peek().getValue()==0){//check to see if cell is empty, if empty only allows an ace or 0 in its stack
                    finalCells.get(x).push(holdingCells.get(y).pop());
                }else{
                    JOptionPane.showMessageDialog(null, "This row requires a 0 or Ace Card");
                }

            }else if(!finalCells.get(x).isEmpty()){
                cardCheck = holdingCells.get(y).peek().getValue()-1; // checks to see if card is of greater value
                if(finalCells.get(x).peek().getValue()==cardCheck && holdingCells.get(y).peek().getSuit().equals(finalCells.get(x).peek().getSuit())){//check to see if card is able to be placed on stack

                    finalCells.get(x).push(holdingCells.get(y).pop());

                }else{

                    JOptionPane.showMessageDialog(null, "In order for a card to be place on this row, it must be of same suit and of greater value!");

                }

            }
        }catch(EmptyStackException e){
            JOptionPane.showMessageDialog(null, "Row is empty!");
        }catch( IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Thats not a row :(");
        }

    }

    /**
     * Method holdingCellToFinalIn
     * Reverses move made by holdingCellToFinal method call
     * @param x A parameter indicates what cell card is going to
     * @param y A parameter indicates what cell card is coming from
     */
    public void holdingCellToFinalIn(int x, int y){
        try{
            holdingCells.get(y).push(finalCells.get(x).pop());
        }catch(IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Nothing will be undone, this move was not valid due to incorrect user input");
        }
    }

    /**
     * Method rowToFinalCell
     * Moves card from Stack of cards to final cell
     * @param x A parameter indicates what cell card is going to
     * @param y A parameter indicates what cell card is coming from
     */
    public void rowToFinalCell(int x, int y){
        int cardCheck;// value to check to see if card will be accepted by previous card
        try{
            if(finalCells.get(x).isEmpty()){
                if(gameCells.get(y).peek().getValue()==0){//check to see if cell is empty, if empty only allows an ace or 0 in its stack
                    finalCells.get(x).push(gameCells.get(y).pop());
                }else{
                    JOptionPane.showMessageDialog(null, "This row requires a 0 or Ace Card");
                }

            }else if(!finalCells.get(x).isEmpty()){
                cardCheck = gameCells.get(y).peek().getValue()-1;// checks to see if card is of greater value
                if(finalCells.get(x).peek().getValue()==cardCheck && gameCells.get(y).peek().getSuit().equals(finalCells.get(x).peek().getSuit())){//check to see if card is able to be placed on stack

                    finalCells.get(x).push(gameCells.get(y).pop());

                }else{

                    JOptionPane.showMessageDialog(null, "In order for a card to be place on this row, it must be of same suit and of greater value!");

                }

            }
        }catch(EmptyStackException e){
            JOptionPane.showMessageDialog(null, "Row is empty!");
        }catch( IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Thats not a row :(");
        }
    }

    /**
     * Method rowToFinalCellIn
     * Reverses move made by rowTofinalCell method call
     * @param x A parameter indicates what cell card is going to
     * @param y A parameter indicates what cell card is coming from
     */
    public void rowToFinalCellIn (int x, int y){
        try{
            gameCells.get(y).push(finalCells.get(x).pop());
        }catch(IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Nothing will be undone, this move was not valid due to incorrect user input");
        }
    }

    /**
     * Method printCardStacks
     * Displays contents of gameCells
     */
    public void printCardStacks(){
        int end = 7; //determine how many cards are printed into each stack
        int i = 0;   //determines number of game cell printed
        int j = 0;   //determines the index of arraylist deck printed 
        while ( i < CARDCELLS ) {
            System.out.print(i+": ");
            if ( i <4){ 
                end = gameCells.get(i).size();//set ending condition to amount of card in stack
                while ( j < end ) {
                    try{
                        System.out.print(gameCells.get(i).elementAt(j).showCard() + " ");

                    }catch(ArrayIndexOutOfBoundsException e){}

                    j++;
                }
            } else{
                end = gameCells.get(i).size();//set ending condition to amount of card in stack
                while ( j < end && j < 52 ) {
                    try{
                        System.out.print(gameCells.get(i).elementAt(j).showCard() + " ");

                    }catch(ArrayIndexOutOfBoundsException e){}
                    j++;
                }
            }
            j = 0;
            i++;
            System.out.println(""); //formatting line
        }
    }

    /**
     * Method printHoldingStacks
     * Displays holding cell, and contents
     */
    public void printHoldingStacks(){   
        for (int i = 0;i<HFCELLS;i++){   
            try{
                System.out.print(i+"H| "); 
                System.out.print(holdingCells.get(i).peek().showCard());
            }catch(IndexOutOfBoundsException | EmptyStackException e){}
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    /**
     * Method printFinalStacks
     * Displays foundation cell, and contents
     */
    public void printFinalStacks(){
        for (int i = 0;i<HFCELLS;i++){   

            try{
                System.out.print(i+"F| ");
                System.out.print(finalCells.get(i).peek().showCard());
            }catch(IndexOutOfBoundsException | EmptyStackException e){}
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
