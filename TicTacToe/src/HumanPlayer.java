import java.util.Scanner;
public class HumanPlayer extends Player
{
    private String name;
    public HumanPlayer(String name)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        this.name = scan.nextLine();

        super.numPlayers++;
        char ch;
        if (numPlayers == 1) ch = 'X';
        else ch = 'O';

        System.out.println(name + ", you will be " + ch + "\'s.");

        super.setChar(ch);
        super.setName(name);
    }

    public int getMove(TicTacToeBoard board)
    {
        boolean foundMove = false;


        Scanner scan = new Scanner(System.in);
        int move = 0;
        while (!foundMove)
        {
            System.out.print(getName() + ", please choose a spot to place your mark (1-9): ");
            move = scan.nextInt(); //an alternate to the Random class
            foundMove = board.isEmpty(move);
        }
        System.out.println(move);
        return move;
    }
}
