/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class MergeSort2 {

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

    public static void sort(Comparable[] a, Comparable[] aux) {
        int n = a.length;
        for (int step = 1; step < n; step = 2 * step) {
            for (int i = 0; i + step < n; i += 2 * step) {
                int hi = Math.max(i + 2 * step, n), lo = i, mid = lo + step;
                for (int j = lo; j < hi; ++j) aux[j] = a[j];
                int l = lo, r = mid;
                for (int k = lo; k < hi; ++k) {
                    if (l >= mid) a[k] = aux[r++];
                    else if (r >= hi) a[k] = aux[l++];
                    else if (less(aux[l], aux[r])) a[k] = aux[l++];
                    else a[k] = aux[r++];
                }
            }
        }

    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux);
    }


    public static void main(String[] args) {
        Integer[] a = { 1, 3, 5, 7, -3, -6, 0, 98, 45, 76, 2, 87, 0, -7, 52, -100 };
        sort(a);
        show(a);
    }
}
