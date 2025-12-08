package com.digitalartefact.main;

import java.util.Scanner;
import com.digitalartefact.savedata.UserData;


/*
 *  Handles the main menu for the Easy Read Checker.
 *  - Logs the user in by name and loads/saves their data.
 *  - Lets the user enter sentences.
 *  - Lets the user choose different checks to run on each sentence entered.
 */
public class MainMethod 
{
	/*
	 * Entry point to the main method.
	 * Sets up the scanner, logs the user in, and starts the main menu loop.
	 */
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Welcome to the Easy Read Checker user!\n");
		
		System.out.println("Please type your first name to enter the programme:\n");
		
		String userName = scanner.nextLine().trim();
		
		UserData currentUser = UserData.loadUserFromFile(userName);
		
		if (currentUser == null)
		{
			currentUser = new UserData(userName);
		    System.out.println("\nHello " + userName + ", welcome! I've created a new save file for you, to save all of your sentences from now on.");
		}
		else
		{
		    System.out.println("\nWelcome back " + userName + "! Loaded your previous sentences.");
		}
		
		boolean runProgramme = true;
		
		// Outer loop - keeps the programme running until the user decides to quit.
		while(runProgramme)
		{
			
			// Asks for a new sentence every time.
			System.out.println("\nInput a sentence to be checked:\n");
			
			String userInput = scanner.nextLine();
			
			currentUser.addSentence(userInput);
			
			boolean checkingSentence = true;
			
			// Inner loop - lets the user run multiple checks on the same sentence.
			while(checkingSentence)
			{
			
				System.out.println(
						"\nCool! Now choose what you would like to be checked, or view your previous sentences:\n"
						+ "\n1. Check amount of words in sentence."
						+ "\n2. Check length of words in sentence."
						+ "\n3. Check for any difficult words in sentence."
						+ "\n4. Check for any written out numbers in sentence."
						+ "\n5. View all past sentences."
						+ "\n6. View one past sentence."
						+ "\n7. Enter a new sentence."
						+ "\n8. Quit programme. \n");
				
				int option = 0;
				
				// Accepts only valid option choices: Choices 1 - 8.
				while(true)
				{
					if(scanner.hasNextInt())
					{
						option = scanner.nextInt();
						scanner.nextLine();
						
						if(option >= 1 && option <= 8)
							break;
					}
					
					else
					{
						scanner.nextLine();
					}
					
					System.out.println("\nThat's an invalid option - please try again.\n");
				}
				
				switch(option)
				{
				case 1:
				case 2:
				case 3:
				case 4:
					runCheckOption(option, userInput);
					break;
				
				case 5:
					OptionsMenu.optionFive(currentUser);
					break;
						
				case 6: System.out.println("\nEnter number of past sentence:\n");
						
						int listSize = currentUser.getAllSentences().size();
						int userSentencePick = -1;
						
						// Accepts only valid option choices.
						// Choices are dependent on the amount of saved sentences.
						while (true)
						{
							
							while (!scanner.hasNextInt())
							{
								System.out.println("Please enter a valid number:");
								scanner.next();
							}
							
							userSentencePick = scanner.nextInt();
							scanner.nextLine();
							
							if (userSentencePick >= 1 && userSentencePick <= listSize)
							{
								break;
							}
							
							else
							{
								System.out.println("Invalid number. You have " + listSize + " sentences.");
								System.out.println("Please try again.");
								
							}
						}
						
						String chosenSentence = currentUser.getSentenceByNumber(userSentencePick);
						System.out.println("\nYou selected: \"" + chosenSentence + "\"");
						System.out.println("Would you like to run the options on this sentence? (yes/no)\n");
						
						String runOptions = scanner.nextLine().trim();
						
						if (runOptions.equalsIgnoreCase("yes"))
						{
							runOptionsOnChosenSentence(scanner, currentUser, chosenSentence);
							break;
						}
						
						else
						{
							System.out.println("\nCool, returning to main menu.");
						}
						
						break;
						
				
						
				case 7: checkingSentence = false;
						 break;
						 
				case 8: checkingSentence = false;
						runProgramme = false;
						break;
					
					
				}
			}
		}
		
		System.out.println("\nExiting Programme - Bye!");
		
		scanner.close();
	}
	
	/*
	 * Shows the sentence options menu for the user's chosen sentence.
	 * Loops until the user chooses to go back.
	 */
	private static void runOptionsOnChosenSentence(Scanner scanner, UserData currentUser, String sentence)
	{
		boolean choosing = true;
		
		while(choosing)
		{
		
			System.out.println(
					"\nWhat would you like to do with this sentence?"
					+ "\n1. Check amount of words in sentence."
					+ "\n2. Check length of words in sentence."
					+ "\n3. Check for any difficult words in sentence."
					+ "\n4. Check for any written out numbers in sentence."
					+ "\n5. Return to main menu.");
			
			System.out.print("\nChoose an option:\n");
			
			int choice = 0;
			
			while(true)
			{
				if(scanner.hasNextInt())
				{
					choice = scanner.nextInt();
					scanner.nextLine();
					
					if(choice >= 1 && choice <= 5)
						break;
				}
				
				else
				{
					scanner.nextLine();
				}
				
				System.out.println("Invalid option - please try again.\n");
			}
			
			switch(choice)
			{
			case 1:
			case 2:
			case 3:
			case 4:
				runCheckOption(choice, sentence);
				break;
					
			case 5: choosing = false;
					break;
				
			default: System.out.println("\nInvalid choice. Try again.\n");
					 break;
			}
		}
	}
	
	/*
	 * Method for handling options 1-4, which are repeated.
	 * Avoids repetition of code in two different menus.
	 */
	private static void runCheckOption(int option, String sentence)
	{
		OptionsMenu.runCheckOption(option, sentence);
	}

}