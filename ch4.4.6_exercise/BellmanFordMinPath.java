/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.EdgeWeightedDirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class BellmanFordMinPath {
    // function1: check if there is a minus circle;
    // function2: if not, find min spt
    // minus weight --> for a vertex, can not use min edge, use a queen to
    // save all revised paths and deal with each one until queen is empty.
    private DirectedEdge[] edgeTo;
    private double[] disTo;
    private Queue<Integer> q;
    private boolean[] onQ;
    private int count; // for minus cycle;
    private Iterable<DirectedEdge> circle;

    public BellmanFordMinPath(EdgeWeightedDigraph g, int s) {
        edgeTo = new DirectedEdge[g.V()];
        disTo = new double[g.V()];
        onQ = new boolean[g.V()];
        q = new Queue<Integer>();
        for (int i = 0; i < g.V(); ++i) {
            disTo[i] = Double.POSITIVE_INFINITY;
            onQ[i] = false;
        }
        disTo[s] = 0.0;
        q.enqueue(s);
        onQ[s] = true;
        while (!q.isEmpty() && !hasNegativeCircle()) {
            int v = q.dequeue();
            onQ[v] = false;
            relax(g, v);
        }
        // if (hasNegativeCircle()) throw new IllegalArgumentException("Have Negative Circle!");
    }

    private void relax(EdgeWeightedDigraph g, int v) {
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (disTo[w] > disTo[v] + e.weight()) {
                edgeTo[w] = e;
                disTo[w] = disTo[v] + e.weight();
                if (!onQ[w]) {
                    q.enqueue(w);
                    onQ[w] = true;
                }
            }
            if (count++ % g.V() == 0) findNegativeCircle();
        }
    }

    private void findNegativeCircle() {
        // collect all edges; call ...
        EdgeWeightedDigraph subtree = new EdgeWeightedDigraph(edgeTo.length);
        for (int i = 0; i < edgeTo.length; ++i) {
            if (edgeTo[i] != null) subtree.addEdge(edgeTo[i]);
        }
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(subtree);
        circle = finder.cycle();
    }

    public boolean hasNegativeCircle() {
        return circle != null;
    }

    public Iterable<DirectedEdge> getCircle() {
        return circle;
    }

    public boolean hasPathTo(int v) {
        return disTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (hasNegativeCircle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    public double disTo(int v) {
        return disTo[v];
    }

    public static void main(String[] args) {
        In in = new In("tinyEWDn.txt");
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(in);
        BellmanFordMinPath sp = new BellmanFordMinPath(g, 0);
        if (sp.hasNegativeCircle()) {
            for (DirectedEdge e : sp.getCircle()) StdOut.println(e);
        }
        else {
            for (int v = 0; v < g.V(); v++) {
                if (sp.hasPathTo(v)) {
                    StdOut.printf("%d to %d (%5.2f)  ", 0, v, sp.disTo(v));
                    for (DirectedEdge e : sp.pathTo(v)) {
                        StdOut.print(e + "   ");
                    }
                    StdOut.println();
                }
                else {
                    StdOut.printf("%d to %d           no path\n", 0, v);
                }
            }
        }
    }
}
