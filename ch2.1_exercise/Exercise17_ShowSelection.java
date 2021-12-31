/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.Color;

public class Exercise17_ShowSelection {
    /* variables*/
    /* constructors*/
    /* methods*/

    private static void showChange(Double[] a, int ith, int min) {
        StdDraw.setYscale(-a.length + ith, ith + 1);
        /* bottom: -a.length + ith, top: ith (bottom top 为矩形外边。0 to n+1 才可以放下n组数据)
         * --- y = 0 = top - ith.
         * selection, i=0:n-1, 每次循环，i=i+1, 图像从top向下一单位
         * 该statement的作用为，将canvas y轴方向重新划分网格，从而调整y=0 在canvas中的位置，从而画yi （yi属于random（0，1））*/
        for (int i = 0; i < a.length; i++) {
            if (i < ith) StdDraw.setPenColor(Color.LIGHT_GRAY);
            else if (i == min) StdDraw.setPenColor(Color.RED);
            else StdDraw.setPenColor(Color.BLACK);
            StdDraw.filledRectangle(i, a[i] * 0.4, 0.25, a[i] * 0.4);
        }
    }

    private static void bottom(Double[] a) {
        int n = a.length;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setYscale(0, n + 1);
        for (int i = 0; i < n; i++) StdDraw.filledRectangle(i, a[i] * 0.3, 0.25, a[i] * 0.3);
    }


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
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min])) min = j;
            }
            showChange(a, i, min);
            exch(a, i, min);
        }
    }

    /* examples*/
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Double[] a = new Double[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform();
        }

        // StdDraw.setCanvasSize(25 * (n + 3), 25 * (n + 3)); /* pixels *//*/
        StdDraw.setCanvasSize(360, 720); /* pixels */
        /* 和屏幕的resolution对应， 1920， 1080. （注意屏幕显示，125%）
         * xy scale来设置该画布的分割线*/
        StdDraw.setXscale(-1, n + 1);
        sort(a);
        bottom(a);
    }
}
