/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.UF;

public class E43_Boruvka {
    // find all min edges in the forest then add to the mst;
    // keep doing this untill mst.size()=g.V()-1
    private Bag<Edge> mst;
    private double weight;

    public E43_Boruvka(EdgeWeightedGraph g) {
        mst = new Bag<Edge>();
        UF uf = new UF(g.V());
        for (int t = 1; t < g.V() && mst.size() < g.V() - 1; t += t) {
            // find all min edge
            Edge[] closest = new Edge[g.V()];
            for (Edge e : g.edges()) {
                int v = e.either(), w = e.other(v);
                int vid = uf.find(v), wid = uf.find(w);
                if (vid == wid) continue; // connected; expired edge;
                if (closest[vid] != null && e.weight() < closest[vid].weight()) closest[vid] = e;
                if (closest[wid] != null && e.weight() < closest[wid].weight()) closest[wid] = e;
            }
            for (Edge e : closest) {
                if (e != null) {
                    int v = e.either(), w = e.other(v);
                    if (uf.find(v) != uf.find(w)) {
                        mst.add(e);
                        weight += e.weight();
                        uf.union(v, w);
                    }
                }
            }
            // for (Edge e : closest) {
            //     if (e != null) {
            //         mst.add(e);
            //         weight += e.weight();
            //         uf.union(e.either(), e.other(e.either()));
            //     }
            // }  // this will lead to the same result
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }


    public static void main(String[] args) {
        In in = new In("tinyEWG.txt");
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        BoruvkaMST mst = new BoruvkaMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}
