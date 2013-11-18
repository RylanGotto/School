/**
 * Startup point for web page content analyzer program
 * 
 * @author JKidney
 * @version 1 
 * 
 *      Created: Nov 7, 2013
 * Last Updated: Nov 7, 2013 - creation (jkidney)
 */


import webContentAnalyze.MSWordDocument;
import webContentAnalyze.WebContentAnalyzer;
import userIO.*;

import java.io.*;

public class WebConAnalyzer 
{
	public static void main(String[] args)
	{
		try
		{
			WebContentAnalyzer pageAnalyzer = new WebContentAnalyzer();
			ConsoleCom com = new ConsoleCom();
			MSWordDocument report; 
			String requestedPath="", fileName = "";

			
			requestedPath = com.getInputString("Enter path: ");
			
			com.println("Gathering data and generating report (this may take a while)");
			report = pageAnalyzer.generateReportForPageContent(requestedPath);
			
			fileName = com.getFileName_removeTags("Enter file name for report (no tags): ");
			fileName += ".docx";
			
			com.println("Saving report to: " + fileName);
			report.setFileName(fileName);
			com.println("Done");
			
			report.save();
		}
		catch(IOException conectionerror) 
		{
			System.out.println("Error: unable to open page : " + conectionerror.getMessage());	
		}
		catch(Exception error)
		{
		  System.out.println("Error: An unknonw error has occured: " + error.getMessage());
		  error.printStackTrace();
		}
	}
}
