/**
 * TreasureHunt
 * This program is the main class file for the game TreasureHunt®. In this game, you search a small, uncannily
 * squarish island for a treasure that the developers say is: "probably somewhere on the island?"® This game
 * is meant for entertainment purposes... you input a row and a column to guess the location of the gold and
 * "eventually"® you win.
 * Authors: Jack Hughes
 * Date: 4-10-20
 * On My Honor: JH
 */

import java.util.Random;
import java.util.Scanner;
public class Hughes_TreasureHunt
{
	//INSTANCE DATA

	//goldRow--int value of what row the gold is in
	protected int goldRow;
	//goldCol--int value of what column the gold is in
	protected int goldCol;
	//mapArr--a 2d array of chars that stores the information to be displayed.
	protected char[][] mapArr;

	//numGuesses--an integer value that is increased by one every time the user makes a guess
	protected int numGuesses;

	//The constructor initializes the 2D array to be 3 rows by 5 cols.
	//It randomly places a 'G' into the 2D array (this will be the gold)
	//Initializes numGuesses
	public Hughes_TreasureHunt()
	{
		//Initialization of mapArr:
		mapArr = new char[3][5];
		//Code to make a random spot for the gold
		Random r1 = new Random();
		goldRow = r1.nextInt(3);
		goldCol = r1.nextInt(5);
		//r for rows
		for(int r = 0; r < 3; r++){
			//c for columns
			for(int c = 0; c < 5; c++){
					mapArr[r][c] = ' ';
			}
		}
		mapArr[goldRow][goldCol] = 'G';

		//Initialization of numGuesses:
		numGuesses = 0;
	}

	//Increments the number of guesses.
	//Places and 'X' in the location if the guess is not correct.
	//Returns true if the row and col passed in is the location of the gold
	//Returns false if the row and col are out of bounds or not the location
	//of the gold.


	//returns the number of guesses
	public int getNumGuesses()
	{
		return numGuesses; //Returns number of guesses
	}

	//Prints the board
	//Code just like from lecture EXCEPT that is does not print the 'G'!!!
	public void printBoard()
	{
		System.out.println("Guess where the gold is!");
		for(int r = 0; r < 3; r++) {
			for (int c = 0; c < 5; c++) {
				//Print normal unless it's the gold
				if(mapArr[r][c] == 'G'){
					System.out.print(' ');
				}
				else{
					System.out.print(mapArr[r][c]);
				}
				//Nice formatting line printer statement
				if (c != 4) {
					System.out.print("|");
				}
			}
			//Nice formatting statement
			System.out.println("");
			if (r != 2) {
				System.out.println("----------");
			}
			else {
				System.out.println("");
			}
		}
	}
	//Checks the guess to see if it qualifies to end the round
	public boolean checkToEnd(int r, int c){
		boolean stop;
		numGuesses++;//Increment numGuesses
		if(r > 2 || c > 4){
			//Out of bounds, we have shenanigans on our hands!
			stop = false; //Trflase? lol
		}
		else{
			//Otherwise, calculate accuracy of guess normally
			stop = mapArr[r][c] == 'G';
		}

		if(!stop){
			mapArr[r][c] = 'X'; //Place missed guess indicator
		}

		//Return whether or not to stop the game
		return stop;
	}
	//Takes one turn
	public boolean takeTurn(){
		boolean stop = false; //Boolean to end the game
		Scanner scan = new Scanner(System.in); //Scanner for reading inputs
		int rowGuess, colGuess;
		this.printBoard();
		System.out.println("Please enter your guess of where the gold is.");
		System.out.print("Row (0-2): ");
		rowGuess = scan.nextInt();
		System.out.print("Col (0-4): ");
		colGuess = scan.nextInt();
		scan.nextLine();

		stop = checkToEnd(rowGuess, colGuess);
		if (stop) {
			System.out.println("You found the gold in just " + this.getNumGuesses() + " tries");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			return true; //Found the gold, game ends
		} else {
			System.out.println("Better keep looking . . .");
			return false; //Game not over
		}
	}
	//Plays the game
	public void playGame(){

		boolean stop = false;
		while(!stop){
			stop = takeTurn();
		}
	}
}

