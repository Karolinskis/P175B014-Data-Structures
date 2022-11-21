/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab3.utils;


import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Darius
 */
public class HashMapOa<K, V> implements EvaluableMap<K, V> {

    public enum OpenAddressingType {

        LINEAR,
        QUADRATIC,
        DOUBLE_HASHING
    }

    public static final int DEFAULT_INITIAL_CAPACITY = 8;
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;
    public static final HashManager.HashType DEFAULT_HASH_TYPE = HashManager.HashType.DIVISION;
    public static final OpenAddressingType DEFAULT_OPEN_ADDRESSING_TYPE = OpenAddressingType.LINEAR;

    // Maišos lentelė
    protected Entry<K, V>[] table;
    // Lentelėje esančių raktas-reikšmė porų kiekis
    protected int size = 0;
    // Apkrovimo faktorius
    protected float loadFactor;
    // Maišos metodas
    protected HashManager.HashType ht;
    //--------------------------------------------------------------------------
    //  Maišos lentelės įvertinimo parametrai
    //--------------------------------------------------------------------------
    // Permaišymų kiekis
    protected int rehashesCounter = 0;
    // Paskutinio papildyto masyvo elemento indeksas maišos lentelėje
    protected int lastUpdated = 0;
    // Lentelėje užimtų elementų skaičius
    protected int numberOfOccupied = 0;

    private final Entry<K, V> DELETED = new Entry<>();
    private final OpenAddressingType oaType;

    public HashMapOa() {
        this(DEFAULT_HASH_TYPE);
    }

    public HashMapOa(HashManager.HashType ht) {
        this(DEFAULT_INITIAL_CAPACITY, ht);
    }

    public HashMapOa(int initialCapacity, HashManager.HashType ht) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR, ht, DEFAULT_OPEN_ADDRESSING_TYPE);
    }

    public HashMapOa(float loadFactor, HashManager.HashType ht) {
        this(DEFAULT_INITIAL_CAPACITY, loadFactor, ht, DEFAULT_OPEN_ADDRESSING_TYPE);
    }

    public HashMapOa(int initialCapacity, float loadFactor, HashManager.HashType ht, OpenAddressingType oaType) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }

        if ((loadFactor <= 0.0) || (loadFactor > 1.0)) {
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        }

        this.table = new Entry[initialCapacity];
        this.loadFactor = loadFactor;
        this.ht = ht;
        this.oaType = oaType;
    }

    /**
     * Patikrinama ar atvaizdis yra tuščias.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Grąžinamas atvaizdyje esančių porų kiekis.
     *
     * @return Grąžinamas atvaizdyje esančių porų kiekis.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Išvalomas atvaizdis.
     */
    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
        lastUpdated = 0;
        rehashesCounter = 0;
        numberOfOccupied = 0;
    }

    @Override
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null in contains(K key)");
        }

        return get(key) != null;
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value is null in put(K key, V value)");
        }
        int position = findPosition(key, true);
        if (position == -1) {
            rehash();
            return put(key, value);
        }

        if (table[position] == null || DELETED.equals(table[position])) {
            table[position] = new Entry(key, value);
            size++;

            if (size > table.length * loadFactor) {
                rehash();
            } else {
                numberOfOccupied++;
                lastUpdated = position;
            }
        } else {
            table[position].value = value;
            lastUpdated = position;
        }

        return value;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null in get(K key)");
        }

        int position = findPosition(key, false);
        if (position != -1 && table[position] != null && table[position] != DELETED) {
            return table[position].value;
        }

        return null;
    }

    // TODO: Studentams reikia realizuoti remove(K key)
    @Override
    public V remove(K key) {
        int position = findPosition(key, false);
        V removedValue = null;
        if (position != -1 && table[position] != null && table[position] != DELETED) {
            removedValue = table[position].value;
            lastUpdated = position;
            numberOfOccupied--;
            table[position] = DELETED;
            size--;
        }
        return removedValue;
    }

    @Override
    public String toString() {
        return Arrays.stream(table)
                .filter(entry -> entry != null && !DELETED.equals(entry))
                .map(Entry::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private void rehash() {
        HashMapOa<K, V> newMap = new HashMapOa<>(table.length * 2, loadFactor, ht, oaType);
        Arrays.stream(table).filter(Objects::nonNull).forEach(kvEntry -> newMap.put(kvEntry.key, kvEntry.value));
        table = newMap.table;
        numberOfOccupied = newMap.numberOfOccupied;
        lastUpdated = newMap.lastUpdated;
        rehashesCounter++;
    }

    private int findPosition(K key, boolean stopAtDeleted) {
        int index = HashManager.hash(key.hashCode(), table.length, ht);

        int position = index;
        for (int i = 0; i < table.length; i++) {
            if (table[position] == null) {
                return position;
            }
            if (DELETED.equals(table[position]) && stopAtDeleted) {
                return position;
            }
            if (!DELETED.equals(table[position]) && table[position].key.equals(key)) {
                return position;
            }

            position = calculatePosition(index, i, key);
        }
        return -1;
    }

    private int calculatePosition(int index, int i, K key) {
        switch (oaType) {
            case LINEAR:
                return (index + i + 1) % table.length;
            case QUADRATIC:
                return (index + (i + 1) * (i + 1)) % table.length;
            case DOUBLE_HASHING:
                return (index + i * (7 - Math.abs(key.hashCode()) % 7)) % table.length;
        }
        return index;
    }

    /**
     * Grąžina formuojant maišos lentelę įvykusių permaišymų kiekį.
     *
     * @return Permaišymų kiekis.
     */
    @Override
    public int getRehashesCounter() {
        return rehashesCounter;
    }

    /**
     * Grąžina maišos lentelės talpą.
     *
     * @return Maišos lentelės talpa.
     */
    @Override
    public int getTableCapacity() {
        return table.length;
    }

    /**
     * Grąžina paskutinio papildyto maišos lentelės masyvo elemento indeksą.
     *
     * @return Paskutinio papildyto maišos lentelės masyvo elemento indeksą.
     */
    @Override
    public int getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Grąžina užimtų maišos lentelės masyvo elementų kiekį.
     *
     * @return Užimtų maišos lentelės masyvo elementų kiekis
     */
    @Override
    public int getNumberOfOccupied() {
        return numberOfOccupied;
    }

    // TODO: Studentams reikia realizuoti replace(K key,  V oldValue, V newValue)
    public boolean replace(K key, V oldValue, V newValue) {
        int position = findPosition(key, false);
        Entry<K, V> entry = getEntry(key, position);
        if (entry != null && (oldValue == null || oldValue.equals(entry.value))) {
            entry.value = newValue;
            lastUpdated = position;
            return true;
        }
        return false;
    }

    public Entry<K, V> getEntry(K key, int position) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null in get(K key)");
        }

        if (position != -1 && table[position] != null && table[position] != DELETED) {
            return table[position];
        }

        return null;
    }

    // TODO: Studentams reikia realizuoti containsValue(Object value)
    public boolean containsValue(Object value) {
        for (int entryIndex = 0; entryIndex < getTableCapacity(); entryIndex++) {
            if (table[entryIndex] != null && table[entryIndex].value.equals(value)) {
                return true;
            }
        }
        return false;
    }

    protected static class Entry<K, V> {

        // Raktas
        protected K key;
        // Reikšmė
        protected V value;

        protected Entry() {
        }

        protected Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}
