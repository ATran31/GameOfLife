/*
The main game
*/

public class Life{
    public static void main(String [] args){
        // game runs for 10 generations with 0.2 second delay between generations
        GameControl game = new GameControl(10, 200);
        
        // init game board
        Cell [][] board = game.makeBoard(10, 10);
        
        // insert glider at position (2, 2)
        game.insertGlider(board, 2, 2);
        
        // print first generation
        game.printBoard(board);

        int gameIterations = game.getMaxGenerations();

        // run game for specificed generations
        for(int i = 1; i < gameIterations; i++){
            System.out.println("Generation: "+i+"/"+gameIterations);
            game.evaluateBoard(board);
            game.printBoard(board);
            game.delayGame(game.getDelay()); // delay between each generation
        }
    }
}