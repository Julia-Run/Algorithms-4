/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class FourSum {
    public static int count(long[] a) {
        int count = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int m = k + 1; m < n; m++) {
                        if (a[i] + a[j] + a[k] + a[m] == 0) count++;
                    }
                }
            }
        }
        return count;
    }

    public static void printAll(long[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int m = k + 1; m < n; m++) {
                        if (a[i] + a[j] + a[k] + a[m] == 0)
                            StdOut.println(
                                    a[i] + " + " + a[j] + " + " + a[k] + " + " + a[m] + " = " + 0);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int Max = 100000;
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(-Max, Max);
        }
        int c = count(a);
        System.out.println("N = " + n + ", Count = " + c);

        if (c < 10) printAll(a);
    }
}
