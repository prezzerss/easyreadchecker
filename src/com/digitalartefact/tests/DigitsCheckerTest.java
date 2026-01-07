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

        // Expect "No written out numbers found!".
        System.out.println("\nTest 2 (no written numbers):");
        DigitsChecker.checkForNumbers("I have 3 dogs and 10 cats.");

        // Expect warning for "one hundred and twenty".
        System.out.println("\nTest 3 (bigger number):");
        DigitsChecker.checkForNumbers("They donated one hundred and twenty pounds.");

        // Expect warning for "one hundred and twenty two".
        System.out.println("\nTest 4 (5 word number):");
        DigitsChecker.checkForNumbers("They donated nine hundred and ninety nine pounds.");
        
        // Expect warning for "Twenty-One" and "Eleven".
        System.out.println("\nTest 5 (mixed case and punctuation):");
        DigitsChecker.checkForNumbers("She had Twenty-One sweets, then gave away Eleven.");

        // Expect warning for "twenty-one" and "thirty-five".
        System.out.println("\nTest 6 (hyphenated numbers):");
        DigitsChecker.checkForNumbers("He scored twenty-one points and thirty-five rebounds.");

        // Expect warnings for "one hundred five", "fifty nine", "eight".
        System.out.println("\nTest 7 (3 word, 2 word, and 1 word combos):");
        DigitsChecker.checkForNumbers("The total was one hundred five items, plus fifty nine more and eight returned.");

        // Expect correct replacement of punctuation attached to numbers.
        System.out.println("\nTest 8 (punctuation edge cases):");
        DigitsChecker.checkForNumbers("She bought twenty apples, thirty oranges, and ninety-nine pears!");

        // Expect "No written out numbers found!".
        System.out.println("\nTest 9 (no numbers):");
        DigitsChecker.checkForNumbers("This sentence does not contain any numbers.");
        
        System.out.println("\nTest complete.");
    }
}
