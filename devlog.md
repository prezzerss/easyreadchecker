# Section 1: Description of Features

This section describes three key features implemented in my Easy Read Checker digital artefact. The features selected are **User Input and Console Output**, **Objects and Methods**, and **Testing**. Together, these features demonstrate my understanding of core Java programming concepts and how they have been applied to create a structured console-based application.

---

## Feature 1: User Input and Console Output

User input and console output are central to how the Easy Read Checker operates. The application runs entirely in the console and relies on clear prompts and readable feedback to guide the user through the program.

User input is handled using Java’s `Scanner` class. When the program starts, the user is prompted to enter their name and then a sentence to check for Easy Read suitability. This input is stored as strings and passed to the relevant checker methods.

Example code:

    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter your name:");
    String userName = scanner.nextLine();

    System.out.println("Enter a sentence to check:");
    String sentence = scanner.nextLine();

Console output is used throughout the program to provide feedback from each checker. Messages are displayed using `System.out.println()` and are written in a clear and simple way to match Easy Read principles. This feature demonstrates how console input and output streams work in Java and how they can be used to build an interactive program.

---

## Feature 2: Objects and Methods (Object-Oriented Design)

The Easy Read Checker is designed using an object-oriented approach. Instead of placing all logic in one class, the program is split into multiple classes, each responsible for a specific task. This makes the code easier to read, maintain, and extend.

Examples of classes include:

- `WordCounter` – counts the number of words in a sentence  
- `WordLengthChecker` – identifies words that are too long  
- `HardWordChecker` – detects difficult words and suggests alternatives  
- `DigitsChecker` – finds written-out numbers  

Each class contains methods that perform a single, well-defined action. This follows good programming practice and reflects concepts taught in lectures about modular design and separation of concerns.

Example method:

    public static void checkWordLength(String sentence) {
        String[] words = sentence.split("\\s+");

        for (String word : words) {
            if (word.length() > 12) {
                System.out.println("Long word found: " + word);
            }
        }
    }

This method demonstrates the use of arrays, loops, selection statements, and methods. Organising the program this way allows each feature to be developed and tested independently.

---

## Feature 3: Testing (Unit Testing with JUnit)

Testing is used to ensure that key parts of the program work correctly. I implemented unit tests using JUnit to verify methods such as word counting and option handling.

Each test checks that a method returns the expected result for a given input. This helps identify bugs early and confirms that changes to the code do not break existing functionality.

Example test:

    @Test
    public void testWordCount() {
        int result = WordCounter.countWords("This is a test sentence");
        assertEquals(5, result);
    }

Writing unit tests encouraged better method design, particularly returning values instead of only printing output. Testing also supported iterative development and helped ensure the overall correctness of the program.

# Section 2: Issues Encountered

During the development of the Easy Read Checker, I encountered several issues that required debugging, additional research, and changes to my original approach. This section discusses three key code-based issues, the learning process behind resolving them, and an evaluation of the solutions used.

---

## Issue 1: Handling Empty or Invalid User Input

One of the first issues encountered was how the program handled empty or invalid user input. If the user pressed Enter without typing a sentence, some of the checker classes would still attempt to process the input, leading to confusing or incorrect output.

Originally, the sentence was passed directly into the checker methods without validation:

    String sentence = scanner.nextLine();
    WordLengthChecker.checkWordLength(sentence);

This caused problems because methods such as `split()` would still run on an empty string. To resolve this, I added validation at the start of each checker method to ensure the sentence was not null or empty.

Final solution:

    if (sentence == null || sentence.isEmpty()) {
        System.out.println("No sentence to check.");
        return;
    }

This approach follows defensive programming principles discussed in lectures, where input should always be validated before processing. While this does result in repeated checks across classes, it ensures that each checker is safe to run independently.

---

## Issue 2: Hard Word Checker Logic Conflicts

The Hard Word Checker was one of the most complex features to implement. Initially, it attempted to handle multiple responsibilities at once, including detecting hard words, suggesting simpler alternatives, and printing definitions. This led to logic conflicts and inconsistent output.

A key issue was that hard words were being added to the list multiple times, causing duplicate results. This happened because the list was populated every time the method ran.

Original behaviour (simplified):

    hardWords.add(new Term("consider", "think about"));

This resulted in repeated suggestions across multiple runs. After reviewing Java collections and debugging the issue, I changed the logic so that hard words are only added once.

Final solution:

    if (hardWords.isEmpty()) {
        addHardWords();
    }

This fix stabilised the feature and improved performance. In hindsight, separating the detection logic from the output logic earlier would have reduced complexity. However, this issue helped reinforce the importance of keeping methods focused on a single responsibility.

---

## Issue 3: Testing Methods That Only Printed Output

Another challenge was testing methods that relied solely on console output. JUnit tests are designed to verify return values, not printed text, which made some early tests ineffective.

Initially, some methods only printed results:

    public static void countWords(String sentence) {
        System.out.println(sentence.split("\\s+").length);
    }

This made it difficult to write meaningful unit tests. To resolve this, I refactored key methods to return values instead of only printing output.

Improved version:

    public static int countWords(String sentence) {
        return sentence.split("\\s+").length;
    }

This change made the code easier to test and improved overall design. While not all methods were refactored due to time constraints, this issue highlighted the importance of considering testability during initial development rather than as an afterthought.

---

## Reflection

These issues encouraged a more thoughtful and structured approach to programming. Debugging and testing helped me better understand how Java handles input, data structures, and method design. The solutions implemented improved the reliability and maintainability of the program and supported my understanding of software verification and problem-solving techniques.

# Conclusion

The development of the Easy Read Checker has strengthened my understanding of fundamental programming concepts in Java, including user input, object-oriented design, and software testing. By building a complete console-based application, I was able to apply theoretical knowledge from lectures and labs to a practical problem focused on accessibility and clarity.

The iterative nature of the project encouraged problem-solving and debugging, particularly when dealing with input validation, complex logic, and test design. These challenges highlighted the importance of writing clear, modular code and considering testability early in development.

Overall, the project demonstrates my ability to design and implement a structured Java program that meets the assignment requirements. The experience gained from this digital artefact provides a strong foundation for future modules involving more advanced programming techniques and software development practices.

