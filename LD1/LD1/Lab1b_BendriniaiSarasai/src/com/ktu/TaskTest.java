package com.ktu;

import util.ArrayStack;
import util.LinkedListQueue;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskTest {
    public static void main(String[] args) {
        OPTTask1Tests();

        OPTTask2Tests();

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
