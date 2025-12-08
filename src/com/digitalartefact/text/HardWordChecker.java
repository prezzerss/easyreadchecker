package com.digitalartefact.text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Checks a sentence for a set list of hard words that may be difficult to understand.
 * For each hard word found, it can suggest an alternative word and/or definition.
 */
public class HardWordChecker 
{
    /**
     * Set list of hard words to check sentence against.
     */
    private static final List<Term> hardWords = new ArrayList<>();

    /**
     * Checks the sentence for hard words and prints feedback.
     * Includes suggested easier words and/or definitions if they are needed.
     */
    public static void checkForHardWords(String sentence)
    {
        if (sentence == null || sentence.isEmpty())
        {
            System.out.println("\nNo sentence to check.");
            return;
        }

        // Only add to the list once.
        if (hardWords.isEmpty())
        {
            add("Accessible", "Easy to use or suitable for people with different needs", "easy to use");
            add("Abuse", "When someone hurts you or treats you badly", "harm");
            add("Addiction", "A strong urge to do something that’s hard to stop");
            add("Anxiety", "Feeling worried or nervous");
            add("Assessment", "Finding out information about someone to decide what help they need", "check");
            add("Benefits", "Money from the government to help you live");
            add("Campaign", "A planned effort to change something or raise awareness", "plan for change");
            add("Consent", "Saying yes or agreeing to something");
            add("Discrimination", "Being treated unfairly because of who you are", "unfair treatment");
            add("Equality", "Treating people fairly and giving everyone the same chances", "fairness");
            add("Homeless", "Having no place of your own to live");
            add("Mental health", "How you think and feel, and how you deal with emotions");
            add("Poverty", "Not having enough money to live well");
            add("Safeguarding", "Keeping people safe from harm or abuse", "keeping people safe");
            add("Wellbeing", "Feeling happy and healthy in body and mind");
        }
        
        boolean foundInScan = checkSentence(sentence);

        ArrayList<String> definitionsOut = new ArrayList<>();
        String suggestion = buildSuggestion(sentence, definitionsOut);

        boolean sentenceChanged = !suggestion.equals(sentence);
        boolean hasDefinitions = !definitionsOut.isEmpty();

        boolean foundHardWord = foundInScan || sentenceChanged || hasDefinitions;

        if (!foundHardWord)
        {
            System.out.println("\nNo hard words detected!");
            return;
        }
        
        if (sentenceChanged)
        {
            System.out.println("Suggested easier sentence:");
            System.out.println(suggestion);
        }

        if (hasDefinitions)
        {
            System.out.println("\nDefinitions:");
            for (String def : definitionsOut)
            {
                System.out.println("- " + def);
            }
        }
    }
    
    /**
     * Scans the sentence and prints feedback for each hard word found.
     * Returns true if at least 1 hard word was found, otherwise false.
     */
    private static boolean checkSentence(String input)
    {
        String lowerInput = input.toLowerCase();
        boolean foundAny = false;
        
        for (Term t : hardWords)
        {
            if (lowerInput.matches(".*\\b" + Pattern.quote(t.hardWord.toLowerCase()) + "\\b.*"))
            {
                foundAny = true;
                System.out.println("\nThe word \"" + t.hardWord + "\" was found in your sentence.");
                if (t.simpler != null)
                {
                    System.out.println("Suggestion: use \"" + t.simpler + "\" instead.");
                }
                else
                {
                    System.out.println("Consider adding a definition: " + t.definition + "\n");
                }
            }
        }

        return foundAny;
    }
    
    static class Term
    {
        final String hardWord;
        final String definition;
        final String simpler;

        Term(String hardWord, String definition, String simpler)
        {
            this.hardWord = hardWord;
            this.definition = definition;
            this.simpler = simpler;
        }
    }
    
    /**
     * Adds a hard word with both a definition and an alternative word.
     */
    private static void add(String hardWord, String definition, String simpler)
    {
        hardWords.add(new Term(hardWord, definition, simpler));
    }
    
    /**
     * Adds a hard word with a definition, but no alternative word.
     */
    private static void add(String hardWord, String definition)
    {
        add(hardWord, definition, null);
    }
    
    /**
     * Builds a suggested easier version of the sentence.
     * Replaces hard words with simpler words where available.
     * If no simpler word is set, the original word is uppercased
     * and its definition is added to defsOut.
     */
    private static String buildSuggestion(String input, List<String> defsOut) 
    {
        String result = input;

        for (Term t : hardWords) 
        {
            Pattern p = Pattern.compile("(?i)\\b" + Pattern.quote(t.hardWord) + "\\b");
            Matcher m = p.matcher(result);
            StringBuffer sb = new StringBuffer();

            while (m.find()) 
            {
                if (t.simpler != null) 
                {
                    m.appendReplacement(sb, Matcher.quoteReplacement(t.simpler));
                } 
                else 
                {
                    String originalMatched = m.group();
                    String upper = originalMatched.toUpperCase();
                    m.appendReplacement(sb, Matcher.quoteReplacement(upper));

                    if (t.definition != null) 
                    {
                        String defLine = upper + ": " + t.definition;
                        if (!defsOut.contains(defLine)) 
                        {
                            defsOut.add(defLine);
                        }
                    }
                }
            }
            m.appendTail(sb);
            result = sb.toString();
        }

        return result;
    }
}

