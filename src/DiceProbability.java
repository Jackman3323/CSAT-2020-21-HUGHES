/**
 * DiceProbability
 * This program rolls some dice and tells you some stats about those dice. Flexible to the user's requirements, ends when they want it to.
 * Authors: JH
 * Date: 2-10-20
 * On My Honor: JH
 */
import java.util.Random;
import java.util.Scanner;
public class DiceProbability {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random r = new Random();
        int[] diceValue = new int[11]; //Will never be lower then 2 or higher than 12
        System.out.println("How many times would you like to roll the die?");
        int numRolls = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < numRolls; i++) {
            int die1Roll = r.nextInt(6) + 1;
            int die2Roll = r.nextInt(6) + 1;
            int sum = die1Roll + die2Roll;
            diceValue[sum - 2] += 1;
        }
        boolean cont = true;
        while (cont) {
            System.out.println("Which number (2-12) would you like to learn about? Or, input \"n\" to end the madness.");
            int userInt = scan.nextInt();
            System.out.println("The percentage of rolls that resulted in a " + userInt + " was " + (diceValue[userInt - 2] / (double)numRolls)*100 + ".");
            System.out.println("Continue? (y/n): ");
            scan.nextLine();
            String userInput = scan.nextLine();
            cont = userInput.equals("Y") || userInput.equals("y");
        }
    }
}