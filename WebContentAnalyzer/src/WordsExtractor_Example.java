import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import webContentAnalyze.WordsExtractor;


public class WordsExtractor_Example {

	public static void main(String[] args) 
	{
		String tag = "";
		WordsExtractor extractor = new WordsExtractor();
		
		   try
		      {
		        Document doc = Jsoup.connect("http://www.google.com").get();
		      
		         tag = doc.text();
		        
		        
		       
		    }
		    catch(Exception ex)
		    {
		        System.out.println("Unable to open page: www.google.com");
		        ex.printStackTrace();
		    }
		        
		 
		
		
		
		extractor.setLine(tag);
		
		while(extractor.hasMoreWords())
		{
			System.out.println(extractor.nextWord());
		}

	}

}
