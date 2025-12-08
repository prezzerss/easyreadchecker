package com.digitalartefact.tests;

import com.digitalartefact.savedata.UserData;

/**
 * Manual test for the UserData class.
 */
public class UserDataTest 
{
    public static void main(String[] args)
    {
    	System.out.println("Testing UserData\n");

        // Expect loadUserFromFile for a made-up name returns null (no save file yet).
        System.out.println("Test 1 (load non-existent user):");
        UserData missingUser = UserData.loadUserFromFile("missingUser");
        System.out.println("Result of loading missing user: " + missingUser);

        // Expect new user has correct name, starts with 0 sentences, then 3 after adding.
        System.out.println("\nTest 2 (create user, add sentences, save, reload):");
        UserData user = new UserData("testuser");
        user.addSentence("First test sentence.");
        user.addSentence("Second test sentence.");
        user.addSentence("Third test sentence.");
        System.out.println("User name: " + user.getName());
        System.out.println("Sentences before save: " + user.getAllSentences().size());

        // Save and reload from file
        user.saveToFile();
        UserData loadedUser = UserData.loadUserFromFile("testuser");

        // Expect loadedUser is not null and has same name.
        System.out.println("\nTest 3 (check if created user is saved)");
        System.out.println("Loaded user is null? " + (loadedUser == null));
        if (loadedUser != null)
        {
            System.out.println("Loaded user name: " + loadedUser.getName());
            
            // Expect loadeduser to have 3 sentences matching what we saved.
            System.out.println("\nTest 4 (check if created user sentences have saved)");
            System.out.println("Sentences after load: " + loadedUser.getAllSentences().size());
            System.out.println("Sentence 1: " + loadedUser.getSentenceByNumber(1));
            System.out.println("Sentence 3: " + loadedUser.getSentenceByNumber(2));
            System.out.println("Sentence 3: " + loadedUser.getSentenceByNumber(3));
        }

        System.out.println("\nTest complete.");
    }
}
