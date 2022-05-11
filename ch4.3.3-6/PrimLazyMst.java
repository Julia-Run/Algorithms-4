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
//  java -cp .:.lift/algs4.jar PrimLazyMst tinyEWG.txt
public class PrimLazyMst {
    // API: PrimLazyMst(G), edges(), weight()
    // start from a single vertex; log all edges; add the effective minEdge with a new vertex; log all edges;
    private MinPQ<Edge> pq;
    private Queue<Edge> mst;
    private boolean[] marked;

    // constructor
    public PrimLazyMst(EdgeWeightedGraph g) {
        marked = new boolean[g.V()];
        pq = new MinPQ<Edge>();
        mst = new Queue<Edge>();
        visit(g, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;
            mst.enqueue(e);  // put in new edge
            // put new edges connected to new vertex to pq;
            if (!marked[v]) visit(g, v);
            if (!marked[w]) visit(g, w);
        }
    }

    public void visit(EdgeWeightedGraph g, int v) {
        marked[v] = true;  // mark vertex added to tree
        for (Edge e : g.adj(v)) {
            int w = e.other(v);
            if (!marked[w]) pq.insert(e);  // add all edges to pq
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        double s = 0;
        for (Edge e : edges()) s += e.weight();
        return s;
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        PrimLazyMst mst = new PrimLazyMst(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}
