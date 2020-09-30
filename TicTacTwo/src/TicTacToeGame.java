/**
 * TicTacToeGame
 * This quenching piece of code brings together the player classes and the board class to form a game class. It allows you to play the game with two players and return the winner.
 * It also -REDACTED-.
 * Authors: JH
 * Date: 2-5-20
 * On My Honor: JH
 */

public class TicTacToeGame
{
	private TicTacToeBoard board;
	Player p1, p2;

	public TicTacToeGame(Player p1, Player p2)
	{
		board = new TicTacToeBoard();
		this.p1 = p1;
		this.p2 = p2;

	}

	public void playGame()
	{
		int winner = 0;
		while(!board.gameOver()&&!board.isBoardFull()) {
			board.printBoard();
			p1.takeTurn(board);

			if (!board.gameOver() && !board.isBoardFull()) {
				board.printBoard();
				p2.takeTurn(board);
				winner = 2;
			}
			else{
				winner = 1;
			}
		}
		if(board.isBoardFull() && ! board.gameOver()){
			System.out.println("Cats Game");
		}
		else{
			System.out.println("Player "+ winner + " wins!!");
		}
		

	}
}
