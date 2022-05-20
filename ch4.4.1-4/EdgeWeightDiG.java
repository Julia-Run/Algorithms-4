/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class EdgeWeightDiG {
    private int V;
    private int E;
    private Bag<DiEdges>[] adj;

    public EdgeWeightDiG(int v) {
        V = v;
        E = 0;
        adj = (Bag<DiEdges>[]) new Bag[V];
        for (int i = 0; i < V; ++i) {
            adj[i] = new Bag<DiEdges>();
        }
    }

    public EdgeWeightDiG(In in) {
        this(in.readInt());
        int allE = in.readInt();
        for (int i = 0; i < allE; ++i) {
            // read and create new edge; then add to G;
            DiEdges e = new DiEdges(in.readInt(), in.readInt(), in.readDouble());
            addEdge(e);
        }
    }

    public void addEdge(DiEdges e) {
        adj[e.from()].add(e);
        ++E;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<DiEdges> adj(int v) {
        return adj[v];
    }

    public Iterable<DiEdges> edges() {
        Bag<DiEdges> q = new Bag<DiEdges>();
        for (int v = 0; v < V; ++v) {
            for (DiEdges e : adj(v)) q.add(e);
        }
        return q;
    }

    public String toString() {
        // vertexes, edges; print from point p;
        String s = "\nConclusion: " + V + " Vertexes, " + E + " Edges.\n";
        for (int i = 0; i < V; ++i) {
            s += i + ":  ";
            for (DiEdges e : adj(i)) {
                s += e + " ";
            }
            s += "\n";
        }
        return s;
    }

    public static void main(String[] args) {
        EdgeWeightDiG g = new EdgeWeightDiG(new In("tinyEWD.txt"));
        StdOut.print(g);
    }
}
