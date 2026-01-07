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
	 * Returns basic written out numbers (1-19) and tens (10,20,30 etc) 
	 * as digits.
	 * @param word
	 * @return correct related digit
	 */
	private static int wordToBasicNumber(String word)
	{
		switch(word)
		{
			case "zero": return 0;
			case "one": return 1;
			case "two": return 2;
            case "three": return 3;
            case "four": return 4;
            case "five": return 5;
            case "six": return 6;
            case "seven": return 7;
            case "eight": return 8;
            case "nine": return 9;
            case "ten": return 10;
            case "eleven": return 10 + 1;
            case "twelve": return 10 + 2;
            case "thirteen": return 10 + 3;
            case "fourteen": return 10 + 4;
            case "fifteen": return 10 + 5;
            case "sixteen": return 10 + 6;
            case "seventeen": return 10 + 7;
            case "eighteen": return 10 + 8;
            case "nineteen": return 10 + 9;
            case "twenty": return 20;
            case "thirty": return 30;
            case "forty": return 40;
            case "fifty": return 50;
            case "sixty": return 60;
            case "seventy": return 70;
            case "eighty": return 80;
            case "ninety": return 90;
            default: return -1;
		}
	}
	
	/**
	 * Checks the sentence for written out numbers (up to 999).
	 * Prints warnings telling the user to change them to digits.
	 * Prints a suggested sentence with digits instead of words.
	 */
	public static void checkForNumbers(String sentence)
	{
		if(sentence == null || sentence.isEmpty())
		{
			System.out.println("\nNo sentence to check!");
			return;
		}
		
		String[] words = sentence.split("\\s+");
		ArrayList<String> suggestedWords = new ArrayList<String>();
		
		boolean foundAny = false;
		int i = 0;
		
		while(i < words.length)
		{
			String word = words[i];
			String cleaned = cleanWordForNumber(word);
			boolean handled = false;
			
			// Look for a 2 word number that is hyphenated like "twenty-two".
			if(!cleaned.isEmpty() && cleaned.contains("-"))
			{
				String[] hyphenParts = cleaned.split("-");
				
				if(hyphenParts.length == 2 && tensList.contains(hyphenParts[0]) && onesList.contains(hyphenParts[1]))
				{
					int value = wordToBasicNumber(hyphenParts[0]) + wordToBasicNumber(hyphenParts[1]);
					String writtenNumber = word;
					System.out.println("\nThe number \"" + writtenNumber.toUpperCase() + "\" is written out - please change this to digits (" + value + ").");
					
					String punctuation = getPunctuation(word);
					String newWord = value + punctuation;
					
					suggestedWords.add(newWord);
					foundAny = true;
					handled = true;
				}
			}
			
			if(handled)
			{
				i++;
				continue;
			}
			
			// Look for a 5 word number like "two hundred and twenty two".
			if(i + 4 < words.length)
			{
				String firstWord = cleanWordForNumber(words[i]);
				String secondWord = cleanWordForNumber(words[i + 1]);
				String thirdWord = cleanWordForNumber(words[i + 2]);
				String fourthWord = cleanWordForNumber(words[i + 3]);
				String fifthWord = cleanWordForNumber(words[i + 4]);
				
				if(onesList.contains(firstWord)
					&& "hundred".equals(secondWord) 
					&& "and".equals(thirdWord)
					&& tensList.contains(fourthWord)
					&& onesList.contains(fifthWord))
				{
					int hundreds = wordToBasicNumber(firstWord) * 100;
					int tens = wordToBasicNumber(fourthWord);
					int ones = wordToBasicNumber(fifthWord);
					int value = hundreds + tens + ones;
					
					String writtenNumber = firstWord + " " + secondWord + " " + thirdWord + " " + fourthWord + " " + fifthWord;
					System.out.println("\nThe number \"" + writtenNumber.toUpperCase() + "\" is written out - please change this to digits (" + value + ").");
					
					
					String punctuation = getPunctuation(words[i + 4]);
					String newWord = value + punctuation;
					
					suggestedWords.add(newWord);
					foundAny = true;
					
					i += 5;
					continue;
				}
			}
			
			// Look for a 4 word number like "two hundred and twenty".
			if(i + 3 < words.length)
			{
				String firstWord = cleanWordForNumber(words[i]);
				String secondWord = cleanWordForNumber(words[i + 1]);
				String thirdWord = cleanWordForNumber(words[i + 2]);
				String fourthWord = cleanWordForNumber(words[i + 3]);
				
				if(onesList.contains(firstWord)
					&& "hundred".equals(secondWord) 
					&& "and".equals(thirdWord)
					&& (onesList.contains(fourthWord) || tensList.contains(fourthWord)))
				{
					int hundreds = wordToBasicNumber(firstWord) * 100;
					int onesOrTens = wordToBasicNumber(fourthWord);
					int value = hundreds + onesOrTens;
					
					String writtenNumber = firstWord + " " + secondWord + " " + thirdWord + " " + fourthWord;
					System.out.println("\nThe number \"" + writtenNumber.toUpperCase() + "\" is written out - please change this to digits (" + value + ").");
					
					
					String punctuation = getPunctuation(words[i + 3]);
					String newWord = value + punctuation;
					
					suggestedWords.add(newWord);
					foundAny = true;
					
					i += 4;
					continue;
				}
			}
			
			// Look for a 3 word number like "two hundred two".
			if(i + 2 < words.length)
			{
				String firstWord = cleanWordForNumber(words[i]);
				String secondWord = cleanWordForNumber(words[i + 1]);
				String thirdWord = cleanWordForNumber(words[i + 2]);
				
				if(onesList.contains(firstWord)
					&& "hundred".equals(secondWord) 
					&& (onesList.contains(thirdWord) || tensList.contains(thirdWord)))
				{
					int hundreds = wordToBasicNumber(firstWord) * 100;
					int onesOrTens = wordToBasicNumber(thirdWord);
					int value = hundreds + onesOrTens;
					
					String writtenNumber = firstWord + " " + secondWord + " " + thirdWord;
					System.out.println("\nThe number \"" + writtenNumber.toUpperCase() + "\" is written out - please change this to digits (" + value + ").");
					
					
					String punctuation = getPunctuation(words[i + 2]);
					String newWord = value + punctuation;
					
					suggestedWords.add(newWord);
					foundAny = true;
					
					i += 3;
					continue;
				}
			}
			
			// Look for a 2 word number like "twenty two".
			if(i + 1 < words.length)
			{
				String firstWord = cleanWordForNumber(words[i]);
				String secondWord = cleanWordForNumber(words[i + 1]);
				
				if(tensList.contains(firstWord) && onesList.contains(secondWord))
				{
					int tens = wordToBasicNumber(firstWord);
					int ones = wordToBasicNumber(secondWord);
					int value = tens + ones;
					
					String writtenNumber = firstWord + " " + secondWord;
					System.out.println("\nThe number \"" + writtenNumber.toUpperCase() + "\" is written out - please change this to digits (" + value + ").");
					
					String punctuation = getPunctuation(words[i + 1]);
					String newWord = value + punctuation;
					
					suggestedWords.add(newWord);
					foundAny = true;
					
					i += 2;
					continue;
				}
			}
			
			int baseValue = cleaned.isEmpty() ? -1 : wordToBasicNumber(cleaned);
			
			// Look for a 1 word number like "two".
			if(baseValue >= 0)
			{
				
				String writtenNumber = word;			
				System.out.println("\nThe number \"" + writtenNumber.toUpperCase() + "\" is written out - please change this to digits (" + baseValue + ").");
				
				String punctuation = getPunctuation(word);
				String newWord = baseValue + punctuation;
				
				suggestedWords.add(newWord);
				foundAny = true;
				i++;
				continue;
			}
			
			else
			{
				suggestedWords.add(word);
				i++;
			}
		}
		
		if(!foundAny)
		{
			System.out.println("\nNo written out numbers found!");
		}
		
		else
		{
			StringBuilder stringBuilder = new StringBuilder();
			
			for(int j = 0; j < suggestedWords.size(); j++)
			{
				if(j > 0)
				{
					stringBuilder.append(" ");
				}
				stringBuilder.append(suggestedWords.get(j));
			}
			
			System.out.println("\nSuggested sentence: " + stringBuilder.toString());
		}
	}
	
	/**
	 * Returns a cleaned version of the word for detecting a
	 * written out number:
	 * - makes it lowercase.
	 * - removes punctuation at beginning and end of word (exception for hyphens).
	 */
	private static String cleanWordForNumber(String word)
	{
		String cleaned = word.toLowerCase();
		cleaned = cleaned.replaceAll("^[^a-z]+", "");
		cleaned = cleaned.replaceAll("[^a-z-]+$", "");
		return cleaned;
	}
	
	/**
	 * Returns any punctuation at the end of a word.
	 * Example:
	 * - "hello," will return ","
	 */
	private static String getPunctuation(String word)
	{
		int position = word.length() - 1;
		
		while(position >= 0)
		{
			char c = word.charAt(position);
			
			if(Character.isLetterOrDigit(c))
			{
				break;
			}
			position--;
		}
		return word.substring(position + 1);
	}
}