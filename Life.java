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
            
            // insert glider
            game.insertGlider();
            
            // print generation 0
            game.printBoard(display);
            game.delayGame(game.getDelay());

            int gameIterations = game.getMaxGenerations();

            // iterate through every generation
            for(int i = 0; i < gameIterations; i++){
                display.setTitle("Life - Evaluating Generation: "+i);
                game.evaluateBoard();
                game.printBoard(display);
                game.delayGame(game.getDelay());
            }
            display.setTitle("Life - Game Complete "+gameIterations+" Generations Evaluated");
        }
    }
}