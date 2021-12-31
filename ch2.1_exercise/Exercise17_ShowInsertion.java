/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.Color;

public class Exercise17_ShowInsertion {

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

    public static void sort(Double[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int j = i;
            for (; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
            showBars(a, i, j);
        }
    }

    public static void showBars(Double[] a, int ith, int jth) {
        /* ith以后元素灰色，不移动
         * jth之前为灰色，不移动
         * jth位置显示为红色*/
        StdDraw.setYscale(-a.length + ith, ith + 1);
        for (int i = 0; i < a.length; i++) {
            if (i > ith) StdDraw.setPenColor(Color.LIGHT_GRAY);
            else if (i == jth) StdDraw.setPenColor(Color.RED);
            else if (i < jth) StdDraw.setPenColor(Color.LIGHT_GRAY);
            else StdDraw.setPenColor(Color.BLACK);
            StdDraw.filledRectangle(i, a[i] * 0.4, 0.25, a[i] * 0.4);
        }
    }

    public static void bottom(Double[] a) {
        StdDraw.setYscale(0, a.length + 1);
        StdDraw.setPenColor(Color.BLACK);
        for (int i = 0; i < a.length; i++) StdDraw.filledRectangle(i, a[i] * 0.4, 0.25, a[i] * 0.4);
    }

    /* examples*/
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Double[] a = new Double[n];
        for (int i = 0; i < n; i++) a[i] = StdRandom.uniform();

        StdDraw.setCanvasSize(200, 720);
        StdDraw.setXscale(-1, n + 1);
        sort(a);
        bottom(a);
    }
}
