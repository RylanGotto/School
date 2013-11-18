import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.*;

import java.io.*;
import dList.*;

public class ExampleSaveToFile 
{
    //This code uses the XStream library that has been added to this project
    //It will save the object to a file in an XML representation
    //Run this first and then you can run the reader example
    public static void main(String args[])
    {
        XStream xstream = new XStream( new StaxDriver() );

        DoubleLinkList<Integer> storage = new DoubleLinkList<Integer>();

        for(int count=0; count < 5; count++)
            storage.addToEnd(count);

        storage.print();   

        try
        {
            PrintStream outFile = new PrintStream(new FileOutputStream("ConvertSaveExample.xml"));  
            outFile.print( xstream.toXML(storage) );

            outFile.close();
        }
        catch(Exception ex) {}
    }
}
