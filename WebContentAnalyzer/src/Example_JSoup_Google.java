import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeVisitor;

public class Example_JSoup_Google
{
  public static void main(String[] args)
  {
      try
      {
        Document doc = Jsoup.connect("http://www.iamrylangotto.com/home.php").get();
        
        Elements elm = doc.getAllElements();
       
        for (int i = 1;i<elm.size();i++)
        System.out.println(elm.get(i).tagName());
        
     
       
    }
    catch(Exception ex)
    {
        System.out.println("Unable to open page: www.google.com");
        ex.printStackTrace();
    }
        
  }
}
