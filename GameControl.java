/*
GameControl.java

Initializes and controls the game..
Handles all outputs of the game to the output display window and/or terminal.
*/
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.util.Random;

public class GameControl{
    // default states of any new game
    private int worldSize = 0;
    private int generations = 0;
    private int delayTime = 0;
    private boolean configStatus = false;
    private Cell [][] board;
    private JFrame display;

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

    public void makeDisplay(){
        // creates a display window to show game results instead of using terminal
        display = new JFrame("Life");
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout gameLayout = new GridLayout(this.worldSize, this.worldSize);
        display.setLayout(gameLayout);
        display.pack();
        display.setVisible(true);
    }

    public void insertGlider(){
        // make new glider object and insert it into the game board
        Glider thisGlider = new Glider();
        String [][] pattern = thisGlider.getPattern();

        // set insert positions
        Random rNum = new Random();
        int xIndex = rNum.nextInt(worldSize/2);
        int yIndex = rNum.nextInt(worldSize/2);

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

    public void printBoard(){
        // prints the entire gameboard for a single generation
        display.getContentPane().removeAll(); // clear current board
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[row].length; col++){
                Cell thisCell = board[row][col];
                if (thisCell.hasLife()){
                    display.add(new CellPanel(true));
                } else {
                    display.add(new CellPanel());
                }
            }
        }
        display.pack();
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

    public void run(){
        // initiate the game

        // delay the game until game configuration status == true
        while(!getConfigStatus()){
            delayGame(1);
        }

        // run program when game is configured
        if (getConfigStatus()) {

            // init game board
            makeBoard();
            
            // make display window
            makeDisplay();
            
            // insert glider
            insertGlider();
            
            // print generation 0
            printBoard();
            delayGame(getDelay());

            int gameIterations = getMaxGenerations();

            // iterate through every generation
            for(int i = 0; i <= gameIterations; i++){
                display.setTitle("Life - Evaluating Generation: "+i);
                evaluateBoard();
                printBoard();
                delayGame(getDelay());
            }
            display.setTitle("Life - Game Complete "+gameIterations+" Generations Evaluated");
        }        
    }
} // end GameControl class