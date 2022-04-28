/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class DirectedGraph {
    // array: index--vertex, val(Bag) -- edge;
    private int V;
    private int E;
    private Bag<Integer>[] adj;

    public DirectedGraph(int x) {
        V = x;
        E = 0;
        adj = (Bag<Integer>[]) new Bag[x];
        for (int i = 0; i < x; ++i) {
            adj[i] = new Bag<Integer>();
        }
    }

    public DirectedGraph(In in) {
        this(in.readInt());
        int e = in.readInt();
        for (int i = 0; i < e; ++i) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        ++E;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public DirectedGraph reverse() {
        DirectedGraph g = new DirectedGraph(V);
        for (int i = 0; i < V; ++i) {
            for (int x : adj(i)) g.addEdge(x, i);
        }
        return g;
    }


    public static void main(String[] args) {
    }
}
