public class Cell{
    //default state of any new cell
    private boolean isAlive = false;
    private int [] position = {0, 0};

    // constructors
    public Cell(){}

    public Cell(boolean living){
        isAlive = living;
    }
    // methods
    public void live(){
        // sets current cell to live
        isAlive = true;
    }

    public void die(){
        // sets current cell to dead
        isAlive = false;
    }

    public int [] getPosition(){
        return position;
    }

    public void setPosition(int rowPos, int colPos){
        position[0] = rowPos;
        position[1] = colPos;
    }

    public boolean hasLife(){
        return isAlive;
    }

    public int countLiveNeighbors(){
        return 0;
    }
}