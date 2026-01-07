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

        // Expect warnings for: Accessible, Addiction.
        System.out.println("Test 1 (simple hard words):");
        HardWordChecker.checkForHardWords("The service must be accessible to people struggling with addiction.");

        // Expect all hard words detected.
        System.out.println("\nTest 2 (all hard words in one sentence):");
        HardWordChecker.checkForHardWords(
            "Accessible abuse addiction anxiety assessment benefits campaign consent " +
            "discrimination equality homeless mental health poverty safeguarding wellbeing."
        );

        // Expect detection with punctuation and mixed case.
        System.out.println("\nTest 3 (mixed case + punctuation):");
        HardWordChecker.checkForHardWords(
            "ACCESSIBLE, Abuse! aDdIcTiOn? Anxiety; Assessment: Benefits. " +
            "Campaign, consent... Discrimination? Equality! Homeless, Mental Health; " +
            "Poverty! Safeguarding. Wellbeing?"
        );

        // Expect detection of multi-word hard word "mental health".
        System.out.println("\nTest 4 (multi-word hard word):");
        HardWordChecker.checkForHardWords("Good mental health is important for wellbeing.");

        // Expect no hard words detected.
        System.out.println("\nTest 5 (no hard words):");
        HardWordChecker.checkForHardWords("This is a simple sentence with easy words.");

        // Expect null + empty input safety handling.
        System.out.println("\nTest 6 (null and empty input):");
        HardWordChecker.checkForHardWords(null);
        HardWordChecker.checkForHardWords("");

        System.out.println("\nTest complete.");
    }
}
