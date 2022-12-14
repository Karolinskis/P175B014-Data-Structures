package edu.ktu.ds.lab2.utils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;

/**
 * Rikiuojamos objektų kolekcijos - aibės realizacija dvejetainiu paieškos
 * medžiu.
 *
 * @param <E> Aibės elemento tipas. Turi tenkinti interfeisą Comparable<E>, arba
 *            per klasės konstruktorių turi būti paduodamas Comparator<E> interfeisą
 *            tenkinantis objektas.
 * @author darius.matulis@ktu.lt
 * @užduotis Peržiūrėkite ir išsiaiškinkite pateiktus metodus.
 */
public class BstSet<E extends Comparable<E>> implements SortedSet<E>, Cloneable {

    // Medžio šaknies mazgas
    protected BstNode<E> root = null;
    // Medžio dydis
    protected int size = 0;
    // Rodyklė į komparatorių
    protected Comparator<? super E> c;

    /**
     * Sukuriamas aibės objektas DP-medžio raktams naudojant Comparable<E>
     */
    public BstSet() {
        this.c = Comparator.naturalOrder();
    }

    /**
     * Sukuriamas aibės objektas DP-medžio raktams naudojant Comparator<E>
     *
     * @param c Komparatorius
     */
    public BstSet(Comparator<? super E> c) {
        this.c = c;
    }

    /**
     * Patikrinama ar aibė tuščia.
     *
     * @return Grąžinama true, jei aibė tuščia.
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * @return Grąžinamas aibėje esančių elementų kiekis.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Išvaloma aibė.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Patikrinama ar aibėje egzistuoja elementas.
     *
     * @param element - Aibės elementas.
     * @return Grąžinama true, jei aibėje egzistuoja elementas.
     */
    @Override
    public boolean contains(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element is null in contains(E element)");
        }

        return get(element) != null;
    }

    /**
     * Returns the maximum key element starting from node
     * @param node node to start from
     * @return The maximum element key from given node
     */
    BstNode<E> getMax(BstNode<E> node) {
        return get(node, true);
    }

    /**
     * Returns the minimum key element starting from node
     * @param node node to start from
     * @return the maximum element key from given node
     */
    BstNode<E> getMin(BstNode<E> node) {
        return get(node, false);
    }

    private BstNode<E> get(BstNode<E> node, boolean findMax) {
        BstNode<E> parent = null;
        while (node != null) {
            parent = node;
            node = (findMax) ? node.right : node.left;
        }

        return parent;
    }



    /**
     * Patikrinama ar visi abės set elementai egzistuoja aibėje
     *
     * @param set aibė
     * @return
     */
    @Override
    public boolean containsAll(Set<E> set) {
        // TODO: Studentams reikia realizuoti containsAll(Set<E> set)

        Object[] array = set.toArray();
        for (Object object : array) {
            if (!contains((E)object)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Aibė papildoma nauju elementu.
     *
     * @param element - Aibės elementas.
     */
    @Override
    public void add(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element is null in add(E element)");
        }

        root = addRecursive(element, root);
    }

    /**
     * Abės set elementai pridedami į esamą aibę, jeigu abi aibės turi tą patį elementą, jis nėra dedamas.
     *
     * @param set pridedamoji aibė
     */
    @Override
    public void addAll(Set<E> set) {
        // TODO : Studentams reikia realizuoti addAll(Set<E> set)

        for (E element: set) {
            this.add(element);
        }
    }

    private BstNode<E> addRecursive(E element, BstNode<E> node) {
        if (node == null) {
            size++;
            return new BstNode<>(element);
        }

        int cmp = c.compare(element, node.element);

        if (cmp < 0) {
            node.left = addRecursive(element, node.left);
        } else if (cmp > 0) {
            node.right = addRecursive(element, node.right);
        }

        return node;
    }

    /**
     * Pašalinamas elementas iš aibės.
     *
     * @param element - Aibės elementas.
     */
    @Override
    public void remove(E element) {
        root = removeRecursive(element, root);
        // TODO : Studentams reikia realizuoti remove(E element)

        if (element == null) {
            throw new IllegalArgumentException("Element is null in remove(E element)");
        }

        root = removeRecursive(element, root);
    }

    /**
     * Aibėje lieka tik tie elementai, kurie yra aibėje set.
     *
     * @param set aibė
     */
    @Override
    public void retainAll(Set<E> set) {
        // TODO : Studentams reikia realizuoti retainAll(Set<E> set)
        for (E element : this) {
            if (!set.contains(element)) {
                remove(element);
            }
        }
    }

    private BstNode<E> removeRecursive(E element, BstNode<E> node) {
        // TODO : Studentams reikia realizuoti removeRecursive(E element, BstNode<E> n)

        // perform standard bst remove
        if (node == null) {
            return node;
        }

        // Traversal
        int compare = c.compare(element, node.element);
        if (compare < 0) {
            node.left = removeRecursive(element, node.left);
        }
        else if (compare > 0) {
            node.right = removeRecursive(element, node.right);
        }
        else {  // Removing node
            size--;
            // No branch
            if (node.left == null && node.right == null) {
                node = null;
            }
            // Branch on right
            else if (node.left == null) {
                node = node.right;
            }
            // Branch on the left
            else if (node.right == null) {
                node = node.left;
            }
            else { // Both branches
                BstNode<E> exchangeValueNode = getMostLeftNode(node.right);
                node.element = exchangeValueNode.element;
                node.right = removeRecursive((E)exchangeValueNode.element, node.right);
            }
        }

        return node;
    }

    protected BstNode<E> getMostLeftNode(BstNode<E> node) {
        BstNode<E> current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private E get(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element is null in get(E element)");
        }

        BstNode<E> node = root;
        while (node != null) {
            int cmp = c.compare(element, node.element);

            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node.element;
            }
        }

        return null;
    }

    /**
     * Grąžinamas aibės elementų masyvas.
     *
     * @return Grąžinamas aibės elementų masyvas.
     */
    @Override
    public Object[] toArray() {
        int i = 0;
        Object[] array = new Object[size];
        for (Object o : this) {
            array[i++] = o;
        }
        return array;
    }

    /**
     * Aibės elementų išvedimas į String eilutę Inorder (Vidine) tvarka. Aibės
     * elementai išvedami surikiuoti didėjimo tvarka pagal raktą.
     *
     * @return elementų eilutė
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E element : this) {
            sb.append(element.toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * Medžio vaizdavimas simboliais, žiūr.: unicode.org/charts/PDF/U2500.pdf
     * Tai 4 galimi terminaliniai simboliai medžio šakos gale
     */
    private static final String[] term = {"\u2500", "\u2534", "\u252C", "\u253C"};
    private static final String rightEdge = "\u250C";
    private static final String leftEdge = "\u2514";
    private static final String endEdge = "\u25CF";
    private static final String vertical = "\u2502  ";
    private String horizontal;

    /* Papildomas metodas, išvedantis aibės elementus į vieną String eilutę.
     * String eilutė formuojama atliekant elementų postūmį nuo krašto,
     * priklausomai nuo elemento lygio medyje. Galima panaudoti spausdinimui į
     * ekraną ar failą tyrinėjant medžio algoritmų veikimą.
     *
     * @author E. Karčiauskas
     */
    @Override
    public String toVisualizedString(String dataCodeDelimiter) {
        horizontal = term[0] + term[0];
        return root == null ? ">" + horizontal
                : toTreeDraw(root, ">", "", dataCodeDelimiter);
    }

    private String toTreeDraw(BstNode<E> node, String edge, String indent, String dataCodeDelimiter) {
        if (node == null) {
            return "";
        }
        String step = (edge.equals(leftEdge)) ? vertical : "   ";
        StringBuilder sb = new StringBuilder();
        sb.append(toTreeDraw(node.right, rightEdge, indent + step, dataCodeDelimiter));
        int t = (node.right != null) ? 1 : 0;
        t = (node.left != null) ? t + 2 : t;
        sb.append(indent).append(edge).append(horizontal).append(term[t]).append(endEdge).append(
                split(node.element.toString(), dataCodeDelimiter)).append(System.lineSeparator());
        step = (edge.equals(rightEdge)) ? vertical : "   ";
        sb.append(toTreeDraw(node.left, leftEdge, indent + step, dataCodeDelimiter));
        return sb.toString();
    }

    private String split(String s, String dataCodeDelimiter) {
        int k = s.indexOf(dataCodeDelimiter);
        if (k <= 0) {
            return s;
        }
        return s.substring(0, k);
    }

    /**
     * Sukuria ir grąžina aibės kopiją.
     *
     * @return Aibės kopija.
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        BstSet<E> cl = (BstSet<E>) super.clone();
        if (root == null) {
            return cl;
        }
        cl.root = cloneRecursive(root);
        cl.size = this.size;
        return cl;
    }

    private BstNode<E> cloneRecursive(BstNode<E> node) {
        if (node == null) {
            return null;
        }

        BstNode<E> clone = new BstNode<>(node.element);
        clone.left = cloneRecursive(node.left);
        clone.right = cloneRecursive(node.right);
        return clone;
    }

    /**
     * Grąžinamas aibės poaibis iki elemento.
     *
     * @param element - Aibės elementas.
     * @return Grąžinamas aibės poaibis iki elemento.
     */
    @Override
    public Set<E> headSet(E element) {
        // TODO : Studentams reikia realizuoti headSet()

        if (element == null) {
            throw new IllegalArgumentException("Element is null in get(E element)");
        }

        BstSet<E> headSet = new BstSet<>();
        headSetRecursive(element, root, headSet);
        return headSet;
    }
    private void headSetRecursive(E element, BstNode<E> node, BstSet<E> outputSet) {
        if (node == null) {
            return;
        }

        int compare = c.compare(node.element, element);
        if (compare <= 0) {
            outputSet.add(node.element);
        }
        headSetRecursive(element, node.left, outputSet);
        if (compare < 0) {
            headSetRecursive(element, node.right, outputSet);
        }
    }

    private BstSet<E> headset = null;

    private boolean setRecursive(E element, BstNode<E> node) {
        if (node == null) {
            return false;
        }
        if (node.element.equals(element)) {
            headset.root = new BstNode<>(node.element);
            return true;
        }
        if (setRecursive(element, node.left)) {
            BstNode<E> temp = new BstNode<>(node.element);
            temp.left = headset.root;
            headset.root = temp;
            return true;
        }
        if (setRecursive(element,node.right)) {
            BstNode<E> temp = new BstNode<>(node.element);
            temp.right = headset.root;
            headset.root = temp;
            return true;
        }
        return false;
    }

    private BstNode<E> find(E element) {
        IteratorBst iterator = (IteratorBst) iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(element)) {
                return iterator.last;
            }
        }
        return null;
    }

    /**
     * Grąžinamas aibės poaibis nuo elemento element1 iki element2.
     *
     * @param element1 - pradinis aibės poaibio elementas.
     * @param element2 - galinis aibės poaibio elementas.
     * @return Grąžinamas aibės poaibis nuo elemento element1 iki element2.
     */
    @Override
    public Set<E> subSet(E element1, E element2) {
        // TODO : Studentams reikia realizuoti subSet()
        if (element1 == null || element2 == null) {
            throw new IllegalArgumentException("Element is null");
        }
        BstSet<E> subSet = new BstSet<>();
        subSetRecursive(element1, element2, root, subSet);
        return subSet;
    }

    private void subSetRecursive(E lowerValue, E higherValue, BstNode<E> node, BstSet<E> outputSet) {
        if (node == null) {
            return;
        }

        int compareHigher = c.compare(node.element, higherValue);
        int compareLower = c.compare(node.element, lowerValue);

        if (compareHigher <= 0 && compareLower >= 0) {
            outputSet.add(node.element);
        }

        if (compareLower > 0) {
            subSetRecursive(lowerValue, higherValue, node.left, outputSet);
        }
        if (compareHigher < 0) {
            subSetRecursive(lowerValue, higherValue, node.right, outputSet);
        }
    }

    /**
     * Grąžinamas aibės poaibis nuo elemento.
     *
     * @param element - Aibės elementas.
     * @return Grąžinamas aibės poaibis nuo elemento.
     */
    @Override
    public Set<E> tailSet(E element) {
        // TODO : Studentams reikia realizuoti tailSet()
        if (element == null) {
            throw new IllegalArgumentException("Element is null in get(E element)");
        }
        BstSet<E> tailSet = new BstSet<>();
        tailSetRecursive(element, root, tailSet);
        return tailSet;
    }

    private void tailSetRecursive(E element, BstNode<E> node, BstSet<E> outputSet) {
        if (node == null) {
            return;
        }

        int compare = c.compare(node.element, element);
        if (compare >= 0) {
            outputSet.add(node.element);
        }
        if (compare > 0) {
            tailSetRecursive(element, node.left, outputSet);
        }
        tailSetRecursive(element, node.right, outputSet);
    }

    /**
     * Grąžinamas tiesioginis iteratorius.
     *
     * @return Grąžinamas tiesioginis iteratorius.
     */
    @Override
    public Iterator<E> iterator() {
        return new IteratorBst(true);
    }

    /**
     * Grąžinamas atvirkštinis iteratorius.
     *
     * @return Grąžinamas atvirkštinis iteratorius.
     */
    @Override
    public Iterator<E> descendingIterator() {
        return new IteratorBst(false);
    }

    /**
     * Vidinė objektų kolekcijos iteratoriaus klasė. Iteratoriai: didėjantis ir
     * mažėjantis. Kolekcija iteruojama kiekvieną elementą aplankant vieną kartą
     * vidine (angl. inorder) tvarka. Visi aplankyti elementai saugomi steke.
     * Stekas panaudotas iš java.util paketo, bet galima susikurti nuosavą.
     */
    private class IteratorBst implements Iterator<E> {

        private final Stack<BstNode<E>> stack = new Stack<>();
        // Nurodo iteravimo kolekcija kryptį, true - didėjimo tvarka, false - mažėjimo
        private final boolean ascending;
        // Reikalinga remove() metodui.
        private BstNode<E> lastInStack;
        private BstNode<E> last;

        IteratorBst(boolean ascendingOrder) {
            this.ascending = ascendingOrder;
            this.toStack(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.empty();
        }

        @Override
        public E next() {// Jei stekas tuščias
            if (stack.empty()) {
                lastInStack = root;
                last = null;
                return null;
            } else {
                // Grąžinamas paskutinis į steką patalpintas elementas
                BstNode<E> n = stack.pop();
                // Atsimenamas paskutinis grąžintas elementas, o taip pat paskutinis steke esantis elementas.
                // Reikia remove() metodui
                lastInStack = stack.isEmpty() ? root : stack.peek();
                last = n;
                BstNode<E> node = (ascending) ? n.right : n.left;
                // Dešiniajame n pomedyje ieškoma minimalaus elemento,
                // o visi paieškos kelyje esantys elementai talpinami į steką
                toStack(node);
                return n.element;
            }
        }

        @Override
        public void remove() {
            // TODO : Studentams reikia realizuoti remove()
            stack.pop();

        }

        private void toStack(BstNode<E> n) {
            while (n != null) {
                stack.push(n);
                n = (ascending) ? n.left : n.right;
            }
        }
    }

    /**
     * Vidinė kolekcijos mazgo klasė
     *
     * @param <N> mazgo elemento duomenų tipas
     */
    protected static class BstNode<N> {

        // Elementas
        protected N element;
        // Rodyklė į kairįjį pomedį
        protected BstNode<N> left;
        // Rodyklė į dešinįjį pomedį
        protected BstNode<N> right;

        protected BstNode() {
        }

        protected BstNode(N element) {
            this.element = element;
            this.left = null;
            this.right = null;
        }
    }
}
