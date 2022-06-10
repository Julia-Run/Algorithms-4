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

public class Dijkstra {
    // Prim on time
    // DirectedEdge, Weighted, MinPath;
    private DirectedEdge[] edgeTo;
    private double[] disTo;
    private IndexMinPQ<Double> pq;
    // private boolean[] marked;

    public Dijkstra(EdgeWeightedDigraph g, int s) {
        // marked = new boolean[g.V()];
        edgeTo = new DirectedEdge[g.V()];
        disTo = new double[g.V()];
        for (int i = 0; i < g.V(); ++i) disTo[i] = Double.POSITIVE_INFINITY;
        disTo[s] = 0.0;
        pq = new IndexMinPQ<Double>(g.V());
        pq.insert(s, 0.0);
        while (!pq.isEmpty()) relax(g, pq.delMin());
    }

    private void relax(EdgeWeightedDigraph g, int v) {
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to(); // if assuming w is marked, refresh its value to min;
            if (disTo[w] > disTo[v] + e.weight()) {
                edgeTo[w] = e;
                disTo[w] = disTo[v] + e.weight();
                if (pq.contains(w)) pq.change(w, disTo[w]);
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
        Dijkstra dPath = new Dijkstra(g, s);
        for (int v = s; v < g.V(); ++v) {
            if (dPath.havePathTo(v)) {
                StdOut.print(s + " to " + v);
                StdOut.printf(" (%.2f):  ", dPath.disTo[v]);
                for (DirectedEdge e : dPath.pathTo(v)) {
                    StdOut.print(e + " ");
                }
                StdOut.println();
            }
        }
    }
}
