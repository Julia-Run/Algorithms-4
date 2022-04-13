/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class Insertion {
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

    // public static void sort(Comparable[] a) {
    //     for (int i = 1; i < a.length; ++i) {
    //         for (int j = i; j - 1 >= 0 && less(a[j], a[j - 1]); --j) exchange(a, j, j - 1);
    //         // && make sure no need to run into the loop if a[j-1]<a[j]
    //     }
    // }

    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; ++i) {
            for (int j = i; j - 1 >= 0 && less(a[j], a[j - 1]); --j) exchange(a, j-1, j);
        }
    }

    public static void main(String[] args) {
        Integer[] a = { 1, 3, 5, 7, -3, -6, 0, 98, 45, 76, 2, 87, 0, -7, 52, -100 };
        sort(a);
        show(a);
    }
}
