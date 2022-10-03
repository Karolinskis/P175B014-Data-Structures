package util;

import java.util.LinkedList;

public class LinkedListStack<E> implements Stack<E>{
    public LinkedList<E> list;

    /**
     * Construct an empty list
     */
    public LinkedListStack() {
        list = new LinkedList<>();
    }

    /**
     * Adds element to the end of the list
     * @param elem element to add
     */
    public void add(E elem) {
        list.add(elem);
    }

    /**
     * Removes element at given index
     * @param index index of element to remove
     */
    public void remove(int index) {
        list.remove(index);
    }

    /**
     * Removes first element from stack and returns it
     * @return first element of stack
     */
    @Override
    public E pop() {
        E tempElem = list.get(0);
        list.remove(0);
        return tempElem;
    }

    /**
     * Insert new item to start of stack
     * @param item item to insert
     */
    @Override
    public void push(E item) {
        list.add(0, item);
    }

    /**
     * Check first element in stack
     * @return first element in stack
     */
    @Override
    public E peak() {
        return list.get(0);
    }

    /**
     * Checks if the stack is empty
     * @return true if the stack contains no elements
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Dumps all data as a string.
     * ONLY USED FOR TESTING
     * @return all data as a string
     */
    public String DumpAll() {
        return list.toString();
    }
}
