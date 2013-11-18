package webContentAnalyze;

/**
 * Used to generate Web Content Analyzer Statistics and other required information
 * This class uses the JSOUP Library to download and gather information for a given 
 * webpage
 * @see http://jsoup.org/
 * 
 * @author JKidney
 * @version 1 
 * 
 *      Created: Nov 7, 2013
 * Last Updated: Nov 7, 2013 - creation (jkidney)
 */

import java.io.*;
import java.util.ArrayList;
import java.util.regex.*;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import comparisonObjects.CompareObjects;
import comparisonObjects.StringCaseSensitiveCompare;
import comparisonObjects.StringNonCaseSensitiveCompare;
import tree.BinarySearchTree;
import userIO.ConsoleCom;

public class WebContentAnalyzer {
	public static final String correctWordFileLocation = "words.txt";

	/**
	 * Default constructor
	 */
	public WebContentAnalyzer() throws Exception {

	}
	
	
	/**
	 * Loads a given web page and builds content analysis information Works for
	 * a local file of a page on the Internet
	 * 
	 * @param path
	 *            the path to the web page to load ( either local file or full
	 *            URL )
	 * @return an MSWordDocument object that contains all information gathered
	 *         about the web content
	 * @throws Exception
	 *             if any errors occur when building
	 */
	public MSWordDocument generateReportForPageContent(String path)
			throws Exception {
		
		 // this is the JSoup document object you will get
								// tags and words from
		

		// Gather data from the given web page content
		// You should break this down into a better design, do not just include
		// all code
		// in this single method.

		
		TreeBuilder builder = new TreeBuilder();
		builder.buildTextTree(getDOM(path));
		builder.buildTagTree(getDOM(path).getAllElements());
		builder.buildErrorTree();
		// Generate Document based upon the given data

		// Note: The library that is used to generate the MS word document has a
		// very
		// slow startup time when the document is generated for the very first
		// time
		// (around 10 seconds)
		MSWordDocument document = new MSWordDocument();
		document = format(document, path, builder);

		return document;
	}
	
	private MSWordDocument format(MSWordDocument document, String path, TreeBuilder build)
	{
		
		document.writeTitle("Report for: " + path);

		
		document = formatDocument(build.getFreqTextList(), "Word Frequency", document);
		
		document = formatDocument(build.getFreqTagList(), "Tag Frequency", document);
		
		document = formatDocument(build.getFreqErrorList(), "Misspelling", document, true);
		
		return document;
		
	}
	
	private MSWordDocument formatDocument(ArrayList<WordFreq> list, String heading, MSWordDocument document)
	{
		MSWordDocument modify = document;
		modify.writeHeading1(heading);
		for (int i = 0; i<list.size();i++)
		{
			
			modify.writeText(String.format("(%10s) %s", list.get(i).getFrequency(), list.get(i).getWord()));
			
		}
		modify.addPageBreak();
		
		return modify;
	}
	MSWordDocument formatDocument(ArrayList<WordFreq> list, String heading, MSWordDocument document, boolean TurnOffFreq)
	{
		MSWordDocument modify = document;
		modify.writeHeading1(heading);
		for (int i = 0; i<list.size();i++)
		{
			
			modify.writeText(String.format(list.get(i).getWord()));
			
		}
		modify.addPageBreak();
		
		return modify;
	}
	/**
	 * Loads and builds a web page from the local file system
	 * 
	 * @param path
	 *            the path to the local page
	 * @return the constructed document object
	 * @throws Exception
	 *             if any errors occur when building
	 */
	private Document buildFromLocalFile(String path) throws IOException {
		return Jsoup.parse(new File(path), "UTF-8", "");
	}

	/**
	 * Loads and builds a web page from an Internet source
	 * 
	 * @param path
	 *            the path to the local page
	 * @return the constructed document object
	 * @throws Exception
	 *             if any errors occur when building
	 */
	private Document buildFromInternetPage(String url) throws IOException {
		return Jsoup.connect(url).get();
	}

	/**
	 * Helper method used to check if a given string is a valid URL or not
	 * 
	 * @param urlStr
	 *            the string to check
	 * @return true for valid false otherwise
	 */
	private boolean isValidURL(String urlStr) {
		boolean result = false;
		Pattern p = Pattern
				.compile("(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?");
		Matcher m = p.matcher(urlStr);
		result = m.matches();
		return result;
	}

	/**
	 * Checks to see if the url string beings with http:// or https:// if it
	 * does not start with either it will add http:// to the url and return the
	 * new string.
	 * 
	 * @param url
	 *            the url path to check
	 * @return the updated url path ( with HTTP:// added if needed)
	 */
	private String ensurePathHasProtocol(String url) {
		if (!url.startsWith("http://") && !url.startsWith("https://"))
			url = "http://" + url;
		return url;
	}
	
	private Document getDOM(String path) throws IOException
	{
		Document doc = null;
		
				// check and see if the given path is for a local file or
				// a page on the Internet
				if (isValidURL(path)) {
					path = ensurePathHasProtocol(path);
					doc = buildFromInternetPage(path);
				} else
					// file on the local machine
					doc = buildFromLocalFile(path);
		return doc;
		
	}
	
}
