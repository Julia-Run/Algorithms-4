/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class ReadInts {
    public static int[] readInts(String name) {
        In txtFile = new In(name);  /*open*/
        String s = txtFile.readAll();
        String[] res = s.split("\\s+");
        int N = res.length;
        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            result[i] = Integer.parseInt(res[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] k = readInts(args[0]);
        for (int i = 0; i < k.length; i++) {
            StdOut.println(k[i]);
        }
    }
}
