package com.ktu;

import util.*;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskTest {
    public static void main(String[] args) {
        new TaskTest().execute();

    }

    void execute() {
        // Main task
        System.out.println("------ Task 1 ------");
        ArrayQueueTests();
        ArrayStackTests();

        LinkedListQueueTests();
        LinkedListStackTests();
        // Optional tasks
        OPTTask1Tests();
        OPTTask2Tests();
    }

    private void LinkedListStackTests() {
        LinkedListStack<Integer> testListS = new LinkedListStack<>();

        System.out.println("  -- ArrayStack --");
        // Initial array
        System.out.println("Initial stack");
        Ks.oun(testListS.DumpAll());

        // Adding element to end of stack | add()
        System.out.println("Adding elements to end of array | add()");
        testListS.add(1);
        Ks.oun(testListS.DumpAll());
        testListS.add(2);
        Ks.oun(testListS.DumpAll());
        testListS.add(3);
        Ks.oun(testListS.DumpAll());

        // Remove first element from stack and return it | pop()
        System.out.println("Removing first element from stack and return it | pop()");
        Ks.oun(testListS.DumpAll());
        Ks.oun("Result: " + testListS.pop().toString());
        Ks.oun(testListS.DumpAll());

        // Insert new item to start of stack | push(E item)
        System.out.println("Inserts new item to start of stack | push(E item)");
        Ks.oun(testListS.DumpAll());
        testListS.push(0);
        Ks.oun(testListS.DumpAll());

        // Checks the first element in stack | peak()
        System.out.println("Checks the first element in stack | peak()");
        Ks.oun(testListS.DumpAll());
        Ks.oun("Result: " + testListS.peak().toString());
        Ks.oun(testListS.DumpAll());

        // Checks if the stack is empty | isEmpty()
        System.out.println("Checks if the stack is empty | isEmpty()");
        Ks.oun(testListS.DumpAll());
        Ks.oun("Result: " + testListS.isEmpty());
        testListS.pop(); testListS.pop(); testListS.pop();   // Remove all elements
        Ks.oun(testListS.DumpAll());
        Ks.oun("Result: " + testListS.isEmpty());
    }

    private void LinkedListQueueTests() {
        LinkedListQueue<Integer> testListsQ = new LinkedListQueue<>();

        System.out.println("  -- LinkedListQueue --");
        // Initial array
        System.out.println("Initial queue");
        Ks.oun(testListsQ.DumpAll());

        // Adding new elements
        System.out.println("Adding new elements | enqueue()");
        testListsQ.enqueue(2);
        Ks.oun(testListsQ.DumpAll());
        testListsQ.enqueue(4);
        Ks.oun(testListsQ.DumpAll());

        // Check the first element
        System.out.println("Checking the first element | peak()");
        Ks.oun("Current array: " + testListsQ.DumpAll());
        Ks.oun("peak() output: " + testListsQ.peak());

        // Removing elements
        System.out.println("Removing elements | dequeue()");
        testListsQ.dequeue();
        Ks.oun(testListsQ.DumpAll());
        testListsQ.dequeue();
        Ks.oun(testListsQ.DumpAll());

        // Checking if the queue is empty
        System.out.println("Checking if the queue is empty | isEmpty()");
        Ks.oun("   Current array: " + testListsQ.DumpAll());
        Ks.oun("isEmpty() output: " + testListsQ.isEmpty());
        testListsQ.enqueue(2);
        Ks.oun("   Current array: " + testListsQ.DumpAll());
        Ks.oun("isEmpty() output: " + testListsQ.isEmpty());
    }

    public static void ArrayQueueTests() {
        ArrayQueue<Integer> testArrayQ = new ArrayQueue<>();

        System.out.println("  -- ArrayQueue --");
        // Initial array
        System.out.println("Initial queue");
        Ks.oun(testArrayQ.DumpAll());

        // Adding new elements
        System.out.println("Adding new elements | enqueue()");
        testArrayQ.enqueue(2);
        Ks.oun(testArrayQ.DumpAll());
        testArrayQ.enqueue(4);
        Ks.oun(testArrayQ.DumpAll());

        // Check the first element
        System.out.println("Checking the first element | peak()");
        Ks.oun("Current array: " + testArrayQ.DumpAll());
        Ks.oun("peak() output: " + testArrayQ.peak());

        // Removing elements
        System.out.println("Removing elements | dequeue()");
        testArrayQ.dequeue();
        Ks.oun(testArrayQ.DumpAll());
        testArrayQ.dequeue();
        Ks.oun(testArrayQ.DumpAll());

        // Checking if the queue is empty
        System.out.println("Checking if the queue is empty | isEmpty()");
        Ks.oun("   Current array: " + testArrayQ.DumpAll());
        Ks.oun("isEmpty() output: " + testArrayQ.isEmpty());
        testArrayQ.enqueue(2);
        Ks.oun("   Current array: " + testArrayQ.DumpAll());
        Ks.oun("isEmpty() output: " + testArrayQ.isEmpty());
    }

    public static void ArrayStackTests() {
        ArrayStack<Integer> testArrayS = new ArrayStack<>();

        System.out.println("  -- ArrayStack --");
        // Initial array
        System.out.println("Initial stack");
        Ks.oun(testArrayS.DumpAll());

        // Adding element to end of stack | add()
        System.out.println("Adding elements to end of array | add()");
        testArrayS.add(1);
        Ks.oun(testArrayS.DumpAll());
        testArrayS.add(2);
        Ks.oun(testArrayS.DumpAll());
        testArrayS.add(3);
        Ks.oun(testArrayS.DumpAll());

        // Remove first element from stack and return it | pop()
        System.out.println("Removing first element from stack and return it | pop()");
        Ks.oun(testArrayS.DumpAll());
        Ks.oun("Result: " + testArrayS.pop().toString());
        Ks.oun(testArrayS.DumpAll());

        // Insert new item to start of stack | push(E item)
        System.out.println("Inserts new item to start of stack | push(E item)");
        Ks.oun(testArrayS.DumpAll());
        testArrayS.push(0);
        Ks.oun(testArrayS.DumpAll());

        // Checks the first element in stack | peak()
        System.out.println("Checks the first element in stack | peak()");
        Ks.oun(testArrayS.DumpAll());
        Ks.oun("Result: " + testArrayS.peak().toString());
        Ks.oun(testArrayS.DumpAll());

        // Checks if the stack is empty | isEmpty()
        System.out.println("Checks if the stack is empty | isEmpty()");
        Ks.oun(testArrayS.DumpAll());
        Ks.oun("Result: " + testArrayS.isEmpty());
        testArrayS.pop(); testArrayS.pop(); testArrayS.pop();   // Remove all elements
        Ks.oun(testArrayS.DumpAll());
        Ks.oun("Result: " + testArrayS.isEmpty());

    }





    public static void OPTTask1Tests() {
        String[] tests = {
                "}",
                "{()}{[]}",
                "[{}}",
                "{()[{}]}{}",
                "{(})",
                "([(]{)})"
        };

        boolean[] expected = {
                false,
                true,
                false,
                true,
                false,
                false
        };

        System.out.println("------ Optional Task 1 ------");
        String format = "%1$12s | %2$10s | %3$10s%n";
        System.out.printf(format, "Parenthesis", "Output", "Expected");

        for (int i = 0; i < tests.length; i++) {
            boolean isCorrect = isParenthesesBalanced(tests[i]);
            System.out.printf(format, tests[i], isCorrect, expected[i]);
        }

    }

    // Task1
    /***
     * Checks if the given parentheses are balanced
     * @param chars parentheses to check
     * @return true if balanced
     */
    static boolean isParenthesesBalanced (String chars) {
        boolean ans = true;

        ArrayStack<Character> stack = new ArrayStack<>();

        for (int i = 0; i < chars.length(); i++) {
            if (chars.charAt(i) == '(' || chars.charAt(i) == '{' || chars.charAt(i) == '[') {
                stack.push(chars.charAt(i));
            } else {
                if (!stack.isEmpty()) {
                    char temp = stack.peak();

                    stack.pop();

                    if (chars.charAt(i) == ')' && temp != '(') {
                        ans = false;
                        break;
                    }
                    if (chars.charAt(i) == ']' && temp != '[') {
                        ans = false;
                        break;
                    }
                    if (chars.charAt(i) == '}' && temp != '{') {
                        ans = false;
                        break;
                    }
                }

                // Stack is empty or not balanced
                else {
                    ans = false;
                    break;
                }

            }
        }

        // If stack is not empty after traversal
        // then parentheses are not balanced
        if (!stack.isEmpty()) {
            ans = false;
        }

        return ans;
    }

    public static void OPTTask2Tests() {
        int[][] testsArray = {
                { 2, 7, 3, 1, 5, 2, 6, 2 },
                { 2, 7, 3, 1, 5, 2, 6, 2 },
                { 2, 7, 3, 1, 5, 2, 6, 2 }
        };

        int[] testsSize = { 4, 1, 8};

        int[][] expected = {
                { 7, 7, 5, 6, 6},
                {2, 7, 3, 1, 5, 2, 6, 2},
                {7}
        };

        System.out.println("------ Optional Task 2 ------");
        String format = "%1$25s | %2$5s | %3$25s | %4$25s%n";
        System.out.printf(format, "Array", "Size", "Output", "Expected");

        for (int i = 0; i < testsArray.length; i++) {
            ArrayList<Integer> maximum = SlidingWindowMaximum(testsSize[i], testsArray[i]);
            String testArray = Arrays.toString(testsArray[i]);
            String expectStr = Arrays.toString(expected[i]);
            System.out.printf(format, testArray, testsSize[i], maximum, expectStr);
        }

    }

    /***
     * Finds maximum element of all sub-arrays of size K.
     * Double ended queue based method
     * @param K size of sub-array
     * @param array array of all elements
     * @return returns the maximum of each and every contiguous sub-array of size K
     */
    static ArrayList<Integer> SlidingWindowMaximum(int K, int[] array) {
        int n = array.length;
        ArrayList<Integer> finished = new ArrayList<>();
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();

        // Process first window (or size)
        int i;
        for (i = 0; i < K; i++) {
            while ( !queue.isEmpty() && array[i] > array[queue.peakLast()] ) {
                // remove from rear
                queue.dequeueRear();
            }
            // Add new element at rear
            queue.enqueue(i);
        }

        // Process rest of windows
        for (; i < n; i++) {
            // The element at the front
            // is the largest from previous window
            finished.add(array[queue.peak()]);

            // Remove elements out of window
            while ( !queue.isEmpty() && queue.peak() <= i - K ) {
                queue.dequeue();
            }

            // Remove elements smaller
            // than the currently added
            while ( !queue.isEmpty() && array[i] >= array[queue.peakLast()] ) {
                queue.dequeueRear();
            }

            // Add current element to end of queue
            queue.enqueue(i);
        }

        // Add maximum element of last window
        finished.add(array[queue.peak()]);

        return finished;
    }



}
