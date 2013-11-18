
/**
 * @author (Rylan Gotto) 
 * @version (2/18/2013)
 */
public class Card
{

    private int value;
    private String suit;
    /**
     * Card Constructor
     *
     * @param v A parameter 
     * @param s A parameter
     */
    public Card(int v, String s){
        this.value = v;
        this.suit = s;
    }

    /**
     * Method setValue
     *
     * @param v A parameter
     */
    public void setValue(int v){
        this.value = v;
    }

    /**
     * Method setSuit
     *
     * @param s A parameter
     */
    public void setSuit(String s){
        this.suit = s;
    }

    /**
     * Method getValue
     *
     * @return The return value
     */
    public int getValue(){
        return value;
    }

    /**
     * Method getSuit
     *
     * @return The return value
     */
    public String getSuit(){
        return suit;
    }

    /**
     * Method showCard
     *
     * @return The return value
     */
    public String showCard(){
        return this.value + this.suit ;
    }
}

