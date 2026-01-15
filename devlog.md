# Development Log for my Easy Read Checker

## Introduction

In this development log, I highlight 3 features and 3 issues I faced when coding my Easy Read Checker.

## Feature 1: [Selection]

In the *MainMethod* class, I used a *switch* statement to handle the nine different options available to the user. From a technical standpoint, a *switch* is often more readable and efficient than a long if-else ladder when dealing with a single variable (in this case, 
*int option*).
``` java
switch(option) 
{ 
    case 1: 
    case 2: 
    case 3: 
    case 4: 
        runCheckOption(option, userInput);
        break;
    case 5: 
        OptionsMenu.runPastSentences(currentUser); 
        break;
    // ...
}
```

By stacking cases 1 - 4, I routed all primary checkers to a single method, *runCheckOption*, reducing any code redundancy. This demonstrates an understanding of how selection can be used for clearer and cleaner structure.

The *AverageSentenceLength* class required a way to evaluate ranges. For this , I implemented an if-else ladder. This allowed the programme to categorise the double average into qualitative responses, like “excellent" or "good."
``` java
if(average >= 3 && average <= 9) 
{ 
    System.out.println("This is an excellent average sentence length."); 
} 
else if(average >= 10 && average <= 13) 
{ 
    System.out.println("This is a good average sentence length."); 
}
```
This allowed me to evaluate conditions sequentially until one is found to be true. I specifically designed these ranges based on Easy Read accessibility standards, ensuring that the code doesn't just function, but serves the purpose of improving readability.

In the *MainMethod* class, I used a *while(true)* loop combined with an *if(scanner.hasNextInt())* check. This selection code was a good prevention plan to stop the programme from crashing if a user enters a string instead of a number.

## Feature 2: [Abstract Class]

I created am abstract base class called *SentenceCheck*. An abstract class acts as a restricted class that cannot be used to create objects, but instead it exists to be inherited by other classes.
``` java
public static abstract class SentenceCheck
{
    private String description;
    public SentenceCheck(String description)
    {
        this.description = description;
    }
    public void run(String sentence) 
    {
        System.out.println("\n" + description);
        perform Check(sentence);
    }
    protected abstract void performCheck(String sentence);
}
```

The run method defines the steps of the algorithm (print header, then run check) but leaves the implementation of *performCheck* to the subclasses.

Each specific class like *WordCountCheck* or *HardWordCheck*, extends this base class. This is known as **inheritance**. By overriding the *protected abstract void performCheck* method, each subclass provides its own unique logic while reusing the header-printing code from the parent.

This design allowed me to use **polymorphism** to manage my classes. I stored all my checkers in a single array:
``` java
private static final SentenceCheck[] checks =
{
	new WordCountCheck(),
	new WordLengthCheck(),
	new HardWordCheck(),
	new DigitCheck()
};
```

When a user picks an option, it just calls *.run()* on the array element. This makes the code extendable – if I wanted to add something like a ‘grammar checker’ in the future, I just create one new class that extends *SentenceCheck*, and the rest of the code can stay unchanged.

Using abstract classes for both *SentenceCheck* and *PastSentenceCheck* massively improved the maintainability of my project.

## Feature 3: [Arrays + ArrayLists]

To build my programme, I needed a way to store large sets of data – stuff like a dictionary of hard words with their replacements and definitions, and lists of written out numbers. For this, I used both *Arrays* and *ArrayLists*.

Unlike a usual *Array* that has a fixed size, an *ArrayList* can grow and shrink dynamically. This was essential for the *HardWordChecker* class, where I store a collection of *Term* objects.
``` java
private static final ArrayList<Term> hardWords = new ArrayList<Term>();
private static void add(String hardWord, String definition, String simpler)
{
	hardWords.add(new Term(hardWord, definition, simpler));
}
```

Using an *ArrayList<Term>* ensured that the list only contained *Term objects*. This allowed me to iterate through the list using an enhanced *for* loop to check the user’s sentence against every hard word in my dictionary.

The *DigitsChecker* class takes my data management further. I used multiple *ArrayLists* to build combinations of numbers (ones, tens, hundreds). By nesting loops to populate these lists, I created a vocabulary of written numbers up to 999.
``` java
for(int i = 0; i < hundredsList.size(); i++)
{
	for(int j = 1; j < onesList.size(); j++)
	{
        hundredsAndOnesList.add(hundredsList.get(i) + “ and “ + onesList.get(j));
	}
}
```
Using *ArrayLists* allowed my programme to be modular. Because the data is stored in a list rather than hard coded into *if* statements, the checker is extendable. I can expand the dictionary simply by adding more items to the list without changing the code.

## Issue 1: [File Overwriting]

One of the main features I wanted in my project was persistence. I wanted to save the user’s sentence history between multiple runs. However, I ran into a bug during the early stages of my *UserData* class. Every time I saved a new sentence, the previous sentences were deleted from the text file. The file would only ever contain the single most recent entry.

In my initial implementation, I treated the file writer as a simple dump for the current sentences. I didn’t realise that initialising a *FileWriter* without any specific arguments defaults to overwriting the existing text file.
``` java
public void addSentence(String sentence)
{
	try
    {
        FileWriter writer = new FileWriter(“users/data.txt”);
		writer.write(sentence);
		writer.close();
	}
	catch(IOException e)
    {
	    e.printStackTrace();
    }
}
```

To fix this, I had to research the *java.io* library and the *FileWriter* class constructors. I learned two key concepts:
- Append Mode: *FileWriter* has a constructor *new FileWriter(file, true)* that adds to the end of the file rather than replacing it.
- State Management: Simply appending wasn’t enough if I wanted to load and view old sentences within the programme. I realised I needed to hold the state of the user’s history in memory (using an *ArrayList*) and sync that entire list to the file.

I decided to manage the data via an ArrayList. Instead of just appending to the data, my UserData class now loads the entire history into memory when the programme starts. When saving, it writes the complete list back to the text file. This ensures that the text file always matches the programme’s memory state.
``` java
public void saveToFile()
{
	File file = new File(“users/” + name.toLowerCase() + “.txt”);
	try (FileWriter writer = new FileWriter(file, false))
	{
		for (String s : userSentences)
		{
			writer.write(s + “\n”);
		}
	}
	catch (IOException exception)
	{
		exception.printStackTrace();
	}
}
```

While I could have just used Append Mode to add singular lines, writing the whole list was more robust. It prevents “duplicate line” bugs if the save function is called multiple times, and would allow me to implement features like deleting sentences if I wanted. This is because the file is always a reflection of the ArrayList.

## Issue 2: [False Positive]

During testing of the HardWordChecker, I noticed a confusing bug. When I was using my test class for the checker, a simple sentence (with no hard words) like “This is a simple sentence with easy words.”, the programme would still print the header: “Suggested easier sentence:” followed by the exact same sentence entered. 

While the code wasn’t crashing, it was providing incorrect feedback. It implied the user needed to change something when they didn’t, which wasn’t the purpose of my programme.

The issue stemmed from the flow of my checkForHardWords method. I was building the suggestion string and then immediately printing it, assuming that if the method ran there must have been changes. I failed to check if any replacements occurred within my code.
``` java
String suggestion = buildSuggestion(sentence, definitionsOut);
System.out.println(“Suggested easier sentence:”);
System.out.println(suggestion);
```

I realised that I needed to compare the state of the sentence before and after the check. I learned that I couldn’t just use == (which compares memory locations), but instead needed to use the .equals() method to compare the actual content of the strings. I also realised I needed to use booleans to control the flow of the console output, ensuring that specific blocks of code ran when specific conditions were met.

I introduced booleans to track the status of the checker. I created a variable sentenceChanged that returns true only if the suggestion is not equal to the original sentence. I then used an if statement controlled by this boolean.
``` java
ArrayList<String> definitionsOut = new ArrayList<String>();
String suggestion = buildSuggestion(sentence, definitionsOut);
boolean sentenceChanged = !suggestion.equals(sentence);
// ...
if(sentencedChanged)
{
	System.out.println(“Suggested easier sentence:”);
	System.out.println(suggestion);
}
```

This helped to hide the incorrect feedback when a sentence was fine, and only interrupted a user when the feedback was correct. 

## Issue 3: [Punctuation Problem]

When testing my WordLengthChecker, I discovered a flaw in how I was calculating word lengths. If a user wrote a sentence like “This is an easy read checker.”, the programme would count the fullstop at the end of ‘checker’ as part of the word itself.

While a 12 character word is usually fine, a 12 character word followed by a comma would be flagged as 13 characters. This led to false positives where the programme would warn the user about a word that was under the character limit, just because of trailing punctuation.

Initially, my code split the sentence by spaces and counted every character in the resulting strings. It didn’t account for the fact that symbols are not part of the word’s actual length in the easy read character limit.
``` java
String[] splitWords = input.split(“\\s+”);
for(String word : splitWords)
{
	int length = word.length();
	if(length > characterLimit)
    {
        // ...
    }
}
```

I realised that I needed to clean the sentence before measuring it. This introduced me to Regular Expressions (Regex). I researched how to use the .replaceAll() method to strip away non-alphanumeric characters from the start and end of a word.

I specifically looked for a way to target leading and trailing punctuation. This ensured that if a word was hyphenated (like “in-depth”), the hyphen in the middle would remain, but any leading or trailing punctuation would be removed.

I implemented a cleaning step inside the loop. Using Regex patterns, I stripped away anything that wasn’t a letter or a number from the boundaries of each word before the length was calculated.
``` java
for(String word : splitWords)
{
    String cleaned = word.replaceAll("^[^\\p{L}\\p{N}]+", ""); 
    cleaned = cleaned.replaceAll("[^\\p{L}\\p{N}]+$", "");
	int length = cleaned.length();
	if(length > characterLimit)
	{
		anyTooLong = true;
        System.out.println(“\nThe word “ + cleaned.toUpperCase() + “ is too long (“
        + length + “ characters).”);
	}
}
```

By adding this cleaning step, I ensured the accuracy of the feedback. This makes the programme much more reliable, as it is now able to check word lengths correctly for the user’s benefit.

