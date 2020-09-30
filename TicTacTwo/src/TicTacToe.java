/**
 * TicTacToe
 * This driver plays lots o' tic tac toe. I also took the liberty of fixing the naming system of human players, not taking
 * in a name but rather taking in no arguments and having the class do it by itself. This delicious piece of code plays
 * tictactoe by using all the given classes and almost all given code plus a little switch case to do either vs the computer
 * or another player. Oh also, this program -REDACTED-.
 * Authors: JH
 * Date: 2-5-20
 * On My Honor: JH
 */

import java.util.*;
public class TicTacToe
{
	public static void main(String[] args)
	{
		Scanner input=new Scanner(System.in);
		TicTacToeGame game;
		boolean again;
		Player p1, p2;
		String choice;


		//determine who the players will be
		System.out.println("Welcome to Tic Tac Toe!\n\n");
		do{
			System.out.println("Who would you like to play against?");
			System.out.println("1.  The Computer");
			System.out.println("2.  Another Player");
			System.out.print("Please enter your choice: ");
			choice = input.nextLine();
			p1 = new HumanPlayer();

		} while (choice.charAt(0)!='1' && choice.charAt(0)!='2');

		//Create the players
		//Add code here to initialize p1 and p2 to the appropriate values
		switch(choice){
			case "1":
				p2 = new ComputerPlayer();
				break;
			case "2":
				p2 = new HumanPlayer();
				break;
			default:
				System.out.println("Stop it u dingus!! Default answer is computer, that's what you'll get!");
				p2 = new ComputerPlayer();
				break;
		}

		//play the game
		do{
			game=new TicTacToeGame(p1, p2);
			game.playGame();
			System.out.print("Would you like to play again? (y/n) ");
			String quit=input.nextLine().toLowerCase();
			if(quit.length()==0) quit="y";
			if(quit.charAt(0)=='n') again=false;
			else again=true;
		}while(again);
	}
}
