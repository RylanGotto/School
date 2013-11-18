import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.*;
import java.util.*;

import java.io.*;
import dList.*;

public class ExampleReadFromFile 
{
    //This code uses the XStream library that has been added to this project
    //It will read the XML representation and use Xstream to rebuild the object
    //Run the file writer first and then this example
    public static void main(String args[])
    {
        try
        {
            XStream xstream = new XStream(new StaxDriver() );
            DoubleLinkList<Integer> storage = null;
            Scanner input = new Scanner( new File("ConvertSaveExample.xml") );
            
            
            storage = (DoubleLinkList<Integer>) xstream.fromXML( input.nextLine() );
            
            storage.print();   

        }
        catch(Exception ex) {}
        
        }
    }
