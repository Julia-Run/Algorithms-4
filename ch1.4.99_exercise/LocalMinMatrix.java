/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

public class LocalMinMatrix {
    public static int oneD(long[] a) {
        int n = a.length;
        int lo = 0;
        int up = n;
        while (up - lo >= 3) {
            int mid = lo + (-lo + up) / 2;
            if (a[mid - 1] > a[mid] && a[mid] < a[mid + 1]) return mid;
            else if (a[mid - 1] < a[mid + 1]) up = mid;
            else lo = mid;
        }
        return -1;
    }

    public static int[] localMinIndex(long[][] a) {
        int n = a.length;
        int row = -1;
        int col = -1;
        for (int i = 0; i < n; i++) {
            col = oneD(a[i]);
            if (col != -1) {
                if (i == 0) {
                    if (a[i][col] < a[i + 1][col]) row = i;
                }
                else if (i == n - 1) {
                    if (a[i - 1][col] > a[i][col]) row = i;
                }
                else {
                    if (a[i - 1][col] > a[i][col] && a[i][col] < a[i + 1][col]) row = i;
                }
            }
            if (row != -1) {
                int[] index = { row, col };
                return index;
            }
        }
        int[] index = { row, col };
        return index;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int Max = 1000;
        long[][] a = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) a[i][j] = StdRandom.uniform(-Max, Max);
        }
        long t0 = System.currentTimeMillis();
        int[] index = localMinIndex(a);
        long t1 = System.currentTimeMillis();
        double deltaT = t1 - t0;
        System.out.println(index[0] + " " + index[1] + ",  time = " + deltaT);
    }
}
