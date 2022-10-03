package util;

import java.util.ArrayList;

public class ArrayQueue<E> implements Queue<E> {

    public ArrayList<E> arr;

    /**
     * Constructs an empty array
     */
    public ArrayQueue() {
        arr = new ArrayList<E>();
    }

    /**
     * Adds new element to the end of the queue
     * @param item item to add
     */
    @Override
    public void enqueue(E item) {
        arr.add(item);
    }

    /**
     * Removes the first element from the queue
     * @return returns the removed element from the queue
     */
    @Override
    public E dequeue() {
        E tempElem = arr.get(0);
        arr.remove(0);
        return tempElem;
    }

    /**
     * Checks the first element in the queue
     * @return returns the first element in the queue
     */
    @Override
    public E peak() {
        return arr.get(0);
    }

    /**
     * Checks if the queue is empty
     * @return true if the queue is empty, otherwise false
     */
    @Override
    public boolean isEmpty() {
        return arr.isEmpty();
    }

    /**
     * Dumps all data as a string.
     * ONLY USED FOR TESTING
     * @return all data as a string
     */
    public String DumpAll() {
        return arr.toString();
    }
}
