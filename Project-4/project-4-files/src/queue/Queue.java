/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queue;

/**
 *
 * @author thomas
 */
public class Queue {
    
    //Global variables
    Node front = null;
    Node back = null;
    int size = 0;
    
    public void enqueue(Object toAdd){
        
        //check to see if a queue already exists
        if(front == null){
            //create new Node
            Node newNode = new Node(toAdd);
            //set newNode to first and back
            front = newNode;
            back = newNode;
            size++;
        }else{
            //create new Node
            Node newNode = new Node(toAdd);
            //add newNode to the end of the list
            back.setNextNode(newNode); 
            //set back to the next node
            back = back.getNextNode();
            System.out.println("This was run");
            size++;
        }  
    }
    
    public Object dequeue(){
        //get value of front
        Object toReturn = front.getcontainedItem();
        //set front to the node after it
        front = front.getNextNode();
        //return toReturn
        return toReturn;
    }
    
    //isEmpty
    public boolean isEmpty(){
        return size == 0;
    }
    
    //dequeueAll
    public void dequeueAll(){
        front = null;
        back = null;
    }
    
    //peek
    public Object peek(){
        return front.getcontainedItem();
    }
}
