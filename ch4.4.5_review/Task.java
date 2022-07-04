/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Task {
    public static void main(String[] args) {
        In in = new In("jobsPC.txt");
        int n = in.readInt();
        in.readLine();
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(2 * n + 2);
        int s = 2 * n, e = 2 * n + 1;

        // construct the diagram
        for (int i = 0; i < n; ++i) {
            String[] a = in.readLine().split("\\s+");
            double duration = Double.parseDouble(a[0]);
            g.addEdge(new DirectedEdge(i, i + n, duration));
            g.addEdge(new DirectedEdge(s, i, 0));
            g.addEdge(new DirectedEdge(i + n, e, 0));
            for (int j = 1; j < a.length; ++j) {
                int v = Integer.parseInt(a[j]);
                g.addEdge(new DirectedEdge(i + n, v, 0));
            }
        }

        TopoMax path = new TopoMax(g, s);
        StdOut.println(" job   start   finish ");
        StdOut.println("-----------------------");
        for (int i = 0; i < n; ++i) {
            StdOut.printf("%3d %8.1f %8.1f\n", i, path.disTo(i), path.disTo(i + n));
        }
        StdOut.printf("Finish time: %8.1f\n", path.disTo(e));
    }
}
