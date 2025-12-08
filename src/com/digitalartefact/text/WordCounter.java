package com.digitalartefact.text;

/**
 * Utility class for counting words in a sentence.
 * A word is counted if it contains at least 1 letter or digit.
 */
public class WordCounter 
{
    /**
     * Counts how many words are in the sentence.
     * Returns the number of words found (0 if the input is null or has no words).
     */
	public static int countWords(String input)
	{
		if(input == null)
		{
			return 0;
		}
		
		int counter = 0;
		
		String [] splitWords = input.split("\\s+");
		
		for(int i = 0; i < splitWords.length; i++)
		{
			String individualWord = splitWords[i];
			
			if(individualWord.matches(".*[A-Za-z0-9].*"))
			{
				counter++;
			}
		}
		
		return counter;
	}
}

