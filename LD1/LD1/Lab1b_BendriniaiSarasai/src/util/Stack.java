package util;

public interface Stack<E> {
    /**
     * Removes first element from stack and returns it
     * @return first element of stack
     */
    E pop();

    /**
     * Insert new item to start of stack
     * @param item item to insert
     */
    void push(E item);

    /**
     * Check first element in stack
     * @return first element in stack
     */
    E peak();

    /**
     * Checks if the stack is empty
     * @return true if the stack is empty, false if it is not
     */
    boolean isEmpty();


}
