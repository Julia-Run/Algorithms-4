/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BagMean {
    public static void main(String[] args) {
        Bag<Double> all = new Bag<Double>();
        while (!StdIn.isEmpty()) {
            all.add(StdIn.readDouble());
        }
        double sum = 0.0;
        for (double i : all) {
            sum += i;
        }
        double mean = sum / all.size();

        double s = 0;  /* sum=0.0; */
        for (double j : all) {
            s += (j - mean) * (j - mean);
        }
        double std = s / (all.size() - 1);
        StdOut.println("mean = " + mean);
        StdOut.println("std = " + String.format("%.2f", std));

    }
}
