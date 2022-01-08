/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

public class Quick3 {
    public static void quick3(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        int i = lo + 1;
        Comparable v = a[lo];
        int c = 0;
        while (i <= gt) {
            c = a[i].compareTo(v);
            if (c < 0) exch(a, i++, lt++);
            else if (c > 0) exch(a, i, gt--);
            else i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    public static void main(String[] args) {
        Integer[] a = { 1, 4, 5, 7, 8, 9, -11, 3, 4, 4, 5, 6, 0, 0, -2222, -6, 463 };
        quick3(a);
        for (int i = 0; i < a.length; i++) System.out.print(a[i] + "  ");
    }
}

