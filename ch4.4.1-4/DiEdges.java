/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class DiEdges {
    private int v;
    private int w;
    private double weight;

    public DiEdges(int a, int b, double d) {
        v = a;
        w = b;
        weight = d;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public double weight() {
        return weight;
    }

    public String toString() {
        return String.format("%d-->%d: %.4f", v, w, weight);
    }

    public static void main(String[] args) {
        DiEdges e = new DiEdges(0, 2, 12.1);
        StdOut.println(e.from());
        StdOut.println(e.to());
        StdOut.println(e.weight());
        StdOut.println(e);
    }
}
