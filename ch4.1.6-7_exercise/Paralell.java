/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;

public class Paralell {
    public static int paraE(Graph g) {
        int para = 0;
        for (int i = 0; i < g.V(); ++i) {
            boolean[] nei = new boolean[g.V()];  // only for one vertex
            for (int x : g.adj(i)) {
                if (!nei[x]) nei[x] = true;
                else para++;
            }
        }
        return para / 2;
    }


    public static void main(String[] args) {
        Paralell p = new Paralell();

        Graph graph1 = new Graph(4);
        graph1.addEdge(0, 1);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 3);
        graph1.addEdge(3, 0);
        StdOut.println(p.paraE(graph1) + " Expected: 0");

        Graph graph2 = new Graph(4);
        graph2.addEdge(0, 1);
        graph2.addEdge(1, 2);
        graph2.addEdge(2, 3);
        graph2.addEdge(3, 0);
        graph2.addEdge(3, 2);
        StdOut.println(p.paraE(graph2) + " Expected: 1");

        Graph graph3 = new Graph(4);
        graph3.addEdge(0, 1);
        graph3.addEdge(1, 0);
        graph3.addEdge(2, 3);
        graph3.addEdge(3, 2);
        graph3.addEdge(3, 2);
        graph3.addEdge(3, 0);
        StdOut.println(p.paraE(graph3) + " Expected: 3");
    }
}
