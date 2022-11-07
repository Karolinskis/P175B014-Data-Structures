package edu.ktu.ds.lab2.demo;

import edu.ktu.ds.lab2.utils.*;
import java.util.Random;

public class BstSetTests {
    ParsableSortedSet<CustomInt> bstSet;

    public BstSetTests() {
        bstSet = new ParsableBstSet<>(CustomInt::new);
    }

    public void executeTests() {
        Ks.oun("BST Set tests");
        removeTests();
        addContainsRetainAllTests();
        subSetTests();
    }

    private void subSetTests() {
        Ks.oun("===================");
        Ks.oun("Testing SubSets");
        initializeBstSetForTests();
        Ks.oun("Testing HeadSet (To 4)");
        System.out.println(bstSet.headSet(new CustomInt(4)).toVisualizedString(""));

        Ks.oun("Testing TailSet (From 1)");
        System.out.println(bstSet.tailSet(new CustomInt(1)).toVisualizedString(""));

        Ks.oun("Testing SubSet (From -2 to 4)");
        System.out.println(bstSet.subSet(new CustomInt(-2), new CustomInt(4)).toVisualizedString(""));
    }

    private void addContainsRetainAllTests() {
        Ks.oun("===================");
        Ks.oun("Testing addAll & containsAll for BST");
        initializeBstSetForTests();

        Ks.oun("Testing if BstSet contains This (customSet) set: (Should return False)");
        ParsableSortedSet<CustomInt> customSet = createCustomBstSet(3, 6);
        Ks.oun(customSet.toVisualizedString(""));
        Ks.oun("Does main set contain the set?: " + bstSet.containsAll(customSet));
        Ks.oun("AddAll the customSet");
        bstSet.addAll(customSet);
        showBstSet();

        Ks.oun("Testing if BstSet containsAll customSet (Should return True)");
        Ks.oun("Does main Set contain this set?: " + bstSet.containsAll(customSet));
        showBstSet();

        Ks.oun("Testing retainAll & Iterator with customSet + Adding to custom Set 10 as a non existing element in mainSet");
        customSet.add(new CustomInt(10));
        bstSet.retainAll(customSet);
        showBstSet();
    }

    private ParsableSortedSet<CustomInt> createCustomBstSet(int size){
        Random rng = new Random();
        ParsableSortedSet<CustomInt> returnSet = new ParsableBstSet<>(CustomInt::new);
        for (Integer i = 0; i < size; i++) {
            Integer val = rng.nextInt(100);
            returnSet.add(new CustomInt(val));
            returnSet.add(new CustomInt(-val));
        }
        return returnSet;
    }

    private ParsableSortedSet<CustomInt> createCustomBstSet(int from, int to){
        ParsableSortedSet<CustomInt> returnSet = new ParsableBstSet<>(CustomInt::new);
        for (Integer i = from; i <= to; i++) {
            returnSet.add(new CustomInt(i));
        }
        return returnSet;
    }

    private void removeTests() {
        Ks.oun("===================");
        Ks.oun("Testing Remove & RemoveRecursive for BST");
        initializeBstSetForTests();

        Ks.oun("Removed: 3");
        bstSet.remove(new CustomInt(3));
        showBstSet();

        Ks.oun("Removed: 4");
        bstSet.remove(new CustomInt(4));
        showBstSet();

        Ks.oun("Removed: 2");
        bstSet.remove(new CustomInt(2));
        showBstSet();

        Ks.oun("Removed; 2 (Again)");
        bstSet.remove(new CustomInt(2));
        showBstSet();
    }

    private void initializeBstSetForTests() {
        createBstSet();
        Ks.oun("Initial tree example used for testing (-4 -4 +2 Morrored RNG Numbers):");
        showBstSet();
    }

    public void showBstSet() {

        System.out.println(bstSet.toVisualizedString(""));
    }

    public void createBstSet() {
        bstSet = createCustomBstSet(2);
        bstSet.addAll(createCustomBstSet(-4, -2));
        bstSet.addAll(createCustomBstSet(3, 3));
        bstSet.addAll(createCustomBstSet(-2, 0));
        bstSet.addAll(createCustomBstSet(2, 4));
    }
}
