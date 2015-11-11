/*
The main game
*/
import javax.swing.JFrame;

public class Life{
    public static void main(String [] args){
        // initiate a new GameControl instance
        GameControl game = new GameControl();

        // set new game settings. All new instances of GameControl are unconfigured
        GameConfig config = new GameConfig(game);

        // continuously check for new configuration settings if GameControl is unconfigured
        while (!game.getConfigStatus()){
            game.delayGame(1);
        }

        // run program when GameControl is configured
        if (game.getConfigStatus()) {

            // init game board
            Cell [][] board = game.makeBoard();
            
            // make display window
            JFrame display = game.makeDisplay();
            
            // insert glider at position (1, 1)
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
}