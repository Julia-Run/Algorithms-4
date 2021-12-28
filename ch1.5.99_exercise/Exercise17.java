/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class Exercise17 {

    public static int count17(int n) {
        int count = 0;
        Exercise14 parent = new Exercise14(n);
        while (parent.count() > 1) {
            int p = StdRandom.uniform(0, n);
            int q = StdRandom.uniform(0, n);
            if (parent.connected(p, q)) continue;
            parent.union(p, q);
            count++;
        }
        return count;
    }


    /* examples*/
    public static void main(String[] args) {
        /* my test code*/
        // int n = Integer.parseInt(args[0]);
        // int count = count17(n);
        // StdOut.println(count);

        /* download*/
        int n = Integer.parseInt(args[0]);          // number of vertices
        int trials = Integer.parseInt(args[1]);     // number of trials
        int[] edges = new int[trials];              // record statistics

        // repeat the experiment trials times
        for (int t = 0; t < trials; t++) {
            edges[t] = count17(n);
        }

        // report statistics
        StdOut.println("1/2 n ln n = " + 0.5 * n * Math.log(n));
        StdOut.println("mean       = " + StdStats.mean(edges));
        StdOut.println("stddev     = " + StdStats.stddev(edges));
    }
}
