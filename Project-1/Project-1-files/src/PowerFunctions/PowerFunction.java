/* Project #1
 * PowerFunction.java
 * Thomas Wessel 2019
 * Due Feb 29, 2019

 * This program calculates the power of a number using recursion, itereation, and
 * finally the default Math.pow function
 */
package PowerFunctions;


public class PowerFunction {
    
    public static void main(String args[]){
       
        double base = 8, power = 9;
        
        System.out.println("This is the iterative version: " + itPow(base, power)); 
        System.out.println("This is the recursive version: " + recPow(base, power));
        System.out.println("This is the normal version: " + Math.pow(base, power));
        
        
    }
    
   //This is the iterative version 
   private static double itPow(double base, double power){
       //result is set to 1 becuase base ^ 1 should = base
       double result = 1;                 
       for(int i = 0; i < power; i++){
          //Keeps multiplying it by itself until it runs out 
          result *= base; 
       }
       return result;
   }
   
   //This is the recursive version
   private static double recPow(double base, double power){
       if(power == 0.0){
           return 1.0;
       }
       else{
           return base * recPow(base, power - 1); 
       }
   }
    
}
