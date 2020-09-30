public class TestPlayer
{
	public static void main(String[] args) {
		TicTacToeBoard b = new TicTacToeBoard();

		HumanPlayer p = new HumanPlayer("Bob");
		ComputerPlayer c = new ComputerPlayer();

		while (!b.gameOver()) {
			b.printBoard();
			p.takeTurn(b);
			b.printBoard();
			c.takeTurn(b);
		}
	}
}
