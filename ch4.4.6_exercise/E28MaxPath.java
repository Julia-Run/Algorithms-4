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

public class E28MaxPath {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public E28MaxPath(EdgeWeightedDigraph g, int s) {

        edgeTo = new DirectedEdge[g.V()];
        distTo = new double[g.V()];
        for (int i = 0; i < g.V(); ++i) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        // reverse
        EdgeWeightedDigraph gc = new EdgeWeightedDigraph(g.V());
        for (int v = 0; v < g.V(); ++v) {
            for (DirectedEdge e : g.adj(v)) gc.addEdge(new DirectedEdge(v, e.to(), -e.weight()));
        }
        // -----------
        Topological topo = new Topological(gc);
        for (int v : topo.order()) relax(gc, v);
    }

    // private EdgeWeightedDigraph copyG(EdgeWeightedDigraph g) {
    //     EdgeWeightedDigraph gc = new EdgeWeightedDigraph(g.V());
    //     for (int v = 0; v < g.V(); ++v) {
    //         for (DirectedEdge e : g.adj(v)) gc.addEdge(new DirectedEdge(v, e.to(), -e.weight()));
    //     }
    //     return gc;
    // }

    private void relax(EdgeWeightedDigraph g, int v) {
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                edgeTo[w] = e;
                distTo[w] = distTo[v] + e.weight();
            }
        }
    }

    public boolean havePathto(int v) {
        return distTo[v] != Double.POSITIVE_INFINITY;
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!havePathto(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) path.push(e);
        return path;
    }


    public static void main(String[] args) {
        In in = new In("tinyEWDAG.txt");
        int s = 5;
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        E28MaxPath lp = new E28MaxPath(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (lp.havePathto(v)) {
                StdOut.printf("%d to %d (%.2f)  ", s, v, -lp.distTo(v));
                for (DirectedEdge e : lp.pathTo(v)) {
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
