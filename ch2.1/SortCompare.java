/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class SortCompare {
    public static double time(String alg, Double[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) Insertion.sort(a);
        if (alg.equals("InsertionFast1")) InsertionFast1.sort(a);
        if (alg.equals("Selection")) Selection.sort(a);
        if (alg.equals("Shell")) Shell.sort(a);
        return timer.elapsedTime();
    }

    public static double randomArraysCost(String alg, int n, int t) {
        Double[] a = new Double[n];
        double total = 0.0;
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < n; j++) a[j] = StdRandom.uniform();  /* 构造随机array*/
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int n = Integer.parseInt(args[2]);
        int t = Integer.parseInt(args[3]);
        double t1 = randomArraysCost(alg1, n, t);
        double t2 = randomArraysCost(alg2, n, t);
        StdOut.printf(alg1 + " cost about %.4f sec. \n", t1);
        StdOut.printf(alg2 + " cost about %.4f sec. \n", t2);
        StdOut.printf(alg2 + " is %.4f faster than %s \n", t1 / t2, alg1);
    }
}
