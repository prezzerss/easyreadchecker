package com.digitalartefact.savedata;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Stores and manages all the data for a single user.
 * - Keeps their name and list of sentences in memory.
 * - Can save sentences to users/<name>.txt.
 * - Can load sentences back from the save file.
 */
public class UserData 
{
	// User name.
	private String name;
	
	// List of all sentences entered by the user.
	private ArrayList<String> userSentences;
	
	/**
     * Creates a new user with the entered name and an empty sentence list.
     */
	public UserData(String name)
	{
		this.name = name;
		this.userSentences = new ArrayList<String>();
	}
	
	/**
     * Creates a new user with an initial list of sentences.
     * (Useful for tests.)
     */
    public UserData(String name, ArrayList<String> initialSentences) 
    {
        this.name = name;
        this.userSentences = new ArrayList<String>(initialSentences);
    }
	
	/**
     * Loads a user's data from users/<name>.txt.
     * Returns a UserData object if the file exists, or nothing if not.
     */
	public static UserData loadUserFromFile(String name)
	{
		File file = new File("users/" + name.toLowerCase() + ".txt");
		
		if (!file.exists())
		{
			return null;
		}
		
		UserData loadedUser = new UserData(name);
		
		try (Scanner fileReader = new Scanner(file))
		{
			while(fileReader.hasNextLine())
			{
				String sentence = fileReader.nextLine();
				loadedUser.userSentences.add(sentence);
			}
		}
		
		catch (IOException exception)
		{
			exception.printStackTrace();
		}
		
		return loadedUser;
	}
	
	/**
     * Saves all current sentences to users/<name>.txt.
     * Overwrites the existing file if it already exists.
     */
	public void saveToFile()
	{
		File file = new File("users/" + name.toLowerCase() + ".txt");
		
		try (FileWriter writer = new FileWriter(file, false))
		{
			for (String s : userSentences)
			{
				writer.write(s + "\n");
			}
		}
		
		catch (IOException exception)
		{
			exception.printStackTrace();
		}
	}
	
	/**
	 * Saves user's sentences to users/<name>.txt.
	 */
	public void addSentence(String sentence)
	{
		userSentences.add(sentence);
		saveToFile();
	}
	
	/**
     * Returns the user's name.
     */
    public String getName()
    {
        return name;
    }
	
	/**
	 * Getter that returns all past user sentences.
	 */
	public ArrayList<String> getAllSentences()
	{
		return userSentences;
	}
	
	/**
	 * Getter that returns a user's specific past sentence.
	 * Uses index to make sure user's first sentence = 1 (instead of 0)
	 */
	public String getSentenceByNumber(int i)
	{
		return userSentences.get(i - 1);
	}
	
}
