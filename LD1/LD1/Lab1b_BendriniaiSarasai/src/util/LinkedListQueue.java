package util;

import java.util.LinkedList;

public class LinkedListQueue<E> implements Queue<E> {

    LinkedList<E> list;

    /**
     * Constructs a new empty queue
     */
    public LinkedListQueue() {
        list = new LinkedList<E>();
    }

    /**
     * Adds new element to the end of the queue
      * @param item item to add
     */
    @Override
    public void enqueue(E item) {
        list.add(item);
    }

    /**
     * Removes the first element from the queue
     * @return returns the removed element from the queue
     */
    @Override
    public E dequeue() {
        E tempElem = list.get(0);
        list.remove(0);
        return tempElem;
    }

    /**
     * Checks the first element in the queue
     * @return returns the first element in the queue
     */
    @Override
    public E peak() {
        return list.get(0);
    }

    /**
     * Checks if the queue is empty
     * @return true if the queue is empty, otherwise false
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
