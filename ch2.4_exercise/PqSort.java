/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class PqSort {  /* 继承*/

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int k = n / 2; k >= 1; k--) sink(a, k, n);  /* heap*/
        /* sort*/
        while (n > 1) {
            exch(a, 1, n--);
            sink(a, 1, n);
        }
    }

    public static void sink(Comparable[] a, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(a, j, j + 1)) j++;
            if (less(a, j, k)) break;
            exch(a, k, j);
            k = j;
        }
    }

    /* helpers*/
    public static boolean less(Comparable[] a, int i, int j) {
        return a[i - 1].compareTo(a[j - 1]) == -1;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i - 1];
        a[i - 1] = a[j - 1];
        a[j - 1] = temp;
    }

    public static void main(String[] args) {
        // MaxPQrepeat b = new MaxPQrepeat(5);
        // b.insert(10);
        // b.insert(15);
        // b.delMax();
        // b.insert(20);
        // b.insert(100);
        // b.insert(9);
        // b.insert(50);
        // for (int i = 1; i <= b.size(); i++) System.out.print(b.call(i) + "  ");
        // System.out.println();
        Integer[] c = { 10, 20, 100, 9, 10, 2, 100, 3, 200, 250, 1000, 11, -1, 0, 0, 9, -20 };
        sort(c);  /* 10 refers to extra spaces*/
        for (int i = 0; i < c.length; i++) System.out.print(c[i] + "  ");
    }
}
