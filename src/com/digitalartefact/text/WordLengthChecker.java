package com.digitalartefact.text;

/**
 * Checks each word length and prints warnings for words over the 12 character limit.
 */
public class WordLengthChecker 
{
	// Maximum recommended word length for Easy Read sentences.
	private static final int characterLimit = 12;
	
	/**
     * Checks each word in the input and prints a warning if any word is longer than the characterLimit.
     */
	public static void checkWordLength(String input)
	{
		if(input == null || input.isEmpty())
		{
			System.out.println("No words to check.");
			return;
		}
		
		String [] splitWords = input.split("\\s+");
		
		boolean anyTooLong = false;
		
		for(String word : splitWords)
		{
			if(word.isEmpty())
			{
				continue;
			}
			
			String cleaned = word.replaceAll("^[^\\p{L}\\p{N}]+", "");
            cleaned = cleaned.replaceAll("[^\\p{L}\\p{N}]+$", "");
            
            if(cleaned.isEmpty() || !cleaned.matches(".*[\\p{L}\\p{N}].*"))
            {
            	continue;
            }
            
            int length = cleaned.length();
            
            if(length > characterLimit)
            {
            	anyTooLong = true;
            	
            	System.out.println("\nThe word " + cleaned.toUpperCase() + " is too long (" + length + " characters).");
				System.out.println("This word should be changed or shortened!");
            }
		}
		
		if(!anyTooLong)
        {
        	System.out.println("\nWord lengths are fine!");
        }
	}
}

