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
        JFrame f = new JFrame("Life");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout gameLayout = new GridLayout(board.length, board.length);
        f.setLayout(gameLayout);
        f.setVisible(true);       
        
        // insert glider at position (2, 2)
        game.insertGlider(board, 2, 2);
        
        // print game board
        game.printBoard(board, f);

        int gameIterations = game.getMaxGenerations();

        for(int i = 1; i <= gameIterations; i++){
            System.out.println("Generation: "+i+"/"+gameIterations);
            board = game.evaluateBoard(board);
            game.printBoard(board, f);
            game.delayGame(game.getDelay());
        }
    }
}