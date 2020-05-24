/*
 * CompSci 182 Project 2 - 2019 Thomas Wessel
 *
 * This is a simple node class that gets and sets two variables: containedItem and nextNode
 */
package CompSci182.project2;

public class Node {
    private char containedItem;
    private Node nextNode;
        
    //Constructors  
    public Node(){
        containedItem = Character.MIN_VALUE;
        setNextNode(null);
    }
        
    public Node(char newChar){
        containedItem = newChar;
        nextNode = null;
    }
    
    //Double Arg Constructor
    public Node(char newChar, Node newNext){
        containedItem = newChar;
        nextNode = newNext;
    }
        
    //methods
    public char getcontainedItem(){
        return containedItem;
    }
        
    public Node getNextNode(){
        return nextNode;
    }
        
    public void setContainedItem(char newChar){
        containedItem = newChar;
    }
    
    public void setNextNode(Node newNext){
        nextNode = newNext;
    }
}
