/*
The main game driver.
*/
import javax.swing.JFrame;

public class Life{
    public static void main(String [] args){
        // initiate game
        GameControl game = new GameControl();

        // configure game settings
        GameSettings config = new GameSettings(game);

        // run game
        game.run();
    }
}