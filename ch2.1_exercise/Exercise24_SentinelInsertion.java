/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class Exercise24_SentinelInsertion {
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

        int n = a.length;
        int min = 0;
        for (int k = 0; k < n; k++) {
            if (less(a[k], a[min])) min = k;
        }
        exch(a, 0, min);
        for (int i = 1; i < n; i++) {
            for (int j = i; less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
        assert isSorted(a);
    }

    /* examples*/
    public static void main(String[] args) {
        // String[] a = StdIn.readAllStrings();
        Integer[] a = { 1, 510, 61, 8, 4, 3, 2, 5, 5, 8, 90 };
        Exercise24_SentinelInsertion.sort(a);
        show(a);
    }
}
