/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class PrimLazy {
    // minimum tree: weighted, undirected;
    // variables: MinPQ--vertexes; mst--edges;
    private MinPQ<Edge> pq;
    private Queue<Edge> mst;
    private boolean[] marked;

    public PrimLazy(EdgeWeightedGraph g) {
        marked = new boolean[g.V()];
        pq = new MinPQ<Edge>();
        mst = new Queue<Edge>();
        visit(g, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int w = e.either(), v = e.other(w);
            if (marked[w] && marked[v]) continue;
            mst.enqueue(e);
            if (!marked[w]) visit(g, w);
            if (!marked[v]) visit(g, v);
        }
    }

    private void visit(EdgeWeightedGraph g, int v) {
        marked[v] = true;
        for (Edge e : g.adj(v)) {
            if (!marked[e.other(v)]) pq.insert(e);
        }
    }

    private double weight() {
        double sum = 0;
        for (Edge e : mst) {
            sum += e.weight();
        }
        return sum;
    }

    private Iterable<Edge> edges() {
        return mst;
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        PrimLazy mst = new PrimLazy(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}
