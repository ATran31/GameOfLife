/*
Life.java

The main game driver.
*/

public class Life{
    public static void main(String [] args){
        // initiate new control
        GameControl game = new GameControl();

        // configure settings
        GameSettings config = new GameSettings(game);

        // run game
        game.run();
    }
}