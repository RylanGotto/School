package webContentAnalyze;
/**
 * Can be used to extract words from a given string. Words in this case
 * are defined to be any sequence of non-whitespace characters.
 * 
 * @author JKidney
 * @version 1 
 * 
 *      Created: Nov 8, 2013
 * Last Updated: Nov 8, 2013 - creation (jkidney)
 */

import java.util.regex.*;
public class WordsExtractor 
{
	private  Matcher wordMatcher = null;
	
	/**
	 * Default constructor 
	 */
	public WordsExtractor() {}
	/**
	 * Constructor
	 * @param line the line to extract words from 
	 */
	public WordsExtractor(String line)
	{
	   setLine(line);		
	}
	/**
	 * Sets the line to extract words from. It will overwrite any
	 * previous lines used.
	 * @param line
	 */
	public void setLine(String line)
	{
		 //Regular Expressions 
		 Pattern p = Pattern.compile("(?<!\\S)\\S+(?!\\S)");
		 wordMatcher = p.matcher(line);
	}
	/**
	 * Determines if their are still more words that can be extracted by the
	 * currently set line
	 * @return true if more words, false otherwise
	 */
	public boolean hasMoreWords()
	{
		return !wordMatcher.hitEnd();
	}
	
	/**
	 * Extracts and returns the next word from the line
	 * @return null if no more words in the line otherwise the next word in the line is returned
	 */
	public String nextWord()
	{
	  String word = null;
	  
	  if(wordMatcher.find())
		  word = wordMatcher.group();
	  
	  return word;
	}
}
