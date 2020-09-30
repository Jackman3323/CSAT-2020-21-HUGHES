import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

public class Hughes_MinesweeperApplet extends JFrame implements ActionListener {

    /**
     * Height of the game frame.
     */
    private static final int DEFAULT_HEIGHT = 1000;
    /**
     * Width of the game frame.
     */
    private static final int DEFAULT_WIDTH = 1800;
    /**
     * Width of a cell.
     */
    private int CELL_WIDTH;
    /**
     * Height of a cell.
     */
    private int CELL_HEIGHT;
    /**
     * Row (y coord) of the upper left corner of the first cell.
     */
    private static final int LAYOUT_TOP = 30;
    /**
     * Column (x coord) of the upper left corner of the first cell.
     */
    private static final int LAYOUT_LEFT = 30;
    /**
     * Distance between the upper left x coords of
     * two horizonally adjacent cells.
     */
    private int LAYOUT_WIDTH_INC;
    /**
     * Distance between the upper left y coords of
     * two vertically adjacent cells.
     */
    private int LAYOUT_HEIGHT_INC;
    /**
     * y coord of the "Reset" button.
     */
    private static final int BUTTON_TOP = 30;
    /**
     * x coord of the "Change Mode" button.
     */
    private static final int BUTTON_LEFT = DEFAULT_WIDTH - 310;
    /**
     * Distance between the tops of the "Replace" and "Restart" buttons.
     */
    private static final int BUTTON_HEIGHT_INC = 50;
    /**
     * y coord of the "n unmarked mines remain" label.
     */
    private static final int LABEL_TOP = 160;
    /**
     * x coord of the "n undealt cards remain" label.
     */
    private static final int LABEL_LEFT = DEFAULT_WIDTH - 300;
    /**
     * Distance between the tops of the "n undealt cards" and
     * the "You lose/win" labels.
     */
    private static final int LABEL_HEIGHT_INC = 35;

    /**
     * The main panel containing the game components.
     */
    private JPanel panel;
    /**
     * The Reset button.
     */
    private JButton resetButton;
    /**
     * The flag button.
     */
    private JButton flagButton;
    /**
     * The dig button.
     */
    private JButton digButton;
    /**
     * The "number of unmarked mines remain" message.
     */
    private JLabel statusMsg;
    /**
     * The "you've won n out of m games" message.
     */
    private JLabel totalsMsg;
    /**
     * The row selector dropdown.
     */
    private JComboBox<Integer> rowDropdown;
    /**
     * The column selector dropdown.
     */
    private JComboBox<Integer> colDropdown;
    /**
     * The Bomb Selector dropdown.
     */
    private JComboBox<Integer> bombDropdown;
    /**
     * The cell grid.
     */
    private JLabel[][] cellGrid;
    /**
     * The win message.
     */
    private JLabel winMsg;
    /**
     * The loss message.
     */
    private JLabel lossMsg;
    /**
     * The coordinates of the cellGrid cells.
     */
    private Point[][] cellCoords;
    /**
     * The "Current mode is: [mark/dig]" message.
     */
    private JLabel modeMsg;
    /**
     * The Grid Size Dropdown
     */
    private JComboBox<String> sizeMenu;
    /**
     * kth element is true iff the user has selected cell #k.
     */
    private boolean[][] selections;
    /**
     * kth element is true iff that cell has not yet been revealed.
     */
    private boolean[][] selectable;
    /**
     * The number of games won.
     */
    private int totalWins;
    /**
     * The number of games played.
     */
    private int totalGames;
    /**
     * The number of mines laid.
     */
    private int numMines;
    /**
     * The number of rows in the  board.
     */
    private int numRows;
    /**
     * The number of columns in the board.
     */
    private int numCols;

    private Hughes_Minesweeper game;//Instance data for the minesweeper game itself

    public void repaint() {
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                String cellImageFileName = imageFileName(r, c, selections[r][c]);
                URL imageURL = getClass().getResource(cellImageFileName);
                if (imageURL != null) {
                    //Image URl is good
                    ImageIcon icon = new ImageIcon(imageURL);
                    Image scaledImg = getScaledImage(icon.getImage(), CELL_WIDTH, CELL_HEIGHT);
                    icon = new ImageIcon(scaledImg);
                    cellGrid[r][c].setIcon(icon);
                    cellGrid[r][c].setVisible(true);
                } else {
                    throw new RuntimeException(
                            "Card image not found: \"" + cellImageFileName + "\"");
                }
            }
        }
        statusMsg.setText(numMines - game.getNumMarks() + " flags remain.");
        statusMsg.setVisible(true);
        totalsMsg.setText("You've won " + totalWins + " out of " + totalGames + " games.");
        totalsMsg.setVisible(true);
        pack();
        panel.repaint();
    }

    //Constructor for custom size
    public Hughes_MinesweeperApplet(int rows, int cols, int bombs) { //size 1 = smol, size 2 = medium, size 3 = large
        this.totalWins = 0;
        this.totalGames = 0;
        numCols = cols;
        numRows = rows;
        numMines = bombs;
        CELL_WIDTH = (DEFAULT_WIDTH - 350) / numCols;
        CELL_HEIGHT = this.CELL_WIDTH;
        this.game = new Hughes_Minesweeper(numRows, numCols, numMines);
        LAYOUT_WIDTH_INC = CELL_WIDTH;
        LAYOUT_HEIGHT_INC = CELL_HEIGHT;

        //Init cellCoords and selections and selectable
        selections = new boolean[numRows][numCols];
        cellCoords = new Point[numRows][numCols];
        selectable = new boolean[numRows][numCols];
        int x;
        int y;
        for (int r = 0; r < numRows; r++) {
            y = LAYOUT_TOP + (r * LAYOUT_HEIGHT_INC);
            for (int c = 0; c < numCols; c++) {
                x = LAYOUT_LEFT + (c * LAYOUT_WIDTH_INC);
                cellCoords[r][c] = new Point(x, y);
                selections[r][c] = false;
            }
        }
        initDisplay();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        repaint();
    }

    //Constructor for preset sizes
    public Hughes_MinesweeperApplet(int size) { //size 1 = smol, size 2 = medium, size 3 = large
        this.totalWins = 0;
        this.totalGames = 0;

        /**
         * These all need to initialize cell width and cell height (they're gonna be squares for now but ive got them
         * separate for nice formatting) and the game itself
         **/
        if (size == 3) {
            //Big, 20 rows and 40 cols, 25 mines
            numRows = 20;
            numCols = 40;
            numMines = 60;
            CELL_WIDTH = (DEFAULT_WIDTH - 350) / numCols;
            CELL_HEIGHT = this.CELL_WIDTH;
            LAYOUT_WIDTH_INC = CELL_WIDTH;
            LAYOUT_HEIGHT_INC = CELL_HEIGHT;
            this.game = new Hughes_Minesweeper(numRows, numCols, numMines);
        } else if (size == 2) {
            //Medium
            numRows = 10;
            numCols = 20;
            numMines = 30;
            CELL_WIDTH = (DEFAULT_WIDTH - 350) / numCols;
            CELL_HEIGHT = this.CELL_WIDTH;
            this.game = new Hughes_Minesweeper(numRows, numCols, numMines);
            LAYOUT_WIDTH_INC = CELL_WIDTH;
            LAYOUT_HEIGHT_INC = CELL_HEIGHT;
        } else {
            //Smol
            numCols = 10;
            numRows = 5;
            numMines = 5;
            CELL_WIDTH = (DEFAULT_WIDTH - 350) / numCols;
            CELL_HEIGHT = this.CELL_WIDTH;
            this.game = new Hughes_Minesweeper(numRows, numCols, numMines);
            LAYOUT_WIDTH_INC = CELL_WIDTH;
            LAYOUT_HEIGHT_INC = CELL_HEIGHT;
        }
        //Init cellCoords and selections and selectable
        selections = new boolean[numRows][numCols];
        cellCoords = new Point[numRows][numCols];
        selectable = new boolean[numRows][numCols];
        int x;
        int y;
        for (int r = 0; r < numRows; r++) {
            y = LAYOUT_TOP + (r * LAYOUT_HEIGHT_INC);
            for (int c = 0; c < numCols; c++) {
                x = LAYOUT_LEFT + (c * LAYOUT_WIDTH_INC);
                cellCoords[r][c] = new Point(x, y);
                selections[r][c] = false;
            }
        }
        initDisplay();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        repaint();
    }

    /**
     * Display a win.
     */
    private void signalWin() {
        game.revealAll();
        getRootPane().setDefaultButton(resetButton);
        winMsg.setVisible(true);
        totalWins++;
        totalGames++;
    }

    /**
     * Display a loss.
     */
    private void signalLoss() {
        game.revealAll();
        getRootPane().setDefaultButton(resetButton);
        lossMsg.setVisible(true);
        totalGames++;
    }

    public void reInitDisplay() {
        //init selectable to all true
        this.selectable = new boolean[numRows][numCols];
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                this.selectable[row][col] = true;
            }
        }
        for (JLabel[] jLabels : cellGrid) {
            for (JLabel jLabel : jLabels) {
                jLabel.setVisible(false);
            }
        }
        cellGrid = new JLabel[numRows][numCols];
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                cellGrid[r][c] = new JLabel();
                panel.add(cellGrid[r][c]);
                cellGrid[r][c].setBounds((int) cellCoords[r][c].getX(), (int) cellCoords[r][c].getY(),
                        CELL_WIDTH, CELL_HEIGHT);
                cellGrid[r][c].addMouseListener(new MyMouseListener());
            }
        }

        pack();
        getContentPane().add(panel);
        getRootPane().setDefaultButton(digButton);
        panel.setVisible(true);
    }

    public void initDisplay() {
        //init selectable to all true
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                selectable[row][col] = true;
            }
        }
        panel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        //Title of panel = Minesweeper
        setTitle("Minesweeper");

        //Set sizes
        this.setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        panel.setLayout(null);
        panel.setPreferredSize(
                new Dimension(DEFAULT_WIDTH - 20, DEFAULT_HEIGHT - 20)
        );
        cellGrid = new JLabel[numRows][numCols];
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                cellGrid[r][c] = new JLabel();
                panel.add(cellGrid[r][c]);
                cellGrid[r][c].setBounds((int) cellCoords[r][c].getX(), (int) cellCoords[r][c].getY(),
                        CELL_WIDTH, CELL_HEIGHT);
                cellGrid[r][c].addMouseListener(new MyMouseListener());
            }
        }
        resetButton = new JButton();
        resetButton.setText("Reset");
        panel.add(resetButton);
        resetButton.setBounds(BUTTON_LEFT, BUTTON_TOP, 100, 30);
        resetButton.addActionListener(this);
        resetButton.addKeyListener(new myKeyboardListener());
        //TEMPORARILY DEPRECATED--JH 9-3-20, CONSIDERING REIMPLEMENTATION IN TANDEM WITH NEW SYSTEM
        /**
         sizeMenu = new JComboBox<String>();
         sizeMenu.addItem("Size 1");
         sizeMenu.addItem("Size 2");
         sizeMenu.addItem("Size 3");
         panel.add(sizeMenu);
         sizeMenu.setBounds(BUTTON_LEFT + 105,BUTTON_TOP,100,30);
         sizeMenu.addActionListener(this);
         */

        JLabel rowLabel = new JLabel("Rows:");
        rowLabel.setBounds(BUTTON_LEFT + 105, BUTTON_TOP, 100, 30);
        panel.add(rowLabel);

        rowDropdown = new JComboBox<Integer>();
        rowDropdown.addItem(5);
        rowDropdown.addItem(10);
        rowDropdown.addItem(15);
        rowDropdown.addItem(20);
        rowDropdown.addItem(25);
        rowDropdown.addItem(30);
        rowDropdown.addItem(35);
        panel.add(rowDropdown);
        rowDropdown.setBounds(BUTTON_LEFT + 165, BUTTON_TOP, 70, 30);
        rowDropdown.addActionListener(this);

        JLabel colLabel = new JLabel("Columns:");
        colLabel.setBounds(BUTTON_LEFT + 105, BUTTON_TOP + BUTTON_HEIGHT_INC, 100, 30);
        panel.add(colLabel);

        colDropdown = new JComboBox<Integer>();
        colDropdown.addItem(10);
        colDropdown.addItem(15);
        colDropdown.addItem(20);
        colDropdown.addItem(25);
        colDropdown.addItem(30);
        colDropdown.addItem(35);
        colDropdown.addItem(40);
        colDropdown.addItem(45);
        colDropdown.addItem(50);
        colDropdown.addItem(55);
        colDropdown.addItem(60);
        panel.add(colDropdown);
        colDropdown.setBounds(BUTTON_LEFT + 165, BUTTON_TOP + BUTTON_HEIGHT_INC, 70, 30);
        colDropdown.addActionListener(this);

        JLabel bombLabel = new JLabel("Mines:");
        bombLabel.setBounds(BUTTON_LEFT + 105, BUTTON_TOP + BUTTON_HEIGHT_INC + BUTTON_HEIGHT_INC, 100,
                30);
        panel.add(bombLabel);

        bombDropdown = new JComboBox<Integer>();
        bombDropdown.addItem(5);
        bombDropdown.addItem(10);
        bombDropdown.addItem(15);
        bombDropdown.addItem(20);
        bombDropdown.addItem(25);
        bombDropdown.addItem(30);
        bombDropdown.addItem(35);
        bombDropdown.addItem(40);
        bombDropdown.addItem(45);
        bombDropdown.addItem(50);
        bombDropdown.addItem(55);
        bombDropdown.addItem(60);
        bombDropdown.addItem(65);
        bombDropdown.addItem(70);
        bombDropdown.addItem(75);
        bombDropdown.addItem(80);
        bombDropdown.addItem(85);
        bombDropdown.addItem(90);
        bombDropdown.addItem(95);
        bombDropdown.addItem(100);
        bombDropdown.addItem(105);
        bombDropdown.addItem(110);
        bombDropdown.addItem(115);
        bombDropdown.addItem(120);
        bombDropdown.addItem(125);
        bombDropdown.addItem(130);
        bombDropdown.addItem(135);
        bombDropdown.addItem(140);
        bombDropdown.addItem(145);
        bombDropdown.addItem(150);
        bombDropdown.addItem(155);
        bombDropdown.addItem(160);
        bombDropdown.addItem(165);
        bombDropdown.addItem(170);
        bombDropdown.addItem(175);
        bombDropdown.addItem(180);
        bombDropdown.addItem(185);
        bombDropdown.addItem(190);
        bombDropdown.addItem(195);
        bombDropdown.addItem(200);
        panel.add(bombDropdown);
        bombDropdown.setBounds(BUTTON_LEFT + 165, BUTTON_TOP + BUTTON_HEIGHT_INC + BUTTON_HEIGHT_INC, 70,
                30);
        bombDropdown.addActionListener(this);

        digButton = new JButton();
        digButton.setText("Dig Here");
        panel.add(digButton);
        digButton.setBounds(BUTTON_LEFT, BUTTON_TOP + BUTTON_HEIGHT_INC, 100, 30);
        digButton.addActionListener(this);
        digButton.addKeyListener(new myKeyboardListener());

        flagButton = new JButton();
        flagButton.setText("Flag Mine");
        panel.add(flagButton);
        flagButton.setBounds(BUTTON_LEFT, BUTTON_TOP + (2 * BUTTON_HEIGHT_INC), 100, 30);
        flagButton.addActionListener(this);
        flagButton.addKeyListener(new myKeyboardListener());

        statusMsg = new JLabel(this.numMines - game.getNumMarks() + " flags remain.");
        panel.add(statusMsg);
        statusMsg.setBounds(LABEL_LEFT, LABEL_TOP, 250, 30);


        winMsg = new JLabel();
        winMsg.setBounds(LABEL_LEFT, LABEL_TOP + LABEL_HEIGHT_INC, 200, 30);
        winMsg.setFont(new Font("SansSerif", Font.BOLD, 25));
        winMsg.setForeground(Color.GREEN);
        winMsg.setText("You win!");
        panel.add(winMsg);
        winMsg.setVisible(false);

        lossMsg = new JLabel();
        lossMsg.setBounds(LABEL_LEFT, LABEL_TOP + LABEL_HEIGHT_INC, 200, 30);
        lossMsg.setFont(new Font("SanSerif", Font.BOLD, 25));
        lossMsg.setForeground(Color.RED);
        lossMsg.setText("Sorry, you lose.");
        panel.add(lossMsg);
        lossMsg.setVisible(false);

        totalsMsg = new JLabel("You've won " + totalWins
                + " out of " + totalGames + " games.");
        totalsMsg.setBounds(LABEL_LEFT, LABEL_TOP + 2 * LABEL_HEIGHT_INC,
                250, 30);
        panel.add(totalsMsg);

        pack();
        getContentPane().add(panel);
        getRootPane().setDefaultButton(digButton);
        panel.setVisible(true);


    }

    public void digSelected() {
        //Dig selected cell
        Point selectedCell = new Point();
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                if (selections[r][c]) {
                    selectedCell = new Point(r, c);
                }
            }
        }
        //method returns false if bomb, variable will be true if bomb
        boolean wasBomb = !game.revealCell(selectedCell.x, selectedCell.y);
        if (wasBomb) {
            signalLoss();
        }
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                selections[r][c] = false;
            }
        }
        this.repaint();
    }

    public void flagSelected() {
        //Flag selected cell
        Point selectedCell = new Point();
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                if (selections[r][c]) {
                    selectedCell = new Point(r, c);
                }
            }
        }
        game.markMine(selectedCell.x, selectedCell.y);
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                selections[r][c] = false;
            }
        }
        if (game.fieldIsClear()) {
            signalWin();
        }
        this.repaint();
    }

    private void signalError() {
        //Deal with silly ppl clicking on something other than what they should be clicking on
        Toolkit t = panel.getToolkit();
        t.beep();
    }

    private String imageFileName(int row, int col, boolean isSelected) {
        if (isSelected) {
            return "images/selected.png";
        }
        String str = "images/";
        switch (game.getCell(row, col)) {
            case 'B':
                //Bomb
                if (game.revealed[row][col]) {
                    //Revealed image
                    str += "bomb";
                    selectable[row][col] = false;
                } else {
                    //Hidden blank image
                    str += "facingDown";
                }
                break;
            case 'S':
            case 'U':
                //Incorrect marker
                //Marker
                str += "flagged";
                break;
            case '1':

                if (game.revealed[row][col]) {
                    //Revealed image
                    str += "1";
                    selectable[row][col] = false;
                } else {
                    //Hidden blank image
                    str += "facingDown";
                }
                break;
            case '2':

                if (game.revealed[row][col]) {
                    //Revealed image
                    str += "2";
                    selectable[row][col] = false;
                } else {
                    //Hidden blank image
                    str += "facingDown";
                }
                break;
            case '3':
                //Incorrect marker
                //Marker
                if (game.revealed[row][col]) {
                    //Revealed image
                    str += "3";
                    selectable[row][col] = false;
                } else {
                    //Hidden blank image
                    str += "facingDown";
                }
                break;
            case '4':
                //Incorrect marker
                //Marker
                if (game.revealed[row][col]) {
                    //Revealed image
                    str += "4";
                    selectable[row][col] = false;
                } else {
                    //Hidden blank image
                    str += "facingDown";
                }
                break;
            case '5':
                //Incorrect marker
                //Marker
                if (game.revealed[row][col]) {
                    //Revealed image
                    str += "5";
                    selectable[row][col] = false;
                } else {
                    //Hidden blank image
                    str += "facingDown";
                }
                break;
            case '6':
                //Incorrect marker
                //Marker
                if (game.revealed[row][col]) {
                    //Revealed image
                    str += "6";
                    selectable[row][col] = false;
                } else {
                    //Hidden blank image
                    str += "facingDown";
                }
                break;
            case '7':
                //Incorrect marker
                //Marker
                if (game.revealed[row][col]) {
                    //Revealed image
                    str += "7";
                    selectable[row][col] = false;
                } else {
                    //Hidden blank image
                    str += "facingDown";
                }
                break;
            case '8':
                //Incorrect marker
                //Marker
                if (game.revealed[row][col]) {
                    //Revealed image
                    str += "8";
                    selectable[row][col] = false;
                } else {
                    //Hidden blank image
                    str += "facingDown";
                }
                break;
            case ' ':
                //Incorrect marker
                //Marker
                if (game.revealed[row][col]) {
                    //Revealed image
                    str += "0";
                    selectable[row][col] = false;
                } else {
                    //Hidden blank image
                    str += "facingDown";
                }
                break;
        }
        str += ".png";
        return str;
    }

    //Respond to a button click (either the restart button, the dig button, or the flag button)
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(resetButton)) {
            //reset the game
            this.numCols = (int) colDropdown.getSelectedItem();
            this.numRows = (int) rowDropdown.getSelectedItem();
            this.numMines = (int) bombDropdown.getSelectedItem();
            CELL_WIDTH = (DEFAULT_WIDTH - 350) / numCols;
            CELL_HEIGHT = this.CELL_WIDTH;
            this.game = new Hughes_Minesweeper(numRows, numCols, numMines);
            LAYOUT_WIDTH_INC = CELL_WIDTH;
            LAYOUT_HEIGHT_INC = CELL_HEIGHT;
            selections = new boolean[numRows][numCols];
            cellCoords = new Point[numRows][numCols];
            int x;
            int y;
            for (int r = 0; r < numRows; r++) {
                y = LAYOUT_TOP + (r * LAYOUT_HEIGHT_INC);
                for (int c = 0; c < numCols; c++) {
                    x = LAYOUT_LEFT + (c * LAYOUT_WIDTH_INC);
                    cellCoords[r][c] = new Point(x, y);
                    selections[r][c] = false;
                }
            }
            getRootPane().setDefaultButton(digButton);
            winMsg.setVisible(false);
            lossMsg.setVisible(false);
            reInitDisplay();
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.repaint();
        } else if (e.getSource().equals(digButton)) {
            digSelected();
        } else if (e.getSource().equals(flagButton)) {
            flagSelected();
        }
    }

    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    //Callable method to display and run the applet
    public void displayGame() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                setVisible(true);
            }
        });
    }

    //Mouse support, click on buttons, cells, etc
    private class MyMouseListener implements MouseListener {
        @Override
        //DEPRECATED--JH 8-3-20
        public void mouseClicked(MouseEvent e) {
            /**
             // A mouse click has occurred.
             // First, determine the cell.
             // If not in the board, do nothing.
             // If on a selectable cell, select.
             // If on an unselectable cell, play error noise
             // Set all not-selected cells to not selected
             for(int r = 0; r < numRows; r++){
             for(int c = 0; c < numCols; c++){
             selections[r][c] = false;
             }
             }
             repaint();
             for(int r = 0; r < numRows; r++){
             for(int c = 0; c < numCols; c++){
             if(e.getSource().equals(cellGrid[r][c]) && selectable[r][c]){
             selections[r][c] = true;
             repaint();
             }
             else{
             signalError();
             }
             }
             }
             */
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //Figure out which cell was previously selected
            //Unselect it
            //Select newly click cell iff:
            //-Cell is selectable
            //-Mouse is on a cell
            for (int r = 0; r < numRows; r++) {
                for (int c = 0; c < numCols; c++) {
                    selections[r][c] = false;
                }
            }
            for (int r = 0; r < numRows; r++) {
                for (int c = 0; c < numCols; c++) {
                    if (e.getSource().equals(cellGrid[r][c]) && selectable[r][c]) {
                        selections[r][c] = true;
                        repaint();
                    } else if (e.getSource().equals(cellGrid[r][c]) && !selectable[r][c]) {
                        signalError();
                        repaint();
                    }
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    //Keyboard support. D key = dig, F key = flag
    private class myKeyboardListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

            if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {

                //DIG BUTTON PRESSED
                digSelected();
            } else if (e.getKeyChar() == 'f' || e.getKeyChar() == 'F') {
                //FLAG BUTTON PRESSED
                flagSelected();
            } else {
                //Wrong key/not applicable key
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
