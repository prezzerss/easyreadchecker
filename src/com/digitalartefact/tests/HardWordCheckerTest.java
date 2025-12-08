package com.digitalartefact.tests;

import com.digitalartefact.text.HardWordChecker;

/**
 * Manual test for the HardWordChecker class.
 */
public class HardWordCheckerTest 
{
    public static void main(String[] args)
    {
        System.out.println("Testing HardWordChecker\n");

        // Expect "accessible" and "addiction" warnings. 
        System.out.println("Test 1 (sentence with hard words):");
        HardWordChecker.checkForHardWords("The service must be accessible to people suffering with addiction.");

        // Expect "No hard words detected!"
        System.out.println("\nTest 2 (sentence with no hard words):");
        HardWordChecker.checkForHardWords("This is a simple sentence with easy words.");
        
        // Expect "No hard words detected!".
        System.out.println("\nTest 3 (null and empty input):");
        HardWordChecker.checkForHardWords(null);
        HardWordChecker.checkForHardWords("");

        System.out.println("\nTest complete.");
    }
}
