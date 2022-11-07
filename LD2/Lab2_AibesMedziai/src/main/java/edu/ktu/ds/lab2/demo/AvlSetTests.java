package edu.ktu.ds.lab2.demo;

import edu.ktu.ds.lab2.utils.*;
import java.util.Random;

public class AvlSetTests {
    ParsableSortedSet<CustomInt> avlSet;

    public AvlSetTests() {
        avlSet = new ParsableAvlSet<>(CustomInt::new);
    }

    public void executeTests() {
        Ks.oun("AVL tests");
        removeTests();
    }

    private ParsableSortedSet<CustomInt> createCustomAvlSet(int size) {
        Random rng = new Random();
        ParsableSortedSet<CustomInt> returnSet = new ParsableAvlSet<>(CustomInt::new);
        for (Integer i = 0; i < size; i++) {
            Integer value = rng.nextInt(100);
            returnSet.add(new CustomInt(value));
            returnSet.add(new CustomInt(-value));
        }
        return returnSet;
    }

    private ParsableSortedSet<CustomInt> createCustomAvlSet(int from, int to) {
        ParsableSortedSet<CustomInt> returnSet = new ParsableAvlSet<>(CustomInt::new);
        for (Integer i = from; i <= to; i++) {
            returnSet.add(new CustomInt(i));
        }
        return returnSet;
    }

    private void removeTests() {
        Ks.oun("================");
        Ks.oun("Testing Remove & RemoveRecursive FOR BST AND AVL");
        initializeAvlSetForTests();

        Ks.oun("Removed: -2 (2 Branches)");
        avlSet.remove(new CustomInt(-2));
        showAvlSet();

        Ks.oun("Removed: 0 (1 Branch)");
        avlSet.remove(new CustomInt(0));
        showAvlSet();

        Ks.oun("Removed: 2 (0 Branches)");
        avlSet.remove(new CustomInt(2));
        showAvlSet();
    }

    private void initializeAvlSetForTests() {
        CreateAvlSetForTests();
        Ks.oun("Initial tree used for testing:");
        showAvlSet();
    }

    public void showAvlSet() {
        System.out.println(avlSet.toVisualizedString(""));
    }

    public void CreateAvlSetForTests() {
        avlSet = createCustomAvlSet(2);
        avlSet.addAll(createCustomAvlSet(-4, -2));
        avlSet.addAll(createCustomAvlSet(3, 3));
        avlSet.addAll(createCustomAvlSet(-2, 0));
        avlSet.addAll(createCustomAvlSet(-2, 0));
        avlSet.addAll(createCustomAvlSet(2, 4));
    }
}
