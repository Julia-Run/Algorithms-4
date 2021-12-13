/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BinarySearch {
    public static int include(int v, int[] a) {
        int N = a.length;
        int lo = 0;
        int up = N - 1;
        while (lo <= up) {
            int mid = lo + (up - lo) / 2;
            if (v < a[mid]) up = mid - 1;
            else if (v > a[mid]) lo = mid + 1;
            else return mid;

        }
        return -1;
    }

    public static void main(String[] args) {
        int[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty()) {
            int value = StdIn.readInt();
            if (include(value, whitelist) < 0) StdOut.println(value);
        }


    }
}
