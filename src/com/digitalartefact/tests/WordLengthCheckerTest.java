
package com.digitalartefact.tests;

import com.digitalartefact.text.WordLengthChecker;

/**
 * Manual test for the WordLengthChecker class.
 */
public class WordLengthCheckerTest 
{
	public static void main(String[] args)
	{
		testWordLengthChecker();
	}
	
	
	private static void testWordLengthChecker()
	{
		System.out.println("Testing Word Length Checker");
		
		// Expect "No words to check".
		System.out.println("\nTest 1 (null):");
		WordLengthChecker.checkWordLength(null);
		
		// Expect "No words to check".
		System.out.println("\nTest 2 (empty string):");
		WordLengthChecker.checkWordLength("");
		
		// Expect null.
		System.out.println("\nTest 3 (one short word):");
		WordLengthChecker.checkWordLength("Hello");
		
		// Expect null.
		System.out.println("\nTest 4 (multiple short words):");
		WordLengthChecker.checkWordLength("This is an easy read checker");
		
		// Expect warning.
		System.out.println("\nTest 5 (one long word):");
		WordLengthChecker.checkWordLength("Accessibility");
		
		// Expect multiple warnings.
		System.out.println("\nTest 6 (multiple long words):");
		WordLengthChecker.checkWordLength("Communication Consultation Participation Organisation Evaluation");
		
		// Expect multiple warnings.
		System.out.println("\nTest 7 (mixed words):");
		WordLengthChecker.checkWordLength("This paragraph contains accessibility and understanding");
		
		// Expect multiple warnings.
		System.out.println("\nTest 8 (mixed words with punctuation):");
		WordLengthChecker.checkWordLength("This paragraph, contains accessibility, and understanding!");
		
		// Expect null.
		System.out.println("\nTest 9 (unconventional spacing):");
		WordLengthChecker.checkWordLength("Hello     world    again");
		
		// Expect warning.
		System.out.println("\nTest 10 (unconventional spacing with long word):");
		WordLengthChecker.checkWordLength("This   is   about      accessibility");
		
		// Expect null.
		System.out.println("\nTest 11 (tabs/new lines):");
        WordLengthChecker.checkWordLength("This\tis\nan\teasy\tread\nchecker");
        
        // Expect warning.
        System.out.println("\nTest 12 (tabs/new lines with long word):");
        WordLengthChecker.checkWordLength("This\tis\nabout\taccessibility\n");
        
        // Expect null.
        System.out.println("\nTest 13 (symbols only):");
        WordLengthChecker.checkWordLength("!@#$%^&*()_+-=");
        
        System.out.println("\nTest Complete");
	}
}