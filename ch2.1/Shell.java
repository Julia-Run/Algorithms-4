/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class Shell {
    /* variables*/
    /* constructors*/
    /* methods*/
    public static boolean less(Comparable ai, Comparable aj) {
        return ai.compareTo(aj) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    public static void sort(Comparable[] a) {
        /* shell*/
        int n = a.length;
        int h = 1;
        while (h < n / 3) h = h * 3 + 1;  /* hmax*/
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) exch(a, j, j - h);
                h = h / 3;
            }
        }
    }

    /* examples*/
    public static void main(String[] args) {
    }
}
