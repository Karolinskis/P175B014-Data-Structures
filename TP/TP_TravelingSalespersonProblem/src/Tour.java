public class Tour {
    private class Node {
        private Point p; // point value of node
        private Node next; // pointer to next Node
    }

    private Node start; // first Node in Linked List

    /**
     * creates an empty tour
     */
    public Tour() {
        start = new Node();
    }

    /**
     * (for debugging)
     * creates the 4-point tour a->b->c->d->a
     * @param a
     * @param b
     * @param c
     * @param d
     */
    public Tour(Point a, Point b, Point c, Point d) {
        start = new Node();
        Node b1 = new Node();
        Node c1 = new Node();
        Node d1 = new Node();
        start.p = a;
        b1.p = b;
        c1.p = c;
        d1.p = d;
        start.next = b1;
        b1.next = c1;
        c1.next = d1;
        d1.next = start;
    }

    /**
     * returns the number of points in this tour
     * @return
     */
    //
    public int size() {
        if (start.p == null) {
            return 0;
        }
        else {
            int counter = 0;
            Node current = start;
            do {
                current = current.next;
                counter += 1;
            } while (!current.equals(start));
            return counter;
        }
    }

    /**
     * returns the length of this tour
     * @return
     */
    public double length() {
        if (start.p == null) {
            return 0.0;
        }
        else {
            double distance = 0.0;
            Node current = start;
            do {
                distance += current.p.distanceTo(current.next.p);
                current = current.next;
            } while (!current.equals(start));

            return distance;
        }

    }

    /**
     * returns a string representation of this tour
     * @return
     */
    public String toString() {
        if (start.p == null) {
            return "";
        }
        else {
            Node current = start;
            StringBuilder str = new StringBuilder();
            do {
                str.append(current.p.toString() + "\n");
                current = current.next;
            } while (!current.equals(start));
            return str.toString();
        }
    }

    /**
     * draws this tour to standard drawing
     */
    public void draw() {
        if (start.p != null && start.next != null) {
            Node current = start;
            do {
                current.p.drawTo(current.next.p);
                //StdDraw.pause(50); // pause
                StdDraw.show();
                current = current.next;
            } while (!current.equals(start));
        }

    }

    /**
     * inserts p using the nearest neighbor heuristic
     * @param p
     */
    public void insertNearest(Point p) {
        Node insert = new Node();
        insert.p = p;

        if (start.p == null) {
            start = insert;
            insert.next = insert;
        }
        else {
            Node current = start;
            double distance = Double.POSITIVE_INFINITY;
            Node chosen = start;
            do {
                if (insert.p.distanceTo(current.p) < distance) {
                    distance = insert.p.distanceTo(current.p);
                    chosen = current;
                }
                current = current.next;
            } while (!current.equals(start));
            Node temp = chosen.next;
            chosen.next = insert;
            insert.next = temp;
        }
    }

    /**
     * inserts p using the smallest increase heuristic
     * @param p
     */
    public void insertSmallest(Point p) {
        Node insert = new Node();
        insert.p = p;
        if (start.p == null) {
            start = insert;
            insert.next = insert;
        }
        else {
            Node current = start;
            double increase = Double.POSITIVE_INFINITY;
            Node chosen = start;
            do {
                double increment =
                        current.p.distanceTo(insert.p)
                                + insert.p.distanceTo(current.next.p)
                                - current.p.distanceTo(current.next.p);
                if (increment < increase) {
                    increase = increment;
                    chosen = current;
                }
                current = current.next;
            } while (!current.equals(start));
            Node temp = chosen.next;
            chosen.next = insert;
            insert.next = temp;
        }
    }

    // tests this class by calling all constructors and instance methods
    public static void main(String[] args) {
        // define 4 points, corners of a square
        Point a = new Point(1.0, 1.0);
        Point b = new Point(1.0, 4.0);
        Point c = new Point(4.0, 4.0);
        Point d = new Point(4.0, 1.0);

        // create the tour a -> b -> c -> d -> a
        Tour squareTour = new Tour(a, b, c, d);

        // print the size to standard output
        int size = squareTour.size();
        StdOut.println("# of points = " + size);

        // print the tour length to standard output
        double length = squareTour.length();
        StdOut.println("Tour length = " + length);

        // print the tour to standard output
        StdOut.println(squareTour);

        StdDraw.setXscale(0, 6);
        StdDraw.setYscale(0, 6);

        Point e = new Point(5.0, 6.0);
        squareTour.insertNearest(e);
        squareTour.insertSmallest(e);
        squareTour.draw();


    }
}
