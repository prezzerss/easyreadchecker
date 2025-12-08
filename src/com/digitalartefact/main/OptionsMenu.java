package com.digitalartefact.main;

import com.digitalartefact.text.*;
import com.digitalartefact.savedata.UserData;

/**
 * Contains helper methods that implement the first 5 options in the options menu.
 */
public class OptionsMenu 
{
	/**
     * Abstract class for sentence checks.
     * All checks:
     *  - Have a short description.
     *  - Print the description as a header.
     *  - Perform their specific checks.
     */
	public static abstract class SentenceCheck
    {
		private String description;

        public SentenceCheck(String description)
        {
        	this.description = description;
        }

        /**
         * Runs this check:
         * - Prints a header using the description
         * - Performs the specific check.
         */
        public void run(String sentence)
        {
        	System.out.println("\n" + description);
            performCheck(sentence);
        }

        /**
         * Each check fills in this method with its own logic.
         */
        protected abstract void performCheck(String sentence);
    }

    /**
     * Option 1:
     * Counts the words in the sentence and prints feedback on the sentence length.
     */
    public static class WordCountCheck extends SentenceCheck
    {
    	public WordCountCheck()
        {
    		super("You have chosen option 1.\nChecking amount words in your sentence...");
        }

        @Override
        protected void performCheck(String sentence)
        {
        	int wordCount = WordCounter.countWords(sentence);
                
            System.out.println("\nThere are " + wordCount + " words in your sentence.");
            switch(wordCount)
            {
            	case 3,4,5,6,7,8,9:
                    System.out.println("This is an excellent length.");
                    break;
                case 10,11,12,13:
                    System.out.println("This is a good length.");
                    break;
                case 14,15,16:
                    System.out.println("This is a fair length. Could be shortened.");
                    break;
                case 17,18:
                    System.out.println("This is a poor length. Suggest shortening.");
                    break;
                default:
                    if(wordCount > 18)
                    {
                    	System.out.println("This is a bad length. Suggest shortening.");
                    }
                    else
                    {
                    	System.out.println("This is an invalid length.");
                    }
                    break;
            }
        }
    }

    /**
     * Option 2:
     * Checks each word length and prints warnings for words over the 12 character limit.
     */
    public static class WordLengthCheck extends SentenceCheck
    {
    	public WordLengthCheck()
    	{
    		super("You have chosen option 2.\nChecking word lengths in your sentence...");
    	}
    	
    	@Override
    	protected void performCheck(String sentence)
    	{
    		WordLengthChecker.checkWordLength(sentence);
    	} 	
    }
    
    /**
     * Option 3:
     * Checks for any hard words and prints suggested alternative words and definitions.
     */
    public static class HardWordCheck extends SentenceCheck
    {
    	public HardWordCheck()
    	{
    		super("You have chosen option 3.\nChecking for any difficult words in your sentence...");
    	}
    	
    	@Override
    	protected void performCheck(String sentence)
    	{
    		HardWordChecker.checkForHardWords(sentence);
    	}	
    }
    
    /**
     * Option 4:
     * Checks for written out numbers and suggests using digits instead.
     */
    public static class DigitCheck extends SentenceCheck
    {
    	public DigitCheck()
    	{
    		super("You have chosen option 4.\nChecking for any written out numbers in your sentence...");
    	}
    	
    	@Override
    	protected void performCheck(String sentence)
    	{
    		DigitsChecker.checkForNumbers(sentence);
    	}	
    }
    
    /**
     * Array of available sentence checks for options 1–4.
     * Index 0 = option 1, index 1 = option 2, etc.
     */
    private static final SentenceCheck[] CHECKS = 
    {
    	new WordCountCheck(),
    	new WordLengthCheck(),
        new HardWordCheck(),
        new DigitCheck()  
    };
    
    /**
     * Runs the sentence checks based off the user's option choice (1–4).
     */
    public static void runCheckOption(int option, String sentence)
    {
    	int i = option - 1;
    	if (i >= 0 && i < CHECKS.length)
    	{
    		CHECKS[i].run(sentence);
    		}
    	else
    	{
    		System.out.println("\nThat's an invalid option - please try again.\n");
    	}
    }
    
    /**
     * Option 5:
     * Prints all the past sentences saved for the current user.
     */
    public static void optionFive(UserData currentUser)
    {
    	System.out.println("\nYour past sentences:");
    	int i = 1;
    	for (String s : currentUser.getAllSentences())
    	{
    		System.out.println(i + ". " + s);
    		i++;	
    	}
    }   
}


	
	/*
	/*
     * Option 1:
     * Counts the words in the sentence and prints feedback on the sentence length.

	public static void optionOne(String sentence)
	{
		System.out.println("\nYou have chosen option 1.");
		System.out.println("Checking amount words in your sentence...");
		
		int wordCount = WordCounter.countWords(sentence);
		
		System.out.println("\nThere are " + wordCount + " words in your sentence.");
		switch(wordCount)
        {
            case 3,4,5,6,7,8,9:
        		System.out.println("This is an excellent length.");
                break;
            case 10,11,12,13:
            	System.out.println("This is a good length.");
                break;
            case 14,15,16:
            	System.out.println("This is a fair length. Could be shortened.");
                break;
            case 17,18:
            	System.out.println("This is a poor length. Suggest shortening.");
                break;
            default:
                if(wordCount > 18)
                {
                	System.out.println("This is a bad length. Suggest shortening.");
                }
                else
                {
                	System.out.println("This is an invalid length.");
                }
                break;
        }
	}
	
	/*
     * Option 2:
     * Checks each word length and prints warnings for words over the 12 character limit.
     
	public static void optionTwo(String sentence)
	{
		System.out.println("\nYou have chosen option 2.");
		System.out.println("Checking word lengths in your sentence...");
		WordLengthChecker.checkWordLength(sentence);
	}
	
	/*
     * Option 3:
     * Checks for any hard words and prints suggested alternative words and definitions.
     
	public static void optionThree(String sentence)
	{
		System.out.println("\nYou have chosen option 3.");
		System.out.println("Checking for any difficult words in your sentence...");
		HardWordChecker.checkForHardWords(sentence);
	}
	
	/*
     * Option 4:
     * Checks for written out numbers and suggests using digits instead.
     
	public static void optionFour(String sentence)
	{
		System.out.println("\nYou have chosen option 4.");
		System.out.println("Checking for any written out numbers in your sentence...");
		DigitsChecker.checkForNumbers(sentence);
	}
	
	/*
     * Option 5:
     * Prints all the past sentences saved for the current user.
    
	public static void optionFive(UserData currentUser)
	{
		System.out.println("\nYour past sentences:");
		int i = 1;
		for (String s : currentUser.getAllSentences())
		{
			System.out.println(i + ". " + s);
			i++;
		}
	}
	*/
