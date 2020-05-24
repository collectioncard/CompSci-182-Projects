/* Project #1
 * BetterClient.java
 * Thomas Wessel 2019
 * Due Feb 29, 2019
 * This program creates a Creditcard object and runs a *better* UI to modify it.
 * 
 * Even though the Creditcard *should* be able to handle calls without any cleanup (they will probably run into custom exceptions)
 * This client tries to clean some stuff up to make things a little bit easier for the Creditcard
 */
package ADT;

import java.util.Scanner;
import java.util.InputMismatchException;



public class BetterClient {
    
    public static void main(String args[]){

        //create variable that tracks the account number
        int accountNumber;
        
        //create other variables
        String name;
        double balance;
        
        //create scanner object
        Scanner scanner = new Scanner(System.in);
        
        //ask user for their name
        System.out.print("Please enter your name: ");
        name = scanner.nextLine();
        
        //ask user for balance
        System.out.print("What is your account balance: ");
        balance = doubleInputValidation();
        
        //create CreditCard object with new name and balance
        Creditcard CreditCard = new Creditcard(balance, name);
        
        //tell the user what the default account number is 
        System.out.println("\nWelcome to the ATM: The default account is: 123456");
        
        //Ask the user for their login
        accountNumber = changeAccountNumber(CreditCard);
        
        //Call the bank menu method
        bankMenu(CreditCard, accountNumber);
        
    }
    
    private static int accountCreator(Creditcard CreditCard){
       
        //Create Temp variables
        String newAccountName;
        double newAccountBalance = 0;
        
        //create a static scanner object
        Scanner scanner = new Scanner(System.in);
        
        //asks the user if they want to create an account
        System.out.print("Would you like to create an account(y/n): ");
        
        //if the user says yes, create the account - Yup, I am uing regex
        if(scanner.nextLine().matches("(yes)|(Yes)|(y)|(Y)|(yup)|(Yup)|(si)|(Si)|(sure)|(Sure)")){
            System.out.println("\nOK, lets set up your account.\n");
            System.out.print("What is your name:");
            newAccountName = scanner.nextLine();
            System.out.print("How much money do you have: ");
            newAccountBalance = doubleInputValidation();
            System.out.print("Done! Your account number is: ");
            System.out.println(CreditCard.createUser(newAccountBalance, newAccountName));
        }
        //if the user says no, tell them ok
        else{
            System.out.println("Ok, nothing will be created");
        }
       return 1;     
    }
        
    private static int changeAccountNumber(Creditcard CreditCard){
       
        //create temp variable 
        int newAccount = 0;
        
        //ask user for their credentials
        System.out.print("\nWhat account would you like to access: ");
        newAccount = intInputValidation();
        
        //checks to see if a account exists with the given number
        if(CreditCard.isMainAccountNumber(newAccount)){
            return newAccount;
        }
        else{
            System.out.println("Sorry, that isnt an account number...\n");
            newAccount = changeAccountNumber(CreditCard);
        }
        return newAccount;
    }
    
    private static void bankMenu(Creditcard CreditCard, int accountNumber){
        
        //create choice variable
        int choice;
        
        
        //display menu
        System.out.println("\n\nHere are your Current Stats: ");
        System.out.println("============================");
        String userInfo[] = CreditCard.getUserInfo(accountNumber);
        System.out.println("Account Name: " + userInfo[0]);
        System.out.println("Current Balance: $" + userInfo[1]);
        System.out.println("Next Payment: " + userInfo[2]);
        System.out.println("Reward Points: " + userInfo[3]);
        System.out.println("------------------------------");
        System.out.println("Options:");
        System.out.println("1. Charge your Credit Card");
        System.out.println("2. Ask for a cash advance");
        System.out.println("3. Make a payment");
        System.out.println("4. Calculate interest");
        System.out.println("5. Create New Account");
        System.out.println("6. Change Account");
        System.out.println("7. Exit");
        System.out.println("=====================");
        System.out.print("Choice:");
        choice = intInputValidation();
        
        switch(choice){
                
            case 1:
                System.out.print("How much would you like to charge: ");
                try{
                CreditCard.chargeCreditCard(accountNumber, doubleSignPosInputValidation());
                System.out.println("Done, saving and exiting");
                }catch(IllegalArgumentException e){
                    System.out.println(e);
                }
                break;
            
            case 2:
                System.out.print("How much money would you like: ");
                try{
                    System.out.println(CreditCard.getCashAdvance(accountNumber, doubleSignPosInputValidation()));
                System.out.println("Done, saving and exiting");
                }catch(IllegalArgumentException e){
                    System.out.println(e);
                }
                break;
            
            case 3:
                System.out.print("How much would you like to pay:");
                try{
                CreditCard.makePayment(accountNumber, doubleSignPosInputValidation());
                System.out.println("Done, saving and exiting");
                }catch(IllegalArgumentException e){
                    System.out.println(e);
                }
                break;
            
            case 4:
                System.out.print("With your current balance, the interest would be: $");
                System.out.println(CreditCard.calculateInterest(accountNumber));
                System.out.println("Done, saving and exiting");
                break;
            
            case 5:
                accountCreator(CreditCard);
                break;
            
            case 6:
                int newAccount = changeAccountNumber(CreditCard);
                bankMenu(CreditCard, newAccount);
            
            default:
                System.out.println("Exiting"); 
                System.exit(0);
                break;
        }
        
     bankMenu(CreditCard, accountNumber);       
    } 
    
    private static int intInputValidation(){
        Scanner scanner = new Scanner(System.in);

        int num = 0;
        boolean loop = true;

        while (loop) {
            try {
                num = scanner.nextInt();
                loop = false;
            } catch (InputMismatchException e) {
                System.out.print("Invalid integer, please try again: ");
                scanner.next();
            } 
        }
        return num;
    }
    
    private static double doubleInputValidation(){
        Scanner scanner = new Scanner(System.in);

        double num = 0;
        boolean loop = true;

        while (loop) {
            try {
                num = scanner.nextDouble();
                loop = false;
            } catch (InputMismatchException e) {
                System.out.print("Invalid value, please try again: ");
                scanner.next();
            } 
            if(Double.isInfinite(num)){
                loop = true;
                System.out.print("That number is to big, try again: ");   
            }
        }
        return num;
    }
    
    private static double doubleSignPosInputValidation(){
        Scanner scanner = new Scanner(System.in);
        

        double num = 0;
        boolean loop = true;

        while (loop) {
            try {
                num = scanner.nextDouble();
                loop = false;
            } catch (InputMismatchException e) {
                System.out.print("Invalid value, please try again: ");
                scanner.next();
            } 
            if(num < 0){
               loop = true;
               System.out.print("Invalid value, please try again: "); 
            }
            if(Double.isInfinite(num)){
                loop = true;
                System.out.print("That number is to big, try again: ");   
            }
        }
        return num;
    }
}
    
    
    
   

