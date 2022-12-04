public class TSPTimer {

    public static void main(String[] args) {
        double lo = 0.0;
        double hi = 600.0;
        int n = Integer.parseInt(args[0]);

        // generate data and run nearest insertion heuristic
        Stopwatch timer1 = new Stopwatch();
        Tour tour1 = new Tour();
        for (int i = 0; i < n; i++) {
            double x = StdRandom.uniformDouble(lo, hi);
            double y = StdRandom.uniformDouble(lo, hi);
            Point p = new Point(x, y);
            tour1.insertNearest(p);
        }
        double length1 = tour1.length();
        double elapsed1 = timer1.elapsedTime();
        StdOut.println("Tour length = " + length1);
        StdOut.println("Nearest insertion:  " + elapsed1 + " seconds");
        StdOut.println();


        // generate data and run smallest insertion heuristic
        Stopwatch timer2 = new Stopwatch();
        Tour tour2 = new Tour();
        for (int i = 0; i < n; i++) {
            double x = StdRandom.uniformDouble(lo, hi);
            double y = StdRandom.uniformDouble(lo, hi);
            Point p = new Point(x, y);
            tour2.insertSmallest(p);
        }
        double length2 = tour2.length();
        double elapsed2 = timer2.elapsedTime();
        StdOut.println("Tour length = " + length2);
        StdOut.println("Smallest insertion:  " + elapsed2 + " seconds");
    }

}
