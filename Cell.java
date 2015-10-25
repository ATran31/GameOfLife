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
    void live(){
        // sets current cell to live
        isAlive = true;
    }

    void die(){
        // sets current cell to dead
        isAlive = false;
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