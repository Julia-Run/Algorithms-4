/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Arrays;

public class Eggs {

    public static int binary(int egg, int[] a) {
        int lo = 0;
        int up = a.length;
        while (up >= lo) {
            int mid = lo + (-lo + up) / 2;
            if (a[mid] >= egg && a[mid - 1] < egg) return mid;
            else if (a[mid] < egg) up = mid - 1;
            else lo = mid + 1;
        }
        return -1;
    }

    public static int throwing(int[] f, int egg) {
        int key = -1;
        if (egg == f[0]) return f[0];
        int i = 1;
        while (i < f.length + 1) {
            if (egg == f[i]) return i;
            else if (egg > f[i]) i = i * 2;
            else {
                int[] a = Arrays.copyOfRange(f, i / 2, i + 1);
                return i / 2 + binary(egg, a);
            }
        }
        return key;
    }

    public static void main(String[] args) {
        int egg = 100;
        int[] a = { 10, 20, 50, 100, 105, 200, 300 };
        int k = throwing(a, egg);
        System.out.println(k);
    }
}
