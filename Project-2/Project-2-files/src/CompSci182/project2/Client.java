/*
 * CompSci 182 Project 2 - Thomas Wessel 2019
 * 
 * This class is very repetitive and this is by design. I want to create as many new LinkedStrings as
 * possible and test them in different ways. This could be done better, but the more things that can 
 * go wrong, the better!
 */
package CompSci182.project2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {
    
    //Pass or fail boolean values - default fail
    static boolean canCreate = false; //testOne
    static boolean canLength = false; //testOne
    static boolean canTellWhenEmpty = false; //testOne
    static boolean canSubstring = false; //testTwo
    static boolean canConcat = false; //testThree
    static boolean canCharAtString = false; //testFour
    
    public static void main(String[] args) {
        menu();
    }
    
    //main Menu
    private static void menu(){
        int choice;
        System.out.println("    LinkedString Tester v1.4");
        System.out.println("<================================>");
        System.out.print("1. Basic String creation    ");
        if(canCreate && canLength && canTellWhenEmpty){System.out.print("✓");}else{System.out.print("✗");}
        System.out.print("\n2. Substring Creation       ");
        if(canSubstring){System.out.print("✓");}else{System.out.print("✗");}
        System.out.print("\n3. String Concatenation     ");
        if(canConcat){System.out.print("✓");}else{System.out.print("✗");}
        System.out.print("\n4. Character at Index       ");
        if(canCharAtString){System.out.print("✓");}else{System.out.print("✗");}
        System.out.print("\n5. All Tests");
        System.out.println("\n6. Exit");
        System.out.println("===================================");
        System.out.print("Choose a test: ");
        choice = indexValidation(1, 6);
        
        switch(choice){
            case 1:
                System.out.println("");
                testOne();
                break;
            case 2:
                System.out.println("");
                testTwo();
                break;
            case 3:
                System.out.println("");
                testThree();
                break;
            case 4:
                System.out.println("");
                testFour();
                break;
            case 5:
                System.out.println("");
                testOne();
                testTwo();
                testThree();
                testFour();
                finalReport();
                break;
            case 6:
                System.out.println("Goodbye!");
                System.exit(0);     
        }
        menu();
    }
    
    //Test One: Basic String Creation
    private static void testOne(){
        
        System.out.println("Test One: Basic String Creation");
        System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
        
        String StringOne;
        System.out.print("Please enter a string: ");
        
        //get String from User
        StringOne = stringValidation();
        
        //Create LinkedString from String
        LinkedString LStringOne = new LinkedString(StringOne);
        
        //tell user what was entered
        System.out.println("\nThe LinkedString is: " + LStringOne.toString());
        
        //Tell the user the String.length
        System.out.println("\nString.length says the length should be: " + StringOne.length());
        System.out.println("LinkedString.length says it should be: " + LStringOne.length());
        
        //Check to see if length matches
        if(StringOne.length() == LStringOne.length()){
            System.out.println("\nThey Match!");
            canLength = true;
            
        }
        else{
            System.out.println("We have an issue...");
        }
        
        //check to make sure creation is correct
        if(StringOne.equalsIgnoreCase(LStringOne.toString())){
            canCreate = true;
        }
        
        //check to see if isEmpty works
        if(StringOne.isEmpty() == LStringOne.isEmpty()){
            canTellWhenEmpty = true;
        }
        
        //clear two lines
        System.out.println("\n");
    }
    
    //Test Two: Substring Creation
    private static void testTwo(){
        
        
        System.out.println("Test Two: Substring Creation");
        System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
        
         //substring of new string
        String StringTwo;
        int index1, index2;
      
        System.out.print("Please enter a new string: ");
        
        StringTwo = stringValidation();
        
        //create new LString
        LinkedString LString2 = new LinkedString(StringTwo);
        
        //Tell the user what the string is
        System.out.println("\nThe string is: " + LString2.toString());
        
        //Ask user for index numbers for new substring
        System.out.print("\nPlease enter the starting index for the substring (0 - " + LString2.length() + "): ");
        index1 = indexValidation(0, LString2.length());
        
        System.out.print("Please enter the ending index for the substring ( " + index1 + " - " + LString2.length() + "): ");
        index2 = indexValidation(index1, LString2.length());
        
        //Create new substring
        LinkedString LSubString = LString2.substring(index1, index2);
        
        //print new substring
        System.out.println("\nThe substring is: " + LSubString.toString());
        System.out.println("It should be: " + StringTwo.substring(index1, index2));
        
        //check to see if they match
        if(StringTwo.substring(index1, index2).equals(LSubString.toString())){
            System.out.println("\nThey Match!");
            canSubstring = true;
        }
        else{
            System.out.println("\nWe have a problem!");
        }
        
        
        
        //two blank lines
        System.out.println("\n");
        
    }
    
    //Test Three: String Concatenation
    private static void testThree(){
     
       System.out.println("Test Three: String Concatenation");
       System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");

      //String Concat
        
        String StringThree;
        String StringFour;
        String ConString;
        LinkedString LStringThree;
        LinkedString LStringFour;
        LinkedString LConString;
        
        //get two strings
        System.out.print("Please enter string one: ");
        StringThree = stringValidation();
        System.out.print("Please enter the string to add: ");
        StringFour = stringValidation();
        
        //Create concat String and LinkedString
        ConString = StringThree.concat(StringFour);
        LStringThree = new LinkedString(StringThree);
        LStringFour = new LinkedString(StringFour);
        LConString = LStringThree.concat(LStringFour);
        
        //Tell the user what they are
        System.out.println("\nResult: " + LConString.toString());
        System.out.println("It should be: " + ConString);
        
        //compare them
        
        if(ConString.equalsIgnoreCase(LConString.toString())){
            System.out.println("\nIt Matches!");
            canConcat = true;
        }
        else{
            System.out.println("\nWe have an issue!");
        }
        
        //Spacing
        System.out.println("\n");
        
        
    }
    
    //Test Four: character at an index
    private static void testFour(){
      
       
       System.out.println("Test Four: character at an index");
       System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
       
       
        String StringFive;
        int selection = 0;
        
        System.out.print("Please enter a string: ");
        
        //get String from User
        StringFive = stringValidation();
        
        //Create LinkedString from String
        LinkedString LStringFive = new LinkedString(StringFive);
        
        //tell user what was entered
        System.out.println("\nThe LinkedString is: " + LStringFive.toString());
        
        //ask user for index to get char
        System.out.println("\nEnter index to get char from (0 - " + (LStringFive.length() - 1) + ")");
        selection = indexValidation(0, LStringFive.length() - 1);
        
        //tell user what that value is
        System.out.println("\nThe result is: " + LStringFive.charAt(selection));
        System.out.println("The resould should be: " + StringFive.charAt(selection));
        
        //check similarity
        
        if(LStringFive.charAt(selection) == StringFive.charAt(selection)){
            canCharAtString = true;
            System.out.println("\nThey are the same!");
        }
        else{
            System.out.println("\nWe have an error!");
        }
        
        //print two lines
        System.out.println("\n");
  
    }
    
    private static void finalReport(){
       System.out.println("Final Report: How bad is it?");
       System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾"); 
       System.out.println("Can create Strings: " + canCreate);
       System.out.println("Can tell if empty: " + canTellWhenEmpty);
       System.out.println("has proper length: " + canLength);
       System.out.println("Proper Substring: " + canSubstring);
       System.out.println("Can concatenate: " + canConcat);
       System.out.println("Can pick a char: " + canCharAtString);
       System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
       System.out.print("Overall: ");
       if(canCreate && canLength && canSubstring && canConcat && canTellWhenEmpty && canCharAtString){
           System.out.println("PASS");
       }
       else{
           System.out.println("FAIL");
       }
       //two blank lines
        System.out.println("\n");
       
        
    }
    
    private static int indexValidation(int min, int max){
        Scanner scanner = new Scanner(System.in);

        int num = 0;
        boolean loop = true;
        boolean haserrored = false;

        while (loop) {
            try {
                num = scanner.nextInt();
                loop = false;
            } catch (InputMismatchException e) {
                System.out.print("Invalid integer, please try again: ");
                scanner.next();
                haserrored = true;
            } 
            if(num > max && haserrored == false){
                loop = true;
                System.out.print("That number is to big, try again: "); 
                haserrored = true;
            }
            else if(num < min && haserrored == false){
                loop = true;
                System.out.print("That number is to small, try again: ");
                haserrored = true;
            }
            haserrored = false;
        }
        return num;
    }
    
    private static String stringValidation(){
        //create scanner
        Scanner Scanner = new Scanner(System.in);
        
        String testString = "";
        boolean loop = true;
        
        while(loop){
        
            testString = Scanner.nextLine();
        
            if(testString.equalsIgnoreCase("")){
                System.out.print("String must not be empty, try again: ");
            }
            else{
                loop = false;
            }
        }
        
        return testString;
    }
    
}
