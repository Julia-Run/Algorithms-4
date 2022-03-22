/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Excercise25_PerfectBalence {


    public static void perfect(BSTrewrite bst, String[] a) {
        Arrays.sort(a);
        perfect(bst, a, 0, a.length);
        StdOut.println();
    }

    /* mid--root; */
    public static void perfect(BSTrewrite bst, String[] a, int lo, int hi) {
        if (lo == hi) return; // [ ), end
        int mid = lo + (hi - lo) / 2;
        bst.put(a[mid], mid);  //
        // StdOut.print(a[mid] + ' ' + mid + ' '); // a[mid] dealed
        StdOut.print(a[mid] + ' '); // a[mid] dealed
        perfect(bst, a, lo, mid);  // mid is ceiling, [lo, mid)
        perfect(bst, a, mid + 1, hi); // mid+1 is floor, [mid + 1, hi)
    }


    public static void main(String[] args) {
        String[] words = StdIn.readAllStrings();
        BSTrewrite<String, Integer> bst = new BSTrewrite<String, Integer>();
        perfect(bst, words);
    }
}
