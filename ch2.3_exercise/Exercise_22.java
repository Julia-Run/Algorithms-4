/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

public class Exercise_22 {
    public static void quick3(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int p = lo, q = hi + 1;
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            if (i > lo && a[i].compareTo(v) == 0) exch(a, ++p, i);  /* 边界1  i > lo */
            if (j <= hi && a[j].compareTo(v) == 0) exch(a, --q, j); /* 边界2： j <= hi */
            while (a[++i].compareTo(v) < 0) if (i >= hi) break;
            while (a[--j].compareTo(v) > 0) ;
            if (i == j && a[i].compareTo(v) == 0)
                exch(a, ++p, i);  /* 边界3：i = lo = j ， 边界4：i = j  */
            /* i = lo = j，--->将更换lo+1 （该value>lo）和lo, 此时会出错 */
            if (i >= j) break;
            exch(a, i, j); /*交换后， a[i]<=v, a[j]>=v  --> 到循环体开头，将=的情况推到两边 。。。 */
        }
        /* i=j， [lo,p] = ; [p+1,i] <; [i+1,q-1]> ; [q,hi]= */
        i = j + 1;
        for (int k = lo; k <= p; k++) exch(a, k, j--);
        for (int k = hi; k >= q; k--) exch(a, k, i++);
        sort(a, lo, j);
        sort(a, i, hi);
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

