/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class E39_LazyDijkstra {
    private DirectedEdge[] edgeTo;
    private double[] disTo;
    private boolean[] marked;
    MinPQ<DirectedEdge> pq;

    private class ByDistanceFromSource implements Comparator<DirectedEdge> {
        public int compare(DirectedEdge e, DirectedEdge f) {
            // DirectedEdge in MinPQ cannot use compare
            double dist1 = disTo[e.from()] + e.weight();
            double dist2 = disTo[f.from()] + f.weight();
            return Double.compare(dist1, dist2);
        }
    }

    public E39_LazyDijkstra(EdgeWeightedDigraph g, int s) {
        edgeTo = new DirectedEdge[g.V()];
        disTo = new double[g.V()];
        marked = new boolean[g.V()];
        for (int v = 0; v < g.V(); v++)
            disTo[v] = Double.POSITIVE_INFINITY;
        disTo[s] = 0.0;
        pq = new MinPQ<DirectedEdge>(new ByDistanceFromSource());
        visit(g, s);
        while (!pq.isEmpty()) {
            DirectedEdge e = pq.delMin();
            int v = e.from(), w = e.to();
            if (!marked[w]) visit(g, w);   // lazy, so w might already have been relaxed
        }
    }

    public void visit(EdgeWeightedDigraph g, int v) {
        marked[v] = true;
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (marked[w]) continue;
            if (disTo[w] > disTo[v] + e.weight()) {
                edgeTo[w] = e;
                disTo[w] = disTo[v] + e.weight();
                pq.insert(e);
            }
        }
    }

    public double distTo(int v) {
        return disTo[v];
    }

    // is there a path from s to v?
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    // return view of shortest path from s to v, null if no such path
    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }


    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In("tinyEWDAG.txt"));

        // print graph
        StdOut.println("Graph");
        StdOut.println("--------------");
        StdOut.println(G);


        // run Dijksra's algorithm from vertex 0
        int s = 5;
        E39_LazyDijkstra spt = new E39_LazyDijkstra(G, s);
        StdOut.println();

        StdOut.println("Shortest paths from " + s);
        StdOut.println("------------------------");
        for (int v = 0; v < G.V(); v++) {
            if (spt.hasPathTo(v)) {
                StdOut.printf("%d to %d (%.2f)  ", s, v, spt.distTo(v));
                for (DirectedEdge e : spt.pathTo(v)) {
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
