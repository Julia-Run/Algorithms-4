/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

public class Quick {

    public static void quickSort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partion(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    public static int partion(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(v, a[--j])) ;
            // while (less(a[++i], v)) ;
            while (less(a[++i], v)) if (i >= hi) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static boolean less(Comparable ai, Comparable aj) {
        return ai.compareTo(aj) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    public static void main(String[] args) {
        Integer[] a = { 1, 4, 5, 7, 8, 9, -11, 3, 4, 4, 5, 6, 0, 0, -2222, -6, 463 };
        quickSort(a);
        for (int i = 0; i < a.length; i++) System.out.print(a[i] + "  ");
    }
}
