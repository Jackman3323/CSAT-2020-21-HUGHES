/**
 * FlashCardsDriver2
 * This program utilizes the SortedFlashCards class, creating an alphabetized list of questions with their corresponding answers.
 * It then asks the user how many turns they'd like to take and gives them that many randomly chosen questions, and tells the
 * user if their answer is correct. For some reason, the alphabetizing feature of SortedFlashCards is not actually used...
 * Authors: Jack Hughes
 * Date: 4/8/20
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class FlashCardsDriver2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console;
        Scanner inFile;
        String question;
        String fileName;
        String answer;
        Random rand;
        SortedFlashCards fc;
        fc = new SortedFlashCards();
        console = new Scanner(System.in);
        rand = new Random();

        //load the question file
        System.out.print("Enter the Question File: ");
        fileName = console.nextLine();
        inFile = new Scanner(new File(fileName));

        while (inFile.hasNextLine()) {

            question = inFile.nextLine();
            answer = inFile.nextLine();
            fc.addCard(question, answer);

        }

        fc.printFlashCards();

        //Ask the user how many turns they would like to take
        System.out.print("How many turns would you like to take? ");
        int numTurns = console.nextInt();
        console.nextLine();

        //Ask the questions and compare the answers
        for (int i = 0; i < numTurns; i++) {
            int questionNum = rand.nextInt(fc.getNumCards());
            String guess;
            System.out.println("Question: " + fc.getQuestion(questionNum));
            guess = console.nextLine();
            if (guess.equals(fc.getAnswer(questionNum)))
                System.out.println("You're correct!!");
            else System.out.println("Sorry, the answer is: " + fc.getAnswer(questionNum));
        }

    }
}


