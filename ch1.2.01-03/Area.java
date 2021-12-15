/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Area {
    public static void main(String[] args) {
        double xlo = Double.parseDouble(args[0]);
        double xup = Double.parseDouble(args[1]);
        double ylo = Double.parseDouble(args[2]);
        double yup = Double.parseDouble(args[3]);
        Interval1D x = new Interval1D(xlo, xup);
        Interval1D y = new Interval1D(ylo, yup);
        Interval2D box = new Interval2D(x, y);
        box.draw();  /*画出矩形*/

        //  possibility，画出点点
        int N = Integer.parseInt(args[4]);
        Counter c = new Counter("points");
        for (int i = 0; i < N; i++) {
            double px = StdRandom.random();
            double py = StdRandom.random();
            Point2D p = new Point2D(px, py);
            if (box.contains(p)) c.increment();
            else p.draw();
        }
        //    print

        StdOut.println(c);
        double area = c.tally() / 1.0 / N;
        // c.tally() / N / 1.0 = 0;--先算前面一步，结果为int 0，再算后面，结果为double 0
        StdOut.println(area);
        StdOut.println(box.area());

    }
}
