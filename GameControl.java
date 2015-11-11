import javax.swing.JFrame;
import java.awt.GridLayout;

public class GameControl{
    // default states of any new game
    private int worldSize = 0;
    private int generations = 0;
    private int delayTime = 0; // default delay is 0 second
    private boolean configStatus = false;

    // constructors
    public GameControl(){}

    public GameControl(int newSize, int numGenerations, int newDelay){
        this.generations = numGenerations;
        this.worldSize = newSize;
        this.delayTime = newDelay;
    }

    // methods
    public Cell [][] makeBoard(){
        /*
        inititializes the gameboard and sets the entire board to blank
        */
        Cell [][] board = new Cell[this.worldSize][this.worldSize];

        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[row].length; col++){
                board[row][col] = new Cell();
                board[row][col].setPosition(row, col); // add cell location as current cell member
            }
        }
        return board;
    }

    public JFrame makeDisplay(){
        // creates a display window to show game results instead of using terminal
        JFrame f = new JFrame("Life");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout gameLayout = new GridLayout(this.worldSize, this.worldSize);
        f.setLayout(gameLayout);
        f.setLocationRelativeTo(null);
        f.pack();
        f.setVisible(true);
        return f;
    }

    public void insertGlider(Cell [][] destBoard, int xPos, int yPos){
        // init glider pattern and inserts it into game board at (xPos, yPos)
        String [][] pattern = {{" ", "*", " "}, {" ", " ", "*"}, {"*","*","*"}}; // " " is dead, "*" is living

        int xIndex = xPos-1;
        int yIndex = yPos-1;

        for (int row = 0; row < pattern.length; row++){
            for (int col = 0; col < pattern[row].length; col++){
                String currentVal = pattern[row][col];
                if (currentVal == "*"){
                    Cell thisCell = destBoard[xIndex+row][yIndex+col];
                    thisCell.live();
                }
            }
        }
    }

    public void printBoard(Cell [][] gameBoard, JFrame gameWindow){
        // prints the entire gameboard for a single generation
        gameWindow.getContentPane().removeAll(); // clear current board
        for (int row = 0; row < gameBoard.length; row++){
            for (int col = 0; col < gameBoard[row].length; col++){
                Cell thisCell = gameBoard[row][col];
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
        this.worldSize = newWorldSize;
    }

    public int getWorldSize(){
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
        this.configStatus = newStatus;
    }

    public Cell [][] evaluateBoard(Cell [][] currentBoard){
        /*
        Evaluate the current game board and determine the state of each cell for next generation
        */

        // make new empty board to store the status of next board
        Cell [][] nextBoard = new Cell [currentBoard.length][currentBoard.length];
        
        // loop through current board & set life status of every cell
        for (int row = 0; row < currentBoard.length; row++){
            for (int col = 0; col < currentBoard[row].length; col++){
                    Cell cellThisGen = currentBoard[row][col]; // the cell during this generation
                    Cell cellNextGen = new Cell(); // the cell during next generation, defaults to dead
                    cellNextGen.setPosition(row, col);
                    int liveNeighborCount = cellThisGen.countLiveNeighbors(currentBoard);
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
        return nextBoard;
    }
} // end GameControl class