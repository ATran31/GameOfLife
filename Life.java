/*
The main game driver.
*/
import javax.swing.JFrame;

public class Life{
    public static void main(String [] args){
        // initiate a new game control  and game settings instance
        GameControl game = new GameControl();

        GameSettings config = new GameSettings(game);

        // delay the game until game configuration status == true
        while(!game.getConfigStatus()){
            game.delayGame(1);
        }

        // run program when game is configured
        if (game.getConfigStatus()) {

            // init game board
            game.makeBoard();
            
            // make display window
            JFrame display = game.makeDisplay();
            
            // insert glider at position (x, y)
            game.insertGlider(1, 1);
            
            // print generation 0
            game.printBoard(display);
            game.delayGame(game.getDelay());

            int gameIterations = game.getMaxGenerations();

            // iterate through every generation
            for(int i = 0; i < gameIterations; i++){
                System.out.println("Evaluating Generation: "+i);
                game.evaluateBoard(); // current board takes on new values for next generation
                game.printBoard(display);
                game.delayGame(game.getDelay());
            }
        }
    }
}