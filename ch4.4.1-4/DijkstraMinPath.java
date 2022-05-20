/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DijkstraMinPath {
    // tree, path, vertexes
    private DirectedEdge[] edgeTo;
    private double[] disTo;
    private IndexMinPQ<Double> pq;

    public DijkstraMinPath(EdgeWeightedDigraph g, int s) {
        edgeTo = new DirectedEdge[g.V()];
        disTo = new double[g.V()];
        pq = new IndexMinPQ<Double>(g.V());

        for (int v = 0; v < g.V(); ++v) disTo[v] = Double.POSITIVE_INFINITY;
        disTo[s] = 0;
        pq.insert(s, 0.0);

        while (!pq.isEmpty()) {
            relax(g, pq.delMin());
        }
    }

    public void relax(EdgeWeightedDigraph g, int v) {
        // find v with mini disTo[v]; put into pq;
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (disTo[w] > disTo[v] + e.weight()) {
                edgeTo[w] = e;
                disTo[w] = disTo[v] + e.weight();
                if (pq.contains(w)) pq.changeKey(w, disTo[w]);
                else pq.insert(w, disTo[w]);
            }
        }
    }

    public double disTo(int v) {
        return disTo[v];
    }

    public boolean havePathTo(int v) {
        return disTo[v] != Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!havePathTo(v)) return null;
        Stack<DirectedEdge> st = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) st.push(e);
        return st;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(new In("tinyEWD.txt"));
        int s = 0;
        DijkstraMinPath dPath = new DijkstraMinPath(g, s);
        for (int v = s; v < g.V(); ++v) {
            if (dPath.havePathTo(v)) {
                StdOut.print(s + " to " + v );
                StdOut.printf(" (%.2f):  ", dPath.disTo[v]);
                for (DirectedEdge e: dPath.pathTo(v)){
                    StdOut.print(e + " ");
                }
                StdOut.println();
            }
        }
    }
}
