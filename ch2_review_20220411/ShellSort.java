/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class ShellSort {
    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; ++i) {
            StdOut.print(a[i] + "  ");
        }
        StdOut.println();
    }

    public static void sort(Comparable[] a) {
        int n = a.length, h = 1;
        while (h < n / 3) h = 3 * h + 1;  // get the largest step
        while (h >= 1) { //insertion sort
            for (int i = h; i < n; ++i) {
                for (int j = i; j - h >= 0 && less(a[j], a[j - h]); j -= h) {
                    exchange(a, j, j - h);
                }
            }
            h = (h - 1) / 3;
        }
    }

    public static void main(String[] args) {
        Integer[] a = { 1, 3, 5, 7, -3, -6, 0, 98, 45, 76, 2, 87, 0, -7, 52, -100 };
        sort(a);
        show(a);
    }
}
