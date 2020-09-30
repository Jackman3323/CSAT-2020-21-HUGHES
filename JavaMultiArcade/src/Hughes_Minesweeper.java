/**
 * Minesweeper
 * This program is the main class file for the game MineSweeper®. This game allows for a flexibly sized grid with a
 * flexible number of mines that are randomly placed throughout the grid. The user must slowly and methodically
 * dig up pieces of the field while scanning the sides of their holes to determine how many bombs are nearby. To
 * win, the user must correctly place a bomb marker above all of the mines and remove any incorrect markers. If the
 * user digs up a mine, they lose the game.
 * The game uses "beautifully formatted colors and symbols on a nicely printed grid"®, while a 2D char array stores
 * information for blank spots, bomb spots, correctly marked bombs, incorrectly marked bombs, and spots next to
 * x number of bombs. The printMap() method reads this data and ensures that no data is given away, covering bombs with
 * blank spots and marking both correct and incorrect markers with the same symbol. All other methods are much more
 * self explanatory. The takeTurn() function returns a boolean representing whether or not the user dug up a mine.
 * The playGame() function checks after every turn to see if all the mines are marked correctly. Correct markers are
 * tracked by a counter that is only updated when the user correctly marks a mine. Clearly, I have absolutley no life
 * and Obsessive Compulsive Disorder--i mean come on, look at dem colorz! Also... over 400 lines of code... yikes!
 * Authors: Jack Hughes
 * Date: 4-10
 * On My Honor: JH
 * P.S.: Have I gone too far this time?
 */

import java.util.Random;
import java.util.Scanner;
import java.awt.*;
import java.util.ArrayList;

public class Hughes_Minesweeper {
    //INSTANCE DATA
    protected int numCorrectMarks; //Counter of correct mine markers
    protected char[][] map; // Main map array
    protected boolean[][] revealed; //Array of true/false booleans representing whether or not each corresponding cell has been revealed
    protected int rows; //# of rows in the grid
    protected int columns; //# of columns in the grid
    protected int numMines; //desired # of mines
    //The rest are background and text color formatting escape character strings that are saved in global variables to be easier to use and understand
    protected static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    protected static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    protected static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    protected static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    protected static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    protected static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    protected static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    protected static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    protected static final String ANSI_RESET = "\u001B[0m";
    protected static final String ANSI_BLACK = "\u001B[30m";
    protected static final String ANSI_RED = "\u001B[31m";
    protected static final String ANSI_GREEN = "\u001B[32m";
    protected static final String ANSI_YELLOW = "\u001B[33m";
    protected static final String ANSI_BLUE = "\u001B[34m";
    protected static final String ANSI_PURPLE = "\u001B[35m";
    protected static final String ANSI_CYAN = "\u001B[36m";
    protected static final String ANSI_WHITE = "\u001B[37m";
    protected int numMarks; //Number of flags, correct or not

    //Constructor
    public Hughes_Minesweeper(int rows, int columns, int bombs) {
        this.rows = rows;
        this.columns = columns;
        this.numMines = bombs;
        numCorrectMarks = 0;
        numMarks = 0;
        map = new char[rows][columns]; //Initialize map
        //Generate random field:
        Random random = new Random();
        ArrayList<Point> openSlotsForMines = new ArrayList<Point>(rows * columns);
        //fill the ArrayList with all the points needed:
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                openSlotsForMines.add(new Point(r, c));
                map[r][c] = ' ';
            }
        }
        //Initialize revealed array
        revealed = new boolean[rows][columns]; //Default value is false! YAY LESS WORK FOR ME!
        for (int i = 0; i < numMines; i++) {
            //Remove a random point, set up the map accordingly for bombs
            int randomBombLocation = random.nextInt(openSlotsForMines.size());
            Point bombPoint = openSlotsForMines.get(randomBombLocation);
            openSlotsForMines.remove(randomBombLocation);
            map[(int) bombPoint.getX()][(int) bombPoint.getY()] = 'B';
        }
        //Now loop through and check each cell's # of surrounding bombs
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                char currCell = map[r][c];
                int bombCounter = 0;
                if (currCell == ' ') {
                    //It's not a bomb, check to see if it needs a number and if so, calculate it.
                    for (int i = 0; i < 8; i++) {
                        try {
                            //Try looking at all possible surrounding cells, but be ready to catch errors cuz of corner/wall cells.
                            switch (i) {
                                case 0:
                                    if (map[r - 1][c - 1] == 'B') bombCounter++;
                                    break;
                                case 1:
                                    if (map[r - 1][c] == 'B') bombCounter++;
                                    break;
                                case 2:
                                    if (map[r - 1][c + 1] == 'B') bombCounter++;
                                    break;
                                case 3:
                                    if (map[r][c - 1] == 'B') bombCounter++;
                                    break;
                                case 4:
                                    if (map[r][c + 1] == 'B') bombCounter++;
                                    break;
                                case 5:
                                    if (map[r + 1][c - 1] == 'B') bombCounter++;
                                    break;
                                case 6:
                                    if (map[r + 1][c] == 'B') bombCounter++;
                                    break;
                                case 7:
                                    if (map[r + 1][c + 1] == 'B') bombCounter++;
                                    break;
                            }
                        } catch (ArrayIndexOutOfBoundsException cellNotFound) {
                            //Corner cell and or wall cell detected! Do nothing to counter.
                        }
                    }
                }
                if (bombCounter != 0) {
                    //Needs a number! Lets give it one!
                    map[r][c] = (char) (bombCounter + '0');
                }
            }
        }
    }

    //Nice method to print the map array in away that doesn't just show where the bombs are--because our game is "challenging"®
    public void printMap() {
        //Draw Helpful Numbers across top
        System.out.print("   ");
        for (int i = 0; i < columns; i++) {
            if (i < 10) {
                System.out.print(" " + i + "  ");
            } else System.out.print(" " + i + " ");
        }
        System.out.println("");
        //Draw line grid and other things
        for (int r = 0; r < rows; r++) {
            //Print helpful numbers down the side
            if (r < 10) {
                System.out.print(" " + r + " ");
            } else System.out.print("" + r + " ");
            for (int c = 0; c < columns; c++) {
                if (revealed[r][c]) {
                    //Display map value in this area of the grid
                    switch (map[r][c]) {
                        case ' ':
                            //Empty cell, green background
                            System.out.print(ANSI_GREEN_BACKGROUND + "   " + ANSI_RESET);
                            break;
                        case '1':
                            //Cyan 1, yellow bkcground
                            System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_CYAN + " 1 " + ANSI_RESET);
                            break;
                        case '2':
                            //Cyan 2, orange bkcground
                            System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_CYAN + " 2 " + ANSI_RESET);
                            break;
                        case '3':
                            //Cyan 3, orange bkcground
                            System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_CYAN + " 3 " + ANSI_RESET);
                            break;
                        case '4':
                            //Purple 4, orange bkcground
                            System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_PURPLE + " 4 " + ANSI_RESET);
                            break;
                        case '5':
                            //Purple 5, orange bkcground
                            System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_PURPLE + " 5 " + ANSI_RESET);
                            break;
                        case '6':
                            //Purple 6, orange bkcground
                            System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_PURPLE + " 6 " + ANSI_RESET);
                            break;
                        case '7':
                            //Red 7, green bkcground
                            System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_RED + " 7 " + ANSI_RESET);
                            break;
                        case '8':
                            //Red 8, green bkcground
                            System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_RED + " 8 " + ANSI_RESET);
                            break;
                        case 'B':
                            //Red background, * black symbol
                            System.out.print(ANSI_BLACK + ANSI_RED_BACKGROUND + " * " + ANSI_RESET);
                            break;
                        case 'S':
                            //Blue background, * red symbol--signifies a correctly marked mine
                            System.out.print(ANSI_BLUE_BACKGROUND + ANSI_RED + " * " + ANSI_RESET);
                            break;
                        case 'U':
                            //Purple background, flag symbol
                            System.out.print(ANSI_PURPLE_BACKGROUND + ANSI_RED + "/|\\" + ANSI_RESET);
                    }
                } else {
                    //Not yet revealed, print white backround space or a mark--marks are blue
                    if (map[r][c] == 'S' || map[r][c] == 'U')
                        System.out.print(ANSI_WHITE_BACKGROUND + ANSI_RED + "/|\\" + ANSI_RESET);
                    else System.out.print(ANSI_BLUE_BACKGROUND + "   " + ANSI_RESET);
                }
                if (c != columns - 1) {
                    //If it's not the last column, print a divider
                    System.out.print("|");
                }
            }
            if (r != rows - 1) {
                System.out.println("");
                System.out.print("---");
                int numCyclesToMakeBorder;
                if (columns % 2 != 0) {
                    //It's odd
                    numCyclesToMakeBorder = (int) (columns / 2);
                    for (int i = 0; i < numCyclesToMakeBorder; i++) {
                        System.out.print("--------");
                    }
                    System.out.print("----");
                } else {
                    //It's even
                    numCyclesToMakeBorder = (int) (columns / 2);
                    for (int i = 0; i < numCyclesToMakeBorder; i++) {
                        System.out.print("--------");
                    }
                }
                System.out.println("");
            } else {
                System.out.println("");
                System.out.println("");
                System.out.println("");
            }
        }
    }

    //attempts to reveal a cell based off of user input, at cell (r,c). If bomb, returns false
    public boolean revealCell(int r, int c) {
        if (map[r][c] == 'U' || map[r][c] == 'S') {//If it's a flag unflag and then dig
            markMine(r, c);
        }
        revealed[r][c] = true;
        this.updateMap(r, c);
        if (this.isBomb(r, c)) {
            //It's a bomb
            return false;
        } else {
            //It's not a bomb, reveal the cell + update map
            return true;
        }
    }

    //Given a cell, and an ArrayList of points, add all the inputted cell's adjacent cell coordinates to the ArrayList and return it.
    public ArrayList<Point> addAdjacentCells(int r, int c, ArrayList<Point> master) {
        //Add all 8 adjacent cells
        ArrayList<Point> adjacentCells = new ArrayList<Point>(9);
        adjacentCells.add(new Point(r - 1, c - 1));
        adjacentCells.add(new Point(r - 1, c));
        adjacentCells.add(new Point(r - 1, c + 1));
        adjacentCells.add(new Point(r, c - 1));
        adjacentCells.add(new Point(r, c + 1));
        adjacentCells.add(new Point(r + 1, c - 1));
        adjacentCells.add(new Point(r + 1, c));
        adjacentCells.add(new Point(r + 1, c + 1));
        double maxRow = rows - 1;
        double minRow = 0;
        double maxCol = columns - 1;
        double minCol = 0;
        //Check to see if they exist

        boolean doneLookingForNonexistantCells = false;
        int currIndex = 0;
        while (adjacentCells.size() != currIndex + 1) {

            Point currCell = new Point((int) adjacentCells.get(currIndex).getX(), (int) adjacentCells.get(currIndex).getY()); //Nice for formatting
            if (currCell.getX() > maxRow || currCell.getX() < minRow || currCell.getY() > maxCol || currCell.getY() < minCol) {
                //Specified cell doesn't exist, remove it from the list
                adjacentCells.remove(currIndex);
                //Dont adjust currIndex
            } else {
                //Adjust currIndex
                currIndex++;
            }
        }
        //return the new ArrayList
        master.addAll(adjacentCells);
        return master;
    }

    //reveals all cells adjacent to inputted coordinates
    public void revealAdjacentCells(int r, int c) {
        //Gotta be ready to catch the out of bounds exception
        try {
            revealed[r - 1][c - 1] = true;
        } catch (ArrayIndexOutOfBoundsException uhOh) {
            //Do nothing
        }
        try {
            revealed[r - 1][c] = true;
        } catch (ArrayIndexOutOfBoundsException uhOh) {
            //Do nothing
        }
        try {
            revealed[r - 1][c + 1] = true;
        } catch (ArrayIndexOutOfBoundsException uhOh) {
            //Do nothing
        }
        try {
            revealed[r][c - 1] = true;
        } catch (ArrayIndexOutOfBoundsException uhOh) {
            //Do nothing
        }
        try {
            revealed[r][c + 1] = true;
        } catch (ArrayIndexOutOfBoundsException uhOh) {
            //Do nothing
        }
        try {
            revealed[r + 1][c - 1] = true;
        } catch (ArrayIndexOutOfBoundsException uhOh) {
            //Do nothing
        }
        try {
            revealed[r + 1][c] = true;
        } catch (ArrayIndexOutOfBoundsException uhOh) {
            //Do nothing
        }
        try {
            revealed[r + 1][c + 1] = true;
        } catch (ArrayIndexOutOfBoundsException uhOh) {
            //Do nothing
        }

    }

    //Based on an input, update map by revealing all adjacent blank spots and numbers--but not bombs
    public void updateMap(int r, int c) {
        //Basic premise:
        //If they click on a 0, find all surrounding 0's and reveal everything directly adjacent to those 0's
        //If they click on a number, reveal that number and nothing else.
        ArrayList<Point> toSearch = new ArrayList<Point>(1);
        toSearch.add(new Point(r, c)); //initial setup of toSearch
        ArrayList<Point> alreadySearched = new ArrayList<Point>(0);
        while (toSearch.size() > 0) { //Until we're out of points to search, continue searching

            //Search all cells in toSearch,
            //Needed to prevent infinite loop in the for loop bc it has to update toSearch.size() without this
            ArrayList<Point> toSearchNextTime = new ArrayList<Point>();
            for (int i = 0; i < toSearch.size(); i++) {
                //Current coordinates from this index in toSearch:
                int currX = (int) toSearch.get(i).getX();
                int currY = (int) toSearch.get(i).getY();
                //Try to search, assuming the cell exists
                try {
                    //Do these coordinates correspond to a 0 in the map?
                    if (map[currX][currY] == ' ' && !alreadySearched.contains(new Point(currX, currY))) {
                        //Yes, it's a 0! Add the adjacents to toSearch!
                        toSearchNextTime = addAdjacentCells(currX, currY, toSearchNextTime);
                        //Alright, we'll search those next time around. But, the cells we just searched are logically
                        // NOT bombs as they were adjacent to a 0. Thus, they all need to be revealed.
                        revealAdjacentCells(currX, currY);
                    }
                } catch (ArrayIndexOutOfBoundsException uhOhSpaghettiOhs) {
                    //Lol woopsies! ya don goofed because this cell does not exist! Don't add it to toSearchNextTime

                }
                alreadySearched.add(toSearch.get(i));
            }

            //Done with current cell. Add to alreadySearched
            toSearch = toSearchNextTime;
        }
    }

    //returns true if bomb or a marker and false if not
    public boolean isBomb(int r, int c) {
        return map[r][c] == 'B' || map[r][c] == 'S';
    }

    //Check to see if all mines are marked
    public boolean fieldIsClear() {
        return numCorrectMarks == numMines;
    }

    // Notes to place a mark, if it's correctly marked then its stored as an S for safe,
    // if it's incorrect it's a U for useless
    // If the spot was previously marked, it will remove the mark and adjust counters accordingly
    public void markMine(int r, int c) {
        revealed[r][c] = false;
        switch (map[r][c]) {
            //Mark a bomb
            case 'B':
                map[r][c] = 'S';
                numCorrectMarks++;
                numMarks++;
                break;
            //Unmark a bomb
            case 'S':
                //They're
                map[r][c] = 'B';
                numCorrectMarks--;
                numMarks--;
                break;
            //Unmark a non-bomb
            case 'U':
                //Convert back to a number or a blank:
                int bombCounter = 0;
                for (int i = 0; i < 8; i++) {
                    try {
                        // Try looking at all possible surrounding cells,
                        // but be ready to catch errors cuz of corner/wall cells.
                        switch (i) {
                            case 0:
                                if (map[r - 1][c - 1] == 'B' || map[r - 1][c - 1] == 'S') bombCounter++;
                                break;
                            case 1:
                                if (map[r - 1][c] == 'B' || map[r - 1][c] == 'S') bombCounter++;
                                break;
                            case 2:
                                if (map[r - 1][c + 1] == 'B' || map[r - 1][c + 1] == 'S') bombCounter++;
                                break;
                            case 3:
                                if (map[r][c - 1] == 'B' || map[r][c - 1] == 'S') bombCounter++;
                                break;
                            case 4:
                                if (map[r][c + 1] == 'B' || map[r][c + 1] == 'S') bombCounter++;
                                break;
                            case 5:
                                if (map[r + 1][c - 1] == 'B' || map[r + 1][c - 1] == 'S') bombCounter++;
                                break;
                            case 6:
                                if (map[r + 1][c] == 'B' || map[r + 1][c] == 'S') bombCounter++;
                                break;
                            case 7:
                                if (map[r + 1][c + 1] == 'B' || map[r + 1][c + 1] == 'S') bombCounter++;
                                break;
                        }
                    } catch (ArrayIndexOutOfBoundsException cellNotFound) {
                        //Corner cell and or wall cell detected! Do nothing to counter.
                    }
                }
                if (bombCounter == 0) {
                    //It's a blank space
                    map[r][c] = ' ';
                } else {
                    //It's a number, set it to that number
                    map[r][c] = (char) (bombCounter + '0');
                }
                numMarks--;
                break;
            //mark a non-bomb
            default:
                map[r][c] = 'U';
                numMarks++;
                break;
        }
    }
    //attempts to reveal a cell based off of user input, at cell (r,c). If bomb, returns false

    //Return true if bomb
    public boolean takeTurn() {
        printMap();
        int userRow;
        int userCol;
        System.out.println("Chose an option:");
        System.out.println("1. Reveal a cell (default)");
        System.out.println("2. Mark/unmark a mine");
        System.out.println("3. Symbol key");
        System.out.println("");
        System.out.println("");
        Scanner keyboard = new Scanner(System.in);
        int userChoice = keyboard.nextInt();
        if (userChoice == 2) {
            //People marking mines will end up here

            //Recieves a row and column to mark
            System.out.println("");
            System.out.println("MARKING/UNMARKING PROTOCOL INITIATED");
            System.out.println("");
            System.out.print("Enter a row:");
            userRow = keyboard.nextInt();
            keyboard.nextLine();
            System.out.print("Enter a column:");
            userCol = keyboard.nextInt();
            System.out.println("");

            //Runs marking protocol on desired cell
            markMine(userRow, userCol);
            return false;
        } else if (userChoice == 3) {
            //Confused ppls will end up here to look at the key
            System.out.println("");
            System.out.println("SYMBOLS KEY:");
            System.out.println("");
            System.out.println("Empty Blue Cell:\t\t\t\t\tUnknown Cell");
            System.out.println("Empty Green Cell:\t\t\t\t\tCleared Empty Cell");
            System.out.println("Yellow Cell with number:\t\t\tNumber = # of mines adjacent to that cell");
            System.out.println("Gray Cell with Red /|\\ mark:\t\tMine Marker");
            System.out.println("");
            System.out.println("GAME OVER/VICTORY MAP ADDITIONAL SYMBOLS:");
            System.out.println("");
            System.out.println("Purple Cell with Red /|\\ mark:\t\tIncorrect Mine Marker");
            System.out.println("Blue Cell with Red *:\t\t\t\tCorrectly marked mine");
            System.out.println("Red Cell with White/Black *:\t\tUnmarked Mine");
            System.out.println("");
            return false;
        } else {
            //Silly tricksters and ppl who want to reveal cells will end up here

            System.out.println("");
            System.out.println("REVEALING PROTOCOL INITIATED");
            System.out.println("");
            System.out.print("Enter a row:");
            userRow = keyboard.nextInt();
            keyboard.nextLine();
            System.out.print("Enter a column:");
            userCol = keyboard.nextInt();
            System.out.println("");
            boolean isABomb;
            if (userRow < rows && userCol < columns) {
                isABomb = !revealCell(userRow, userCol);
            } else isABomb = false;
            //Runs revealing protocol on desired cell
            return isABomb;
        }

    }

    public char getCell(int r, int c) {
        return map[r][c];
    }

    public void revealAll() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                revealed[r][c] = true;
            }
        }
    }

    //Implement take turn and check if the game ends.
    public void playGame() {
        boolean done = false;
        System.out.println("Minesweeper!");
        System.out.println("Mark all the mines!");
        while (!done) {
            boolean isBomb = takeTurn();
            if (!isBomb) {
                //Do nothing, take another turn lmao
            } else {
                //Found a bomb, game ends in a loss
                System.out.println("You dug up a mine and exploded!");
                System.out.println("");
                System.out.println("Here's the whole map:");
                for (int r = 0; r < rows; r++) {
                    for (int c = 0; c < columns; c++) {
                        revealed[r][c] = true;
                    }
                }
                printMap();
                System.out.println("Thanks for trying! Better luck next time...");
                done = true;
            }
            if (fieldIsClear()) {
                //All mines marked, they win, end the game and declare it so
                System.out.println("You Won!!");
                System.out.println("");
                System.out.println("Here's the whole map:");
                for (int r = 0; r < rows; r++) {
                    for (int c = 0; c < columns; c++) {
                        revealed[r][c] = true;
                    }
                }
                printMap();
                System.out.println("Come again soon epic gamer!!");
                done = true;

            }
        }
    }

    public int getNumCorrectMarks() {
        return numCorrectMarks;
    }

    public int getNumMarks() {
        return numMarks;
    }
}