public class Cell{
    //default state of any new cell
    private boolean isAlive = false;
    private int [] position = {0, 0};

    // constructors
    public Cell(){}

    public Cell(boolean living, int xPos, int yPos){
        isAlive = living;
        position[0] = xPos-1;
        position[1] = yPos-1;
    }
    // methods
    void live(){
        // sets current cell to live
        isAlive = true;
    }

    void die(){
        // sets current cell to dead
        isAlive = false;
    }

    void setPosition(int newXPos, int newYPos){
        position[0] = newXPos-1;
        position[1] = newYPos-1;
    }

    public int [] getPosition(){
        return position;
    }

    public boolean hasLife(){
        return isAlive;
    }

    public int countLiveNeighbors(){
        return 0;
    }
}