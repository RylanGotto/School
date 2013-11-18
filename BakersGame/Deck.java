import java.util.*;
/**
 * The deck class is made up of 52 card objects. The deck class makes and assigns value 
 * and suit to 13 cards of each suit.
 * 
 * @author (Rylan Gotto) 
 * @version (2/18/2013)
 */
public class Deck
{
    private ArrayList<Card> deck;

    /**
     * Deck Constructor
     * Populates arraylist know as deck
     */
    public Deck(){
        deck = new ArrayList<Card>();

        for(int i = 0;i<=12;i++){
            deck.add(new Card(i,"D"));
            deck.add(new Card(i,"S"));
            deck.add(new Card(i,"C"));
            deck.add(new Card(i,"H"));

        }
        long seed = System.nanoTime(); // Returns the current value of the most precise available system timer, in nanoseconds.
        Collections.shuffle(deck, new Random(seed));//shuffles deck

    }

    /**
     * Method dealCards
     * returns a card object of given index x
     * @param x A parameter
     * @return The return value
     */
    public Card dealCards(int x){            
        return deck.get(x);
    }

}