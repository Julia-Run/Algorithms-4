/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick {
    public static Comparable[] aux;

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

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        // merge into one array
        for (int i = lo; i <= hi; ++i) aux[i] = a[i];
        int i = lo, j = mid + 1, k = lo;
        while (k <= hi) {
            if (i > mid) a[k++] = aux[j++];
            else if (j > hi) a[k++] = aux[i++];
            else if (less(aux[i], aux[j])) a[k++] = aux[i++];
            else a[k++] = aux[j++];
        }
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = rankOne(a, lo, hi);
        sort(a, lo, j);
        sort(a, j + 1, hi);
    }

    private static int rankOne(Comparable[] a, int lo, int hi) {
        Comparable bar = a[lo];
        int i = lo, j = hi;
        while (true) {
            while (less(a[++i], bar)) if (i >= hi - 1) break;
            // have to be --i; otherwise there will be a problem
            // next time will start with an end i;
            while (less(bar, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exchange(a, i, j);
        }
        exchange(a, lo, j);
        return j;
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length);
    }

    public static void main(String[] args) {
        Integer[] a = { 1, 3, 5, 7, -3, -6, 0, 98, 45, 76, 2, 87, 0, -7, 52, -100 };
        sort(a);
        show(a);
    }
}
