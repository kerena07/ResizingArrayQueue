/* Matric: 211276

Name: Kerena Natalie

Course code/Group: CSC3104(G7)

Lab 8
*/
package LAB8;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.Iterator;

public class ResizingArrayQueueTest {
    @Test
    public void testQueueOperations() {
        ResizingArrayQueue<Integer> queue = new ResizingArrayQueue<>();

        // Test enqueue() and peek()
        //add one element to the queue
        queue.enqueue(1);
      //peek is the value most in front
        System.out.println("Adding element 1 to the queue. Peek: " + queue.peek());
       
        //add one other  element to the queue
        queue.enqueue(3);
        //peek is the value most in front
        System.out.println("Adding element 3 to the queue. Peek: " + queue.peek());
        assertEquals(1, (int) queue.peek());
       System.out.println("\nSize of queue now:"+queue.size());
        // Test dequeue() and size()
        //delete one element to the queue
        assertEquals(1, (int) queue.dequeue());
        System.out.println("Removing element 1  from the queue. Size of queue: " + queue.size());
        assertEquals(1, queue.size());
        assertEquals(3, (int) queue.dequeue());
        System.out.println("Removing element 3  from the queue. Size of queue: " + queue.size());
        assertTrue(queue.isEmpty());

        // Test iterator()
        queue.enqueue(10);
        queue.enqueue(8);
        System.out.println("\nAdded 2 elements to the queue");
        Iterator<Integer> it = queue.iterator();
        assertTrue(it.hasNext());
        assertEquals(10, (int) it.next());
        assertTrue(it.hasNext());
        assertEquals(8, (int) it.next());
        assertFalse(it.hasNext());
        System.out.println("\nIterating through the queue");
        
        // Test resizing
        for (int i = 0; i < 16; i++) {
            queue.enqueue(i);
        }
        System.out.println("Adding 16 elements to the existing queue. \nSize of queue: " + queue.size());
        assertEquals(18, queue.size());
       
        for (int i = 0; i < 8; i++) {
            queue.dequeue();
        }
        System.out.println("\nRemoving 8 elements from the existing queue. \nSize: " + queue.size());
        assertEquals(10, queue.size());
  
    }
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ResizingArrayQueueTest.class);
        System.out.println("Tests run: " + result.getRunCount()+" Success");
        for (Failure failure : result.getFailures()) {
            System.out.println("Test failed: " + failure.getTestHeader());
            System.out.println("Failure message: " + failure.getMessage());
            System.out.println("Stack trace: " + failure.getTrace());
        }
    }
}