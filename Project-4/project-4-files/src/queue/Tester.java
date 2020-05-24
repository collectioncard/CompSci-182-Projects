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
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Queue queueOne = new Queue();
       
       queueOne.enqueue(1);
       queueOne.enqueue(2);
       queueOne.enqueue(3);
       queueOne.enqueue(4);
       queueOne.enqueue(5);
       queueOne.enqueue(6);
       
       
        System.out.println(queueOne.dequeue());
        System.out.println(queueOne.dequeue());
        System.out.println(queueOne.dequeue());
        System.out.println(queueOne.dequeue());
        System.out.println(queueOne.dequeue());
        System.out.println(queueOne.dequeue());
        
        queueOne.enqueue("Lol This is funny");
        System.out.println(queueOne.dequeue());
       
    }
    
}
