package webContentAnalyze;
/**
 * Can Be used to Create a basic Microsoft word document and load content into it.
 * uses the docx4j to do the majority of the work. This is just a basic wrapper class 
 * to make interaction with the library a lot easier
 * 
 * @see http://www.docx4java.org/trac/docx4j
 * @see http://www.docx4java.org/docx4j/javadoc-2.5.0/
 * 
 * For examples @see http://blog.iprofs.nl/2012/09/06/creating-word-documents-with-docx4j/
 * @author JKidney
 * @version 1 
 * 
 *      Created: Nov 8, 2013
 * Last Updated: Nov 8, 2013 - creation (jkidney)
 */

import org.apache.log4j.*;
import org.docx4j.*;
import org.docx4j.openpackaging.exceptions.*;
import org.docx4j.openpackaging.packages.*;
import org.docx4j.openpackaging.parts.WordprocessingML.*;
import org.docx4j.utils.*;
import org.docx4j.wml.*;

import java.io.*;


public class MSWordDocument 
{
	private WordprocessingMLPackage wordMLPackage;
	private MainDocumentPart docMain;
	private String fileName = ""; // just the name of the file with no path
	private String savePath = ""; // just the path to where the file should be saved


	/**
	 * Default constructor
	 * @throws InvalidFormatException 
	 */
	public MSWordDocument() throws InvalidFormatException
	{
		//this turns of loging messages
		Docx4jProperties.getProperties().setProperty(
			    "docx4j.Log4j.Configurator.disabled", "true");
	    Log4jConfigurator.configure();
	    Logger.getRootLogger().setLevel(Level.OFF);
	    
		wordMLPackage = WordprocessingMLPackage.createPackage();
		
		docMain = wordMLPackage.getMainDocumentPart();
	}
	/**
	 * Constructor
	 * @param fileName file name for saving the document, defaults the path to the local directory
	 * @throws InvalidFormatException
	 */
	public MSWordDocument(String fileName)  throws InvalidFormatException
	{
		this();
		this.fileName = fileName;
	}
	/**
	 * Constructor 
	 * @param savePath the path to save the file to
	 * @param fileName the name to use when the file is saved
	 * @throws InvalidFormatException
	 */
	public MSWordDocument(String savePath, String fileName) throws InvalidFormatException 
	{
		this();
		this.fileName = fileName;
		this.savePath = savePath;
	}
	/**
	 * Writes a string to the current position in the document applying a title style
	 * @param title the text to use as the title
	 */
	public void writeTitle(String title)
	{
		docMain.addStyledParagraphOfText("Title",title);
	}
	/**
	 * Writes a string to the current position in the document applying a Heading1 style
	 * @param text the text to use as the title
	 */
	public void writeHeading1(String text)
	{
		docMain.addStyledParagraphOfText("Heading1",text);
	}
	
	/**
	 * Writes a string to the current position in the document
	 * @param text the text to write
	 */
	public void writeText(String text)
	{
		docMain.addParagraphOfText(text);
	}
	
	/**
	 * Adds a page break into the current location in the document
	 */
	public void addPageBreak()
	{
		Br objBr = new Br();
		objBr.setType(STBrType.PAGE);
		docMain.addObject(objBr);
	}
	
	
	
	/**
	 * Save the current version of the document to the given path and file name (savePath + fileName )
	 * @throws IOException thrown if anything went wrong wile saving the file. 
	 */
	public void save() throws IOException
	{
		try
		{
			wordMLPackage.save(new File( savePath + fileName ) );
		}
		catch(Docx4JException error)
		{
			throw new IOException("Unable to save file to: " + savePath + fileName); 
		}
	}


	public void setFileName(String fileName) { this.fileName = fileName; }
	public void setSavePath(String savePath) { this.savePath = savePath; }
}
