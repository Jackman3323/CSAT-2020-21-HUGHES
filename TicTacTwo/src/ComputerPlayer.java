// ComputerPlayer.java
// 1.3.2006
// by Mr. Daubenmier

/* This class extends Player to create a computer player.
   The computer is "dumb" in the sense that the strategy
   the is has is to pick a random spot on the board in
   getMove(). */
import java.util.Random;
public class ComputerPlayer extends Player {
	private boolean hasMiddle;
	private int numCornersOwned;

	public ComputerPlayer() {

		System.out.print("Please enter your name: ");

		System.out.println("Dumb Computer");
		String name = "Dumb Computer";

		super.numPlayers++;
		hasMiddle = false;
		numCornersOwned = 0;
		char ch;
		if (numPlayers == 1) ch = 'X';
		else ch = 'O';

		System.out.println(name + ", you will be " + ch + "\'s.");

		super.setChar(ch);
		super.setName(name);
	}

	public int getMove(TicTacToeBoard board) {
		Random r = new Random();
		int[] corners = new int[]{1, 3, 7, 9};
		boolean foundMove = false;

		System.out.print(getName() + ", please choose a spot to place your mark (1-9): ");
		int move = 0;
		while (!foundMove) {

			int i = 0;
			//If we don't have any corners yet, grab one if possible
			while (!foundMove || i < 4 && numCornersOwned < 1) {
				if (board.isEmpty(corners[i])) {
					//If a corner is availiable, take it!
					move = i;
					this.numCornersOwned++;
					i++;
					foundMove = true;
				}
			}
			//Do we already have a corner? Try to get an adjacent corner
			if (numCornersOwned == 1) {
				int cornerID = 0;
				//Figure out which corner it is
				for (int j = 0; j < 4; j++) {
					if (board.spotIsComputer(corners[i])) {
						cornerID = corners[i];
					}
				}
				//Ok, now find adjacent corners that don't have a blocked middle.
				switch (cornerID) {
					case 1:
						if (board.isEmpty(3) && board.isEmpty(2)) {
							move = 3;
							foundMove = true;
						} else if (board.isEmpty(7) && board.isEmpty(4)) {
							move = 7;
							foundMove = true;
						}
						break;
					case 3:
						if (board.isEmpty(1) && board.isEmpty(2)) {
							move = 1;
							foundMove = true;
						} else if (board.isEmpty(9) && board.isEmpty(6)) {
							move = 9;
							foundMove = true;
						}
						break;
					case 7:
						if (board.isEmpty(1) && board.isEmpty(2)) {
							move = 1;
							foundMove = true;
						} else if (board.isEmpty(9) && board.isEmpty(8)) {
							move = 9;
							foundMove = true;
						}
						break;
					case 9:
						if (board.isEmpty(3) && board.isEmpty(6)) {
							move = 3;
							foundMove = true;
						} else if (board.isEmpty(7) && board.isEmpty(8)) {
							move = 7;
							foundMove = true;
						}
						break;
				}
				if (!foundMove && numCornersOwned == 2) {
					int[] cornersOwned = new int[2];
					int index = 0;
					for (int q = 0; q < 4; q++) {
						if (board.spotIsComputer(corners[i])) {
							cornersOwned[index] = corners[i];
							index++;
						}
					}
					int firstCorner = cornersOwned[0];
					int secondCorner = cornersOwned[1];

					if (firstCorner == 1) {
					}
					if (secondCorner == 3) {
						if (board.isEmpty(2)) {
							move = 2;
							foundMove = true;
						}
					} else if (secondCorner == 7) {
						if (board.isEmpty(4)) {
							move = 4;
							foundMove = true;
						}
					} else if (firstCorner == 3) {
						if (secondCorner == 1) {
							if (board.isEmpty(2)) {
								move = 2;
								foundMove = true;
							}
						} else if (secondCorner == 9) {
							if (board.isEmpty(6)) {
								move = 6;
								foundMove = true;
							}
						}
					} else if (firstCorner == 7) {
						if (secondCorner == 1) {
							if (board.isEmpty(4)) {
								move = 4;
								foundMove = true;
							}
						} else if (secondCorner == 9) {
							if (board.isEmpty(6)) {
								move = 6;
								foundMove = true;
							}
						}
						//If we have STILL not found a move, do a random lol im done strategeizing
					}
				} else {
					while (!foundMove) {
						move = r.nextInt(9);
						foundMove = board.isEmpty(move);
					}
				}
			}
		}
		return move;
	}
}