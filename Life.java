/*
The main game
*/
import javax.swing.JFrame;
import java.awt.GridLayout;

public class Life{
    public static void main(String [] args){
        // game runs for 10 generations with 1 second delay between generations
        GameControl game = new GameControl(10, 1000);

        // init game board
        Cell [][] board = game.makeBoard(10, 10);
        
        // make game window
        JFrame display = game.makeDisplay(board);       
        
        // insert glider at position (2, 2)
        game.insertGlider(board, 2, 2);
        
        // print game board
        game.printBoard(board, display);
        game.delayGame(game.getDelay());

        int gameIterations = game.getMaxGenerations();

        for(int i = 1; i <= gameIterations; i++){
            System.out.println("Generation: "+i+"/"+gameIterations);
            board = game.evaluateBoard(board);
            game.printBoard(board, display);
            game.delayGame(game.getDelay());
        }
    }
}