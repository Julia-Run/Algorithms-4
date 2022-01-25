/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Exercise_25 {

    public static class Point implements Comparable<Point> {

        private final double x;
        private final double y;
        private static Comparator<Point> xorder = new Xorder();
        private static Comparator<Point> yorder = new Yorder();
        private static Comparator<Point> rorder = new Rorder();

        public Point(double x, double y) {

            if (Double.isInfinite(x) || Double.isInfinite(y))
                throw new IllegalArgumentException("Coordinates CANNOT be Infinite!");
            if (Double.isNaN(x) || Double.isNaN(y))
                throw new IllegalArgumentException("Coordinates CANNOT be NaN!");
            if (x == 0.0) this.x = 0.0;  // convert -0.0 to +0.0
            else this.x = x;
            if (y == 0.0) this.y = 0.0;
            else this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getR() {
            return Math.sqrt(x * x + y * y);
        }

        public double theta() {
            return Math.atan2(y, x);
        }

        public double theta2point(Point p) {
            double deltaY = this.y - p.y;
            double deltaX = this.x - p.x;
            return Math.atan2(deltaY, deltaX);
        }

        public double distance2point(Point p) {
            return this.getR() - p.getR();
        }

        public void show() {
            StdDraw.point(x, y);
        }

        public int compareTo(Point that) {
            if (this.y < that.y) return -1;
            if (this.y > that.y) return 1;
            if (this.x < that.x) return -1;
            if (this.x > that.x) return 1;
            return 0;
        }


        /* static comparator class*/


        private static class Xorder implements Comparator<Point> {
            public int compare(Point p1, Point p2) {
                return Double.compare(p1.x, p2.x);
            }
        }

        private static class Yorder implements Comparator<Point> {
            public int compare(Point p1, Point p2) {
                return Double.compare(p1.y, p2.y);
            }
        }

        private static class Rorder implements Comparator<Point> {
            public int compare(Point p1, Point p2) {
                return Double.compare(p1.getR(), p2.getR());
            }
        }

        /* nonstatic comparator */
        public Comparator<Point> orderDistance2point() {
            return new OrderDistance2point();
        }

        public class OrderDistance2point implements Comparator<Point> {
            public int compare(Point p1, Point p2) {
                double d1 = Math.sqrt((p1.x - x) * (p1.x - x) + (p1.y - y) * (p1.y - y));
                double d2 = Math.sqrt((p2.x - x) * (p2.x - x) + (p2.y - y) * (p2.y - y));
                return Double.compare(d1, d2);
            }
        }

        Comparator<Point> orderTheta2point = new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                double dy1 = p1.y - y;
                double dx1 = p1.x - x;
                double dy2 = p2.y - y;
                double dx2 = p2.x - x;
                /* 上下：(0,180),(180,360) */
                if (dy1 >= 0 && dy2 < 0) return -1;
                else if (dy1 < 0 && dy2 >= 0) return 1;
                    /* collinear*/
                else if (dy1 == 0 && dy2 == 0) {
                    if (dx1 >= 0 && dx2 < 0) return -1;
                    else if (dx1 <= 0 && dx2 > 0) return 1;
                    else return 0;

                }
                else return ccw(p1, p2);
                /* both above or both below*/

            }
        };

        public int ccw(Point p2, Point p3) {
            // { -1, 0, +1 } if a(this)→2→3 is a { clockwise, collinear; counterclocwise } turn.
            double dy3 = p3.y - y;
            double dx3 = p3.x - x;
            double dy2 = p2.y - y;
            double dx2 = p2.x - x;
            double area2 = dx2 * dy3 - dx3 * dy2;
            if (area2 < 0) return 1; /* clockwise, theta2>theta3*/
            else if (area2 > 0) return -1;
            else return 0;
        }


        /* this is the end of this whole class*/
    }

    /*  main ***********************************************************/
    public static void main(String[] args) {
        Point point2D1 = new Point(0.2, 1.3);
        Point point2D2 = new Point(92.12, 140.82);
        Point point2D3 = new Point(20, 22.0);
        Point point2D4 = new Point(30, 0);

        Point[] points = { point2D1, point2D2, point2D3, point2D4 };

        //X coordinate order
        StdOut.println("Order by X coordinate");
        Arrays.sort(points, Point.xorder);

        for (Point point2D : points) {
            StdOut.print(point2D.x + " ");
        }

        StdOut.println("\nExpected: 0.2 20.0 30.0 92.12");

        //Y coordinate order
        StdOut.println("\nOrder by Y coordinate");
        Arrays.sort(points, Point.yorder);

        for (Point point2D : points) {
            StdOut.print(point2D.y + " ");
        }

        StdOut.println("\nExpected: 0.0 1.3 22.0 140.82");

        //Distance from origin order
        StdOut.println("\nOrder by distance from origin");
        Arrays.sort(points, Point.rorder);

        for (Point point2D : points) {
            StdOut.print(point2D.x + " ");
        }

        StdOut.println("\nExpected: 0.2 20.0 30.0 92.12");

        //Distance from specified point
        StdOut.println("\nOrder by distance from specified point");
        Arrays.sort(points, point2D2.orderDistance2point());

        for (Point point2D : points) {
            StdOut.print(point2D.x + " ");
        }

        StdOut.println("\nExpected: 92.12 20.0 30.0 0.2");

        //Polar angle distance from specified point
        StdOut.println("\nOrder by polar angle distance from specified point");
        Arrays.sort(points, point2D2.orderTheta2point);

        for (Point point2D : points) {
            StdOut.print(point2D.x + " ");
        }

        StdOut.println("\nExpected: 92.12 0.2 20.0 30.0");
    }
}
