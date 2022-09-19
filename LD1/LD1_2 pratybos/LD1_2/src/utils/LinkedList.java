package utils;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {

    /* Linked list node */
    class Node {
        public T data;
        public Node next;

        public Node(T data) { this.data = data; }
    }

    private Node head;

    /**
     * Creates an empty linked list
     */
    public LinkedList() { }


    /**
     * Add item to end
     * @param item item to add
     */
    public void add(T item) {
        // Create new node with given item
        Node new_node = new Node(item);
        new_node.next = null;

        // If the linked list is empty,
        // make new node as head
        if (head == null) {
            head = new_node;
        } else {
            // Traverse till last node
            // and insert new_node there
            Node last = head;
            while (last.next != null) {
                last = last.next;
            }

            // Insert the new_node at last node
            last.next = new_node;
        }
    }

    /**
     * Get element by its place in list
     * @param index index of element
     * @return element selected by its index
     */
    public T get(int index) {
        int current = 0;
        for (Node node = head; node != null; node = node.next) {
            if (current == index) {
                return node.data;
            }
            current++;
        }

        return null; // Index not found or list is empty
    }

    /**
     * Removes given item from the list
     * @param item item to remove
     * @return Returns true if the item was removed, else returns false
     */
    public boolean remove(T item) {
        // Store head node
        Node currNode = head;
        Node prevNode = null;

        // If head is item
        if (currNode != null && currNode.data == item) {
            head = currNode.next;
            return true;
        }

        // If item was somewhere else
        while (currNode != null && currNode.data != item) {
            prevNode = currNode;
            currNode = currNode.next;
        }
        if (currNode != null) {
            prevNode.next = currNode.next;
            return true;
        }

        // Item not found
        return false;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {

            Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T data = current.data;
                    current = current.next;
                    return data;
                }

                return null;
            }
        };
    }

}
