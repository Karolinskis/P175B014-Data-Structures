package util;

import java.util.ArrayList;

public class ArrayStack<E> implements Stack<E> {

    public ArrayList<E> arr;

    /**
     * Constructs an empty array
     */
    public ArrayStack(){
        arr = new ArrayList<E>();
    }

    /**
     * Adds element to end of the list
     * @param elem element to add
     */
    public void add(E elem){
        arr.add(elem);
    }

    /**
     * Removes first element from stack and returns it
     * @return first element of stack
     */
    @Override
    public E pop() {
        E tempElem = arr.get(0);
        arr.remove(0);
        return tempElem;
    }

    /**
     * Insert new item to start of stack
     * @param item item to insert
     */
    @Override
    public void push(E item) {
        arr.add(0, item);
    }

    /**
     * Check first element in stack
     * @return first element in stack
     */
    @Override
    public E peak() {
        return arr.get(0);
    }

    /**
     * Checks if the stack is empty
     * @return true if the stack contains no elements
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
