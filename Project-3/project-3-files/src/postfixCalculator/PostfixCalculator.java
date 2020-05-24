/* Project #3
 * PostfixCalculator.java
 * Thomas Wessel 2019
 * Due April 15, 2019
 * 
 * This program uses a stack to calculate the result of an equation givin in 
 * postfix form. It can only handle integers, so divisions will be wrong.
 */


package postfixCalculator;

import java.util.Scanner;
import stack.Stack;

public class PostfixCalculator {
    
    public static void main(String[] args) {
        
        //boolean hasErrored
        boolean hasErrored = false;
        //Create Scanner
        Scanner keyboard = new Scanner(System.in);
        
        //Create Stack
        Stack Numbers = new Stack();
        
        //String that holds the equation for parsing
        String equation = "";
        
        //asks the user for an expression
        System.out.print("Please enter an expression in postfix notation: ");
        equation = keyboard.nextLine();
        
        //run through the string to find an operand
        for(int i = 0; i < equation.length(); i++){
            if(isOperator(equation.charAt(i)) && hasErrored == false){
                
                //this can fail if there is a malformed equation
                try{
                    //Get two numbers from the stack and store them in variables
                    int num1 = (Integer)Numbers.pop();
                    int num2 = (Integer)Numbers.pop();
                    //perform operation and push the result from the stack
                    Numbers.push(performOperation(equation.charAt(i), num1, num2 )); 
                    
                }catch(Exception e){
                    //if it does fail, it will be because of a malformed equation
                    System.out.println("ERROR: Malformed Equation. Please Try Again!");
                    hasErrored = true;
                }  
            }
            //While the specification only wants one long string, this method is a bit more robust
            // as it can take a string in any format as long as there are digits entered
            else if(Character.isDigit(equation.charAt(i))){
                Numbers.push(Character.getNumericValue(equation.charAt(i)));
            }
        }
        
        //print the result when finished - This could fail if the previous try{} failed
        //This will only run if the stack contains one item and an error has not occured.
        if(Numbers.getSize() == 1 && hasErrored == false){
            System.out.println("The answer is: " + Numbers.pop());  
        }else{
            System.out.println("Unable to return an answer!");
        }
    }
    
    //return true if operand is detected
    private static boolean isOperator(char i){
        return i == '+' || i == '-' || i == '*' || i == '/' || i == '%';
    }
    
    //perform operation (num1 is the top of the stack)
    private static int performOperation(char operator, int num1, int num2) throws IllegalArgumentException{
        if(operator == '+'){
            return num1 + num2;
        }
        else if(operator == '-'){
            return num2 - num1;
        }
        else if(operator == '*'){
            return num1 * num2;
        }
        else if(operator == '/'){
            return num2 / num1;
        }
        else if(operator == '%'){
            return num2 % num1;
        }
        else{
            //I didn't bother to catch this because it should be impossible to get here...
            //Watch someone show me an obvious flaw in my code that catching this error 
            //could have prevented.
            throw new IllegalArgumentException("Invalid Operator");
        }
        
    }
    
}
