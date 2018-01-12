package dm550.tictactoe;

/** represents a tic tac toe board of a given size */
public class TTTBoard {
    
    /** 2-dimensional array representing the board
     * coordinates are counted from top-left (0,0) to bottom-right (size-1, size-1)
     * board[x][y] == 0   signifies free at position (x,y)
     * board[x][y] == i   for i > 0 signifies that Player i made a move on (x,y)
     */
    private int[][] board;
    
    /** size of the (quadratic) board */
    private int size;
    
    /** constructor for creating a copy of the board
     * not needed in Part 1 - can be viewed as an example 
     */
    public TTTBoard(TTTBoard original) {
        this.size = original.size;
        for (int y = 0; y < this.size; y++) {
            for (int x = 0; x < this.size; x++) {
                this.board[y][x] = original.board[y][x];
            }
        }
    }
    
    /** constructor for creating an empty board for a given number of players */
    public TTTBoard(int numPlayers) {
        this.size = numPlayers+1;
        this.board = new int[this.getSize()][this.getSize()];
    }
    
    /** checks whether the board is free at the given position */
    public boolean isFree(Coordinate c) {
        if (this.board[c.getX()][c.getY()] == 0){
            return true;
        } else {
            return false;
        }
    }
    
    /** returns the players that made a move on (x,y) or 0 if the positon is free */
    public int getPlayer(Coordinate c) {
        return this.board[c.getX()][c.getY()];
    }
    
    /** record that a given player made a move at the given position
     * checks that the given positions is on the board
     * checks that the player number is valid 
     */
    public void addMove(Coordinate c, int player) {
        this.board[c.getX()][c.getY()] = player;
    }

    /** returns true if, and only if, there are no more free positions on the board */
    public boolean checkFull() {
        for (int x=0; x < this.size; x++){
            for (int y=0; y <this.size; y++){
                if (this.board[x][y] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    /** returns 0 if no player has won (yet)
     * otherwise returns the number of the player that has three in a row
     */
    public int checkWinning() {
        for (int x=0; x < this.size; ++x){
            for (int y = 0; y < this.size; ++y){
                Coordinate cord = new XYCoordinate(x,y);
                if (this.checkSequence(cord, 1,0) != 0){
                    return this.checkSequence(cord, 1,0);
                }
                if (this.checkSequence(cord, 1,1) != 0){
                    return this.checkSequence(cord, 1,0);
                }
                if (this.checkSequence(cord,0 ,1) != 0){
                    return this.checkSequence(cord, 1,0);
                }
                if (this.checkSequence(cord, -1,-1) != 0){
                    return this.checkSequence(cord, 1,0);
                }
            }
        }
        return 0;
    }
    
    /** internal helper function checking one row, column, or diagonal */
    private int checkSequence(Coordinate start, int dx, int dy) {
        int player = this.board[start.getX()][start.getY()];
        if (player == 0) return 0;
        Coordinate c = start;
        for (int i = 0; i < 2; ++i){
            c = c.shift(dx, dy);
            if (!c.checkBoundaries(this.size, this.size)){
                return 0;
            }
            if (this.board[c.getX()][c.getY()] != player){
                return 0;
            }
        }
        return player;
    }
    
    /** getter for size of the board */
    public int getSize() {
        return this.size;
    }
    
    /** pretty printing of the board
     * usefule for debugging purposes
     */
    public String toString() {
        String result = "";
        for (int y = 0; y < this.size; y++) {
            for (int x = 0; x < this.size; x++) {
                result += this.board[y][x]+" ";
            }
            result += "\n";
        }
        return result;
    }

}
