
package com.digitalartefact.tests;

import com.digitalartefact.text.WordCounter;

/**
 * Manual tests for the WordCounter class.
 */
public class WordCounterTest 
{
	public static void main(String[] args)
	{
		testWordCounter();
	}
	
	
	private static void testWordCounter()
	{
		System.out.println("Testing Word Counter Method\n");
		
		// Expect 0
		System.out.println("Test 1 (null): " + WordCounter.countWords(null));
		// Expect 0
		System.out.println("Test 2 (empty string): " + WordCounter.countWords(""));
		// Expect 0
		System.out.println("Test 3 (only spaces): " + WordCounter.countWords("      "));
		// Expect 0
		System.out.println("Test 4 (tabs/new lines): " + WordCounter.countWords("\t \n"));
		// Expect 1
		System.out.println("Test 5 ('Hello'): " + WordCounter.countWords("Hello"));
		// Expect 2
		System.out.println("Test 6 ('Hello world'): " + WordCounter.countWords("Hello world"));
		// Expect 3
		System.out.println("Test 7 (unconventional spacing): " + WordCounter.countWords("Hello 	world     again"));
		// Expect 5
		System.out.println("Test 8 (punctuation): " + WordCounter.countWords("Hello, world! I am here."));
		// Expect 3
		System.out.println("Test 9 (numbers): " + WordCounter.countWords("123 456 789"));
		// Expect 0
		System.out.println("Test 10 (symbols): " + WordCounter.countWords("!@#£$%^&*()_+-="));
		// Expect 6
		System.out.println("Test 11 (mixed punctuation and symbols): " + WordCounter.countWords("Hello world! My email, is hell@world.com :)"));
		// Expect 18
		System.out.println("Test 12 (long sentence): " + WordCounter.countWords("This is a long sentence written to test how well the word counter works when given many words."));
		// Expect 4
		System.out.println("Test 13 (unicode letters): " + WordCounter.countWords("Café naïve résumé coöperate"));
		
		System.out.println("\nTest Complete");
	}
}