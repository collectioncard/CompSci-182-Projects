/* Project #3
 * Node.java
 * Thomas Wessel 2019
 * Due April 15, 2019
 * 
 * A simple node class built around Objects instead of a fixed datatype.
 */
package stack;


public class Node {
    private Object containedItem;
    private Node nextNode;
        
    //Constructors  
    public Node(){
        containedItem = null;
        setNextNode(null);
    }
        
    public Node(Object newObj){
        containedItem = newObj;
        nextNode = null;
    }
    
    //Double Arg Constructor
    public Node(Object newObj, Node newNext){
        containedItem = newObj;
        nextNode = newNext;
    }
        
    //methods
    public Object getcontainedItem(){
        return containedItem;
    }
        
    public Node getNextNode(){
        return nextNode;
    }
        
    public void setContainedItem(Object newObj){
        containedItem = newObj;
    }
    
    public void setNextNode(Node newNext){
        nextNode = newNext;
    }
}
