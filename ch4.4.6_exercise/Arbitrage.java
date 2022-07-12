/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Arbitrage {
    public static void main(String[] args) {
        In in = new In("rates.txt");
        int n = in.readInt();
        in.readLine();
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(n);
        String[] name = new String[n];
        for (int i = 0; i < n; ++i) {
            name[i] = in.readString();
            for (int j = 0; j < n; ++j) {
                double k = -Math.log(in.readDouble());
                DirectedEdge e = new DirectedEdge(i, j, k);
                g.addEdge(e);
            }
            in.readLine();
        }
        StdOut.println(g);

        BellmanFordMinPath finder = new BellmanFordMinPath(g, 0);
        if (finder.hasNegativeCircle()) {
            double stake = 1000.0;
            for (DirectedEdge e : finder.getCircle()) {
                StdOut.printf("%10.5f %s", stake, name[e.from()]);
                stake *= Math.exp(-e.weight());
                StdOut.printf(" = %10.5f %s\n", stake, name[e.to()]);
            }
        }
        else StdOut.println("No Oppotunity!");
    }
}
