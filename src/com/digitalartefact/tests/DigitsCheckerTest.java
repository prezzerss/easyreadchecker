package com.digitalartefact.tests;

import com.digitalartefact.text.DigitsChecker;

/**
 * Manual test for the DigitsChecker class.
 */
public class DigitsCheckerTest 
{
    public static void main(String[] args)
    {
        System.out.println("Testing DigitsChecker\n");

        // Expect warnings for "three" and "ten".
        System.out.println("Test 1 (simple written numbers):");
        DigitsChecker.checkForNumbers("I have three dogs and ten cats.");

        // Expect null
        System.out.println("\nTest 2 (no written numbers):");
        DigitsChecker.checkForNumbers("I have 3 dogs and 10 cats.");

        // Expect warning for "one hundred and twenty".
        System.out.println("\nTest 3 (bigger number):");
        DigitsChecker.checkForNumbers("They donated one hundred and twenty pounds.");
        
        // Expect warning for "Twenty-One" and "Eleven".
        System.out.println("\nTest 4 (mixed case and punctuation):");
        DigitsChecker.checkForNumbers("She had Twenty-One sweets, then gave away Eleven.");

        // Expect null
        System.out.println("\nTest 5 (no numbers):");
        DigitsChecker.checkForNumbers("This sentence does not contain any numbers.");
        
        System.out.println("\nDigitsChecker tests complete.");
    }
}
