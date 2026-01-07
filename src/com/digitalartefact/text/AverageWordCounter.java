package com.digitalartefact.text;

import java.util.List;

/**
 * Calculates an average word count for a sentence using the WordCounter 
 * class and dividing the total words by the amount of saved sentences.
 */
public class AverageWordCounter 
{
	public static double averageWords(List<String> sentences)
	{
		if(sentences == null || sentences.isEmpty()) 
		{
			return 0.0;
		}
		
		int totalWords = 0;
		
		for(String s : sentences)
		{
			totalWords += WordCounter.countWords(s);
		}
		
		return (double) totalWords / sentences.size();
	}
}
