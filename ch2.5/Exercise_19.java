/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise_19 {

    public int kendallTau(int[] a1, int[] a2) {
        if (a1.length != a2.length) throw new RuntimeException("Input Error!");

        int[] ref = new int[a1.length];
        for (int i = 0; i < a1.length; i++) ref[a1[i]] = i;
        int[] b = new int[a2.length];
        for (int i = 0; i < a2.length; i++) b[i] = ref[a2[i]];
        return movements(b);
    }

    public int movements(int[] x) {
        int n = x.length;
        int[] aux = new int[n];
        return binaryDiv(x, aux, 0, n - 1);
    }

    public int binaryDiv(int[] a, int[] aux, int lo, int hi) {
        if (lo >= hi) return 0;
        int mid = lo + (hi - lo) / 2;
        int left = binaryDiv(a, aux, lo, mid);
        int right = binaryDiv(a, aux, mid + 1, hi);
        int num = count(a, aux, lo, mid, hi);
        return left + right + num;
    }

    public int count(int[] a, int[] aux, int lo, int mid, int hi) {
        /* 排序*/
        for (int i = lo; i <= hi; i++) aux[i] = a[i];

        int left = lo, right = mid + 1, index = lo, times = 0;

        while (left <= mid && right <= hi) {
            if (aux[left] <= aux[right]) a[index] = aux[left++];
            else {
                times += mid - left + 1;
                a[index] = aux[right++];
            }
            index++;
        }
        while (left <= mid) a[index++] = aux[left++];
        return times;
    }


    public static void main(String[] args) {
        //Book example
        // int[] a1 = { 0, 3, 1, 6, 2, 5, 4 };
        // int[] a2 = { 1, 0, 3, 6, 4, 2, 5 };
        int n = Integer.parseInt(args[0]);
        int[] a1 = new int[n], a2 = new int[n];
        for (int i = 0; i < n; i++) {
            a1[i] = i;
            a2[i] = i;
        }
        StdRandom.shuffle(a1);
        StdRandom.shuffle(a2);

        int kendallTauDistance = new Exercise_19().kendallTau(a1, a2);

        // StdOut.println("Kendall tau Distance: " + kendallTauDistance + " Expected: 4");
        for (int i = 0; i < n; i++) StdOut.print(a1[i] + "  ");
        StdOut.println();
        for (int i = 0; i < n; i++) StdOut.print(a2[i] + "  ");
        StdOut.println();
        StdOut.println("Kendall tau Distance: " + kendallTauDistance);
    }
}

