/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class Exercise_16 {

    // postcondition: a[lo..hi] is best-case input for quicksorting that subarray
    private static void merge(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        merge(a, lo, mid - 1);
        merge(a, mid + 1, hi);
        /* 快速排序最快速的方式：完整数列（乱序）--lo和mid交换，分支，lo和mid交换，再分支。。。直到最后只剩一个元素，return
         * 此处，为该过程的逆过程
         * 完整数列（顺序）-- 分支-- 分支。。。直到一个元素。（开始打乱）对左右分支，将mid和lo的元素换位，回上一级，将mid和lo的元素换位。。。
         * 。。。直到最后回到完整数列，将将mid和lo的元素换位*/
        /* 类似merge函数自上向下法。merge函数在返回的路程中，进行的是将两个sorted数列（叶子位置一元素 or 插入排序后sorted）进行融合的过程*/
        /* exchange mid and lo*/
        int temp = a[mid];
        a[mid] = a[lo];
        a[lo] = temp;
    }

    public static int[] best(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = i;
        merge(a, 0, n - 1);
        return a;
    }


    public static void main(String[] args) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int n = Integer.parseInt(args[0]);
        int[] a = best(n);
        for (int i = 0; i < n; i++)
            // StdOut.println(a[i]);
            StdOut.print(alphabet.charAt(a[i]));
        StdOut.println();
    }
}
