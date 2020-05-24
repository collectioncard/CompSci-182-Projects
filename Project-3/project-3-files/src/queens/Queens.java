/* Project #3
 * Queens.java
 * Thomas Wessel 2019
 * Due April 15, 2019
 * 
 * This program creates a 8x8 chessboard and uses backtracking to find a way to place
 * them in a way that the isUnderAttack method returns false.
 */

package queens;

public class Queens {
    
    //Number or squares per column
    public static final int BOARD_SIZE = 8;
    
    //Define what an empty square in an array looks like
    public static final int EMPTY = 0;
    
    //Define what queen containing square looks like
    public static final int QUEEN = 1; 
    
    //declare board shape
    private int board[][];
    
    //Constructor - builds 2D aray and fills it with zeros via nested for loops
    public Queens(){
        //init new board based upon BOARD_SIZE
        board = new int[BOARD_SIZE][BOARD_SIZE];
        
        //fill new boad with EMPTY var
        clearBoard();
    }
    
    //Method to clear the board
    public void clearBoard(){
        for(int row = 0; row < board.length; row++){
            for(int column = 0; column < board.length; column++){
                board[row][column] = EMPTY;
            }
        }
    }
    
    //Method to print the board
    public void displayBoard(){
        for(int row = 0; row < board.length; row++){
            for(int column = 0; column < board.length; column++){
                System.out.print("|" + board[row][column] + "|");
            }
            System.out.println("");
        }
    }
    
    //Method that places all queens
    public boolean placeQueens(int column){
        
        boolean hasPlaced = false;
        
        //check to see if you are finished
        if(column >= BOARD_SIZE){
            return true;
        }
        else{
         //start placing some queens
         hasPlaced = false;
         int row = 0; 
         
         //start loop
         while(row < BOARD_SIZE && !hasPlaced){
             //check to see if position is under attack
             if(isUnderAttack(row, column)){
                 //try the next row
                 row++;
             }
             else{
                 //queen should be safe, try placing it
                 setQueen(row, column);
                 
                 //update hasplaced and place the queen
                 hasPlaced = placeQueens(column + 1);
                 
                 //if the queen was not placed, remove it
                 if(!hasPlaced){
                   removeQueen(row,column);
                   row++;
                 }
                 
             }
         }
        }
        return hasPlaced;
    }
    
    //method places a queen at a given position
    private void setQueen(int row, int column){
        board[row][column] = QUEEN;
    }
   
    //method removes queen from given position
    private void removeQueen(int row, int column){
        //set given position to empty
        board[row][column] = EMPTY;
    }
    
    //method determines if a position is under attack
    private boolean isUnderAttack(int row, int column){
        //check Column to see if queen is present
        for(int i = 0; i < BOARD_SIZE; i++ ){
            if(board[i][column] == 1){
                return true;
            }
        }
        
        //A queen shouldnt be on the same row
        for(int i = 0; i < BOARD_SIZE; i++ ){
            if(board[row][i] == 1){
                return true;
            }
        }
        
        //Check Diag up left
        for (int i = row - 1, j = column - 1; i >= 0 && j >= 0; i--, j--) {
            if(board[i][j] == QUEEN){
            return true;
            }
        }
        //Check Diag down right
        for (int i = row + 1, j = column + 1; i < BOARD_SIZE && j < BOARD_SIZE; i++, j++) {
            if(board[i][j] == QUEEN){
            return true;
            }
        }
        
        //check Diag up right
        for (int i = row - 1, j = column + 1; i >= 0 && j < BOARD_SIZE; i--, j++) {
            if(board[i][j] == QUEEN){
            return true;
            }
        }
        //check Diag down left
        for (int i = row + 1, j = column - 1; i < BOARD_SIZE && j >= 0; i++, j--) {
            if(board[i][j] == QUEEN){
            return true;
            }
        }
        //If none of those tests returned true, return false
        return false;
    }
}
