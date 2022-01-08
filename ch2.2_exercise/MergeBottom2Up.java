/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class MergeBottom2Up {

    private static Comparable[] aux;

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) aux[i] = a[i];  /* 开辟存储*/ /* 初始化吗*/
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
        int n = a.length;
        aux = new Comparable[n];
        // int j = 0;
        // int i = 2;
        // for (i = 2; i < n; i *= 2) {  /* i = current step array . length*/
        //     /* i永远不可能大于*/
        //     for (j = 0; j < n - i / 2; j += i) { /* 得到currentarray的端点*/
        //         merge(a, j, j + i / 2 - 1, j + Math.min(i - 1, n - 1 - j));
        //     }
        // }
        // merge(a, 0, j - i / 2 - 1, a.length - 1);  /* 这样写缺少最后一次针对整体数组的merge。---依旧不对。。why*/
        for (int i = 1; i < n; i *= 2) {  /* i = current step array . length*/
            for (int j = 0; j < n - i; j += 2 * i) { /* 得到currentarray的端点*/
                merge(a, j, j + i - 1, j + Math.min(2 * i - 1, n - 1 - j));
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = { 3, 56, 8, 9, 0, 0, 6, 3, 6, 4563, 2, -11, -2, 56, 33, 545, 787 };
        sort(a);
        for (int i = 0; i < a.length; i++) System.out.print(a[i] + "  ");
    }
}
