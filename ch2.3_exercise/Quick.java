/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick {

    public static void quickSort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        /* [lo, hi】 */
        if (hi <= lo) return;

        /* partion */
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (a[++i].compareTo(v) < 0) if (i >= hi) break;
            while (a[--j].compareTo(v) > 0) ;
            if (i >= j) break;
            Comparable temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        a[lo] = a[j];
        a[j] = v;

        /* devide */
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    public static void main(String[] args) {
        // Comparable[] a = { 1, 4, 5, 7, 8, 9, -11, 3, 4, 4, 5, 6, 0, 0, -2222, -6, 463,"a" }; /* not works*/
        // quickSort(a);
        // for (int i = 0; i < a.length; i++) System.out.print(a[i] + "  ");
        /* an official example*/
        String s = args[0];
        int n = s.length();
        String[] a = new String[n];
        for (int i = 0; i < n; i++)
            a[i] = s.substring(i, i + 1);

        quickSort(a);
        for (int i = 0; i < n; i++)
            StdOut.print(a[i]);
        StdOut.println();
    }
}
