/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;
import java.awt.Font;

public class Exercise18_DrawSelection {
    /* variables*/
    /* constructors*/
    /* methods*/

    private static void draw(String[] a, int row, int ith, int min) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(-2.5, row, ith + "");
        StdDraw.text(-1.25, row, min + "");
        for (int i = 0; i < a.length; i++) {
            if (i < ith) StdDraw.setPenColor(Color.LIGHT_GRAY);
            else if (i == min) StdDraw.setPenColor(StdDraw.BOOK_RED);
            else StdDraw.setPenColor(Color.BLACK);
            StdDraw.text(i, row, a[i]);
        }
    }

    private static void header(String[] a) {
        int n = a.length;
        StdDraw.setPenColor(Color.BLACK);
        /* a[], -3*/
        StdDraw.text(n / 2.0, -3, "a[ ]");
        /* -2 */
        StdDraw.text(-2.5, -2, "i");
        StdDraw.text(-1.25, -2, "min");
        for (int i = 0; i < n; i++) StdDraw.text(i, -2, i + "");
        /* red line*/
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.line(-3, -1.6, n - 0.5, -1.6);
        StdDraw.setPenColor(StdDraw.BLACK);
        /* -1*/
        for (int i = 0; i < n; i++) StdDraw.text(i, -1, a[i]);
    }

    private static void bottom(String[] a) {
        int n = a.length;
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < n; i++) StdDraw.text(i, n, a[i]);
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

    public static void sort(String[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min])) min = j;
            }
            draw(a, i, i, min);
            exch(a, i, min);
        }
    }

    /* examples*/
    public static void main(String[] args) {
        String s = args[0];
        int n = s.length();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) a[i] = s.substring(i, i + 1);

        // StdDraw.setCanvasSize(25 * (n + 3), 25 * (n + 3)); /* pixels *//*/
        StdDraw.setCanvasSize(800, 720); /* pixels */
        /* 和屏幕的resolution对应， 1920， 1080. （注意屏幕显示，125%）
         * xy scale来设置该画布的分割线*/
        StdDraw.setXscale(-3, n + 0.5);
        StdDraw.setYscale(n + 0.5, -3.5);
        StdDraw.setFont(new Font("TimesNewRoman", Font.PLAIN, 13));

        header(a);
        sort(a);
        bottom(a);
    }
}
