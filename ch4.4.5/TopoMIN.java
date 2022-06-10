/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Topological;

public class TopoMIN {
    private DirectedEdge[] edgeTo;
    private double[] disTo;

    public TopoMIN(EdgeWeightedDigraph g, int s) {
        edgeTo = new DirectedEdge[g.V()];
        disTo = new double[g.V()];
        for (int i = 0; i < g.V(); ++i) disTo[i] = Double.POSITIVE_INFINITY;
        disTo[s] = 0.0;

        Topological tp = new Topological(g);
        for (int w : tp.order()) relax(g, w);
    }

    private void relax(EdgeWeightedDigraph g, int v) {
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (disTo[w] > disTo[v] + e.weight()) {
                edgeTo[w] = e;
                disTo[w] = disTo[v] + e.weight();
            }
        }
    }

    private double disTo(int v) {
        return disTo[v];
    }

    public boolean hasPathTo(int v) {
        return disTo(v) < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> s = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) s.push(e);
        return s;
    }

    public static void main(String[] args) {
        In in = new In("tinyEWDAG.txt");
        int s = Integer.parseInt(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);

        // find shortest path from s to each other vertex in DAG
        TopoMIN sp = new TopoMIN(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (sp.hasPathTo(v)) {
                StdOut.printf("%d to %d (%.2f)  ", s, v, sp.disTo(v));
                for (DirectedEdge e : sp.pathTo(v)) {
                    StdOut.print(e + "   ");
                }
                StdOut.println();
            }
            else {
                StdOut.printf("%d to %d         no path\n", s, v);
            }
        }
    }
}
