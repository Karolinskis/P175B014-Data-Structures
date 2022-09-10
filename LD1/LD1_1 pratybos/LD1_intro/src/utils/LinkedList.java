package utils;

import java.util.Iterator;

/*
Realizuokite visus interfeiso metodus susietojo sarašo pagrindu.
Nesinaudokite java klase LinkedList<E>, visa logika meginkite parasyti patys.
Jeigu reikia, galima kurti papildomus metodus ir kintamuosius.
*/
public class LinkedList<T> implements List<T> {

    /* Linked list node */
    class Node {
        public T data;
        public Node next;

        // Constructor to create a new node
        // Next is by default initialized as null
        public Node(T d) { data = d; }
    }

    private Node head;

    // Creates an empty linked list
    public LinkedList() {
    }

    @Override
    public void add(T item) {
        // Create new node with given item
        Node new_node = new Node(item);
        new_node.next = null;

        // If the Linked list is empty,
        // make the new node as head
        if (head == null) {
            head = new_node;
        }
        else {
            // Else traverse till last node
            // and insert the new_node there
            Node last = head;
            while (last.next != null) {
                last = last.next;
            }

            // Insert the new_node at last node
            last.next = new_node;
        }
        //throw new UnsupportedOperationException("Metoda reikia realiztuoti");
    }

    @Override
    public T get(int index) {
        int current = 0;
        for (Node node = head; node != null; node = node.next) {
            if (current == index) {
                return node.data;
            }
            current++;
        }

        // Index not found or list is empty
        return null;

        //throw new UnsupportedOperationException("Metoda reikia realiztuoti");
    }

    @Override
    public boolean remove(T item) {
        // Store head node
        Node currNode = head;
        Node prev = null;

        //If head is item
        if (currNode != null && currNode.data == item) {
            head = currNode.next;
            return true;
        }

        //If item is somewhere else
        while (currNode != null && currNode.data != item) {
            prev = currNode;
            currNode = currNode.next;
        }
        if (currNode != null) {
            prev.next = currNode.next;
            return true;
        }

        // Item not found
        return false;

        //throw new UnsupportedOperationException("Metoda reikia realiztuoti");
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node currNode = head;
            Node prev = null;

            @Override
            public boolean hasNext() {
                currNode = currNode.next;
                if (currNode != null) {
                    return true;
                }

                return false;

                //throw new UnsupportedOperationException("Metoda reikia realiztuoti");
            }

            @Override
            public T next() {
                return currNode.data;
                //throw new UnsupportedOperationException("Metoda reikia realiztuoti");
            }
        };
    }
}
