package webContentAnalyze;

public class WordFreq {
private String word; 
private int frequency;


public WordFreq()
{
}

public WordFreq(String word) {
	super();
	this.word = word;
	this.frequency = 1;
}
public void increaseFreq()
{
	frequency++;
}
public String getWord() {
	return word;
}
public void setWord(String word) {
	this.word = word;
}
public int getFrequency() {
	return frequency;
}
public void setFrequency(int frequency) {
	this.frequency = frequency;
}



}
