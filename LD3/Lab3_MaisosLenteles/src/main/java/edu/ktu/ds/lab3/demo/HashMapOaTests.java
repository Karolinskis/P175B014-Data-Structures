package edu.ktu.ds.lab3.demo;

import edu.ktu.ds.lab3.utils.HashMapOa;
import edu.ktu.ds.lab3.utils.Ks;

public class HashMapOaTests {
    private HashMapOa<Integer, String > hashMapOa;

    public HashMapOaTests() {
        hashMapOa = new HashMapOa<>();
    }

    public void executeTests() {
        Ks.oun("Testing HashMap<int, string>");
        initializeHashMap(1, 100);
        printContainsChecks();

        containsValueTests();
        replaceTests();
        printContainsChecks();

        replaceWithOldValueTests();
        printContainsChecks();

        removeTests();
        printContainsChecks();

        initializeHashMap(201, 250);
    }

    private void removeTests() {
        Ks.oun("===================================");
        Ks.oun("Testing remove() from -10 to 110");
        for (int i = -10; i <= 110; i++) {
            Ks.oun("Removed from HashMapOa with key: " + i +
                    "Value: " + hashMapOa.remove(i) +
                    "Size: " + hashMapOa.size());
        }
    }

    private void replaceWithOldValueTests() {
        Ks.oun("===================================");
        Ks.oun("Testing replace() from -10 to 75 where key == value, Replacing with \"OldValueReplace\"");
        for (int i = -10; i <= 75; i++) {
            hashMapOa.replace(i, String.valueOf(i), "OldValueReplaced");
        }
        Ks.oun("Size: " + hashMapOa.size());
    }

    private void replaceTests() {
        Ks.oun("===================================");
        Ks.oun("Testing replace() value replacing all keys from -15 to 15 value to \"replaced\"");
        for (int i = -15; i <= 15; i++) {
            hashMapOa.replace(i, null, "replaced");
        }
        Ks.oun("Size: " + hashMapOa.size());
    }

    private void containsValueTests() {
        Ks.oun("===================================");
        Ks.oun("Testing contains() value from -10 to 110");
        for (int i = -10; i <= 110; i++) {
            Ks.oun("Does HashMapOa containsValue(): " + i +
                    " (" + hashMapOa.containsValue(String.valueOf(i)) + ")");
        }
        Ks.oun("Current size: " + hashMapOa.size());
    }

    private void printContainsChecks() {
        Ks.oun("=============CONTAINS==============");
        Ks.oun("Doing contains() checks key from -10 to 110");
        for (int i = -10; i <= 110; i++) {
            Ks.oun("Does HashMapOa contains() Key: " +
                    " (" + hashMapOa.contains(i) + ") " +
                    "Value: " + hashMapOa.get(i));
        }
        Ks.oun("Size: " + hashMapOa.size());
    }

    private void initializeHashMap(int from, int to) {
        Ks.oun("===================================");
        Ks.oun("Initializing HashMapOa tests, adding values from " + from + " to " + to);
        for (int i = from; i <= to; i++) {
            hashMapOa.put(i, String.valueOf(i));
        }
        Ks.oun("Table Capacity: " + hashMapOa.getTableCapacity());
        Ks.oun("Number occupied: " + hashMapOa.getNumberOfOccupied());
        Ks.oun("Size:" + hashMapOa.size());
    }
}
