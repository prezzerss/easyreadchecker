# Digital Artefact - Easy Read Checker

This is my digital artefact.
It’s a console programme that checks whether a sentence is Easy Read friendly by running it through multiple custom checkers. The programme provides feedback on things like word count, word length, hard words, written out numbers, average sentence length, and more.

---
---

## How to Run It

Compile and run the main method in the **EasyReadChecker** class.

---
---

## What the Programme Does

When the user enters a sentence, the programme gives the user an option to:

- Count the number of words in the sentence.

- Flag any words that are too long.

- Detect “hard words” and suggest easier alternatives/definitions.

- Detect any written out numbers instead of digits.

- Calculate the average sentence length.


It also stores user data (name + previous sentences) between multiple runs, by creating, writing, and appending to a *<name>.txt* file.

---
---

## The Structure

<table style="border-collapse: collapse; width: 100%;">
  <tr>
    <th style="border: 1px solid #aaa; padding: 6px; text-align: left;">Class</th>
    <th style="border: 1px solid #aaa; padding: 6px; text-align: left;">What it does</th>
  </tr>

  <tr>
    <td style="border: 1px solid #aaa; padding: 6px;">MainMethod</td>
    <td style="border: 1px solid #aaa; padding: 6px;">Handles the main method and the menu.</td>
  </tr>

  <tr>
    <td style="border: 1px solid #aaa; padding: 6px;">OptionsMenu</td>
    <td style="border: 1px solid #aaa; padding: 6px;">Handles user options.</td>
  </tr>

  <tr>
    <td style="border: 1px solid #aaa; padding: 6px;">EasyReadChecker</td>
    <td style="border: 1px solid #aaa; padding: 6px;">Starts the programme.</td>
  </tr>

  <tr>
    <td style="border: 1px solid #aaa; padding: 6px;">SentenceChecker (abstract)</td>
    <td style="border: 1px solid #aaa; padding: 6px;">Base class for all checkers.</td>
  </tr>

  <tr>
    <td style="border: 1px solid #aaa; padding: 6px;">WordCounter / AverageWC</td>
    <td style="border: 1px solid #aaa; padding: 6px;">Counts words and calculates average word length.</td>
  </tr>

  <tr>
    <td style="border: 1px solid #aaa; padding: 6px;">WordLengthChecker</td>
    <td style="border: 1px solid #aaa; padding: 6px;">Flags long words.</td>
  </tr>

  <tr>
    <td style="border: 1px solid #aaa; padding: 6px;">DigitsChecker</td>
    <td style="border: 1px solid #aaa; padding: 6px;">Detects written-out numbers.</td>
  </tr>

  <tr>
    <td style="border: 1px solid #aaa; padding: 6px;">HardWordChecker</td>
    <td style="border: 1px solid #aaa; padding: 6px;">Finds difficult words and suggests easier alternatives.</td>
  </tr>

  <tr>
    <td style="border: 1px solid #aaa; padding: 6px;">UserData</td>
    <td style="border: 1px solid #aaa; padding: 6px;">Stores usernames and sentence history.</td>
  </tr>
</table>

---
---

## Testing

I included multiple test classes to check:

- **WordCountChecker**.

- **WordLengthChecker**.

- **HardWordChecker**.

- **DigitsChecker**.

- **AverageWordCounter**.

- **OptionsMenu**.

- **UserData**.

---
---

## Additional Features

The additional features used include:

- An abstract base class + subclasses.

- A modular OO architecture with multiple interacting objects.

- Regex usage in **HardWordChecker** and **DigitsChecker**.

- File handling for saving and storing user data.

- A full set of test classes.

---
---

## Overall

The programme is a console tool that utilises:

- User input + output.

- Selection (if/else + switch).

- Loops.

- Methods.

- Arrays.

- Testing.

- Object-orientation.

- Inheritance + Advanced OO.

- File handling.

- Good commenting + package structure.

---
---