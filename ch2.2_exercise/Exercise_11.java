/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class Exercise_11 {

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
        // for (int i = lo; i <= hi; i++) aux[i] = a[i];  /* 开辟存储*/ /* 初始化吗*/
        int i = lo;  /* lo --mid*/
        int j = mid + 1;  /* mid+1---hi*/
        for (int k = lo; k <= hi; k++) {
            if (i > mid) aux[k] = a[j++];
            else if (j > hi) aux[k] = a[i++];
            else if (less(a[i], a[j])) aux[k] = a[i++];
            else aux[k] = a[j++];
        }
    }

    public static boolean less(Comparable ai, Comparable aj) {
        if (ai.compareTo(aj) < 0) return true;
        return false;
    }

    public static void sort(Comparable[] a) {
        int lo = 0;
        int hi = a.length - 1;
        Comparable[] aux = a.clone();
        sortArray(aux, a, lo, hi);
    }

    public static void sortArray(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi - lo + 1 <= limit) {
            sortInsertion(aux, lo, hi);  /* 将参数二升序排列*/
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sortArray(aux, a, lo, mid);
        sortArray(aux, a, mid + 1, hi);
        if (less(a[mid], a[mid + 1])) {
            System.arraycopy(a, lo, aux, lo, hi - lo + 1);  /* 将参数一lo-hi,复制给参数二；*/
            return;
        }
        merge(a, aux, lo, mid, hi);
    }

    public static void main(String[] args) {
        Integer[] a = { 3, 56, 8, 9, 0, 0, 345, -11, -7, -0, 44, 6778, 9000, 322, 3, 67 };
        sort(a);
        for (int i = 0; i < a.length; i++) System.out.print(a[i] + "  ");
    }
}
