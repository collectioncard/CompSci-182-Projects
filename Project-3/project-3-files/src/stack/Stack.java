/* Project #3
 * Stack.java
 * Thomas Wessel 2019
 * Due April 15, 2019
 * 
 * A simple stack that uses Objects instead of a set datatype
 */


package stack;


public class Stack {
    
    //global variables
    private Node top;
    private int numOfNodes = 0;
    
    public boolean isEmpty(){
        return numOfNodes == 0;
    }
    public int getSize(){
        return numOfNodes;
    }
    
    public void popAll(){
     while(!isEmpty()){
         pop();
     }   
    }
    
    public void push(Object newItem){
        
        //check to see if bottom has a value. Also sets top.
        if(top == null){
            //create new node
            top = new Node(newItem);
            //increment the number of nodes
            numOfNodes++;
        }
        else{
            //Create a new node
            Node toStack = new Node(newItem);
            //set toStack.next to current top
            toStack.setNextNode(top);
            //set top to new node
            top = toStack;
            //increment the number of nodes
            numOfNodes++;
            
        }
    }
    
    //this should work
    public Object pop(){
        Object toReturn = top.getcontainedItem();
        top = top.getNextNode();
        //decrement numOfNodes
        numOfNodes --;
        return toReturn;
    }
    
    //This works
    public Object peek(){
        return top.getcontainedItem();
    }
    
    
    
    
}
