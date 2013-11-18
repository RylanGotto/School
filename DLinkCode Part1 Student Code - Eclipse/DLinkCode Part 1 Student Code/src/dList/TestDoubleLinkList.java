package dList;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import org.junit.Before;
import comparisonObjects.IntegerCompare;

public class TestDoubleLinkList 
{
    private DoubleLinkList<Integer> list;

    /**
     * Helper method used to populate the link list with data 
     * adding to the start of the list
     */
    public void addToFront(int[] thingsToAdd) 
    {
        for (int i : thingsToAdd) {
            list.addToFront(new Integer(i));
        }
    }

    /**
     * Helper method used to populate the link list with data 
     * adding to the end of the list
     */
    public void addToEnd(int[] thingsToAdd) 
    {
        for (int i : thingsToAdd) {
            list.addToEnd(new Integer(i));
        }
    }

    /**
     * Helper method used to populate the link list with data 
     * adding to the list using the sorted method
     */
    public void addSorted(int[] thingsToAdd) 
    {
        IntegerCompare cmp = new IntegerCompare();
        for (int i : thingsToAdd) {
            list.addSorted(new Integer(i), cmp);
        }
    }

    /**
     * Helper method used to confirm that all links traversing through the next links
     * is corect with the given data
     */
    public void confirmElementOrderForwards(int[] expectedElements)
    {
        DoubleLinkListIterator<Integer> iter = list.getStartIterator();
        for (int e : expectedElements) {
            assertEquals(new Integer(e), iter.getdata());
            iter.moveToNext();
        }
    }

    /**
     * Helper method used to confirm that all links traversing through the prev links
     * is corect with the given data
     */
    public void confirmElementOrderBackwards(int[] expectedElements) 
    {
        DoubleLinkListIterator<Integer> iter = list.getEndIterator();
        for (int e : expectedElements) {
            assertEquals(new Integer(e), iter.getdata());
            iter.moveToPrev();
        }
    }

    /**
     * Called before the start of each test case below is run ( this is done by JUnit)
     */
    @Before
    public void setUp()
    {
        list = new DoubleLinkList<Integer>();
    }

    /**
     * Checks to make sure the list is empty when it is first created
     */
    @Test
    public void list_Empty_at_start() 
    {
        try
        {
            DoubleLinkListIterator<Integer> iter = list.getStartIterator();
            DoubleLinkListIterator<Integer> iter2 = list.getEndIterator();

            assertEquals(null, iter.getdata() );
            assertEquals(null, iter2.getdata() );
            assertEquals(0, list.getSize());
        }
        catch(Exception ex)
        {
            fail("Exception caught: " + ex.getMessage());
        }
    }

    /*====================================================================
    ADD TO FRONT TESTS
    ==================================================================*/
    /**
     * Tests adding a single element to the front of an empty list
     */
    @Test
    public void add_To_Front_Single_Element() 
    {
        try
        {
            int[] elements = { 5 };
            addToFront(elements);

            confirmElementOrderForwards(elements);
            confirmElementOrderBackwards(elements);

            assertEquals(1, list.getSize() );  //confirm size of list is one
        }
        catch(Exception ex)
        {
            fail("Exception caught");
        }
    }

    /**
     * Tests adding a multiple elements to the front of an empty list
     * and that the next/prev links are all correct
     * also verifies that the size is correct
     */
    @Test
    public void add_To_Front_Multiple_Element_check() 
    {
        try
        {
            int[] elements = { 5, 4, 7 };
            addToFront(elements);

            int[] expectedElementsInOrder = { 7, 4, 5 };
            confirmElementOrderForwards(expectedElementsInOrder);

            int[] expectedElementsInOrder2 = { 5, 4, 7 };
            confirmElementOrderBackwards(expectedElementsInOrder2);

            assertEquals(3, list.getSize() ); 
        }
        catch(Exception ex)
        {
            fail("Exception caught");
        }
    }
    /*====================================================================
    ADD TO END TESTS
    ==================================================================*/
    /**
     * Tests adding a single element to the end of an empty list
     */
    @Test
    public void add_To_End_Single_Element() 
    {
        try
        {
            int[] elements = { 5 };
            addToEnd(elements);

            confirmElementOrderForwards(elements);
            confirmElementOrderBackwards(elements);

            assertEquals(1, list.getSize() );  
        }
        catch(Exception ex)
        {
            fail("Exception caught");
        }
    }

    /**
     * Tests adding a multiple elements to the end of an empty list
     *and that the next/prev links are all correct
     *also verifies that the start reference is correct
     */
    @Test
    public void add_To_End_Multiple_Element_check() 
    {
        try
        {
            int[] elements = { 5, 4, 7 };
            addToEnd(elements);

            int[] expectedElementsInOrder = { 5, 4, 7 };
            confirmElementOrderForwards(expectedElementsInOrder);

            int[] expectedElementsInOrder2 = { 7, 4, 5 };
            confirmElementOrderBackwards(expectedElementsInOrder2);

            assertEquals(3, list.getSize() );
        }
        catch(Exception ex)
        {
            fail("Exception caught");
        }
    }

    /*====================================================================
    ADD Sorted TESTS
    ==================================================================*/
    /**
     * Tests adding a single element to the list calling add sorted
     */
    @Test
    public void add_Sorted_Single_Element() 
    {
        try
        {

            int[] elements = { 5 };
            addSorted(elements);
            confirmElementOrderForwards(elements);
            confirmElementOrderBackwards(elements);
            assertEquals(1, list.getSize() );
        }
        catch(Exception ex)
        {
            fail("Exception caught");
        }
    }

    /**
     * Tests adding multiple elements using addSorted on an empty list
     * and that the next/prev links are all correct
     * also verifies that the start reference is correct
     */ 
    @Test
    public void add_Sorted_Multiple_Element() 
    {
        try
        {
            int[] elements = { 5, 4, 7, -1, 6, 7, 4, -1 };
            addSorted(elements);

            int[] expectedElementsInOrder = { -1, -1, 4, 4, 5, 6, 7, 7 };
            confirmElementOrderForwards(expectedElementsInOrder);

            int[] expectedElementsInOrder2 = { 7, 7, 6, 5, 4, 4, -1, -1 };
            confirmElementOrderBackwards(expectedElementsInOrder2);

            assertEquals(8, list.getSize() );
        }
        catch(Exception ex)
        {
            fail("Exception caught");
        }
    }

    /**
     * Tests adding elements sorted after having normal addToStart
     * called ( sorted into a non sorted list )
     */
    @Test
    public void add_Sorted_After_Noraml_Adds_check() 
    {
        try
        {
            int[] elements  = { 5, 10, 20, 1 };
            int[] elements2 = { 1, 20, 10, 5 };
            addToFront(elements2);
            confirmElementOrderForwards(elements);
            confirmElementOrderBackwards(elements2);
            assertEquals(4, list.getSize() );  //confirm size of list 

            int[] elements3 = { 1, 19, 22, 7, 3 };

            addSorted(elements3);
            assertEquals(9, list.getSize() );  //confirm size of list 

            int[] expectedElementsInOrder = { 1,3,5,7,10,19,20,1,22};
            confirmElementOrderForwards(expectedElementsInOrder);

            int[] expectedElementsInOrder2 = { 22,1,20,19,10,7,5,3,1 };
            confirmElementOrderBackwards(expectedElementsInOrder2);
        }
        catch(Exception ex)
        {
            fail("Exception caught");
        }
    }

    /*====================================================================
    get TESTS
    ==================================================================*/
    /**
     * Tests trying to do a get(index) on an empty list. Should throw an exception 
     */
    @Test
    public void get_On_An_Empty_List() 
    {
        try
        {
            DoubleLinkList<Integer> list = new DoubleLinkList<Integer>();
            list.get(1);
            fail("Exception not thrown");
        }
        catch(Exception ex)
        {
            assertTrue(true);
        }
    }
    //--------------------------------------------------------------------
    //Tests trying to do a get(index) that is a fail on a lower and uper bound
    @Test
    public void get_On_Out_Of_Bound_Indexs() 
    {
        try
        {
            int[] elements  = { 5, 10, 20, 1 };
            addToFront(elements);

            try
            {
                list.get(-1); // lower bound
                fail("Exception not thrown for lower bound");
            }
            catch(Exception er1){ }

            try
            {
                list.get(100); // upper bound
                fail("Exception not thrown for upper bound");
            }
            catch(Exception er2){ }

            try
            {
                list.get(4); // right on the edge of the upper bound
                fail("Exception not thrown for hight bound on edge");
            }
            catch(Exception er3){ }

            assertTrue(true); // if it gets here it means that the past 
            // two tests for bounds past
        }
        catch(Exception ex)
        {
            fail("Unknown Exception caught");
        }
    }

    /**
     * Tests trying to do a get(index) on all elements within bounds
     * in the list
     */
    @Test
    public void get_On_Start_Middle_End_Of_the_List() 
    {
        try
        {
            int[] elements = { 5, 4, 7, 11 , 20 };
            addToEnd(elements);

            int[] indexToCheck = { 0, 2, 1, 4, 3, 2, 4  };

            for(int index : indexToCheck)
            {
                assertEquals( new Integer(elements[index]) , list.get(index) );  
            }
        }
        catch(Exception ex)
        {
            fail("Exception caught");
        }
    }

    /*====================================================================
    remove TESTS
    ==================================================================*/
    /**
     *  Tests trying to do a removeAt(index) on an empty list. 
     *  Should throw an exception 
     */
    @Test
    public void removeAt_On_An_Empty_List() 
    {
        try
        {
            DoubleLinkList<Integer> list = new DoubleLinkList<Integer>();
            list.removeAt(1);
            fail("Exception not thrown");
        }
        catch(Exception ex)
        {
            assertTrue(true);
        }
    }

    /**
     * Tests trying to do a removeAt(index) that is a fail on a lower/upper bound
     */
    @Test
    public void removeAt_On_Out_Of_Bounds() 
    {
        try
        {
            int[] elements  = { 5, 10, 20, 1 };
            addToFront(elements);

            try
            {
                list.removeAt(-1); // lower bound
                fail("Exception not thrown for lower bound");
            }
            catch(Exception er1){ }

            try
            {
                list.removeAt(100); // upper bound
                fail("Exception not thrown for upper bound");
            }
            catch(Exception er2){ }

            try
            {
                list.removeAt(4); // right on the edge of the upper bound
                fail("Exception not thrown for hight bound on edge");
            }
            catch(Exception er3){ }

            assertTrue(true); // if it gets here it means that the past 
            //two tests for bounds past
        }
        catch(Exception ex)
        {
            fail("Unknown Exception caught");
        }

    }

    /**
     * Helper methods for testing the remove function
     */
    public int[] toArray(ArrayList<Integer> numbers)
    {
        int[] list = new int[numbers.size()];
        for(int index=0; index < list.length; index++)
            list[index] = numbers.get(index);
        return list;
    }

    public int[] toRevArray(ArrayList<Integer> numbers)
    {
        int[] list = new int[numbers.size()];
        int count = 0;
        for(int index=list.length-1; index >= 0; index--)
        {
            list[count] = numbers.get(index);
            count++;
        }

        return list;
    }

    /**
     * Tests RemoveAt on an non empty list 
     * First, middle and end points
     */
    @Test
    public void removeAT_NotEmpty_check() 
    {
        try
        {
            int[] elements = { 5, 4, 7, 11 , 20 };
            int[] indexToRem = {1,2,2,0,0};
            ArrayList<Integer> numbers = new ArrayList<Integer>();

            for(int value : elements)
                numbers.add(value);

            addToEnd(elements);

            for(int index : indexToRem)
            {
                int remValue = numbers.remove(index);
                assertEquals( new Integer(remValue), list.removeAt(index));
                confirmElementOrderForwards( toArray(numbers) );
                confirmElementOrderBackwards( toRevArray(numbers) );
                assertEquals( numbers.size() , list.getSize() );
            }

            // list should be empty so confirm that all refs are set properly
            DoubleLinkListIterator<Integer> iter = list.getStartIterator();
            DoubleLinkListIterator<Integer> iter2 = list.getEndIterator();

            assertEquals(null, iter.getdata() );
            assertEquals(null, iter2.getdata() );
            assertEquals(0, list.getSize());
        }
        catch(Exception ex)
        {
            fail("Exception caught");
        }
    }
}
