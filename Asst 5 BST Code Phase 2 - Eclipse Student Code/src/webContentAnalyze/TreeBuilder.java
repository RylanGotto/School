package webContentAnalyze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import tree.BinarySearchTree;
import tree.treeIterators.BinaryTreeIterator;
import userIO.ConsoleCom;
import comparisonObjects.StringNonCaseSensitiveCompare;
import comparisonObjects.WordFreqCompare;

import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class TreeBuilder extends ConsoleCom{
	
	
	
	ArrayList<WordFreq> freqTextList;
	ArrayList<WordFreq> freqTagList;
	ArrayList<WordFreq> freqErrorList;
	StringNonCaseSensitiveCompare stringComp;
	BinarySearchTree<String, String> bStError;
	BinarySearchTree<String, String> bStTree;
	BinarySearchTree<String, String> bStTag;
	
	public TreeBuilder() throws FileNotFoundException {
		
		
		freqTextList = new ArrayList<WordFreq>();
		freqTagList = new ArrayList<WordFreq>();
		freqErrorList = new ArrayList<WordFreq>();
		stringComp = new StringNonCaseSensitiveCompare();
		bStError = new BinarySearchTree<String, String>(stringComp);
		bStTree = new BinarySearchTree<String, String>(stringComp);	
		bStTag = new BinarySearchTree<String, String>(stringComp);	
		
		popSpellCheck();
		
		
	}
	public void buildTextTree(Document doc)
	{
	
		WordsExtractor extractor = new WordsExtractor();
		
		String result = doc.text();
		String curWord = "";
		extractor.setLine(result);
		while (extractor.hasMoreWords()) {

			curWord = extractor.nextWord();
			
			buildTree(bStTree, freqTextList, curWord);
			
		}
		Collections.sort(freqTextList, new  WordFreqCompare());
	}
	
	public void buildTagTree(Elements elmm)
	{
			String curWord = "";
		    Elements elm = elmm;
	        for (int i = 1;i<elm.size();i++){
	        curWord = elm.get(i).tagName();
	        buildTree(bStTag, freqTagList, curWord);

		}
	        Collections.sort(freqTagList, new  WordFreqCompare());
	}
	
	 public void buildErrorTree()
	 {
		 
		BinaryTreeIterator<String, String> iter = bStTree.getTraversalIterator(bStTree.IN_TRAV);
		while(iter.canMoveToNext())
		{
			if(!bStError.containskey(iter.getCurrentData()))
					{
						freqErrorList.add(new WordFreq(iter.getCurrentData()));
					}
		
					iter.moveToNext();
			
		}
		Collections.sort(freqErrorList, new  WordFreqCompare());
	 }
 
	 
	public ArrayList<WordFreq> getFreqTextList() {
		return freqTextList;
	}


	public void setFreqTextList(ArrayList<WordFreq> freqTextList) {
		this.freqTextList = freqTextList;
	}

	public ArrayList<WordFreq> getFreqTagList() {
		return freqTagList;
	}

	public void setFreqTagList(ArrayList<WordFreq> freqTagList) {
		this.freqTagList = freqTagList;
	}

	public ArrayList<WordFreq> getFreqErrorList() {
		return freqErrorList;
	}

	public void setFreqErrorList(ArrayList<WordFreq> freqErrorList) {
		this.freqErrorList = freqErrorList;
	}





	private void popSpellCheck() throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File("web/words.txt"));
		String curWord = "";
		while(scan.hasNextLine()){
			curWord = scan.nextLine();
			bStError.add(curWord,curWord);
			}
			scan.close();
	}
	
	
	private void buildTree(BinarySearchTree<String, String> tree, ArrayList<WordFreq> list, String word)
	{
		if (tree.containskey(word)) {
			for (int i = 0; i < list.size(); i++) {
				if (stringComp.compare(word, list.get(i).getWord()) == 0) {
					list.get(i).increaseFreq();
				}
			}
		} else {
			tree.add(word, word);
			list.add(new WordFreq(word));
		}
	}
	
	}
	
	
	
	
	

	
	