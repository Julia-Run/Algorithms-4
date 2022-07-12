/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.EdgeWeightedDirectedCycle;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class E12_EWDGcircle {
    private boolean[] onStack;
    private boolean[] marked;
    private Iterable<DirectedEdge> circle;
    private DirectedEdge[] edgeTo;

    public E12_EWDGcircle(EdgeWeightedDigraph g) {
        onStack = new boolean[g.V()];
        marked = new boolean[g.V()];
        edgeTo = new DirectedEdge[g.V()];
        for (int i = 0; i < g.V(); ++i) {
            if (!marked[i]) dfs(g, i);
        }
    }

    public void dfs(EdgeWeightedDigraph g, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (circle != null) return;
            else if (onStack[w]) {
                Stack<DirectedEdge> path = new Stack<DirectedEdge>();
                path.push(e);
                for (DirectedEdge j = e; j != null; j = edgeTo[e.from()]) {
                    path.push(j);
                }
                circle = path;
                return;
            }
            else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(g, w);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return circle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return circle;
    }


    public static void main(String[] args) {
        // int V = Integer.parseInt(args[0]);
        // int E = Integer.parseInt(args[1]);
        // int F = Integer.parseInt(args[2]);
        int V = 8, E = 5, F = 3;
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);
        for (int i = 0; i < E; i++) {
            int v, w;
            do {
                v = StdRandom.uniform(V);
                w = StdRandom.uniform(V);
            } while (v >= w);
            double weight = StdRandom.uniform();
            G.addEdge(new DirectedEdge(v, w, weight));
        }

        // add F extra edges
        for (int i = 0; i < F; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = StdRandom.uniform(0.0, 1.0);
            G.addEdge(new DirectedEdge(v, w, weight));
        }

        StdOut.println(G);

        // find a directed cycle
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(G);
        if (finder.hasCycle()) {
            StdOut.print("Cycle: ");
            for (DirectedEdge e : finder.cycle()) {
                StdOut.print(e + " ");
            }
            StdOut.println();
        }

        // or give topologial sort
        else {
            StdOut.println("No directed cycle");
        }
    }
}
