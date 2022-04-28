/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;
//  java -classpath /home/dora/Codes/Algorithms-4/ch4.1.6-7_exercise:/home/dora/Codes/Algorithms-4/ch4.1.6-7_exercise/.lift/algs4.jar Bridge
public class Bridge {
    // Tarjan -- find bridge
    private int[] time;
    private int[] low;
    private int count;
    private int bridges;

    public Bridge(Graph g) {
        time = new int[g.V()];
        low = new int[g.V()];
        for (int i = 0; i < g.V(); ++i) {
            time[i] = -1;
            low[i] = -1;
        }
        count = 0;
        for (int i = 0; i < g.V(); ++i) {
            if (time[i] == -1) dfs(g, i, i);
        }
    }

    public void dfs(Graph g, int u, int v) {
        time[v] = count++;
        low[v] = time[v];
        for (int x : g.adj(v)) {
            if (time[x] == -1) {
                dfs(g, v, x);
                low[v] = Math.min(low[v], low[x]); // after dfs done, go backwards to refresh low[v]
                if (low[x] > time[v]) {
                    StdOut.println("Edge " + v + " --> " + x + " is a bridge. ");
                    bridges++;
                }

            }
            else if (x != u) {
                low[v] = Math.min(low[v], low[x]); // et with marked kids --> refresh low[v];
            }
        }
    }

    public int components() {
        return bridges + 1;
    }


    public static void main(String[] args) {
        // int V = Integer.parseInt(args[0]);
        // int E = Integer.parseInt(args[1]);
        // Graph G = GraphGenerator.simple(V, E);
        // StdOut.println(G);
        //
        // Bridge bridge = new Bridge(G);
        // StdOut.println("Edge connected components = " + bridge.components());
        StdOut.println();
        Graph graph1 = new Graph(4);
        graph1.addEdge(0, 1);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 3);
        graph1.addEdge(3, 0);
        Bridge a = new Bridge(graph1);
        StdOut.println("Edge connected components = " + a.components());
        StdOut.println("Expected: Graph is two-edge connected\n");
        StdOut.println();

        Graph graph2 = new Graph(6);
        graph2.addEdge(0, 1);
        graph2.addEdge(2, 1);
        graph2.addEdge(0, 2);
        graph2.addEdge(3, 5);
        graph2.addEdge(4, 5);
        graph2.addEdge(3, 4);
        graph2.addEdge(1, 5);

        Bridge b = new Bridge(graph2);
        StdOut.println("Edge connected components = " + b.components());
        StdOut.println("Expected: 1-5\n");
        StdOut.println();

        Graph graph3 = new Graph(7);
        graph3.addEdge(0, 1);
        graph3.addEdge(2, 1);
        graph3.addEdge(0, 2);
        graph3.addEdge(3, 6);
        graph3.addEdge(4, 6);
        graph3.addEdge(3, 4);
        graph3.addEdge(1, 5);
        graph3.addEdge(5, 6);
        Bridge c = new Bridge(graph3);
        StdOut.println("Edge connected components = " + c.components());
        StdOut.println("Expected: 5-6");
        StdOut.println("Expected: 1-5");
    }
}
