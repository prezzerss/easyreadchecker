package com.digitalartefact.tests;

import com.digitalartefact.text.AverageWordCounter;
import java.util.ArrayList;

/**
 * Manual test for the AverageWordCounter class.
 */
public class AverageWordCounterTest
{
    public static void main(String[] args)
    {
        System.out.println("Testing AverageWordCounter\n");

        // Expect average = 3.5
        System.out.println("Test 1 (simple sentences):");
        ArrayList<String> test1 = new ArrayList<String>();
        test1.add("This is short.");
        test1.add("This is another sentence.");
        System.out.println("Average = " + AverageWordCounter.averageWords(test1));

        // Expect average = 0.0
        System.out.println("\nTest 2 (empty list):");
        ArrayList<String> test2 = new ArrayList<String>();
        System.out.println("Average = " + AverageWordCounter.averageWords(test2));

        // Expect average = 4.0
        System.out.println("\nTest 3 (single sentence):");
        ArrayList<String> test3 = new ArrayList<String>();
        test3.add("One clear sentence here.");
        System.out.println("Average = " + AverageWordCounter.averageWords(test3));

        // Expect average = 5.666...
        System.out.println("\nTest 4 (mixed lengths):");
        ArrayList<String> test4 = new ArrayList<String>();
        test4.add("Here is a slightly longer test sentence!");
        test4.add("Short one.");
        test4.add("This sentence has several words, punctuation, and spacing!");
        System.out.println("Average = " + AverageWordCounter.averageWords(test4));

        // Expect average = 3.0
        System.out.println("\nTest 5 (extra spaces and formatting):");
        ArrayList<String> test5 = new ArrayList<String>();
        test5.add("Too   many   spaces.");
        test5.add("Newline\ttabs.\n");
        test5.add("Four   spaced   out   words.");
        System.out.println("Average = " + AverageWordCounter.averageWords(test5));

        // Expect average = 20.5
        System.out.println("\nTest 6 (long sentences):");
        ArrayList<String> test6 = new ArrayList<String>();
        test6.add("This is a very long sentence containing many words to push the average higher than usual for demonstration purposes.");
        test6.add("Another extremely long sentence is used here so that the average word count rises above the threshold for poor or bad feedback.");
        System.out.println("Average = " + AverageWordCounter.averageWords(test6));

        System.out.println("\nTest complete.");
    }
}

