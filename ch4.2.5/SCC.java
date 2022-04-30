/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
//  java -classpath /home/dora/Codes/Algorithms-4/ch4.2.5:/home/dora/Codes/Algorithms-4/ch4.2.5/.lift/algs4.jar SCC tinyDG.txt

public class SCC {

    private int[] id;
    private int count;
    private boolean[] marked;

    public SCC(Digraph g) {
        id = new int[g.V()];
        marked = new boolean[g.V()];
        // DFSorder order = new DFSorder(g.reverse()); // reverse g -- reversePost order;
        DepthFirstOrder order = new DepthFirstOrder(g.reverse());
        for (int v : order.reversePost()) {
            if (!marked(v)) {
                kosaraju(g, v);
                count++;
            }
        }
    }

    public void kosaraju(Digraph g, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : g.adj(v)) {
            if (!marked(w)) kosaraju(g, w);
        }
    }

    public boolean scc(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }

    private boolean marked(int v) {
        return marked[v];
    }

    public static void main(String[] args) {
        Digraph g = new Digraph(new In(args[0]));
        SCC scc = new SCC(g);
        // KosarajuSharirSCC scc = new KosarajuSharirSCC(g);
        int m = scc.count();
        StdOut.println(m + " components.");
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
        for (int i = 0; i < m; i++) {
            components[i] = new Queue<Integer>();
        }
        for (int v = 0; v < g.V(); v++) {
            components[scc.id(v)].enqueue(v);
        }

        // print results
        for (int i = 0; i < m; i++) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }
}
