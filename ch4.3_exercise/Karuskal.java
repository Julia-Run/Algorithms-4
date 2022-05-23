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
import edu.princeton.cs.algs4.UF;

public class Karuskal {
    // api: prim(), edges(), weight();
    // each vertexes as a tree; -- rank edges by weight;
    // -- using union-find to connect two tree with smaller edges until connected edges=v-1
    private Queue<Edge> mst;

    public Karuskal(EdgeWeightedGraph g) {
        mst = new Queue<Edge>(); // ---edges
        MinPQ<Edge> pq = new MinPQ<Edge>();  // ranked edges
        for (Edge e : g.edges()) {
            pq.insert(e);
        }
        UF uf = new UF(g.V());
        while (!pq.isEmpty() && mst.size() < g.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) continue;
            uf.union(v, w);
            mst.enqueue(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        double s = 0.0;
        for (Edge e : edges())
            s += e.weight();
        return s;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        Karuskal mst = new Karuskal(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}
