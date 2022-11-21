package edu.ktu.ds.lab3.demo;

import edu.ktu.ds.lab3.utils.*;

import java.util.Locale;

import static edu.ktu.ds.lab3.utils.HashMap.DEFAULT_INITIAL_CAPACITY;
import static edu.ktu.ds.lab3.utils.HashMap.DEFAULT_LOAD_FACTOR;

public class HashMapTests {
    private HashMap<Integer, String> hashMap;

    public HashMapTests() {
        hashMap = new HashMap<>();
    }

    public void executeTests() {
        Ks.oun("Testing HashMap<Integer, String>");
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
            Ks.oun("Removed from HashMap with key: " + i +
                    " Value: " + hashMap.remove(i) +
                    " Sie: " + hashMap.size());
        }
    }

    private void replaceWithOldValueTests() {
        Ks.oun("===================================");
        Ks.oun("Testing replace() from -10 to 75, where key == value, replacing with \"OldValueReplaced\"");
        for (int i = -10; i <= 75; i++) {
            hashMap.replace(i, String.valueOf(i), "OldValueReplaced");
        }
        Ks.oun("Size: " + hashMap.size());
    }

    private void replaceTests() {
        Ks.oun("===================================");
        Ks.oun("Testing replace() replacing all keys from -15 to 15 to \"replace\"");
        for (int i = -15; i <= 15; i++) {
            hashMap.replace(i, null, "replaced");
        }
        Ks.oun("Size: " + hashMap.size());
    }

    private void containsValueTests() {
        Ks.oun("===================================");
        Ks.oun("Testing contains() from -10 to 110");
        for (int i = -10; i <= 100; i++) {
            Ks.oun("Does HashMap containsValue(): " + i +
                    " ( " + hashMap.containsValue(String.valueOf(i)) + ")");
        }
        Ks.oun("Current size: " + hashMap.size());
    }

    private void printContainsChecks() {
        Ks.oun("=============CONTAINS==============");
        Ks.oun("Doing contains() checks from -10 to 110");
        for (int i = -10; i <= 110; i++) {
            Ks.oun("Does HashMap contains(): " + i +
                    " (" + hashMap.contains(i) + ")" +
                    " Value: " + hashMap.get(i));
        }
    }

    private void initializeHashMap(int from, int to) {
        Ks.oun("===================================");
        Ks.oun("Initializing HashMap tests, adding values: from " + from + " to " + to);
        for (int i = from; i <= to; i++) {
            hashMap.put(i, String.valueOf(i));
        }
        Ks.oun("Table capacity: " + hashMap.getTableCapacity());
        Ks.oun("Number occupied: " + hashMap.getNumberOfOccupied());
        Ks.oun("Size: " + hashMap.size());
    }
}
