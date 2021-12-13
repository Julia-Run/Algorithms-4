/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomSeq {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        double lo = Double.parseDouble(args[1]);
        double up = Double.parseDouble(args[2]);
        for (int i = 0; i < n; i++) {
            double k = StdRandom.uniform(lo, up);
            StdOut.printf("%-15.3f", k);
        }
    }
}
