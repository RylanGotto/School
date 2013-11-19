package webContentAnalyze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import tree.BinarySearchTree;
import tree.treeIterators.BinaryTreeIterator;
import userIO.ConsoleCom;
import comparisonObjects.StringNonCaseSensitiveCompare;

import org.jsoup.select.*;

public class TreeBuilder extends ConsoleCom
{

	private StringNonCaseSensitiveCompare stringComp;
	private BinarySearchTree<String, String> bStSplChk;
	private BinarySearchTree<String, WordFreq> bStPageWord;
	private BinarySearchTree<String, WordFreq> bStTag;
	private BinarySearchTree<String, String> bStBadWord;

	public TreeBuilder()
	{

		stringComp = new StringNonCaseSensitiveCompare();
		bStSplChk = new BinarySearchTree<String, String>(stringComp);
		bStBadWord = new BinarySearchTree<String, String>(stringComp);
		bStPageWord = new BinarySearchTree<String, WordFreq>(stringComp);
		bStTag = new BinarySearchTree<String, WordFreq>(stringComp);

	}

	public void buildTrees(Elements listOfElements) throws FileNotFoundException
	{
		WordsExtractor extractor = new WordsExtractor();
		String tag = "";
		String apageWord = "";
		
		popSpellCheck();
		
		for (int i = 0; i < listOfElements.size(); i++)
		{
			tag = listOfElements.get(i).tagName();

			if (bStTag.containskey(tag))
			{
				bStTag.find(tag).increaseFreq();
			} else
			{
				bStTag.add(tag, new WordFreq(tag));
			}
			if (listOfElements.get(i).hasText())
			{
				extractor.setLine(listOfElements.get(i).ownText());

				while (extractor.hasMoreWords())
				{
					apageWord = extractor.nextWord();
					if (apageWord != null)
					{
						if (bStPageWord.containskey(apageWord))
						{
							bStPageWord.find(apageWord).increaseFreq();
						} else if (bStSplChk.containskey(apageWord))
						{
							bStPageWord.add(apageWord, new WordFreq(apageWord));
						} else
						{
							if (!bStBadWord.containskey(apageWord))
								bStBadWord.add(apageWord, apageWord);

							bStPageWord.add(apageWord, new WordFreq(apageWord));
						}
					}

				}
			}
		}
		
		
	}

	private void popSpellCheck() throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File("web/words.txt"));
		String curWord = "";
		while (scan.hasNextLine())
		{
			curWord = scan.nextLine();
			bStSplChk.add(curWord, curWord);
		}
		scan.close();
	}

	public BinarySearchTree<String, String> getbStSplChk()
	{
		return bStSplChk;
	}

	public void setbStSplChk(BinarySearchTree<String, String> bStSplChk)
	{
		this.bStSplChk = bStSplChk;
	}

	public BinarySearchTree<String, WordFreq> getbStPageWord()
	{
		return bStPageWord;
	}

	public void setbStPageWord(BinarySearchTree<String, WordFreq> bStPageWord)
	{
		this.bStPageWord = bStPageWord;
	}

	public BinarySearchTree<String, WordFreq> getbStTag()
	{
		return bStTag;
	}

	public void setbStTag(BinarySearchTree<String, WordFreq> bStTag)
	{
		this.bStTag = bStTag;
	}

	public BinarySearchTree<String, String> getbStBadWord()
	{
		return bStBadWord;
	}

	public void setbStBadWord(BinarySearchTree<String, String> bStBadWord)
	{
		this.bStBadWord = bStBadWord;
	}

}
