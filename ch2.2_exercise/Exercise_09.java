/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class Exercise_09 {

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) aux[i] = a[i];  /* 3. begin to work in merge*/
        int i = lo;  /* lo --mid*/
        int j = mid + 1;  /* mid+1---hi*/
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }

    public static boolean less(Comparable ai, Comparable aj) {
        if (ai.compareTo(aj) < 0) return true;
        return false;
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];  /* 该函数只运行一次，只开辟一次存储*/
        // for (int i = 0; i < a.length; i++) aux[i] = 0; /* aux init, sort 的局部变量*/
        int lo = 0;
        int hi = a.length - 1;
        sortArray(a, aux, lo, hi);  /* 1. 传递给sortArray*/
    }

    public static void sortArray(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sortArray(a, aux, lo, mid);
        sortArray(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi); /* 2. 传递给merge*/
    }

    public static void main(String[] args) {
        Integer[] a = { 3, 56, 8, 9, 0, 0, 6, 3, 6, 4563, 2, -11, -2, 56 };
        sort(a);
        for (int i = 0; i < a.length; i++) System.out.print(a[i] + "  ");
    }
}
