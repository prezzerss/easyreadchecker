package com.digitalartefact.tests;

import com.digitalartefact.main.OptionsMenu;
import com.digitalartefact.savedata.UserData;

/**
 * Manual test for the OptionsMenu methods.
 */
public class OptionsMenuTest 
{
    public static void main(String[] args)
    {
    	String sentence = "This is a simple sentence with nine words maybe.";
        UserData testUser = new UserData("testuser");
        testUser.addSentence(sentence);

        System.out.println("Testing OptionsMenu");

        // Expect word count + rating.
        System.out.println("\nRunning option 1 (word count check):");
        OptionsMenu.runCheckOption(1, sentence);

        // Expect null.
        System.out.println("\nRunning option 2 (word length check):");
        OptionsMenu.runCheckOption(2, sentence);

        // Expect "No hard words detected!".
        System.out.println("\nRunning option 3 (hard word check):");
        OptionsMenu.runCheckOption(3, sentence);

        // Expect warning for "nine".
        System.out.println("\nRunning option 4 (number check):");
        OptionsMenu.runCheckOption(4, sentence);
        
        // Expect invalid option warning.
        System.out.println("\nRunning option 10 (invalid option check)");
        OptionsMenu.runCheckOption(10, sentence);

        // Expect "1. This is a simple sentence with nine words maybe."
        System.out.println("Running option 5 (show past sentences):");
        OptionsMenu.optionFive(testUser);

        System.out.println("\nTest complete");
    }
}
