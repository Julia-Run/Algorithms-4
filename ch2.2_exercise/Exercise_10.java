/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class Exercise_10 {

    private static Comparable[] aux;

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        for (int i = lo; i <= mid; i++) aux[i] = a[i];  /* 开辟存储*/ /* 初始化吗*/
        for (int j = mid + 1; j <= hi; j++) aux[mid + 1 + hi - j] = a[j];  /* 开辟存储*/ /* 初始化吗*/
        int i = lo;  /* lo --mid*/
        int j = hi;
        for (int k = lo; k <= hi; k++) {
            if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j--];
        }
    }

    public static boolean less(Comparable ai, Comparable aj) {
        if (ai.compareTo(aj) < 0) return true;
        return false;
    }

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];  /* 该函数只运行一次，只开辟一次存储*/
        int lo = 0;
        int hi = a.length - 1;
        sortArray(a, lo, hi);
    }

    public static void sortArray(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sortArray(a, lo, mid);
        sortArray(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public static void main(String[] args) {
        Integer[] a = { 3, 56, 8, 9, 0, 0, 6, 3, 6, 4563, 2, -11, -2, 56 };
        sort(a);
        for (int i = 0; i < a.length; i++) System.out.print(a[i] + "  ");
    }
}
