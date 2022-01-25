/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class Exercise_31 {

    public static void quickSort(int[] a) {
        /* quick sort*/
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int i = lo, j = hi + 1;
        int v = a[lo];
        while (true) {
            while (a[++i] < v) if (i >= hi) break;
            while (a[--j] > v) ;
            if (i >= j) break;
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        a[lo] = a[j];
        a[j] = v;
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    public static int repeatedValNums(int[] a) {
        quickSort(a);
        int times = 0;
        for (int i = 1; i < a.length - 1; i++) {
            if (a[i] != a[i - 1]) times += 1;
        }
        return times;
    }

    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        int trials = Integer.parseInt(args[2]);

        int[] distinct = new int[trials];
        for (int t = 0; t < trials; t++) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = StdRandom.uniform(m);
            }
            distinct[t] = repeatedValNums(a);
        }

        double empirical = StdStats.mean(distinct);
        double alpha = (double) n / m;
        double theoretical = m * (1 - Math.exp(-alpha));
        StdOut.printf("Theoretical = %.3f\n", theoretical);
        StdOut.printf("Empirical   = %.3f\n", empirical);

    }
}
