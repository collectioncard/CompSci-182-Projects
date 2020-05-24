/* Project #1
 * HarmonicFunction.java
 * Thomas Wessel 2019
 * Due Feb 29, 2019
 * 
 * This program calls a method that recursivley calculates the nth harmonic with a
 * set of numbers
 */
package HarmonicFunction;

import java.util.Scanner;

public class HarmonicFunction {
    
    public static void main(String args[]){
       
        //Create Scanner
        Scanner input = new Scanner(System.in);
        
        //ask for nth harmonic number
        System.out.println("Please enter the nth harmonic number: ");
        
        System.out.println("The answer is: " + nthHarmonic(input.nextInt()));   
    }
     
    private static double nthHarmonic(int harmonic){
        
        //Check to see if anything follows the number given
        if (harmonic == 1){
            return 1.0;
        }
        else{
            //This took me a while to get, but I want to write it out or I wont remember
            //The first part will be 1 at first and will decrease as the nth
              // harmonic goes up. This new number will be added to 1 and this 
              // will loop until harmonic is 0
            return (1.0 / harmonic) + nthHarmonic(harmonic - 1);
        } 
    }
}
