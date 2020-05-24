/* Project #1
 * Creditcard.java
 * Thomas Wessel 2019
 * Due Feb 29, 2019
 * 
 * This program defines a Creditcard class that other things can make an object out of
 * This class contains many methods to modify the data in different ways.
 *
 * As far as I know, this code is very safe. It will check for overflows, prevent 
 * accounts from being overwritten and will throw exceptions if it finds anything it
 * doesnt really like.
 */
package ADT;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;

public class Creditcard {
    
//Data storage
    
    //Map that holds Credit Data
    private final Map<Integer, String> AccountDataBase = new HashMap<>();
    
    //Vector that contains list of accountNumbers
    private final ArrayList<Integer> accountNumbers = new ArrayList<>();
    
//Constructors
    
    //This is run every time the object is created
    public Creditcard(double balance, String name){
        createUserConst(balance, name);
    }
    
    //This is not run unless someone doesnt call the Creditcard right
    public Creditcard(){
        createUserConst(0.00, "default");
    }
    
//Main get and set methods (Only way for client to change variables)
    
    //forNewUsers
    public int createUser(double balance, String accountName){
        
        //Create Account Number
        int newAccountNumber = (int)(Math.random() * 1000000);
        
        //check to see if this account number already exists
        newAccountNumber = moveUntilEmpty(newAccountNumber);
        
        //Set Bank Balance
        setBalance(newAccountNumber, doubleToString(balance));  //accountNumber + 2
        
        //Set Account Name
        setAccountName(newAccountNumber, accountName);  //accountNumber + 1
        
        //Set Account Due Date
        setNewDueDate(newAccountNumber, 0, 0, 0);  //acountNumber + 3
        
        //Set Reward Points
        setNewRewardPoint(newAccountNumber,"None for you"); //accountNumber + 4
        
        //push newAccountNumber 
        accountNumbers.add(newAccountNumber);
        
        //return the new account number
        return newAccountNumber;
    }
    
    //for new users created by the constructor
    private void createUserConst(double balance, String accountName){
        
        //Create Account Number
        int newAccountNumber = 123456;
        
        //check to see if this account number already exists
        newAccountNumber = moveUntilEmpty(newAccountNumber);
        
        //Set Bank Balance
        setBalance(newAccountNumber, doubleToString(balance));  //accountNumber + 2
        
        //Set Account Name
        setAccountName(newAccountNumber, accountName);  //accountNumber + 1
        
        //Set Account Due Date
        setNewDueDate(newAccountNumber, 0, 0, 0);  //acountNumber + 3
        
        //Set Reward Points
        setNewRewardPoint(newAccountNumber,"None for you"); //accountNumber + 4
        
        //push newAccountNumber 
        accountNumbers.add(newAccountNumber);
    }
    
    //For Returning Users
    public String[] getUserInfo(int AccountNumber){
        
        
        
        String userInfo[] = new String[5];
        
        //Sets userInfo[0] to accountName
        userInfo[0] = getName(AccountNumber);
        
        //Sets userInfo[1] to Balance 
        userInfo[1] = getBalance(AccountNumber);
        
        //Sets userInfo[2] to newDueDate
        userInfo[2] = getDueDate(AccountNumber);
        
        //Sets userInfo[3] to rewardPoints
        userInfo[3] = getRewardsPoints(AccountNumber);
        
        
        return userInfo;
    }
 
//Options:
    
    //Charge Credit Card - throws exception if less than zero
    public void chargeCreditCard(int AccountNumber, double charge){
        
        if(charge < 0){
            throw new IllegalArgumentException("Charge can not be less than zero");
        }
        if(checkDoubleSubOverflow(stringToDouble(getBalance(AccountNumber)), charge)){
            throw new IllegalArgumentException("This value would overflow the number");
        }
        double oldBalance = stringToDouble(getBalance(AccountNumber));
        double newBalance =  oldBalance - charge;
        setBalance(AccountNumber, doubleToString(newBalance)); 
    }
   
    //Ask for cash advance - throws exception if less than zero
    public String getCashAdvance(int AccountNumber, double advance){
        
        String result = null;
        Random rand = new Random();
        
        if (rand.nextInt(10) >= 5) {
        
            if(advance < 0){
                throw new IllegalArgumentException("Charge can not be less than zero");
            }
            if(checkDoubleAddOverflow(stringToDouble(getBalance(AccountNumber)), advance)){
                throw new IllegalArgumentException("This number would cause an overflow");
            }
            double oldBalance = stringToDouble(getBalance(AccountNumber));
            double newBalance = oldBalance + advance;
            setBalance(AccountNumber, doubleToString(newBalance));
            result = "Ok, the money is in your account!";
        }
        else if(rand.nextInt(10) >= 5){
          result = "The bank laughs at your credit";
        }
        else if(rand.nextInt(10) >= 5){
           result = "Who do I look like? Elon Musk?";
        }
        else if(rand.nextInt(10) >= 5){
           result = "LOL, why would I want to give you money?";
        }
        else{
           result = "Ha, you got so unlucky, I had to put this in an else statement..." ;
        }
        
        return result;
    }
    
    //Make a payment - calls getCashAdvance because they do the same thing
    public void makePayment(int AccountNumber, double payment) {
        getCashAdvance(AccountNumber, payment); 
    }
    
    //calculate interest
    public double calculateInterest(int AccountNumber){
        double oldBalance = stringToDouble(getBalance(AccountNumber));
        
        if (oldBalance > 0){
            return 0;
        }
        else{
        return oldBalance * -.1518;
        }
    }
    
//Validation
    
     //checks to make sure adding to a (integer) number will not cause an overflow (not used anymore)
    private boolean checkIntAddOverflow(int currentNum, int toAdd){
        boolean isOverflow = true;
        long r = (long)currentNum + toAdd;
        if (r >>> 32 == 0) {    
            isOverflow = false;
        }
        return isOverflow;
    }
    
    //checks to make sure subtracting from a (double) number will not cause an overflow
    private boolean checkDoubleSubOverflow(double currentNum, double toSubtract){
        boolean isOverflow = true;
        
        if(Double.isFinite(currentNum - toSubtract)){
            isOverflow = false;
        }
        return isOverflow;
    }
    
    //checks to make sure adding to a (double) number will not cause an overflow
    private boolean checkDoubleAddOverflow(double currentNum, double toAdd){
        boolean isOverflow = true;
        
        if(Double.isFinite(currentNum + toAdd)){
            isOverflow = false;
        }
        return isOverflow;
    }
    
    //Allows the client to check if an account exists
    public boolean isMainAccountNumber(int AccountNumber){
        
        boolean inList = false;
        
        for(int i = 0; i < accountNumbers.size(); i++){
            if (AccountNumber == accountNumbers.get(i)){
                inList = true;
            }
        }
        return inList;
    }
    
    //check to see if an account exists by checking the value if balance in the map; it should not be null if there is a user
    private int hasValue(int AccountNumber){
        if(getName(AccountNumber) != null){
            return 1;
        }
        else{
            return 0;
        }
    }
    
    //prevent override of other user (I know this wouldnt be needed if I generated
    //account numbers better or used a better storage system...)
    private int moveUntilEmpty(int AccountNumber){
        
        int newAccountNumber = AccountNumber;
        
        //This checks to see if the account number was used before and will change it if needed
        while(hasValue(newAccountNumber) == 1){
            newAccountNumber ++; 
        }
        
        //now I check to make sure that there isnt an account within a few numbers of the new account number (Just to be safe it has a large buffer)
        int numbersExist = 0;
        for(int i = newAccountNumber; i < newAccountNumber + 5; i++){
            if(hasValue(i) == 1){
               numbersExist++;
            }  
        }
        
        //If the previous for loop detects an account within five slots (the four data points + 1 buffer)
        //The account number will be pushed past the numbers and the function is recursivly called
        if(numbersExist != 0){
           newAccountNumber = moveUntilEmpty(newAccountNumber + numbersExist);
        }
        
        return newAccountNumber;
    }

//functions to set map data
    private void setAccountName(int accountNumber, String accountName){
       AccountDataBase.put(accountNumber, accountName); 
        
    }
    
    private void setBalance(int AccountNumber, String newBalance){
        AccountDataBase.put(AccountNumber + 1, newBalance);
        
    }
    
    private void setNewDueDate(int AccountNumber, int mo, int day, int year){
        String newDate = intToString(mo) + "/" + intToString(day) + "/" +intToString(year);
        AccountDataBase.put(AccountNumber + 2, newDate);
    }
    
    private void setNewRewardPoint(int AccountNumber, String newRewardPoint){
        AccountDataBase.put(AccountNumber + 3, newRewardPoint);
        
    }
      
//functions to return map data
    private String getBalance(int AccountNumber){
       return AccountDataBase.get(AccountNumber + 1);
    }
    
    private String getName(int AccountNumber){
       return AccountDataBase.get(AccountNumber);
    }
    
    //Every time the user tries to access the due date, the date changes
    private String getDueDate(int AccountNumber){
        Random rand = new Random();
        int newMonth = rand.nextInt(11 + 1);
        int newDay = rand.nextInt(27 + 1);
        int newYear = rand.nextInt(1000) + 2000;
        
        setNewDueDate(AccountNumber, newMonth, newDay, newYear);
        return AccountDataBase.get(AccountNumber + 2);
    }
    
    //Every time the user tries to access their rewards, there is a 1/2 chance that points will increase by Math.Rand() % 10;
    private String getRewardsPoints(int AccountNumber){
        
        //creates Random object (a better version of Math.rand())
        Random rand = new Random();
        
        //gives a 50/50 change and then sets rewards to a random number
        if (rand.nextInt(10) >= 5) {
                setNewRewardPoint(AccountNumber, doubleToString(rand.nextInt(999999)));
            }
        
        return AccountDataBase.get(AccountNumber + 3);
    } 
    
//Utilities (because I dont want to fix stuff)
    
    //String to INT - Yes, I know it is useless...
    private int stringToInt(String toConvert){
        return Integer.valueOf(toConvert); 
    }
    
    //String to Double
    private double stringToDouble(String toConvert){
        return Double.valueOf(toConvert);
    }
    
    //Double to String
    private String doubleToString(double toConvert){
        return String.valueOf(toConvert);
    }
    
    //Int to String
    private String intToString(int toConvert){
        return String.valueOf(toConvert);
    }
}




 