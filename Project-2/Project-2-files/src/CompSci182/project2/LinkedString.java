/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompSci182.project2;

public class LinkedString { 
   
   //Global Variables
   private Node head;
    
   private int numOfNodes = 0;
 
   //LinkedString constructors
    
   public LinkedString(){
       //Why did I do it like this? Who Knows! The real questions is, why did I write
       //this comment instead of fixing it?
        append('H');
        append('i');
        append('!');
    }
   
   //constructor that takes a char array
   public LinkedString(char[] value){
       for(int i = 0; i < value.length; i++){
            append(value[i]);
       } 
   }
   
   public LinkedString(String original){
       this(original.toCharArray());
   }
    
   //Public Methods
   public char charAt(int index){
       if(head == null){
           return Character.MIN_VALUE;
       }
       else{
           Node current = head;
           for(int i = 0; i < index; i++){
               current = current.getNextNode();
           }
           return current.getcontainedItem();
       }
   }
   
   public boolean isEmpty(){
       return length() == 0;
   }
   
   public int length(){
       return numOfNodes;
   }
   
   public LinkedString substring(int beginIndex, int endIndex){
       char[] substring = new char[endIndex - beginIndex];
       int arrayTracker = 0;
       for(int i = beginIndex; i < endIndex; i++){
           substring[arrayTracker] = charAt(i);
           arrayTracker++;
       }
       return new LinkedString(substring);
   }
   
   public LinkedString concat(LinkedString Str){
       return new LinkedString(toString() + Str.toString());
   }
   
   //I am adding this because I want to be able to print stuff
   @Override
   public String toString(){
       String result = "";
       for(int i = 0; i < length(); i++){
           result += charAt(i);
       }
       return result;
   }
   
   //private methods
   
   private void incNumOfNodes(int amount){
       numOfNodes += amount;
   }
   
   private void append(char newChar) {
 
		Node charToAdd = new Node(newChar);
		Node current = head;

                // Initialize head Node only incase of 1st element
		if (head == null) {
			head = new Node(newChar);
                        incNumOfNodes(1);
		}
                else if (current != null) {
 
			// start at head and find the next null node
			while (current.getNextNode() != null) {
				current = current.getNextNode();
			}
 
			// now add the char to the selected location
			current.setNextNode(charToAdd);
                        incNumOfNodes(1);
		}	
   }
}
