/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;


public class DrawRandom {
    // public static void main(String[] args) {
    //     int N = 100;
    //     double[] r = new double[N];
    //     for (int i = 0; i < N; i += 1) r[i] = StdRandom.uniform(0, 100);
    //     StdDraw.setXscale(0.0 - 1.0, N);
    //     StdDraw.setYscale(0.0, N);
    //     for (int i = 0; i < N; i += 1) {
    //         double x = i * 1.0;
    //         double y = r[i] / 2;
    //         double w = 1.0 / 3;
    //         double h = r[i] / 2;
    //         StdDraw.filledRectangle(x, y, w, h);
    //     }
    // }
    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);
        double[] r = new double[N];
        for (int i = 0; i < N; i += 1) r[i] = StdRandom.random();
        for (int i = 1; i < N; i += 1) {
            double x = i * 1.0 / N;
            double y = r[i] / 2;
            double w = 0.25 / N;
            double h = r[i] / 2;
            StdDraw.filledRectangle(x, y, w, h);
        }
    }
}
