package com.digitalartefact.text;

import java.util.ArrayList;

/**
 * Detects written out numbers in a sentence (for example "twenty one" or
 * "one hundred and five") and suggests changing them to digits.
 */
public class DigitsChecker 
{
	
	private static ArrayList<String> onesList =  new ArrayList<String>();
	
	private static ArrayList<String> tensList =  new ArrayList<String>();
	
	private static ArrayList<String> hundredsList = new ArrayList<String>();
	
	private static ArrayList<String> tensAndOnesList = new ArrayList<String>();
	
	private static ArrayList<String> hundredsAndOnesList = new ArrayList<String>();
	
	private static ArrayList<String> hundredsAndTensList = new ArrayList<String>();
	
    // Adding to the lists of written out numbers (ones, tens, hundreds).
    // Building combinations like "twenty one", "one hundred and five".
	static 
	{
		String[] ones = 
			{
				"zero","one","two","three","four","five","six","seven",
				"eight","nine","ten","eleven","twelve","thirteen","fourteen",
				"fifteen","sixteen","seventeen","eighteen","nineteen"
			};
		
		for(int i = 0; i < ones.length; i++)
		{
			onesList.add(ones[i]);
		}
		
		String[] tens = 
			{
				"twenty","thirty","forty","fifty","sixty","seventy",
				"eighty","ninety"
			};
		
		for(int i = 0; i < tens.length; i++)
		{
			tensList.add(tens[i]);
		}
		
		
		for(int i = 1; i <= 9; i++)
		{
			hundredsList.add(onesList.get(i) + " hundred");
		}
		
		for(int i = 0; i < tensList.size(); i++)
		{
			for(int j = 1; j < onesList.size() && j < 10; j++)
			{
				tensAndOnesList.add(tensList.get(i) + " " + onesList.get(j));
			}
		}
		
		for(int i = 0; i < hundredsList.size(); i++)
		{
			for(int j = 1; j < onesList.size(); j++)
			{
				hundredsAndOnesList.add(hundredsList.get(i) + " and " + onesList.get(j));
			}
		}
		
		for(int i = 0; i < hundredsList.size(); i++)
		{
			for(int j = 1; j < tensList.size(); j++)
			{
				hundredsAndTensList.add(hundredsList.get(i) + " and " + tensList.get(j));
			}
		}
		
	}
	
	/**
     * Checks the input sentence for written out numbers up to 999
     * and prints suggestions to use digits instead.
     */
	public static void checkForNumbers(String sentence)
	{
	    if(sentence == null || sentence.isEmpty())
	    {
	        System.out.println("\nNo sentence to check.");
	        return;
	    }

	    String lowerCaseSentence = sentence.toLowerCase().replace("-", " ");
	    String[] indivWords = lowerCaseSentence.split("\\s+");
	    ArrayList<String> words = new ArrayList<>();

	    for(String w : indivWords)
	    {
	        if(w.isEmpty())
	            continue;

	        String cleaned = w.replaceAll("^[^a-z]+", "");
	        cleaned = cleaned.replaceAll("[^a-z]+$", "");

	        if(!cleaned.isEmpty())
	            words.add(cleaned);
	    }

	    if(words.isEmpty())
	    {
	        System.out.println("\nNo words to check.");
	        return;
	    }

	    boolean foundAny = false;

	    for(int i = 0; i < words.size(); i++)
	    {
	        String wordOne = words.get(i);

	        if(i + 3 < words.size())
	        {
	            String wordTwo = words.get(i + 1);
	            String wordThree = words.get(i + 2);
	            String wordFour = words.get(i + 3);

	            if(onesList.contains(wordOne) && "hundred".equals(wordTwo) && "and".equals(wordThree)
	                    && (onesList.contains(wordFour) || tensList.contains(wordFour)))
	            {
	                String phrase = wordOne + " " + wordTwo + " " + wordThree + " " + wordFour;
	                System.out.println("\nThe number \"" + phrase.toUpperCase() + "\" is written out - please change this to digits.");
	                foundAny = true;
	                i += 3;
	                continue;
	            }
	        }

	        if(i + 2 < words.size())
	        {
	            String wordTwo = words.get(i + 1);
	            String wordThree = words.get(i + 2);

	            if(onesList.contains(wordOne) && "hundred".equals(wordTwo)
	                    && (onesList.contains(wordThree) || tensList.contains(wordThree)))
	            {
	                String phrase = wordOne + " " + wordTwo + " " + wordThree;
	                System.out.println("\nThe number \"" + phrase.toUpperCase() + "\" is written out - please change this to digits.");
	                foundAny = true;
	                i += 2;
	                continue;
	            }
	        }

	        if(i + 1 < words.size())
	        {
	            String wordTwo = words.get(i + 1);

	            if(tensList.contains(wordOne) && onesList.contains(wordTwo))
	            {
	                String phrase = wordOne + " " + wordTwo;
	                System.out.println("\nThe number \"" + phrase.toUpperCase() + "\" is written out - please change this to digits.");
	                foundAny = true;
	                i += 1;
	                continue;
	            }
	        }
	        if(onesList.contains(wordOne) || tensList.contains(wordOne))
	        {
	            System.out.println("\nThe number \"" + wordOne.toUpperCase() + "\" is written out - please change this to digits.");
	            foundAny = true;
	        }
	    }
	    if(!foundAny)
	    {
	        System.out.println("\nNo written out numbers found.");
	    }
	}
}
