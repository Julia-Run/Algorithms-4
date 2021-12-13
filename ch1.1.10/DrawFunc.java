/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdDraw;

public class DrawFunc {
    public static void main(String[] args) {
        int n = 100;
        StdDraw.setXscale(0.0, n);
        StdDraw.setYscale(0.0, n * n);
        // for (double i = 0.0; i < n; i += 0.1) {
        //     StdDraw.point(i, i);
        //     StdDraw.point(i, i * i);
        //     StdDraw.point(i, Math.log(i * i + 0.1));
        // }
        for (int i = 1; i < n; i += 1) {
            StdDraw.point(i, i);
            StdDraw.point(i, i * i);
            StdDraw.point(i, Math.log(i * i));
        }
    }
}
