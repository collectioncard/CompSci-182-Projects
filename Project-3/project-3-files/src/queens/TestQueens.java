/* Project #3
 * TestQueens.java
 * Thomas Wessel 2019
 * Due April 15, 2019
 * 
 * This program creates a Queens object and calls placeQueens and displayBoard
 */


package queens;


public class TestQueens {

    
    public static void main(String[] args) {
        
        //Create a Queens object called Qfinder
        Queens Qfinder = new Queens();
        //place the queens starting at column 0
        Qfinder.placeQueens(0);
        //Display the board
        Qfinder.displayBoard();
        
    }
    
}
