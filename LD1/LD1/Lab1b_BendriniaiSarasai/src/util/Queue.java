package util;

public interface Queue<E> {
    /**
     * Adds new element to the end of the queue
     * @param item item to add
     */
    void enqueue(E item);

    /**
     * Removes the first element from the queue
     * @return returns the removed element from the queue
     */
    E dequeue();

    /**
     * Checks the first element in the queue
     * @return returns the first element in the queue
     */
    E peak();

    /**
     * Checks if the queue is empty
     * @return true if the queue is empty, otherwise false
     */
    boolean isEmpty();

}
