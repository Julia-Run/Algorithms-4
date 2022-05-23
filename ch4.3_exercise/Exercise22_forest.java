/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

//  java -cp .:.lift/algs4.jar Exercise22_forest tinyEWG.txt
public class Exercise22_forest {
    // minimum tree: weighted, undirected;
    // variables: IndexMinPQ--MinE(weight); mst--edges; edgeTo---minEdge; disTo--minE-Weight
    private IndexMinPQ<Double> pq;
    private Edge[] edgeTo;
    private double[] disTo;
    private boolean[] marked;

    public Exercise22_forest(EdgeWeightedGraph g) {
        marked = new boolean[g.V()];
        edgeTo = new Edge[g.V()];
        disTo = new double[g.V()];
        pq = new IndexMinPQ<Double>(g.V());
        for (int i = 0; i < g.V(); ++i) disTo[i] = Double.POSITIVE_INFINITY;
        for (int i = 0; i < g.V(); ++i) if (!marked[i]) prim(g, i);
    }

    public void prim(EdgeWeightedGraph g, int s) {
        disTo[s] = 0;
        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            visit(g, pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph g, int v) {
        marked[v] = true;
        for (Edge e : g.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;
            if (disTo[w] > e.weight()) {
                edgeTo[w] = e;
                disTo[w] = e.weight();
                if (pq.contains(w)) pq.changeKey(w, disTo[w]);
                else pq.insert(w, disTo[w]);
            }
        }
    }

    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<Edge>();
        for (int v = 1; v < edgeTo.length; v++) {  // seems the edgeTo[0]=null
            Edge e = edgeTo[v];
            mst.enqueue(e);
        }
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
        Exercise22_forest mst = new Exercise22_forest(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
        // StdOut.println(mst.edgeTo.length);
        // for (Edge e: mst.edgeTo) StdOut.println(e);  // seems the edgeTo[0]=null
    }
}