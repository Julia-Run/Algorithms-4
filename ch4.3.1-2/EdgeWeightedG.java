/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

// java -cp .:.lift/algs4.jar EdgeWeightedG tinyEWG.txt
public class EdgeWeightedG {
    public int V;
    public int E;
    public Bag<EdgeM>[] adj;

    public EdgeWeightedG(int v) {
        V = v;
        E = 0;
        adj = (Bag<EdgeM>[]) new Bag[V];
        for (int x = 0; x < V; ++x) {
            adj[x] = new Bag<EdgeM>();
        }
    }

    public EdgeWeightedG(In in) {
        this(in.readInt());
        int e = in.readInt();
        for (int i = 0; i < e; ++i) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            EdgeM edge = new EdgeM(v, w, weight);
            addEdge(edge);
        }
    }

    public void addEdge(EdgeM e) {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        ++E;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<EdgeM> adj(int v) {
        return adj[v];
    }

    public Iterable<EdgeM> edges() {
        Bag<EdgeM> e = new Bag<EdgeM>();
        for (int v = 0; v < V; ++v) {
            for (EdgeM x : adj(v)) {
                if (v < x.other(v)) e.add(x);
            }
        }
        return e;
    }

    public String toString() {
        String s = "";
        s = V + "  " + E + "\n";
        for (int v = 0; v < V; v++) {
            s += v + ": ";
            for (EdgeM e : adj[v]) {
                s += (e + "  ");
            }
            s += "\n";
        }
        return s;
    }
    //
    // public String toString() {
    //     StringBuilder s = new StringBuilder();
    //     s.append(V + "  " + E + "\n");
    //     for (int v = 0; v < V; v++) {
    //         s.append(v + ": ");
    //         for (EdgeM e : adj[v]) {
    //             s.append(e + "  ");
    //         }
    //         s.append("\n");
    //     }
    //     return s.toString();
    // }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedG G = new EdgeWeightedG(in);
        StdOut.println(G);
    }
}
