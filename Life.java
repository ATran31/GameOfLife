/*
The main game
*/
import javax.swing.JFrame;
import java.awt.GridLayout;

public class Life{
    public static void main(String [] args){
        // game runs for 10 generations with 1 second delay between generations
        GameControl game = new GameControl(10);

        // init game board
        Cell [][] board = game.makeBoard(10, 10);
        
        // make display window
        JFrame display = game.makeDisplay(board);
        
        // insert glider at position (2, 2)
        game.insertGlider(board, 1, 1);
        
        // print generation 0
        game.printBoard(board, display);
        game.delayGame(game.getDelay());

        int gameIterations = game.getMaxGenerations();

        // iterate through every generation
        for(int i = 0; i < gameIterations; i++){
            System.out.println("Evaluating Generation: "+i);
            board = game.evaluateBoard(board); // current board takes on new values for next generation
            game.printBoard(board, display);
            game.delayGame(game.getDelay());
        }
    }
}