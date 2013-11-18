import comparisonObjects.*;

public class ExampleUsingAComparisonObject
{
    public static void main(String args[])
    {
        StringNonCaseSensitiveCompare comparator = new StringNonCaseSensitiveCompare();

        switch( comparator.compare("aaa","AAA") )
        {
            case -1 : System.out.println("first is less then the second"); break;
            case  0 : System.out.println("they are the same"); break;
            case  1 : System.out.println("first is greater then the second"); break;
        }

        switch( comparator.compare("aaa","bbb") )
        {
            case -1 : System.out.println("first is less then the second"); break;
            case  0 : System.out.println("they are the same"); break;
            case  1 : System.out.println("first is greater then the second"); break;
        }

        switch( comparator.compare("ccccccc","bbb") )
        {
            case -1 : System.out.println("first is less then the second"); break;
            case  0 : System.out.println("they are the same"); break;
            case  1 : System.out.println("first is greater then the second"); break;
        }
    }
}
