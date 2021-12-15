/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Accumulator {
    //variables
    private double sum;
    private int N;
    // private final String name;

    //constructors
    // public Accumulator(String s) {
    //     name = s;
    // }

    //method
    public void addDataValue(double val) {
        N += 1;
        sum += val;
    }

    public double mean() {
        return sum / N;
    }

    public String toString() {
        return "sum = " + String.format("%.4f", sum) + ", mean = " + String.format("%.4f", mean());
    }

    //test
    public static void main(String[] args) {
        // Accumulator a = new Accumulator(args[0]);
        Accumulator a = new Accumulator();
        // int T = Integer.parseInt(args[1]);
        int T = Integer.parseInt(args[0]);
        for (int i = 0; i < T; i += 1) {
            a.addDataValue(StdRandom.random());
        }
        StdOut.print(a);

    }
}
