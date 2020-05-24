/* Project #3
 * StringRecognizer.java
 * Thomas Wessel 2019
 * Due April 15, 2019
 * 
 * This uses a stack to determine if if text after a $ are the reverse of what is
 * before the $. This code will say that a $ is part of the language.
 */


package stringRecognizer;

import stack.Stack;
import java.util.Scanner;

public class StringRecognizer {
    public static void main(String[] args) {
        
        //create scanner
        Scanner input = new Scanner(System.in);
        
        //Ask user for a string
        System.out.print("Please enter a string: ");
        
        //inform the user if the string is valid
        if(isValidString(input.nextLine())){
            System.out.println("The string is part of the language!");
        }
        else{
            System.out.println("The string is not part of the language!");
        }
    }
    
    
    //method to test validity
    private static boolean isValidString(String newString){
        
        //does match bool - true until a test fails
        boolean doesMatch = true;
        
        //create empty stack
        Stack numberStack = new Stack();
        
        //push while char is not $ (If this fails, the string is not part of the language)
        try{
          for(int i = 0; newString.charAt(i) != '$'; i++ ){
            numberStack.push(newString.charAt(i));
          }  
        }catch(Exception e){
            doesMatch = false;
        }
        
        
        //The code below just makes sure that there are at least as many characters on the right side that match,
        //I will now make sure that newString.length is equal to numberStack.length * 2 + 1
        //The stack should be half of the string sans the $
        if(newString.length() != numberStack.getSize() * 2 + 1){
            doesMatch = false;
        }
        
        //compare stack while it is not empty
        int currentPos = numberStack.getSize() ;
        
        //This can fail if the user does not enter anything but a $, so I will catch it
        try{
            while(numberStack.getSize() !=0 && doesMatch == true){
                doesMatch = (Character)numberStack.pop() == newString.charAt(currentPos + 1);
                currentPos++;
            }
        }catch(Exception e){
            //This should only happen if there is just a $
            doesMatch = false;
        }
        
        
//        //This is in place if I have to make a single $ false
//        if(newString.length() == 1 && newString.contains("$")){
//            doesMatch = false;
//        }
        
        return doesMatch;
    }
}
