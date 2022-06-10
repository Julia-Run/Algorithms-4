/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Jobs {
    public static void main(String[] args) {
        In in = new In("jobsPC.txt");
        int n = in.readInt();
        in.readLine();
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(2 * n + 2);

        int s = 2 * n, e = 2 * n + 1;
        for (int i = 0; i < n; ++i) {
            String a[] = in.readLine().split("\\s+");
            double duration = Double.parseDouble(a[0]);
            g.addEdge(new DirectedEdge(i, i + n, duration));
            g.addEdge(new DirectedEdge(s, i, 0));
            g.addEdge(new DirectedEdge(i + n, e, 0));
            for (int j = 1; j < a.length; ++j) {
                int follower = Integer.parseInt(a[j]);
                g.addEdge(new DirectedEdge(i + n, follower, 0));
            }
        }
        TopoMax lp = new TopoMax(g, s);

        // print results
        StdOut.println(" job   start  finish");
        StdOut.println("--------------------");
        for (int i = 0; i < n; i++) {
            StdOut.printf("%4d %7.1f %7.1f\n", i, lp.disTo(i), lp.disTo(i+n));
        }
        StdOut.printf("Finish time: %7.1f\n", lp.disTo(e));
    }
}
