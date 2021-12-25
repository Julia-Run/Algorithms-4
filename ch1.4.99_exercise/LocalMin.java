/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

public class LocalMin {
    public static int localMinIndex(long[] a) {
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

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int Max = 100000;
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(-Max, Max);
        }
        int index = localMinIndex(a);
        if (index != -1) {
            System.out.println(
                    index + "   " + a[index - 1] + " | " + a[index] + " | " + a[index + 1]);
        }
        else System.out.println("No such value!");
    }
}
