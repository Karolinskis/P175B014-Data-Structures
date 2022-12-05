import java.io.Console;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class TSPVisualizer {

    public static void main(String[] args) {

        int xscale = 512;
        int yscale = 512 - 70;

        StdDraw.setXscale(0, 512);
        StdDraw.setYscale(-70, 512 - 70);

        if (args.length > 0) {
            StdOut.println("Given arg:"+args[0]);
        }


        StdDraw.textLeft(50, 400, "No directory detected.");
        StdDraw.textLeft(80, 380, "To manually draw click on the screen to add points");

        StdDraw.enableDoubleBuffering();

        Tour nearest = new Tour();
        Tour smallest = new Tour();

        boolean animate = args.length == 0 ? false : true;

        ArrayList<Point> points = new ArrayList<Point>();
        List<String> contents = new ArrayList<String>(); // All files read

        boolean redraw = false;

        boolean showingNearest = true;
        boolean showingSmallest = true;

        boolean mouseWasUp = true;
        boolean mouseCorrect = true;

        // initialize the two data structures with point from file
        if (args.length > 0) {
            StdDraw.clear(); // clear screen

            // Creating a File object for directory
            File directoryPath = new File(args[0]);
            // List of all files and directories
            Collections.addAll(contents, directoryPath.list());

            StdDraw.clear(); // clear screen
            StdDraw.textLeft(50, 400, "Select file to read");
            for (int i = 0; i < contents.size(); i++) {
                StdDraw.textLeft(80, (380-i*20), ("- "+ Integer.toString(i) + " " + contents.get(i)));
            }
            StdDraw.show();
            StdDraw.enableDoubleBuffering();
        } else {
            // Print dimensions
            StdOut.println(xscale + " " + yscale);
        }


        // MAIN EVENT LOOP
        // ------------------------------------------------------------------
        while (true) {

            // Reading from files
            if (args.length > 0) {
                if (StdDraw.hasNextKeyTyped()) {

                    char key = StdDraw.nextKeyTyped();

                    int keyIndex = Integer.parseInt(String.valueOf(key));
                    if (contents.size() >= keyIndex) {
                        In in = new In(args[0] + "/" + contents.get(keyIndex));
                        StdOut.println("READING: " + args[0] + "/" + contents.get(keyIndex));
                        xscale = in.readInt();
                        yscale = in.readInt();

                        StdDraw.setXscale(0, xscale);
                        StdDraw.setYscale(-70, yscale);

                        // Print dimensions
                        StdOut.println(xscale + " " + yscale);

                        while (!in.isEmpty()) {
                            double x = in.readDouble();
                            double y = in.readDouble();

                            // Print line with new points coordinates
                            StdOut.println(x + " " + y);
                            Point p = new Point(x, y);

                            points.add(p);

                            nearest.insertNearest(p);
                            smallest.insertSmallest(p);

                            StdDraw.show();
                        }

                        redraw = true;
                    }
                }
            }
            // Hand draw
            else {
                if (StdDraw.hasNextKeyTyped()) {

                    char key = StdDraw.nextKeyTyped();

                    // check keyboard events
                    if (key == 'n') showingNearest = !showingNearest;
                    if (key == 's') showingSmallest = !showingSmallest;
                    if (key == 'm') mouseCorrect = !mouseCorrect;
                    if (key == 'q') break;

                    redraw = true;
                }

                // on mouse click: add new point to tours
                if (StdDraw.isMousePressed() && (!mouseCorrect || mouseWasUp)) {
                    mouseWasUp = false;

                    // the location (x, y) of the mouse
                    double x = StdDraw.mouseX();
                    double y = StdDraw.mouseY();

                    Point p = new Point(x, y);

                    points.add(p);

                    // insert points in the tours
                    nearest.insertNearest(p);
                    smallest.insertSmallest(p);

                    // Print line with new points coordinates
                    StdOut.println(x + " " + y);

                    // indicate that the frame needs to be redrawn
                    redraw = true;
                }
                else
                    mouseWasUp = !StdDraw.isMousePressed();
            }

            // when the frame needs to be refreshed
            if (redraw) {
                redraw = false;

                StdDraw.clear();

                // draw all of the points
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.setPenRadius(0.010);
                for (Point p : points) {
                    p.draw();
                }

                StdDraw.show();
                if (animate)
                    StdDraw.pause(2000);

                // draw in red the nearest neighbor
                if (showingNearest) {
                    StdDraw.setPenRadius(0.004);
                    StdDraw.setPenColor(StdDraw.RED);
                    nearest.draw();
                    StdDraw.show();
                    if (animate)
                        StdDraw.pause(100);
                }
                StdDraw.show();


                // draw in blue the smallest
                if (showingSmallest) {
                    StdDraw.setPenRadius(0.003);
                    StdDraw.setPenColor(StdDraw.BLUE);
                    smallest.draw();
                    StdDraw.show();
                    if (animate)
                        StdDraw.pause(50);
                }



                // print captions
                StdDraw.textLeft(10, -10, "num points: " + points.size());
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.textLeft(10, -35, "nearest: " + nearest.length());
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.textLeft(10, -60, "smallest: " + smallest.length());
                StdDraw.setPenColor(StdDraw.BLACK);

                StdDraw.show();
                //StdDraw.pause(5);
            }
        }
        System.exit(0);
    }
}
