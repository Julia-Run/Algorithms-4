/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class Exercise_20 {

    private static int limit = 6;

    public static void merge(Integer[] a, Integer[] aux, Integer[] index, int lo, int mid,
                             int hi) {
        for (int i = lo; i <= hi; i++) aux[i] = index[i];
        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                index[k] = aux[j++];
            }
            else if (j > hi) {
                index[k] = aux[i++];
            }
            else if (a[aux[i]] < a[aux[j]]) {
                index[k] = aux[i++];
            }
            else {
                index[k] = aux[j++];
            }
        }
    }

    public static Integer[] sort(Integer[] a) {
        Integer[] aux = a.clone();
        Integer[] index = new Integer[a.length];
        for (int i = 0; i < a.length; i++) index[i] = i;
        sortTimes(a, aux, index, 0, a.length - 1);
        return index;
    }

    public static void sortTimes(Integer[] a, Integer[] aux, Integer[] index, int lo,
                                 int hi) {
        // if (hi - lo <= limit) {
        //     sortInsertion(a, lo, hi);
        //     return;
        // }
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sortTimes(a, aux, index, lo, mid);
        sortTimes(a, aux, index, mid + 1, hi);
        merge(a, aux, index, lo, mid, hi);
    }

    public static void sortInsertion(Integer[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            Integer curr = a[i];
            int j = i;
            for (; j - 1 >= lo && less(curr, a[j - 1]); j--) {
                a[j] = a[j - 1];
            }
            a[j] = curr;
        }

    }

    public static boolean less(Integer ai, Integer aj) {
        if (ai.compareTo(aj) < 0) return true;
        return false;
    }


    public static void main(String[] args) {
        Integer[] a = { 3, 5, 8, 9, 0, 0, 4, 1, 7, 0, -4, 56, -9, 13, 93, 77 };
        for (int i = 0; i < a.length; i++) System.out.print(i + "  ");
        System.out.println();
        for (int i = 0; i < a.length; i++) System.out.print(a[i] + "  ");
        System.out.println();
        System.out.println();
        Integer[] perm = sort(a);
        for (int i = 0; i < a.length; i++) System.out.print(perm[i] + "  ");
        System.out.println();
        for (int i = 0; i < a.length; i++) System.out.print(a[perm[i]] + "  ");
    }
}
