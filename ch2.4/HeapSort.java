/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class HeapSort {

    public static void heapsort(Comparable[] a) {  /* 堆操作*/
        int n = a.length;
        for (int k = n / 2; k >= 1; k--) sink(a, k, n);
        while (n > 1) {
            exch(a, 1, n--);
            sink(a, 1, n);
        }

    }

    public static void sink(Comparable[] a, int k, int n) {  /* 堆操作*/
        while (2 * k <= n) {
            int j = 2 * k;
            if (less(a, j, j + 1) && j < n) j++;
            if (!less(a, k, j)) break;
            exch(a, k, j);
            k = j;
        }
    }

    public static void exch(Comparable[] x, int i, int j) {  /* 实际对数组进行操作的函数*/
        Comparable temp = x[i - 1];
        x[i - 1] = x[j - 1];
        x[j - 1] = temp;
    }

    public static boolean less(Comparable[] a, int i, int j) {  /* 实际对数组进行操作的函数*/
        return a[i - 1].compareTo(a[j - 1]) == -1;
    }


    public static void main(String[] args) {
        Integer[] a = { 1, 4, 5, 7, 8, 9, -11, 3, 4, 4, 5, 6, 0, 0, -2222, -6, 463 };
        heapsort(a);
        for (int i = 0; i < a.length; i++) System.out.print(a[i] + "  ");
    }
}

