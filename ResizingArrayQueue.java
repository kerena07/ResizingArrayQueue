/* Matric: 211276

Name: Kerena Natalie

Course code/Group: CSC3104(G7)

Lab 8
*/
package LAB8;

/**************************
 *  Compilation:  javac ResizingArrayQueue.java
 *  Execution:    java ResizingArrayQueue < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/13stacks/tobe.txt
 *
 *  Queue implementation with a resizing array.
 *
 *  % java ResizingArrayQueue < tobe.txt
 *  to be or not to be (2 left on queue)
 *
 **************************/

 import java.util.Iterator;
 import java.util.NoSuchElementException;
 
 public class ResizingArrayQueue<Item> implements Iterable<Item> {
     // initial capacity of underlying resizing array
     private static final int INIT_CAPACITY = 8;
 
     private Item[] q;       // queue elements
     private int n;          // number of elements on queue
     private int first;      // index of first element of queue
     private int last;       // index of next available slot
 
 
     /**
      * Initializes an empty queue.
      */
     public ResizingArrayQueue() {
         q = (Item[]) new Object[INIT_CAPACITY];
         n = 0;
         first = 0;
         last = 0;
     }
 
     /**
      * Is this queue empty?
      * @return true if this queue is empty; false otherwise
      */
     public boolean isEmpty() {
         return n == 0;
     }
 
     /**
      * Returns the number of items in this queue.
      * @return the number of items in this queue
      */
     public int size() {
         return n;
     }
 
     // resize the underlying array
     private void resize(int capacity) {
         assert capacity >= n;
         Item[] copy = (Item[]) new Object[capacity];
         for (int i = 0; i < n; i++) {
             copy[i] = q[(first + i) % q.length];
         }
         q = copy;
         first = 0;
         last  = n;
     }
     public int capacity() {
        return q.length;
    }
 
     /**
      * Adds the item to this queue.
      * @param item the item to add
      */
     public void enqueue(Item item) {
         // double size of array if necessary and re copy to front of array
         if (n == q.length) resize(2*q.length);   // double size of array if necessary
         q[last++] = item;                        // add item
         if (last == q.length) last = 0;          // wrap-around
         n++;
     }
 
     /**
      * Removes and returns the item on this queue that was least recently added.
      * @return the item on this queue that was least recently added
      * @throws java.util.NoSuchElementException if this queue is empty
      */
     public Item dequeue() {
         if (isEmpty()) throw new NoSuchElementException("Queue underflow");
         Item item = q[first];
         q[first] = null;                            // to avoid loitering
         n--;
         first++;
         if (first == q.length) first = 0;           // wrap-around
         // shrink size of array if necessary
         if (n > 0 && n == q.length/4) resize(q.length/2);
         return item;
     }
 
     /**
      * Returns the item least recently added to this queue.
      * @return the item least recently added to this queue
      * @throws java.util.NoSuchElementException if this queue is empty
      */
     public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return q[first];
     }
 
 
     /**
      * Returns an iterator that iterates over the items in this queue in FIFO order.
      * @return an iterator that iterates over the items in this queue in FIFO order
      */
     public Iterator<Item> iterator() {
         return new ArrayIterator();
     }
 
     // an iterator, doesn't implement remove() since it's optional
     private class ArrayIterator implements Iterator<Item> {
         private int i = 0;
         public boolean hasNext()  { return i < n;                               }
         public void remove()      { throw new UnsupportedOperationException();  }
 
         public Item next() {
             if (!hasNext()) throw new NoSuchElementException();
             Item item = q[(i + first) % q.length];
             i++;
             return item;
         }
     }
    }
 
   