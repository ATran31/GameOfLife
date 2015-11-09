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

    // classify position
    private String isCornerOrEdge(Cell [][] gameBoard){
        int boardSize = gameBoard.length-1;
        String str = "";
        int rowIndex = this.position[0];
        int colIndex = this.position[1];
        int sumPos = rowIndex+colIndex;

        if (sumPos == 0){
            str = "Corner-TL";
        }
        else if (sumPos == boardSize && rowIndex == 0){
            str = "Corner-TR";
        }
        else if (sumPos == boardSize && colIndex == 0){
            str = "Corner-BL";
        }
        else if (sumPos == boardSize*2){
            str = "Corner-BL";
        }
        else if (rowIndex == 0){
            str = "Edge-Top";
        }
        else if (colIndex == 0){
            str = "Edge-Left";
        }
        else if (rowIndex == boardSize){
            str = "Edge-Bottom";
        }
        else if (colIndex == boardSize){
            str = "Edge-Right";
        }
        return str;
    }

    // count live neighbors
    public int countLiveNeighbors(Cell [][] gameBoard){
        int counter = 0;
        int rowIndex = this.position[0];
        int colIndex = this.position[1];
        int boardSize = gameBoard.length;
        int [] topLeft = {rowIndex-1, colIndex-1};
        int [] topCenter = {rowIndex-1, colIndex};
        int [] topRight = {rowIndex-1, colIndex+1};
        int [] left = {rowIndex, colIndex-1};
        int [] right = {rowIndex, colIndex+1};
        int [] bottomLeft = {rowIndex+1, colIndex-1};
        int [] bottomCenter = {rowIndex+1, colIndex};
        int [] bottomRight = {rowIndex+1, colIndex+1};
        
        String position = isCornerOrEdge(gameBoard); 
        
        if (position == "Corner-TL"){
            int [][] searchPattern = {right, bottomCenter, bottomRight};
            for (int [] place : searchPattern){
                int cRow = place[0];
                int cCol = place[1];
                if (gameBoard[cRow][cCol].hasLife()){
                    counter += 1;
                }
            }
        }
        else if (position == "Corner-TR"){
            int [][] searchPattern = {left, bottomCenter, bottomLeft};
            for (int [] place : searchPattern){
                int cRow = place[0];
                int cCol = place[1];
                if (gameBoard[cRow][cCol].hasLife()){
                    counter += 1;
                }
            }
        }
        else if (position == "Corner-BL"){
            int [][] searchPattern = {topCenter, topRight, right};
            for (int [] place : searchPattern){
                int cRow = place[0];
                int cCol = place[1];
                if (gameBoard[cRow][cCol].hasLife()){
                    counter += 1;
                }
            }
        }
        else if (position == "Corner-BR"){
            int [][] searchPattern = {left, topLeft, topCenter};
            for (int [] place : searchPattern){
                int cRow = place[0];
                int cCol = place[1];
                if (gameBoard[cRow][cCol].hasLife()){
                    counter += 1;
                }
            }
        }
        else if (position == "Edge-Left"){
            int [][] searchPattern = {topCenter, topRight, right, bottomCenter, bottomRight};
            for (int [] place : searchPattern){
                int cRow = place[0];
                int cCol = place[1];
                if (gameBoard[cRow][cCol].hasLife()){
                    counter += 1;
                }
            }
        }
        else if (position == "Edge-Right"){
            int [][] searchPattern = {topCenter, topLeft, bottomCenter, bottomLeft, left};
            for (int [] place : searchPattern){
                int cRow = place[0];
                int cCol = place[1];
                if (gameBoard[cRow][cCol].hasLife()){
                    counter += 1;
                }
            }
        }
        else if (position == "Edge-Top"){
            int [][] searchPattern = {left, bottomLeft, bottomCenter, bottomRight, right};
            for (int [] place : searchPattern){
                int cRow = place[0];
                int cCol = place[1];
                if (gameBoard[cRow][cCol].hasLife()){
                    counter += 1;
                }
            }
        }
        else if (position == "Edge-Bottom"){
            int [][] searchPattern = {left, topLeft, topCenter, topRight, right};
            for (int [] place : searchPattern){
                int cRow = place[0];
                int cCol = place[1];
                if (gameBoard[cRow][cCol].hasLife()){
                    counter += 1;
                }
            }
        }
        else {
            // default
            int [][] searchPattern = {topLeft, topCenter, topRight, left, right, bottomLeft, bottomCenter, bottomRight};
            for (int [] place : searchPattern){
                int cRow = place[0];
                int cCol = place[1];
                if (gameBoard[cRow][cCol].hasLife()){
                    counter += 1;
                }
            }
        }
        return counter;
    }
}