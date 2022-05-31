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

//  java -cp .:.lift/algs4.jar PathMin 5
// 5 is topological start point
public class PathMin {
    private DirectedEdge[] edgeTo; // save edges;
    private double[] disTo; // save total distance to any vertex;


    public PathMin(EdgeWeightedDigraph g, int s) {
        // we keep a start vertex;
        edgeTo = new DirectedEdge[g.V()];
        disTo = new double[g.V()];
        for (int i = 0; i < g.V(); ++i) disTo[i] = Double.POSITIVE_INFINITY;
        disTo[s] = 0;

        // what we need to do is update disTo and edgeTo at a topological order;
        Topological top = new Topological(g);
        for (int i : top.order()) {
            relax(g, i);
        }
    }

    public void relax(EdgeWeightedDigraph g, int v) {
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (disTo[w] > disTo[v] + e.weight()) {
                edgeTo[w] = e;
                disTo[w] = disTo[v] + e.weight();
            }
        }
    }

    public double disTo(int v) {
        return disTo[v];
    }

    public boolean hasPathTo(int v) {
        return disTo(v) < Double.POSITIVE_INFINITY;
    }


    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> paths = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            paths.push(e);
        }
        return paths;
    }

    public static void main(String[] args) {
        In in = new In("tinyEWDAG.txt");
        int s = Integer.parseInt(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);

        // find shortest path from s to each other vertex in DAG
        PathMin sp = new PathMin(G, s);
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
