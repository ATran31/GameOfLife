/*
* Initializes and controls the game..
* Handles all outputs of the game to the output display window and/or terminal.
*/
import javax.swing.JFrame;
import java.awt.GridLayout;

public class GameControl{
    // default states of any new game
    private int worldSize = 0;
    private int generations = 0;
    private int delayTime = 0;
    private boolean configStatus = false;
    private Cell [][] board;

    // constructors
    public GameControl(){}

    public GameControl(int newSize, int numGenerations, int newDelay){
        this.generations = numGenerations;
        this.worldSize = newSize;
        this.delayTime = newDelay;
    }

    // methods
    public void makeBoard(){
        /*
        inititializes the gameboard and sets the entire board to blank
        */
        board = new Cell[this.worldSize][this.worldSize];

        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[row].length; col++){
                board[row][col] = new Cell();
                board[row][col].setPosition(row, col); // add cell location as current cell member
            }
        }
    }

    public JFrame makeDisplay(){
        // creates a display window to show game results instead of using terminal
        JFrame f = new JFrame("Life");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout gameLayout = new GridLayout(this.worldSize, this.worldSize);
        f.setLayout(gameLayout);
        f.pack();
        f.setVisible(true);
        return f;
    }

    public void insertGlider(int xPos, int yPos){
        // make new glider object and insert it into the game board
        Glider thisGlider = new Glider();
        String [][] pattern = thisGlider.getPattern();

        // set insert positions
        int xIndex = xPos-1;
        int yIndex = yPos-1;

        // loop through pattern and insert * positions into main game board
        for (int row = 0; row < pattern.length; row++){
            for (int col = 0; col < pattern[row].length; col++){
                String currentVal = pattern[row][col];
                if (currentVal == "*"){
                    Cell thisCell = board[xIndex+row][yIndex+col];
                    thisCell.live();
                }
            }
        }
    }

    public void printBoard(JFrame gameWindow){
        // prints the entire gameboard for a single generation
        gameWindow.getContentPane().removeAll(); // clear current board
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[row].length; col++){
                Cell thisCell = board[row][col];
                if (thisCell.hasLife()){
                    gameWindow.add(new CellPanel(true));
                } else {
                    gameWindow.add(new CellPanel());
                }
            }
        }
        gameWindow.pack();
    }

    public void setWorldSize(int newWorldSize){
        // sets the size of the game world
        this.worldSize = newWorldSize;
    }

    public int getWorldSize(){
        // get the size of the world
        return this.worldSize;
    }

    public void setMaxGenerations(int numGenerations){
        // set the max number of generations to run the game
        this.generations = numGenerations;
    }

    public int getMaxGenerations(){
        // returns the number of max generations in game instance
        return this.generations;
    }

    public void setDelay(int newDelay){
        // set the delay between each generation in miliseconds
        this.delayTime = newDelay;
    }

    public int getDelay(){
        // get the delay between generations in miliseconds
        return this.delayTime;
    }

    public void delayGame(int delayFor){
        // delay the game for specified number of miliseconds
        try{
            Thread.sleep(delayFor);
        } catch (InterruptedException e) {
            System.out.println("Delay Exception");
        }
    }

    public boolean getConfigStatus(){
        return this.configStatus;
    }

    public void setConfigStatus(boolean newStatus){
        // set whether the game control has been configured
        this.configStatus = newStatus;
    }

    public void evaluateBoard(){
        //Evaluate the current game board and determine the state of each cell for next generation
        // make new empty board to store the status of next board

        Cell [][] nextBoard = new Cell [board.length][board.length];
        
        // loop through current board & set life status of every cell
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[row].length; col++){
                    Cell cellThisGen = board[row][col]; // the cell during this generation
                    Cell cellNextGen = new Cell(); // the cell during next generation, defaults to dead
                    cellNextGen.setPosition(row, col);
                    int liveNeighborCount = cellThisGen.countLiveNeighbors(board);
                    boolean cellAlive = cellThisGen.hasLife();
                    
                    if (cellAlive && (liveNeighborCount < 2 || liveNeighborCount > 3)){
                        cellNextGen.die();
                    } else if (cellAlive){
                        cellNextGen.live();
                    }

                    if (!cellAlive && liveNeighborCount == 3) {
                        cellNextGen.live();
                    } else if (!cellAlive && liveNeighborCount != 3) {
                        cellNextGen.die();
                    }

                    nextBoard[row][col] = cellNextGen; // insert new cell status into next generation board
            }
        }
        board = nextBoard;
    }
} // end GameControl class