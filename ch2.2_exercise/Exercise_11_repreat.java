/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class Exercise_11_repreat {

    private static int limit = 6;

    public static void sortInsertion(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            Comparable curr = a[i];
            int j = i;
            for (; j - 1 >= lo && less(curr, a[j - 1]); j--) {
                a[j] = a[j - 1];
            }
            a[j] = curr;
        }

    }

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();
        sortTimes(a, aux, 0, a.length - 1);
    }

    public static void sortTimes(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi - lo <= limit) {
            sortInsertion(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sortTimes(aux, a, lo, mid);
        sortTimes(aux, a, mid + 1, hi);
        if (less(aux[mid], aux[mid + 1])) {
            System.arraycopy(aux, lo, a, lo, hi - lo + 1);
            return;
        }
        merge(a, aux, lo, mid, hi);
    }

    public static boolean less(Comparable ai, Comparable aj) {
        if (ai.compareTo(aj) < 0) return true;
        return false;
    }


    public static void main(String[] args) {
        Integer[] a = { 3, 56, 8, 9, 0, 0, 345, -11, -7, -0, 44, 6778, 9000, 322, 3, 67 };
        sort(a);
        for (int i = 0; i < a.length; i++) System.out.print(a[i] + "  ");
    }
}
