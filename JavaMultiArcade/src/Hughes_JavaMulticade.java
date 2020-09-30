/**
 * JavaMulticade
 * This program runs multiple arcade style java games all in one epic, free package. There's two kinds of the all-time classic
 * "TreasureHunt"®, and of course, the breakthrough in gaming that is "MineSweeper"®
 * The aim of this driver was to make things as utterly simple as possible, leaving almost ALL of the work to the class files and
 * their respective methods. Thus, this is one main loop with a switch case for the different games and some print statements with
 * a scanner to chose a game and set up MineSweeper games.
 * Authors: Jack Hughes
 * Date: 4/10/20
 * On My Honor: JH
 */

import java.util.Scanner;
import javax.swing.*;

public class Hughes_JavaMulticade {
    public static void main(String[] args) {
        while (true) {
            Scanner s = new Scanner(System.in);

            Hughes_TreasureHunt game; //Non declared var allows for polymorphism

            //Welcoming print statement
            System.out.println("Welcome to the Multi-Game Java Arcade!");
            System.out.println("Select an option:");
            System.out.println("");
            System.out.println("1. TreasureHunt--Classic");
            System.out.println("2. TreasureHunt--Proximity Tracker");
            System.out.println("3. Minesweeper");
            System.out.println("4. Minesweeper 2");
            System.out.println("");
            System.out.print("Input the number of your choice here: ");

            int gameID = s.nextInt();
            System.out.println("");
            System.out.println("Good luck gamer! You chose...");
            switch (gameID) {
                case 1:
                    //TreasureHunt classic
                    System.out.println("Treasure Hunt!");
                    System.out.println("The goal if this game is to find the treasure on treasure island!");
                    System.out.println("In this version, your missed guesses are marked with X's! Good luck gamer!");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    game = new Hughes_TreasureHunt();
                    game.playGame();
                    break;
                case 2:
                    //TreasureHunt Proximity Tracker
                    System.out.println("Treasure Hunt!");
                    System.out.println("The goal if this game is to find the treasure on treasure island!");
                    System.out.println("In this version the game tells you how far you were from the gold when you guess incorrectly!");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    game = new Hughes_TreasureHuntProximity();
                    game.playGame();
                    break;
                case 3:
                    System.out.println("Minesweeper!");
                    System.out.println("In this game the goal is to mark all the mines in the minefield without digging one up!");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("INITIAL SETUP REQUIRED:");
                    Scanner keyboard = new Scanner(System.in);
                    //Minesweeper
                    System.out.println("How many rows would you like? Enter an integer:");
                    int rows = keyboard.nextInt();
                    System.out.println("How many columns would you like? Enter an integer:");
                    int columns = keyboard.nextInt();
                    System.out.println("How many mines would you like? Enter an integer:");
                    int mines = keyboard.nextInt();
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    Hughes_Minesweeper epicGame = new Hughes_Minesweeper(rows, columns, mines);
                    epicGame.playGame();
                    break;
                case 4:
                    System.out.println("Minesweeper 2!");
                    System.out.println("The goal of this game is to mark all the mines in the minefield without digging one up!");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("INITIAL SETUP REQUIRED:");
                    Scanner keyboard2 = new Scanner(System.in);
                    //Minesweeper
                    System.out.println("Would you like size 1(default), 2 or 3?:");
                    int size = s.nextInt();
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("This game has a separate window. Loading...");
                    Hughes_MinesweeperApplet epicestGame = new Hughes_MinesweeperApplet(size);
                    epicestGame.displayGame();
            }
        }
    }
}