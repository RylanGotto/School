package comparisonObjects;

import java.util.Comparator;

import webContentAnalyze.WordFreq;

public class WordFreqCompare implements Comparator<WordFreq> {
		@Override
		public int compare(WordFreq s1,WordFreq s2) {

			return s1.getWord().toLowerCase().compareTo(s2.getWord().toLowerCase());
		}
	}

